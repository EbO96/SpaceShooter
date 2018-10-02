package com.ebo96.space.shooter.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ebo96.space.shooter.game.GameInfo;

public class Ship extends Sprite {


    //Ship body
    private Body body;

    //Ship location
    private Vector2 location;
    private Vector2 middle; //Ship middle

    //Ship velocity
    private Vector2 velocity;

    public Ship(SpriteBatch batch) {

        //Load ship texture
        super(new Texture("ship.png"));
        //Set start ship position on screen
        location = new Vector2((GameInfo.WIDTH / 2) - (getWidth() / 2), getHeight() / 2);
        //Calculate middle of ship
        middle = new Vector2(getWidth() / 2f, getHeight() / 2f);

        //Set ship velocity
        velocity = new Vector2(5f, 5f);

        //Set ship position on screen
        setPosition(location.x, location.y);
    }

    /**
     * Update ship position on screen and createAndDraw
     */
    @Override
    public void draw(Batch batch) {

        //Touch coordinates
        float x = -Gdx.input.getAccelerometerX() * velocity.x;
        float y = -Gdx.input.getAccelerometerY() * velocity.y;

        location.x += x;
        location.y += y;

        if (location.x <= 0) location.x = 0;
        if (location.y <= 0) location.y = 0;

        float boundRight = GameInfo.WIDTH - getWidth();
        if (location.x >= boundRight) location.x = boundRight;

        float boundTop = GameInfo.HEIGHT - getHeight();
        if (location.y >= boundTop) location.y = boundTop;

        setPosition(location.x, location.y);

        //Draw ship on screen
        super.draw(batch);
    }

    public Vector2 getMiddle() {
        return middle;
    }
}
