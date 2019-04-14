package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.sprites.Cell;
import com.mygdx.game.sprites.MovingCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.DropMode;

public class Snake {
    private MovingCell snakeHead;
    private Array<MovingCell> snakeBody;
    private TiledMap map;

    private static final int INITIAL_SNAKE_LENGTH = 5;

    public static final String HEAD_TEXTURE_PATH = "snake-head.png";
    public static final String BODY_TEXTURE_PATH = "snake-body.png";


    public Snake(TiledMap map) {
        this.map = map;
        this.snakeHead = new MovingCell(INITIAL_SNAKE_LENGTH * MovingCell.SNAKE_MOVEMENT, MovingCell.SNAKE_MOVEMENT, HEAD_TEXTURE_PATH, map);
        this.snakeHead.flip(true, false);
        this.snakeBody = createSnakeBody();
    }

    private Array<MovingCell> createSnakeBody() {
        Array<MovingCell> snakeBody = new Array<>();

        for (int i = 1; i < INITIAL_SNAKE_LENGTH; i++) {
            snakeBody.add(new MovingCell(i * MovingCell.SNAKE_MOVEMENT, MovingCell.SNAKE_MOVEMENT, BODY_TEXTURE_PATH, map));
        }

        return snakeBody;
    }

    public Array<MovingCell> getSnakeBody() {
        return snakeBody;
    }

    public MovingCell getSnakeHead() {
        return snakeHead;
    }

    public void setDirection(Direction direction) {
        //snakeHead.setDirection(direction);
        snakeHead.setNextDirection(direction);
    }

    public void updatePosition() {
        snakeHead.updatePosition();
        snakeBody.get(snakeBody.size - 1).updatePosition();
        snakeBody.get(snakeBody.size - 1).setNextDirection(snakeHead.getDirection());


        for (int i = snakeBody.size - 2; i >= 0; i--) {
            snakeBody.get(i).updatePosition();
            snakeBody.get(i).setNextDirection(snakeBody.get(i + 1).getDirection());
        }

    }

    public void eat() {
        MovingCell lastCell = snakeBody.get(0);

        switch (lastCell.getDirection()) {
            case RIGHT:
                snakeBody.insert(0, new MovingCell(lastCell.getX() - MovingCell.SNAKE_MOVEMENT, lastCell.getY(), Direction.RIGHT, lastCell.getDirection(), map));
                break;
            case LEFT:
                snakeBody.insert(0, new MovingCell(lastCell.getX() + MovingCell.SNAKE_MOVEMENT, lastCell.getY(), Direction.LEFT, lastCell.getDirection(), map));
                break;
            case DOWN:
                snakeBody.insert(0,new MovingCell(lastCell.getX(), lastCell.getY() + MovingCell.SNAKE_MOVEMENT, Direction.DOWN, lastCell.getDirection(), map));
                break;
            case UP:
                snakeBody.insert(0,new MovingCell(lastCell.getX(), lastCell.getY() - MovingCell.SNAKE_MOVEMENT, Direction.UP, lastCell.getDirection(), map));
                break;
        }
    }

    public boolean checkSnakeCollision() {
        for (MovingCell snakeCell : snakeBody) {
            if (checkSnakeHeadCollision(snakeCell)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkSnakeCollision(Cell cell) {
        for (MovingCell snakeCell : snakeBody) {
            if (snakeCell.checkCellsCollision(cell)) {
                return true;
            }
        }
        return false;
    }


    public boolean checkSnakeHeadCollision(Sprite sprite) {
        return (snakeHead.getX() == sprite.getX()) && (snakeHead.getY() == sprite.getY());
    }

    public void draw(SpriteBatch batch) {
        snakeHead.draw(batch);

        for (MovingCell snakeCell : snakeBody) {
            snakeCell.draw(batch);
        }
    }
}
