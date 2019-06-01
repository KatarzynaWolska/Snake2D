package com.mygdx.game;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.sprites.Cell;
import com.mygdx.game.sprites.MovingCell;

import org.junit.Assert;
import org.junit.Test;


public class SnakeTest {

    @Test
    public void updateFalsePosition() {
        MovingCell head = new MovingCell(3, 2);
        Array<MovingCell> body = new Array();
        body.add(new MovingCell(2, 2));
        body.add(new MovingCell(1, 2));

        Snake snake = new Snake(head, body);
        snake.updatePosition();
        Cell test_cell = new Cell(2,2);

        Assert.assertFalse(snake.checkSnakeCollision(test_cell));
    }

    @Test
    public void updateTruePosition() {
        MovingCell head = new MovingCell(3, 2);
        Array<MovingCell> body = new Array();
        body.add(new MovingCell(2, 2));
        body.add(new MovingCell(1, 2));

        Snake snake = new Snake(head, body);
        snake.updatePosition();
        Cell test_cell = new Cell(19,2);

        Assert.assertFalse(snake.checkSnakeCollision(test_cell));
    }


    @Test
    public void checkTrueSnakeCollision() {
        MovingCell head = new MovingCell(3, 2);
        Array<MovingCell> body = new Array();
        body.add(new MovingCell(2, 2));
        body.add(new MovingCell(1, 2));

        Snake snake = new Snake(head, body);
        Cell cell_test = new Cell(2, 2);

        Assert.assertTrue(snake.checkSnakeCollision(cell_test));
    }

    @Test
    public void checkFalseSnakeCollision() {
        MovingCell head = new MovingCell(3, 2);
        Array<MovingCell> body = new Array();
        body.add(new MovingCell(2, 2));
        body.add(new MovingCell(1, 2));

        Snake snake = new Snake(head, body);
        Cell test_cell = new Cell(3,3);

        Assert.assertFalse(snake.checkSnakeCollision(test_cell));
    }

    @Test
    public void checkTrueSnakeHeadCollision() {
        MovingCell head = new MovingCell(3, 2);
        Array<MovingCell> body = new Array();
        body.add(new MovingCell(2, 2));

        Snake snake = new Snake(head, body);
        Cell cell_test = new Cell(3, 2);

        Assert.assertTrue(snake.checkSnakeHeadCollision(cell_test));
    }

    @Test
    public void checkFalseSnakeHeadCollision() {
        MovingCell head = new MovingCell(3, 2);
        Array<MovingCell> body = new Array();
        body.add(new MovingCell(2, 2));

        Snake snake = new Snake(head, body);
        Cell cell_test = new Cell(4, 2);

        Assert.assertFalse(snake.checkSnakeHeadCollision(cell_test));
    }
}