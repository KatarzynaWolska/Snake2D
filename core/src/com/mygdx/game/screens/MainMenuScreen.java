package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.SnakeGame;

public class MainMenuScreen implements Screen {

    private Viewport viewport;
    private Stage stage;
    public static Skin skin;

    private SnakeGame game;

    public MainMenuScreen(SnakeGame game) {
        this.game = game;
        viewport = new StretchViewport(PlayScreen.WORLD_WIDTH, PlayScreen.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);


        createSkin();

        Label snakeLabel = new Label("Snake2D", skin);

        TextButton newGameButton = new TextButton("New Game", skin);
        newGameButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               dispose();
               game.setScreen(new GameboardMenuScreen(game));
           }
        });

        TextButton optionsButton = new TextButton("Options", skin);
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new OptionsScreen(game));
            }
        });

        TextButton statisticsButton = new TextButton("Statistics", skin);
        statisticsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new StatisticsScreen(game));
            }
        });

        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                System.exit(0);
            }
        });

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        table.add(snakeLabel).height(PlayScreen.WORLD_HEIGHT / 8).expandX();
        table.row();
        table.add(newGameButton).height(PlayScreen.WORLD_HEIGHT / 8).expandX().padTop(20);
        table.row();
        table.add(optionsButton).height(PlayScreen.WORLD_HEIGHT / 8).expandX().padTop(20);
        table.row();
        table.add(statisticsButton).height(PlayScreen.WORLD_HEIGHT / 8).expandX().padTop(20);
        table.row();
        table.add(exitButton).height(PlayScreen.WORLD_HEIGHT / 8).expandX().padTop(70);


        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            dispose();
            System.exit(0);
        }

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
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
        stage.dispose();
    }

    private void createSkin() {
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GREEN);
        textButtonStyle.down = skin.newDrawable("background", Color.GREEN);
        textButtonStyle.checked = skin.newDrawable("background", Color.FIREBRICK);
        textButtonStyle.over = skin.newDrawable("background", Color.FIREBRICK);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
        labelStyle.fontColor = Color.GREEN;
        skin.add("default", labelStyle);
    }
}
