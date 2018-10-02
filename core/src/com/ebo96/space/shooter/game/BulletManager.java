package com.ebo96.space.shooter.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ebo96.space.shooter.actors.Bullet;
import com.ebo96.space.shooter.actors.Meteor;
import com.ebo96.space.shooter.actors.Ship;

import java.util.ArrayList;

public class BulletManager {

    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    private Batch batch;

    private Ship ship;

    public BulletManager(SpriteBatch batch, Ship ship) {
        this.batch = batch;
        this.ship = ship;
    }

    private long lastShot = System.currentTimeMillis();
    private long denisity = 100L;

    public void draw() {

        if (System.currentTimeMillis() - lastShot > denisity) {
            lastShot = System.currentTimeMillis();
            bullets.add(new Bullet(ship));
        }

        int index = 0;

        while (index < bullets.size()) {
            Bullet bullet = bullets.get(index);

            if (bullet.getY() < GameInfo.HEIGHT) {
                bullet.update();
                bullet.draw(batch);
            } else {
                bullet.getTexture().dispose();
                bullets.remove(index);
                index--;
            }
            index++;
        }

    }

    public void collision(ArrayList<Meteor> meteors) {
        for (Meteor meteor : meteors) {
            for (Bullet bullet : bullets) {
                if (meteor.getBoundingRectangle().overlaps(bullet.getBoundingRectangle()))
                    meteor.newPosition();
            }
        }
    }
}
