package com.ebo96.space.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ebo96.space.shooter.SpaceShooter;
import com.ebo96.space.shooter.actors.Background;
import com.ebo96.space.shooter.actors.Ship;
import com.ebo96.space.shooter.game.BulletManager;
import com.ebo96.space.shooter.game.MeteorManager;

public class GameScreen implements Screen {

    private SpaceShooter game;
    private SpriteBatch batch;

    //Game objects
    private Ship ship; //This is player

    //Background
    private Background background;

    //Meteor manager
    private MeteorManager meteorManager;

    //Bullet manager
    private BulletManager bulletManager;

    public GameScreen(SpaceShooter game) {
        this.game = game;
        this.batch = new SpriteBatch();

        //Create player
        ship = new Ship(batch);

        background = new Background();

        //Setup meteor manager
        meteorManager = new MeteorManager(batch);

        //Setup bullet manager
        bulletManager = new BulletManager(batch, ship);
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        ship.getTexture().dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //Draw
        background.draw(batch);
        bulletManager.draw();
        meteorManager.draw();
        bulletManager.collision(meteorManager.getMeteors());
        ship.draw(batch);
        batch.end();

    }
}
