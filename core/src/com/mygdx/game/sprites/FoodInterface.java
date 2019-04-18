package com.mygdx.game.sprites;

import com.mygdx.game.Snake;

import java.util.Random;

public interface FoodInterface {

    default void generateFoodPosition(Cell food, Snake snake, Random random) {
        int x = (random.nextInt(Cell.CELLS_ON_SCREEN)) * Cell.SIZE;
        int y = (random.nextInt(Cell.CELLS_ON_SCREEN)) * Cell.SIZE;


        while (!checkFoodPosition(x, y, snake)) {
            x = (random.nextInt(Cell.CELLS_ON_SCREEN)) * Cell.SIZE;
            y = (random.nextInt(Cell.CELLS_ON_SCREEN)) * Cell.SIZE;
        }

        food.setX(x);
        food.setY(y);

        food.setX_coord(x);
        food.setY_coord(y);

        food.setBounds(x,y, Cell.SNAKE_MOVEMENT, Cell.SNAKE_MOVEMENT);
    }

    default boolean checkFoodPosition(int x, int y, Snake snake) {
        if(snake.getSnakeHead().checkPosition(x, y)) {
            for(MovingCell snakeCell : snake.getSnakeBody()) {
                if(!snakeCell.checkPosition(x, y)) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    default boolean checkSnakeCollision(Cell food, Snake snake) {
        return snake.checkSnakeHeadCollision(food);
    }

}
