package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.game.Snake;

import java.util.Random;

public class Food extends Cell implements FoodInterface {

    public static final String TEXTURE_PATH = "food.png";


    public Food(String pathToTexture, TiledMap map) {
        super(pathToTexture, map);
    }


    public void draw(Batch batch){
        super.draw(batch);
    }

}
