package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
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
        Label numberOneLabel = new Label("1. ", MainMenuScreen.skin);
        Label highScoreOneLabel = new Label(game.preferences.getHighScore1().toString(), MainMenuScreen.skin);
        Label highScoreOneDateLabel = new Label(game.preferences.getHighScore1Date(), MainMenuScreen.skin);
        Label highScoreOneGameboardLabel = new Label(game.preferences.getHighScore1Gameboard(), MainMenuScreen.skin);
        Label numberTwoLabel = new Label("2. ", MainMenuScreen.skin);
        Label highScoreTwoLabel = new Label(game.preferences.getHighScore2().toString(), MainMenuScreen.skin);
        Label highScoreTwoDateLabel = new Label(game.preferences.getHighScore2Date(), MainMenuScreen.skin);
        Label highScoreTwoGameboardLabel = new Label(game.preferences.getHighScore2Gameboard(), MainMenuScreen.skin);
        Label numberThreeLabel = new Label("3. ", MainMenuScreen.skin);
        Label highScoreThreeLabel = new Label(game.preferences.getHighScore3().toString(), MainMenuScreen.skin);
        Label highScoreThreeDateLabel = new Label(game.preferences.getHighScore3Date(), MainMenuScreen.skin);
        Label highScoreThreeGameboardLabel = new Label(game.preferences.getHighScore3Gameboard(), MainMenuScreen.skin);


        TextButton backButton = new TextButton("Back", MainMenuScreen.skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        Container<Table> container = new Container<>();
        container.setSize(PlayScreen.WORLD_WIDTH, PlayScreen.WORLD_HEIGHT);
        container.center();
        container.fillX();

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        table.row().colspan(4).expandX();
        table.add(statisticsLabel).height(PlayScreen.WORLD_HEIGHT / 8).expandX();
        table.row().colspan(4).expandX();
        table.add(highScoresLabel).height(PlayScreen.WORLD_HEIGHT / 8).expandX().padTop(30);
        table.row().expandX();
        table.add(numberOneLabel);
        table.add(highScoreOneLabel);
        table.add(highScoreOneDateLabel);
        table.add(highScoreOneGameboardLabel);
        table.row().expandX();
        table.add(numberTwoLabel);
        table.add(highScoreTwoLabel);
        table.add(highScoreTwoDateLabel);
        table.add(highScoreTwoGameboardLabel);
        table.row().expandX();
        table.add(numberThreeLabel);
        table.add(highScoreThreeLabel);
        table.add(highScoreThreeDateLabel);
        table.add(highScoreThreeGameboardLabel);
        table.row().colspan(4).expandX();
        table.add(backButton).height(PlayScreen.WORLD_HEIGHT / 8).expandX().padTop(150);

        container.setActor(table);
        stage.addActor(container);
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
