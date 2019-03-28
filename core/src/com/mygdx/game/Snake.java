package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.sprites.SnakeCell;

public class Snake {
    private SnakeCell snakeHead;
    private Array<SnakeCell> snakeBody;
    private static final int INITIAL_SNAKE_LENGTH = 5;

    public Snake() {
        this.snakeHead = new SnakeCell(INITIAL_SNAKE_LENGTH * SnakeCell.SNAKE_MOVEMENT, SnakeCell.SNAKE_MOVEMENT, "snake-head.png");
        this.snakeHead.flip(true, false);
        this.snakeBody = createSnakeBody();
    }

    private Array<SnakeCell> createSnakeBody() {
        Array<SnakeCell> snakeBody = new Array<SnakeCell>();

        for (int i = 1; i < INITIAL_SNAKE_LENGTH; i++) {
            snakeBody.add(new SnakeCell(i * SnakeCell.SNAKE_MOVEMENT, SnakeCell.SNAKE_MOVEMENT, "snake-body.png"));
        }

        return snakeBody;
    }

    public SnakeCell getSnakeHead() {
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


        for(int i = snakeBody.size - 2; i >= 0; i--) {
            snakeBody.get(i).updatePosition();
            snakeBody.get(i).setNextDirection(snakeBody.get(i+1).getDirection());
        }
    }

    public void draw(SpriteBatch batch) {
        snakeHead.draw(batch);

        for(SnakeCell snakeCell : snakeBody) {
            snakeCell.draw(batch);
        }
    }
}
