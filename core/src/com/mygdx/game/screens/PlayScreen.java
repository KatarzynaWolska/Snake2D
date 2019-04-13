package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Direction;
import com.mygdx.game.Snake;
import com.mygdx.game.SnakeGame;
import com.mygdx.game.controllers.SnakeController;
import com.mygdx.game.sprites.Food;
import com.mygdx.game.sprites.Spider;

public class PlayScreen implements Screen {

    private SnakeGame game;
    private OrthographicCamera gameCamera;
    private Viewport gameViewport;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private Snake snake;
    private Food food;
    private Spider spider;

    private boolean gameOver = false;

    public static final int WORLD_WIDTH = 400;
    public static final int WORLD_HEIGHT = 400;
    private static final int FPS_SLEEP = 10;


    public PlayScreen(SnakeGame game) {
        this.game = game;
        gameCamera = new OrthographicCamera();
        gameViewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, gameCamera);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCamera.position.set(gameViewport.getWorldWidth() / 2, gameViewport.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();

        snake = new Snake(map);
        food = new Food(Food.TEXTURE_PATH, map);
        spider = new Spider(Spider.TEXTURE_PATH, map);
        food.generateFoodPosition(food, snake, food.getRandomGenerator());
        spider.generateFoodPosition(spider, snake, spider.getRandomGenerator());

        while(food.checkWallCollision() || spider.checkWallCollision()) {
            food.generateFoodPosition(food, snake, food.getRandomGenerator());
            spider.generateFoodPosition(spider, snake, spider.getRandomGenerator());
        }

        Gdx.input.setInputProcessor(new GestureDetector(new SnakeController(snake)));
    }

    @Override
    public void show() {

    }

    private void update() {
        if(!gameOver) {
            handleInput();

            world.step(1 / 60f, 6, 2);

            gameCamera.update();

            spider.updatePosition();
            spider.hitObstacle(snake, food);

            checkAllColissions();

            snake.updatePosition();

            checkAllColissions();

            renderer.setView(gameCamera);
        }
    }

    private void checkAllColissions() {
        if(food.checkSnakeCollision(food, snake)) {
            snake.eat();
            food.generateFoodPosition(food, snake, food.getRandomGenerator());

            while(food.checkWallCollision()) {
                food.generateFoodPosition(food, snake, food.getRandomGenerator());
            }
        }


        if(spider.checkSnakeCollision(spider, snake)){

            snake.eat();
            spider.generateFoodPosition(spider, snake, spider.getRandomGenerator());
            spider.generateRandomDirection();

            while(spider.checkWallCollision()) {
                spider.generateFoodPosition(spider, snake, spider.getRandomGenerator());
            }
        }


        if (snake.checkSnakeCollision() || snake.getSnakeHead().checkWallCollision()) {
            gameOver = true;
        }
    }

    private void handleInput() {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && snake.getSnakeHead().getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && snake.getSnakeHead().getDirection() != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && snake.getSnakeHead().getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snake.getSnakeHead().getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            }
    }

    private void sleep(int fps) {
        long diff, start = System.currentTimeMillis();
        if(fps>0){
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000/fps;
            if (diff < targetDelay) {
                try{
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        sleep(FPS_SLEEP);
        update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        debugRenderer.render(world, gameCamera.combined);

        game.batch.setProjectionMatrix(gameCamera.combined);
        game.batch.begin();
        snake.draw(game.batch);
        food.draw(game.batch);
        spider.draw(game.batch);
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
