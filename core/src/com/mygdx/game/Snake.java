package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.sprites.Food;
import com.mygdx.game.sprites.SnakeCell;

public class Snake {
    private SnakeCell snakeHead;
    private Array<SnakeCell> snakeBody;
    private Map map;

    private static final int INITIAL_SNAKE_LENGTH = 5;

    public Snake(Map map) {
        this.map = map;
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

    public Array<SnakeCell> getSnakeBody() {
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


        for (int i = snakeBody.size - 2; i >= 0; i--) {
            snakeBody.get(i).updatePosition();
            snakeBody.get(i).setNextDirection(snakeBody.get(i + 1).getDirection());
        }
    }

    private void eat() {
        SnakeCell lastCell = snakeBody.get(0);

        switch (lastCell.getDirection()) {
            case RIGHT:
                snakeBody.insert(0, new SnakeCell(lastCell.getX() - SnakeCell.SNAKE_MOVEMENT, lastCell.getY(), Direction.RIGHT, lastCell.getDirection()));
                break;
            case LEFT:
                snakeBody.insert(0, new SnakeCell(lastCell.getX() + SnakeCell.SNAKE_MOVEMENT, lastCell.getY(), Direction.LEFT, lastCell.getDirection()));
                break;
            case DOWN:
                snakeBody.insert(0, new SnakeCell(lastCell.getX(), lastCell.getY() + SnakeCell.SNAKE_MOVEMENT, Direction.DOWN, lastCell.getDirection()));
                break;
            case UP:
                snakeBody.insert(0, new SnakeCell(lastCell.getX(), lastCell.getY() - SnakeCell.SNAKE_MOVEMENT, Direction.UP, lastCell.getDirection()));
                break;
        }
    }

    public void checkFoodCollision(Food food) {
        if (checkCollision(food)) {
            eat();
            food.generateFoodPosition(this);

            while(checkWallCollision(food)) {
                food.generateFoodPosition(this);
            }
        }
    }

    public boolean checkSnakeCollision() {
        for (SnakeCell snakeCell : snakeBody) {
            if (checkCollision(snakeCell)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWallCollision(Sprite sprite) {
        if (map.getLayers().size() > 1) {
            for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {

                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                if (Intersector.overlaps(rectangle, sprite.getBoundingRectangle())) {
                    return true;
                }

            }

            return false;
        } else {
            return false;
        }
    }

    private boolean checkCollision(Sprite sprite) {
        return (snakeHead.getX() == sprite.getX()) && (snakeHead.getY() == sprite.getY());
    }

    public void draw(SpriteBatch batch) {
        snakeHead.draw(batch);

        for (SnakeCell snakeCell : snakeBody) {
            snakeCell.draw(batch);
        }
    }
}
