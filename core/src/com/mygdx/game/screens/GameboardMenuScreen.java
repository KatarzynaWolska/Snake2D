package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.SnakeGame;

public class GameboardMenuScreen implements Screen {

    private Viewport viewport;
    private Stage stage;

    private SnakeGame game;

    private static final String level1 = "level1.tmx";
    private static final String level2 = "level2.tmx";

    public GameboardMenuScreen(SnakeGame game) {
        this.game = game;
        viewport = new StretchViewport(PlayScreen.WORLD_WIDTH, PlayScreen.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);

        Gdx.input.setInputProcessor(stage);

        Label chooseGameboardLabel = new Label("Snake2D", MainMenuScreen.skin);

        TextButton firstGameboardButton = new TextButton("Gameboard 1", MainMenuScreen.skin);
        firstGameboardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.preferences.setActualGameboard("Gameboard 1");
                dispose();
                game.setScreen(new PlayScreen(game, level1));
            }
        });

        TextButton secondGameboardButton = new TextButton("Gameboard 2", MainMenuScreen.skin);
        secondGameboardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.preferences.setActualGameboard("Gameboard 2");
                dispose();
                game.setScreen(new PlayScreen(game, level2));
            }
        });


        TextButton backButton = new TextButton("Back", MainMenuScreen.skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        table.add(chooseGameboardLabel).height(PlayScreen.WORLD_HEIGHT / 8).expandX();
        table.row();
        table.add(firstGameboardButton).height(PlayScreen.WORLD_HEIGHT / 8).expandX().padTop(20);
        table.row();
        table.add(secondGameboardButton).height(PlayScreen.WORLD_HEIGHT / 8).expandX().padTop(10);
        table.row();
        table.add(backButton).height(PlayScreen.WORLD_HEIGHT / 8).expandX().padTop(150);

        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            game.setScreen(new MainMenuScreen(game));
            dispose();
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
}
