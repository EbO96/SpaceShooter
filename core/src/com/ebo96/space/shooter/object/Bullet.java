package com.ebo96.space.shooter.object;

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

public class Bullet extends Sprite {

    //Game world
    private World world;

    //Bullet body
    private Body body;

    //Bullet location
    private Vector2 location;

    //Bullet velocity
    private Vector2 velocity;

    public Bullet(World world, Ship ship) {
        super(new Texture("bullet.png"));
        this.world = world;

        //Set bullet location
        location = new Vector2(ship.getX() + ship.getMiddle().x, ship.getY() + (ship.getHeight() * 0.8f));
        setPosition(location.x, location.y);

        //Set bullet velocity
        velocity = new Vector2(0, 12);
        //Create body for bullet
        createBody();
    }

    @Override
    public void draw(Batch batch) {
        ((Bullet) body.getUserData()).setPosition(location.x, location.y += velocity.y);
        super.draw(batch);
    }

    private void createBody() {
        //Ship body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(location);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.2f;

        Fixture fixture = body.createFixture(fixtureDef);

        body.setUserData(this);

        shape.dispose();
    }
}
