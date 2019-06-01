package com.mygdx.game.sprites;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovingCellTest {

    @Test
    public void checkPositionTrue() {
        int x = 2;
        int y = 3;

        MovingCell cell = new MovingCell(x, y);

        int x_test = 2;
        int y_test = 3;

        Assert.assertTrue(cell.checkPosition(x_test, y_test));
    }

    @Test
    public void checkPositionFalse() {
        int x = 2;
        int y = 3;

        MovingCell cell = new MovingCell(x, y);

        int x_test = 5;
        int y_test = 6;

        Assert.assertFalse(cell.checkPosition(x_test, y_test));
    }

    @Test
    public void updatePositionTrueTest() {
        int x = 0;
        int y = 0;
        MovingCell cell = new MovingCell(x,y);

        int x_test = 16;
        int y_test = 0;
        cell.updatePosition();

        Assert.assertTrue(cell.checkPosition(x_test, y_test));
    }

    @Test
    public void updatePositionFalseTest() {
        int x = 0;
        int y = 0;
        MovingCell cell = new MovingCell(x,y);

        int x_test = 16;
        int y_test = 16;
        cell.updatePosition();

        Assert.assertFalse(cell.checkPosition(x_test, y_test));
    }
}