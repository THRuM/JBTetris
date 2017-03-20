package pl.tetris.blocks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TowerBlockTest {

    private Square expectedShape[][];
    private int expectedSize = 4;
    private Square expectedColor = Square.BLUE;

    @Before
    public void setUp() throws Exception {
        expectedShape = new Square[expectedSize][1];

        for(int i=0; i < expectedShape.length; i++)
            for(int j=0; j < expectedShape[i].length; j++)
                expectedShape[i][j] = expectedColor;
    }

    @Test
    public void getShape() throws Exception {

        Block block = new TowerBlock(expectedColor, expectedSize);

        assertArrayEquals("Failure creating TowerBlock - arrays not the same", expectedShape, block.getShape());
    }

    @Test
    public void rotateRight() throws Exception {
        expectedShape = new Square[1][expectedSize];

        for(int i=0; i < expectedShape.length; i++)
            for(int j=0; j < expectedShape[i].length; j++)
                expectedShape[i][j] = expectedColor;

        Block block = new TowerBlock(expectedColor, expectedSize);

        block.rotateRight();

        assertArrayEquals("Failure rotating TowerBlock - arrays not the same", expectedShape, block.getShape());
    }

    @Test
    public void revertMove() throws Exception {
        Block block = new TowerBlock(expectedColor, expectedSize);

        block.rotateRight();

        block.revertMove();

        assertArrayEquals("Failure reverting move of TowerBlock - arrays not the same", expectedShape, block.getShape());
    }

}