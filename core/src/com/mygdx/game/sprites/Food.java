package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.game.Snake;

import java.util.Random;

public class Food extends Cell implements FoodInterface {

    public static final String TEXTURE_PATH = "food";


    public Food(String pathToTexture, TiledMap map, TextureAtlas atlas) {
        super(atlas, pathToTexture, map, 0, 0, 16, 16);
    }


    public void draw(Batch batch){
        super.draw(batch);
    }

}
