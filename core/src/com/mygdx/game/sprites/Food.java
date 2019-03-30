package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Snake;

import java.util.Random;

import static com.mygdx.game.sprites.SnakeCell.SNAKE_MOVEMENT;

public class Food extends Sprite {

    private int x;
    private int y;
    private Random randomGenerator;
    private Texture texture;

    private static final int SIZE = 16;
    private static final int CELLS_ON_SCREEN = 25;

    public Food() {
        this.randomGenerator = new Random();
        this.texture = new Texture("food.png");
        this.x = 0;
        this.y = 0;
        setBounds(x,y, SNAKE_MOVEMENT, SNAKE_MOVEMENT);
        setRegion(this.texture);
    }

    public void generateFoodPosition(Snake snake) {
        this.x = (randomGenerator.nextInt(CELLS_ON_SCREEN)) * SIZE;
        this.y = (randomGenerator.nextInt(CELLS_ON_SCREEN)) * SIZE;


        while (!checkFoodPosition(x, y, snake)) {
            this.x = (randomGenerator.nextInt(CELLS_ON_SCREEN)) * SIZE;
            this.y = (randomGenerator.nextInt(CELLS_ON_SCREEN)) * SIZE;
        }

        setBounds(x,y, SNAKE_MOVEMENT, SNAKE_MOVEMENT);
    }

    private boolean checkFoodPosition(int x, int y, Snake snake) {
        if(snake.getSnakeHead().checkPosition(x, y)) {
            for(SnakeCell snakeCell : snake.getSnakeBody()) {
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

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void draw(Batch batch){
        super.draw(batch);
    }

}
