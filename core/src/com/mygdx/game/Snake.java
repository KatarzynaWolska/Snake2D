package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.sprites.Cell;
import com.mygdx.game.sprites.MovingCell;

public class Snake {
    private MovingCell snakeHead;
    private Array<MovingCell> snakeBody;
    private TiledMap map;
    private TextureAtlas atlas;

    private static final int INITIAL_SNAKE_LENGTH = 5;

    public static final String HEAD_TEXTURE_PATH = "snake-head";
    public static final String BODY_TEXTURE_PATH = "snake-body";


    public Snake(TiledMap map, TextureAtlas atlas) {
        this.map = map;
        this.atlas = atlas;
        this.snakeHead = new MovingCell(INITIAL_SNAKE_LENGTH * MovingCell.SNAKE_MOVEMENT, MovingCell.SNAKE_MOVEMENT, atlas, HEAD_TEXTURE_PATH, map, 0, 16, 16, 16);
        this.snakeHead.flip(true, false);
        createSnakeBody();
    }

    public Snake() {
        this.snakeHead = new MovingCell(INITIAL_SNAKE_LENGTH * MovingCell.SNAKE_MOVEMENT, MovingCell.SNAKE_MOVEMENT);
        createSnakeBody();
    }

    private void createSnakeBody() {
        Array<MovingCell> snakeBody = new Array<>();

        for (int i = 1; i < INITIAL_SNAKE_LENGTH; i++) {
            MovingCell cell = new MovingCell(i * MovingCell.SNAKE_MOVEMENT, MovingCell.SNAKE_MOVEMENT, atlas, BODY_TEXTURE_PATH, map, 16, 16, 16, 16);
            snakeBody.add(cell);
        }

        this.snakeBody = snakeBody;
    }

    public Array<MovingCell> getSnakeBody() {
        return snakeBody;
    }

    public MovingCell getSnakeHead() {
        return snakeHead;
    }

    public void setDirection(Direction direction) {
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
        MovingCell cell;

        switch (lastCell.getDirection()) {
            case RIGHT:
                cell = new MovingCell(lastCell.getX() - MovingCell.SNAKE_MOVEMENT, lastCell.getY(), atlas, Direction.RIGHT, lastCell.getDirection(), map, 16, 16, 16, 16);

                if(cell.getX() >= PlayScreen.WORLD_WIDTH) {
                    cell.setPosition(0, cell.getY());
                }

                snakeBody.insert(0, cell);
                break;
            case LEFT:
                cell = new MovingCell(lastCell.getX() + MovingCell.SNAKE_MOVEMENT, lastCell.getY(), atlas,  Direction.LEFT, lastCell.getDirection(), map, 16, 16, 16, 16);

                if(cell.getX() < 0) {
                    cell.setPosition(PlayScreen.WORLD_WIDTH - MovingCell.SNAKE_MOVEMENT, cell.getY());
                }

                snakeBody.insert(0, cell);
                break;
            case DOWN:
                cell = new MovingCell(lastCell.getX(), lastCell.getY() + MovingCell.SNAKE_MOVEMENT, atlas, Direction.DOWN, lastCell.getDirection(), map, 16, 16, 16, 16);

                if(cell.getY() < 0) {
                    cell.setPosition(cell.getX(), PlayScreen.WORLD_HEIGHT - MovingCell.SNAKE_MOVEMENT);
                }

                snakeBody.insert(0, cell);
                break;
            case UP:
                cell = new MovingCell(lastCell.getX(), lastCell.getY() - MovingCell.SNAKE_MOVEMENT, atlas, Direction.UP, lastCell.getDirection(), map, 16, 16, 16, 16);

                if(cell.getY() >= PlayScreen.WORLD_HEIGHT) {
                    cell.setPosition(cell.getX(), 0);
                }

                snakeBody.insert(0, cell);
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
