package com.ebo96.space.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ebo96.space.shooter.SpaceShooter;
import com.ebo96.space.shooter.game.GameInfo;
import com.ebo96.space.shooter.game.MeteorManager;
import com.ebo96.space.shooter.object.Ship;

public class GameScreen implements Screen {

    private SpaceShooter game;
    private SpriteBatch batch;

    //This game world
    private World world;

    //Game objects
    private Ship ship; //This is player

    private Viewport viewport;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    //Meteor manager
    private MeteorManager meteorManager;

    public GameScreen(SpaceShooter game) {
        this.game = game;
        this.batch = new SpriteBatch();

        Box2D.init();

        //Setup view port
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM, camera);

        world = new World(new Vector2(0, -9.8f), true);

        //Setup box debug
        debugRenderer = new Box2DDebugRenderer();

        //Contact listener
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        //Create player
        ship = new Ship(batch, world);

        //Setup meteor manager
        meteorManager = new MeteorManager(batch, world);

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        debugRenderer.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        batch.begin();
        //Draw
        ship.draw(batch);
        meteorManager.draw();
        debugRenderer.render(world, camera.combined);
        batch.end();

    }
}
