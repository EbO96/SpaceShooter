package com.ebo96.space.shooter.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ebo96.space.shooter.game.GameInfo;

import java.util.Random;

public class Meteor extends Sprite {

    //Meteor location on screen
    private Vector2 location;

    private Vector2 middle;

    private Vector2 velocity;

    public Meteor() {
        super(new Texture("box.png"));
        newPosition();

        //Calculate middle of ship
        middle = new Vector2(getWidth() / 2f, getHeight() / 2f);

        velocity = new Vector2(0, -randomY());
    }

    public void newPosition() {
        location = new Vector2(randomize(), GameInfo.HEIGHT);
        setPosition(location.x, location.y);
    }

    @Override
    public void draw(Batch batch) {
        location.y += velocity.y;
        setPosition(location.x, location.y);
        super.draw(batch);
    }

    private float randomize() {
        Random random = new Random();
        return random.nextInt((int) GameInfo.WIDTH);
    }

    private int randomY() {
        Random random = new Random();
        return 5 + random.nextInt(8);
    }
}
