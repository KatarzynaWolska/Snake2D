package com.mygdx.game.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GamePreferences {
    private Preferences preferences;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private static final String HIGH_SCORE_1 = "high_score_1";
    private static final String DATE_HIGH_SCORE_1 = "high_score_1_date";
    private static final String HIGH_SCORE_2 = "high_score_2";
    private static final String DATE_HIGH_SCORE_2 = "high_score_2_date";
    private static final String HIGH_SCORE_3 = "high_score_3";
    private static final String DATE_HIGH_SCORE_3 = "high_score_3_date";
    private static final String PREFS_NAME = "game_prefs";

    private static final String GAMEBOARD_HIGH_SCORE_1 = "gameboard_high_score_1";
    private static final String GAMEBOARD_HIGH_SCORE_2 = "gameboard_high_score_2";
    private static final String GAMEBOARD_HIGH_SCORE_3 = "gameboard_high_score_3";

    private static final String ACTUAL_GAMEBOARD = "actual_gameboard";


    public GamePreferences() {

    }

    public void putHighScore(int score, Date date, String gameboard) {
        if(score > getPrefs().getInteger(HIGH_SCORE_1)) {
            setHighScore1(score);
            getPrefs().putString(DATE_HIGH_SCORE_1, dateFormat.format(date));
            getPrefs().putString(GAMEBOARD_HIGH_SCORE_1, gameboard);
            getPrefs().flush();
        }
        else if (score > getPrefs().getInteger(HIGH_SCORE_2)) {
            setHighScore2(score);
            getPrefs().putString(DATE_HIGH_SCORE_2, dateFormat.format(date));
            getPrefs().putString(GAMEBOARD_HIGH_SCORE_2, gameboard);
            getPrefs().flush();
        }
        else if (score > getPrefs().getInteger(HIGH_SCORE_3)) {
            setHighScore3(score);
            getPrefs().putString(DATE_HIGH_SCORE_3, dateFormat.format(date));
            getPrefs().putString(GAMEBOARD_HIGH_SCORE_3, gameboard);
            getPrefs().flush();
        }
    }

    protected Preferences getPrefs () {
        if(preferences == null) {
            preferences = Gdx.app.getPreferences(PREFS_NAME);
            preferences.flush();
        }
        return preferences;
    }

    public void setHighScore1(int score) {
        int score1 = getPrefs().getInteger(HIGH_SCORE_1);
        int score2 = getPrefs().getInteger(HIGH_SCORE_2);

        getPrefs().putInteger(HIGH_SCORE_1, score);
        getPrefs().putInteger(HIGH_SCORE_2, score1);
        getPrefs().putInteger(HIGH_SCORE_3, score2);
    }

    public void setHighScore2(int score) {
        int score2 = getPrefs().getInteger(HIGH_SCORE_2);

        getPrefs().putInteger(HIGH_SCORE_2, score);
        getPrefs().putInteger(HIGH_SCORE_3, score2);
    }

    public void setHighScore3(int score) {
        getPrefs().putInteger(HIGH_SCORE_3, score);
    }

    public Integer getHighScore1() {
        return getPrefs().getInteger(HIGH_SCORE_1);
    }

    public Integer getHighScore2() {
        return getPrefs().getInteger(HIGH_SCORE_2);
    }

    public Integer getHighScore3() {
        return getPrefs().getInteger(HIGH_SCORE_3);
    }

    public String getHighScore1Date() {
        return getPrefs().getString(DATE_HIGH_SCORE_1);
    }

    public String getHighScore2Date() {
        return getPrefs().getString(DATE_HIGH_SCORE_2);
    }

    public String getHighScore3Date() {
        return getPrefs().getString(DATE_HIGH_SCORE_3);
    }

    public String getActualGameboard() {
        return getPrefs().getString(ACTUAL_GAMEBOARD);
    }

    public void setActualGameboard(String gameboard) {
        getPrefs().putString(ACTUAL_GAMEBOARD, gameboard);
    }

    public String getHighScore1Gameboard() {
        return getPrefs().getString(GAMEBOARD_HIGH_SCORE_1);
    }

    public String getHighScore2Gameboard() {
        return getPrefs().getString(GAMEBOARD_HIGH_SCORE_2);
    }

    public String getHighScore3Gameboard() {
        return getPrefs().getString(GAMEBOARD_HIGH_SCORE_3);
    }
}
