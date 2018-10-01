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

    private Vector2 middle;

    public Meteor(World world) {
        super(new Texture("meteor.png"));
        this.world = world;
        location = new Vector2(randomizeX() / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        setPosition(location.x, location.y);

        //Calculate middle of ship
        middle = new Vector2(getWidth() / 2f / GameInfo.PPM, getHeight() / 2f / GameInfo.PPM);

        createBody();
    }

    @Override
    public void draw(Batch batch) {
        float meteorX = (body.getPosition().x * GameInfo.PPM) - (middle.x * GameInfo.PPM);
        float meteorY = (body.getPosition().y * GameInfo.PPM) - (middle.y * GameInfo.PPM);
        setPosition(meteorX, meteorY);
        super.draw(batch);

    }

    private float randomizeX() {
        Random random = new Random();
        return random.nextFloat() * (GameInfo.WIDTH);
    }

    private void createBody() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(location);

        body = world.createBody(bodyDef);
        body.setGravityScale(2f);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2 / GameInfo.PPM, getHeight() / 2 / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0.5f;

        Fixture fixture = body.createFixture(fixtureDef);

        body.setUserData(this);

        shape.dispose();
    }

    public Body getBody() {
        return body;
    }
}
