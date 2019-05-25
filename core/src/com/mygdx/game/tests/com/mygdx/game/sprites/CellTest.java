package com.mygdx.game.sprites;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void checkCellsCollisionTrue() {
        int x = 2;
        int y = 3;
        Cell cell = new Cell(x, y);

        int x_test = 2;
        int y_test = 3;
        Cell cell_test = new Cell(x_test, y_test);

        assertTrue(cell.checkCellsCollision(cell_test));
    }

    @Test
    public void checkCellsCollisionFalse() {
        int x = 2;
        int y = 3;
        Cell cell = new Cell(x, y);

        int x_test = 4;
        int y_test = 5;
        Cell cell_test = new Cell(x_test, y_test);

        assertFalse(cell.checkCellsCollision(cell_test));
    }
}