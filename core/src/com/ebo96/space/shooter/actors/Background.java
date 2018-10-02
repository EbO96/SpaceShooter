package com.ebo96.space.shooter.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ebo96.space.shooter.game.GameInfo;

public class Background extends Sprite {

    public Background() {
        super(new Texture("bcg.png"));
        setPosition(0, 0);
        setSize(GameInfo.WIDTH, GameInfo.HEIGHT);
    }
}
