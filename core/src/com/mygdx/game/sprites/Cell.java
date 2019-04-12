package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Cell extends Sprite {

    private int x_coord;
    private int y_coord;
    private Texture texture;
    private TiledMap map;
    private Random randomGenerator;

    public static final int SIZE = 16;
    public static final int CELLS_ON_SCREEN = 25;
    public static final int SNAKE_MOVEMENT = 16;


    public Cell(String pathToTexture, TiledMap map) {
        this.map = map;
        this.texture = new Texture(pathToTexture);
        this.x_coord = 0;
        this.y_coord = 0;
        this.randomGenerator = new Random();
        setBounds(x_coord, y_coord, SNAKE_MOVEMENT, SNAKE_MOVEMENT);
        setRegion(this.texture);
    }

    public void setX_coord(int x_coord) {
        this.x_coord = x_coord;
    }

    public void setY_coord(int y_coord) {
        this.y_coord = y_coord;
    }


    public Random getRandomGenerator() {
        return randomGenerator;
    }

    public boolean checkWallCollision() {
        if (map.getLayers().size() > 1) {
            for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {

                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                if (Intersector.overlaps(rectangle, getBoundingRectangle())) {
                    return true;
                }

            }

            return false;
        } else {
            return false;
        }
    }
}
