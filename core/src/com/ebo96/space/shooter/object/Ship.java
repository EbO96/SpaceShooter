package com.ebo96.space.shooter.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ebo96.space.shooter.game.GameInfo;

public class Ship extends Sprite {

    //Game world
    private World world;

    //Ship body
    private Body body;

    //Ship location
    private Vector2 location;
    private Vector2 middle; //Ship middle

    public Ship(SpriteBatch batch, World world) {

        //Load ship texture
        super(new Texture("ship.png"));
        this.world = world;
        //Set start ship position on screen
        location = new Vector2((GameInfo.WIDTH / 2) / GameInfo.PPM - (getWidth() / 2) / GameInfo.PPM, getHeight() / 2 / GameInfo.PPM);
        //Calculate middle of ship
        middle = new Vector2(getWidth() / 2f / GameInfo.PPM, getHeight() / 2f / GameInfo.PPM);
        //Set ship position on screen
        setPosition(location.x, location.y);
        //Create ship body
        createBody();
    }

    /**
     * Update ship position on screen and createAndDraw
     */
    @Override
    public void draw(Batch batch) {
        if (Gdx.input.isTouched()) {

            //Touch coordinates
            float x = Gdx.input.getX() - middle.x;
            float y = GameInfo.HEIGHT - Gdx.input.getY() - middle.y;

            location.x = x / GameInfo.PPM;
            location.y = y / GameInfo.PPM;

            if (x < GameInfo.WIDTH / 2 && body.getPosition().x > middle.x) { // First half Screen ==> move backward
                body.setLinearVelocity(-10, 0);
            } else if (x > GameInfo.WIDTH / 2 && (body.getPosition().x * GameInfo.PPM) < GameInfo.WIDTH) { // Second half Screen ==> move forward
                body.setLinearVelocity(10, 0);
            } else {
                body.setLinearVelocity(new Vector2(0, 0));
            }


        } else {
            body.setLinearVelocity(new Vector2(0, 0));
        }

        float shipX = (body.getPosition().x * GameInfo.PPM) - (middle.x * GameInfo.PPM);
        float shipY = (body.getPosition().y * GameInfo.PPM) - (middle.y * GameInfo.PPM);

        Gdx.app.log("ship", "ship X = " + shipX + " ship Y = " + shipY);
        setPosition(shipX, shipY);

        //Draw ship on screen
        super.draw(batch);
    }


    //Create body and attach ship
    private void createBody() {

        //Ship body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(location);
        bodyDef.gravityScale = 0;

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(middle.x, middle.y);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;

        Fixture fixture = body.createFixture(fixtureDef);

        body.setUserData(this);

        shape.dispose();
    }

    public Vector2 getMiddle() {
        return middle;
    }
}
