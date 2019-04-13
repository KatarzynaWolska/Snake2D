package com.mygdx.game.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.game.Direction;
import com.mygdx.game.Snake;

public class Spider extends MovingCell implements FoodInterface {

    public static final String TEXTURE_PATH = "spider.png";
    private static final int NUMBER_OF_DIRECTIONS = 4;

    public Spider(String pathToTexture, TiledMap map) {
        super(pathToTexture, map);
    }

    public void hitObstacle(Snake snake, Food food) {
        if(checkWallCollision() || snake.checkSnakeCollision(this) || food.checkCellsCollision(this)) {
            Direction actualDirection = getDirection();
            switch (actualDirection) {
                case UP:
                    setPosition(getX(), getY() - SNAKE_MOVEMENT);
                    setDirection(Direction.DOWN);
                    setNextDirection(Direction.DOWN);
                    break;
                case DOWN:
                    setPosition(getX(), getY() + SNAKE_MOVEMENT);
                    setDirection(Direction.UP);
                    setNextDirection(Direction.UP);
                    break;
                case LEFT:
                    setPosition(getX() + SNAKE_MOVEMENT, getY());
                    setDirection(Direction.RIGHT);
                    setNextDirection(Direction.RIGHT);
                    break;
                case RIGHT:
                    setPosition(getX() - SNAKE_MOVEMENT, getY());
                    setDirection(Direction.LEFT);
                    setNextDirection(Direction.LEFT);
                    break;
            }
        }
    }

    public void generateRandomDirection() {
        int number = getRandomGenerator().nextInt(NUMBER_OF_DIRECTIONS) + 1;

        switch (number) {
            case 1:
                setDirection(Direction.RIGHT);
                setNextDirection(Direction.RIGHT);
                break;
            case 2:
                setDirection(Direction.LEFT);
                setNextDirection(Direction.LEFT);
                break;
            case 3:
                setDirection(Direction.UP);
                setNextDirection(Direction.UP);
                break;
            case 4:
                setDirection(Direction.DOWN);
                setNextDirection(Direction.DOWN);
                break;
        }
    }

}
