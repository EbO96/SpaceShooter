package com.ebo96.space.shooter.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ebo96.space.shooter.object.Bullet;
import com.ebo96.space.shooter.object.Meteor;

import java.util.ArrayList;

public class MeteorManager {

    //List of meteors to render
    private ArrayList<Meteor> meteors = new ArrayList<Meteor>();

    //Main batch
    private SpriteBatch batch;

    public MeteorManager(SpriteBatch batch) {
        this.batch = batch;
    }

    public void create(Bullet bullet){
        meteors.add(new Meteor(bullet));
    }

    public void draw() {

        int index = 0;

        while (index < meteors.size()) {

            Meteor meteor = meteors.get(index);

            if (meteor.getY() > 0)
                meteor.draw(batch);
            else {
                meteors.remove(index);
                index--;
            }

            index++;
        }

    }

}
