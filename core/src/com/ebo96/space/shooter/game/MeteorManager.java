package com.ebo96.space.shooter.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.ebo96.space.shooter.object.Meteor;

import java.util.ArrayList;

public class MeteorManager {

    //List of meteors to render
    private ArrayList<Meteor> meteors = new ArrayList<Meteor>();

    //Main batch
    private SpriteBatch batch;

    private World world;

    //Last fire
    private long lastFire;
    private final long fireSpace = 150L;

    public MeteorManager(SpriteBatch batch, World world) {
        this.world = world;
        this.batch = batch;
        //Set last fire time
        lastFire = System.currentTimeMillis();
    }

    public void draw() {

        if (System.currentTimeMillis() - lastFire > fireSpace) {
            lastFire = System.currentTimeMillis();
            meteors.add(new Meteor(world));
        }

        int index = 0;

        while (index < meteors.size()) {

            Meteor meteor = meteors.get(index);

            if (meteor.getY() > 0)
                meteor.draw(batch);
            else {
                meteors.remove(index);
                world.destroyBody(meteor.getBody());
                index--;
            }

            index++;
        }

    }

}
