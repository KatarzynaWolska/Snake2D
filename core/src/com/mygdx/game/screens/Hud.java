package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private static int score;

    private static Label scoreLabel;
    private Label scoreTextLabel;

    public Hud (SpriteBatch batch) {
        score = 0;
        viewport = new FitViewport(PlayScreen.WORLD_WIDTH, PlayScreen.WORLD_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK ));
        scoreTextLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.BLACK ));

        table.add(scoreTextLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);
        //table.row();

        stage.addActor(table);
    }

    public static void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    public int getScore() {
        return score;
    }

    @Override
    public void dispose() {
        score = 0;
        stage.dispose();
    }
}
