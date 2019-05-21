package com.mygdx.game.sprites;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovingCellTest {

    @Test
    public void checkPosition() {
        int x = 2;
        int y = 3;

        MovingCell cell = new MovingCell(x, y);

        int x_test = 2;
        int y_test = 3;
        
        assertTrue(cell.checkPosition(x_test, y_test));
    }
}