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

public class OptionsScreen implements Screen {

    private Viewport viewport;
    private Stage stage;

    private SnakeGame game;

    public OptionsScreen(SnakeGame game) {
        this.game = game;
        viewport = new StretchViewport(PlayScreen.WORLD_WIDTH, PlayScreen.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch);


        Container<Table> container = new Container<>();
        container.setSize(PlayScreen.WORLD_WIDTH, PlayScreen.WORLD_HEIGHT);
        container.center();
        container.fillX();

        Label optionsLabel = new Label("Options", MainMenuScreen.skin);
        Label difficultyLabel = new Label("Difficulty", MainMenuScreen.skin);



        TextButton easyButton = new TextButton("Easy", MainMenuScreen.skin);
        TextButton mediumButton = new TextButton("Medium", MainMenuScreen.skin);
        TextButton hardButton = new TextButton("Hard", MainMenuScreen.skin);

        if(PlayScreen.FPS_SLEEP == 10) {
            easyButton.setChecked(true);
        }
        else if (PlayScreen.FPS_SLEEP == 20) {
            mediumButton.setChecked(true);
        }
        else {
            hardButton.setChecked(true);
        }

        easyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                easyButton.setChecked(true);

                if(mediumButton.isChecked()) {
                    mediumButton.setChecked(false);
                }

                if(hardButton.isChecked()) {
                    hardButton.setChecked(false);
                }

                PlayScreen.FPS_SLEEP = 10;
            }
        });

        mediumButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mediumButton.setChecked(true);

                if(easyButton.isChecked()) {
                    easyButton.setChecked(false);
                }

                if(hardButton.isChecked()) {
                    hardButton.setChecked(false);
                }

                PlayScreen.FPS_SLEEP = 20;
            }
        });

        hardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hardButton.setChecked(true);

                if(mediumButton.isChecked()) {
                    mediumButton.setChecked(false);
                }

                if(easyButton.isChecked()) {
                    easyButton.setChecked(false);
                }

                PlayScreen.FPS_SLEEP = 30;
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

        table.row().colspan(3).expandX();
        table.add(optionsLabel).height(PlayScreen.WORLD_HEIGHT / 8).expandX();
        table.row().colspan(3).expandX();
        table.add(difficultyLabel).height(PlayScreen.WORLD_HEIGHT / 8).expandX();
        table.row().expandX();

        table.add(easyButton).width(PlayScreen.WORLD_WIDTH / 4);
        table.add(mediumButton).width(PlayScreen.WORLD_WIDTH / 4);
        table.add(hardButton).width(PlayScreen.WORLD_WIDTH / 4);
        table.row().expandX();

        table.add(backButton).colspan(3).padTop(100).width(PlayScreen.WORLD_WIDTH / 4);


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
