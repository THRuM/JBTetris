package pl.tetris.blocks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LBlockTest {

    private Square expectedShape[][];
    private int expectedSize = 3;
    private Square expectedColor = Square.RED;

    @Before
    public void setUp() throws Exception {
        expectedShape = new Square[expectedSize][expectedSize];

        for(int i=0; i < expectedShape.length; i++)
            for(int j=0; j < expectedShape[i].length; j++)
                expectedShape[i][j] = Square.BLANK;
    }

    @Test
    public void getShape() throws Exception {
        expectedShape[0][2] = expectedColor;
        expectedShape[1][2] = expectedColor;
        expectedShape[2][0] = expectedColor;
        expectedShape[2][1] = expectedColor;
        expectedShape[2][2] = expectedColor;

        Block block = new LBlock(expectedColor, expectedSize);

        assertArrayEquals("Failure creating LBlock - arrays not the same", expectedShape, block.getShape());
    }

    @Test
    public void rotateRight() throws Exception {
        expectedShape[0][0] = expectedColor;
        expectedShape[1][0] = expectedColor;
        expectedShape[2][0] = expectedColor;
        expectedShape[2][1] = expectedColor;
        expectedShape[2][2] = expectedColor;

        Block block = new LBlock(expectedColor, expectedSize);

        block.rotateRight();

        assertArrayEquals("Failure rotating block right - arrays not the same", expectedShape, block.getShape());
    }

    @Test
    public void rotateLeft() throws Exception {
        expectedShape[0][0] = expectedColor;
        expectedShape[0][1] = expectedColor;
        expectedShape[0][2] = expectedColor;
        expectedShape[1][2] = expectedColor;
        expectedShape[2][2] = expectedColor;

        Block block = new LBlock(expectedColor, expectedSize);

        block.rotateLeft();

        assertArrayEquals("Failure rotating block left - arrays not the same", expectedShape, block.getShape());
    }

    @Test
    public void revertMove() throws Exception {
        expectedShape[0][2] = expectedColor;
        expectedShape[1][2] = expectedColor;
        expectedShape[2][0] = expectedColor;
        expectedShape[2][1] = expectedColor;
        expectedShape[2][2] = expectedColor;

        Block block = new LBlock(expectedColor, expectedSize);

        block.rotateRight();

        block.revertMove();

        assertArrayEquals("Failure creating LBlock - arrays not the same", expectedShape, block.getShape());
    }

}