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
import com.ebo96.space.shooter.game.GameInfo;

import java.util.Random;

public class Meteor extends Sprite {

    //Game world
    private World world;

    //Meteor body
    private Body body;

    //Meteor location on screen
    private Vector2 location;

    //Meteor velocity. Same as bullet velocity
    private float velocity;

    public boolean shouldDraw = true;

    public Meteor(Bullet bullet) {
        super(new Texture("meteor.png"));

        this.world = bullet.getWorld();

        //Set position
        location = new Vector2(randomizeX(bullet.getX()), GameInfo.HEIGHT);

        //Set meteor position
        setPosition(location.x, location.y);

        velocity = -bullet.getVelocity() * 0.7f;

        createBody();
    }

    @Override
    public void draw(Batch batch) {
        if (shouldDraw) {
            setPosition(location.x, location.y += velocity);
            body.getPosition().set(location.x, location.y += velocity);
            super.draw(batch);
        }
    }

    private float randomizeX(float x) {
        float min = 0.5f;
        float max = 2.5f;

        Random random = new Random();

        return x * random.nextFloat() * (max - min) + min;
    }

    private void createBody() {

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
