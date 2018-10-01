package com.ebo96.space.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.ebo96.space.shooter.SpaceShooter;
import com.ebo96.space.shooter.object.Ship;

public class GameScreen implements Screen {

    private SpaceShooter game;
    private SpriteBatch batch;

    //This game world
    private World world;

    //Game objects
    private Ship ship; //This is player

    public GameScreen(SpaceShooter game) {
        this.game = game;
        this.batch = new SpriteBatch();

        world = new World(new Vector2(0, -9.8f), true);
        //Create player
        ship = new Ship(world);
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
        ship.draw(batch);
        batch.end();

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }
}
