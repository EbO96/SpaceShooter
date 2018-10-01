package com.ebo96.space.shooter.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ebo96.space.shooter.game.GameInfo;

import java.util.ArrayList;

public class Ship extends Sprite {

    //Game world
    private World world;

    //Ship body
    private Body body;

    //Ship location
    private Vector2 location;
    private Vector2 middle; //Ship middle

    //Last fire
    private long lastFire;
    private final long fireSpace = 100L;

    //Fired bullets
    private ArrayList<Bullet> firedBullets = new ArrayList<Bullet>();

    public Ship(World world) {

        //Load ship texture
        super(new Texture("ship.png"));
        this.world = world;
        //Set start ship position on screen
        location = new Vector2((GameInfo.WIDTH / 2) - (getWidth() / 2), GameInfo.HEIGHT / 4);
        //Calculate middle of ship
        middle = new Vector2(getWidth() / 2f, getHeight() / 2f);
        //Set ship position on screen
        setPosition(location.x, location.y);
        //Create ship body
        createBody();
        //Set last fire time
        lastFire = System.currentTimeMillis();
    }

    /**
     * Update ship position on screen and draw
     */
    @Override
    public void draw(Batch batch) {
        if (Gdx.input.isTouched()) {

            //Touch coordinates
            float x = Gdx.input.getX() - middle.x;
            float y = GameInfo.HEIGHT - Gdx.input.getY() - middle.y;

            //Check if touch is on ship (40% margin is allowed)
            if (Math.abs(x - location.x) <= getWidth() * 1.4 && Math.abs(y - location.y) <= getHeight() * 1.4) {
                location.x = x;
                location.y = y;
            }

            if (System.currentTimeMillis() - lastFire > fireSpace) {
                lastFire = System.currentTimeMillis();
                firedBullets.add(new Bullet(world, Ship.this));
            }

        }

        //Draw ship on screen
        setPosition(location.x, location.y);

        int index = 0;

        //Draw bullets on screen
        while (index < firedBullets.size()) {
            Bullet bullet = firedBullets.get(index);

            if (bullet.getY() <= GameInfo.HEIGHT)
                bullet.draw(batch);
            else {
                firedBullets.remove(index);
                index--;
            }

            index++;
        }

        //Draw ship on screen
        super.draw(batch);
    }


    //Create body and attach ship
    private void createBody() {

        //Ship body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(location);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(middle.x, middle.y);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.2f;

        Fixture fixture = body.createFixture(fixtureDef);

        body.setUserData(this);

        shape.dispose();
    }

    public Vector2 getMiddle() {
        return middle;
    }
}
