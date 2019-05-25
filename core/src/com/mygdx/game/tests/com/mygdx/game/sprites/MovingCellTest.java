package com.mygdx.game.sprites;

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
        
        assertTrue(cell.checkPosition(x_test, y_test));
    }

    @Test
    public void checkPositionFalse() {
        int x = 2;
        int y = 3;

        MovingCell cell = new MovingCell(x, y);

        int x_test = 5;
        int y_test = 6;

        assertFalse(cell.checkPosition(x_test, y_test));
    }

    @Test
    public void updatePositionTrueTest() {
        int x = 0;
        int y = 0;
        MovingCell cell = new MovingCell(x,y);

        int x_test = 16;
        int y_test = 0;
        cell.updatePosition();

        assertTrue(cell.checkPosition(x_test, y_test));
    }

    @Test
    public void updatePositionFalseTest() {
        int x = 0;
        int y = 0;
        MovingCell cell = new MovingCell(x,y);

        int x_test = 16;
        int y_test = 16;
        cell.updatePosition();

        assertFalse(cell.checkPosition(x_test, y_test));
    }
}