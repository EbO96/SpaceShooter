package com.ebo96.space.shooter.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ebo96.space.shooter.actors.Meteor;

import java.util.ArrayList;

public class MeteorManager {

    //List of meteors to render
    private ArrayList<Meteor> meteors = new ArrayList<Meteor>();

    //Main batch
    private SpriteBatch batch;

    //Last fire
    private long lastFire;
    private final long fireSpace = 250L;

    public MeteorManager(SpriteBatch batch) {
        this.batch = batch;
        //Set last fire time
        lastFire = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            meteors.add(new Meteor());
        }
    }

    public void draw() {

        int index = 0;

        while (index < meteors.size()) {

            Meteor meteor = meteors.get(index);

            if (meteor.getY() > 0)
                meteor.draw(batch);
            else {
                meteor.newPosition();
            }

            index++;
        }
    }

    public ArrayList<Meteor> getMeteors() {
        return meteors;
    }
}
