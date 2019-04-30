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

public class StatisticsScreen implements Screen {

    private Viewport viewport;
    private Stage stage;

    private SnakeGame game;

    public StatisticsScreen(SnakeGame game) {
        this.game = game;
        viewport = new StretchViewport(PlayScreen.WORLD_WIDTH, PlayScreen.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);


        Label statisticsLabel = new Label("Statistics", MainMenuScreen.skin);
        Label highScoresLabel = new Label("High Scores", MainMenuScreen.skin);

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

        table.add(statisticsLabel).height(PlayScreen.WORLD_HEIGHT / 8).expandX();
        table.row();
        table.add(highScoresLabel).height(PlayScreen.WORLD_HEIGHT / 8).expandX().padTop(30);
        table.row();
        table.add(backButton).height(PlayScreen.WORLD_HEIGHT / 8).expandX().padTop(150);


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
