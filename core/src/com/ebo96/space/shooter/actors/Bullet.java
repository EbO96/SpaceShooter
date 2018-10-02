package com.ebo96.space.shooter.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Sprite {

    private Vector2 location;

    private Vector2 velocity;

    private Ship ship;

    public Bullet(Ship ship) {
        super(new Texture("bullet.png"));

        this.ship = ship;

        newPosition();

        velocity = new Vector2(0, 21);
    }

    public void newPosition() {
        location = new Vector2(ship.getX() + (ship.getWidth() / 2), ship.getY() + (ship.getHeight() / 2));
        setPosition(location.x, location.y);
    }

    public void update(){
        location.y += velocity.y;
        setPosition(location.x, location.y);

    }

}
