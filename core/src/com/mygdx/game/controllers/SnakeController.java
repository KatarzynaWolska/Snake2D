package com.mygdx.game.controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Direction;
import com.mygdx.game.Snake;

public class SnakeController implements GestureDetector.GestureListener {

    private Snake snake;

    public SnakeController(Snake snake) {
        this.snake = snake;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if(Math.abs(velocityX)>Math.abs(velocityY)){
            if(velocityX > 0 && snake.getSnakeHead().getDirection() != Direction.LEFT){
                snake.setDirection(Direction.RIGHT);
            }else if (velocityX < 0 && snake.getSnakeHead().getDirection() != Direction.RIGHT){
                snake.setDirection(Direction.LEFT);
            }
        }else{
            if(velocityY > 0 && snake.getSnakeHead().getDirection() != Direction.UP){
                snake.setDirection(Direction.DOWN);
            } else if (velocityY < 0 && snake.getSnakeHead().getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }
        }
        return true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
