package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

import java.util.concurrent.TimeUnit;

public class PlayScreen implements Screen {

    private SnakeGame game;
    private OrthographicCamera gameCamera;
    private Viewport gameViewport;
    private String level;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Hud hud;

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private TextureAtlas atlas;

    private Snake snake;
    private Food food;
    private Spider spider;

    private boolean gameOver = false;
    private boolean renderSpider = false;
    private int lastSpiderScore = 0;

    public static final int WORLD_WIDTH = 400;
    public static final int WORLD_HEIGHT = 400;
    private static final int FPS_SLEEP = 20;

    private static final int FOOD_SCORE = 10;
    private static final int SPIDER_SCORE = 20;


    public PlayScreen(SnakeGame game, String level) {
        this.level = level;
        this.game = game;
        gameCamera = new OrthographicCamera();
        gameViewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, gameCamera);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(level);
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCamera.position.set(gameViewport.getWorldWidth() / 2, gameViewport.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
        atlas = new TextureAtlas("Snake2D.pack");

        hud = new Hud(game.batch);

        snake = new Snake(map, atlas);
        food = new Food(Food.TEXTURE_PATH, map, atlas);
        spider = new Spider(Spider.TEXTURE_PATH, map, atlas);

        food.generateFoodPosition(food, snake, food.getRandomGenerator());

        while(food.checkWallCollision()) {
            food.generateFoodPosition(food, snake, food.getRandomGenerator());
        }

        Gdx.input.setInputProcessor(new GestureDetector(new SnakeController(snake)));
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    private void update() {
        if(!gameOver) {
            handleInput();

            world.step(1 / 60f, 6, 2);

            gameCamera.update();

            if(hud.getScore() - lastSpiderScore >= 50 && !renderSpider) {
                renderSpider = true;

                spider.generateFoodPosition(spider, snake, spider.getRandomGenerator());
                spider.generateRandomDirection();

                while(spider.checkWallCollision() || spider.checkCellsCollision(food)) {
                    spider.generateFoodPosition(spider, snake, spider.getRandomGenerator());
                }
            }


            if(renderSpider) {
                spider.updatePosition();
                spider.hitObstacle(snake, food);
            }


            checkAllColissions();

            snake.updatePosition();


            checkAllColissions();

            renderer.setView(gameCamera);
        }
    }

    private void checkAllColissions() {
        if(food.checkSnakeCollision(food, snake)) {
            snake.eat();
            hud.addScore(FOOD_SCORE);
            food.generateFoodPosition(food, snake, food.getRandomGenerator());

            while(food.checkWallCollision() || food.checkCellsCollision(spider)) {
                food.generateFoodPosition(food, snake, food.getRandomGenerator());
            }
        }


        if(renderSpider && spider.checkSnakeCollision(spider, snake)){
            snake.eat();
            lastSpiderScore = hud.getScore();
            hud.addScore(SPIDER_SCORE);
            renderSpider = false;
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
        update();
        if(fps>0){
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000/fps;
            if (diff < targetDelay) {
                try{
                    //Thread.sleep(targetDelay - diff);
                    TimeUnit.MILLISECONDS.sleep(targetDelay - diff);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sleep(FPS_SLEEP);


        renderer.render();

        debugRenderer.render(world, gameCamera.combined);

        game.batch.setProjectionMatrix(gameCamera.combined);
        game.batch.begin();
        snake.draw(game.batch);

        food.draw(game.batch);

        if(renderSpider) {
            spider.draw(game.batch);

        }
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(gameOver) {
            game.setScreen(new GameOverScreen(game, level));
            dispose();
        }

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
        hud.dispose();
    }
}
