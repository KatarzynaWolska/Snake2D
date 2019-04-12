package com.mygdx.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.game.Snake;

import java.util.Random;

public class Spider extends MovingCell implements FoodInterface {

    public static final String TEXTURE_PATH = "spider.png";

    public Spider(String pathToTexture, TiledMap map) {
        super(pathToTexture, map);
    }

}
