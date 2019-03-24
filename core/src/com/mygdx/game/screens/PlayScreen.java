package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.SnakeGame;
import com.mygdx.game.sprites.SnakeBody;

public class PlayScreen implements Screen {

    private SnakeGame game;
    private OrthographicCamera gameCamera;
    private Viewport gameViewport;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private SnakeBody snakeBody;

    public static final int WORLD_WIDTH = 400;
    public static final int WORLD_HEIGHT = 400;


    public PlayScreen(SnakeGame game) {
        this.game = game;
        gameCamera = new OrthographicCamera();
        gameViewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, gameCamera);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCamera.position.set(gameViewport.getWorldWidth() / 2, gameViewport.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,0), true);
        debugRenderer = new Box2DDebugRenderer();

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        snakeBody = new SnakeBody(world);

    }

    @Override
    public void show() {

    }

    public void update(float deltaTime) {
        handleInput(deltaTime);

        world.step(1/60f, 6, 2);

        gameCamera.update();

        snakeBody.update(deltaTime);

        renderer.setView(gameCamera);
    }

    private void handleInput(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && snakeBody.body.getLinearVelocity().y == 0) {
            //snakeBody.body.applyLinearImpulse(new Vector2(0, SnakeGame.PIXELS_PER_METER), snakeBody.body.getWorldCenter(), true);
            snakeBody.body.setLinearVelocity(new Vector2(0, SnakeGame.PIXELS_PER_METER));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && snakeBody.body.getLinearVelocity().y == 0) {
            //snakeBody.body.applyLinearImpulse(new Vector2(0, -SnakeGame.PIXELS_PER_METER), snakeBody.body.getWorldCenter(), true);
            snakeBody.body.setLinearVelocity(new Vector2(0, -SnakeGame.PIXELS_PER_METER));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && snakeBody.body.getLinearVelocity().x == 0) {
            //snakeBody.body.applyLinearImpulse(new Vector2(-SnakeGame.PIXELS_PER_METER, 0), snakeBody.body.getWorldCenter(), true);
            snakeBody.body.setLinearVelocity(new Vector2(-SnakeGame.PIXELS_PER_METER, 0));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snakeBody.body.getLinearVelocity().x == 0) {
            //snakeBody.body.applyLinearImpulse(new Vector2(SnakeGame.PIXELS_PER_METER, 0), snakeBody.body.getWorldCenter(), true);
            snakeBody.body.setLinearVelocity(new Vector2(SnakeGame.PIXELS_PER_METER, 0));
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        debugRenderer.render(world, gameCamera.combined);

        game.batch.setProjectionMatrix(gameCamera.combined);
        game.batch.begin();
        snakeBody.draw(game.batch);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        debugRenderer.dispose();
    }
}
