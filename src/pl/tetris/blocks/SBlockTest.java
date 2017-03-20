package pl.tetris.blocks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SBlockTest {

    private Square expectedShape[][];
    private int expectedSize = 5;
    private Square expectedColor = Square.GREEN;

    @Before
    public void setUp() throws Exception {
        expectedShape = new Square[2][expectedSize];

        for(int i=0; i < expectedShape.length; i++)
            for(int j=0; j < expectedShape[i].length; j++)
                expectedShape[i][j] = Square.BLANK;
    }

    @Test
    public void getShape() throws Exception {
        expectedShape[0][0] = expectedColor;
        expectedShape[0][1] = expectedColor;
        expectedShape[0][2] = expectedColor;
        expectedShape[1][2] = expectedColor;
        expectedShape[1][3] = expectedColor;
        expectedShape[1][4] = expectedColor;

        Block block = new SBlock(expectedColor, expectedSize);

        assertArrayEquals("Failure creating SBlock - arrays not the same", expectedShape, block.getShape());
    }

    @Test
    public void rotateRight() throws Exception {
        expectedShape = new Square[expectedSize][2];

        for(int i=0; i < expectedShape.length; i++)
            for(int j=0; j < expectedShape[i].length; j++)
                expectedShape[i][j] = Square.BLANK;

        expectedShape[0][1] = expectedColor;
        expectedShape[1][1] = expectedColor;
        expectedShape[2][1] = expectedColor;
        expectedShape[2][0] = expectedColor;
        expectedShape[3][0] = expectedColor;
        expectedShape[4][0] = expectedColor;

        Block block = new SBlock(expectedColor, expectedSize);

        block.rotateRight();

        assertArrayEquals("Failure rotating SBlock right - arrays not the same", expectedShape, block.getShape());
    }

    @Test
    public void rotateLeft() throws Exception {
        expectedShape = new Square[expectedSize][2];

        for(int i=0; i < expectedShape.length; i++)
            for(int j=0; j < expectedShape[i].length; j++)
                expectedShape[i][j] = Square.BLANK;

        expectedShape[0][1] = expectedColor;
        expectedShape[1][1] = expectedColor;
        expectedShape[2][1] = expectedColor;
        expectedShape[2][0] = expectedColor;
        expectedShape[3][0] = expectedColor;
        expectedShape[4][0] = expectedColor;

        Block block = new SBlock(expectedColor, expectedSize);

        block.rotateLeft();

        assertArrayEquals("Failure rotating SBlock left - arrays not the same", expectedShape, block.getShape());
    }

    @Test
    public void revertMove() throws Exception {
        expectedShape[0][0] = expectedColor;
        expectedShape[0][1] = expectedColor;
        expectedShape[0][2] = expectedColor;
        expectedShape[1][2] = expectedColor;
        expectedShape[1][3] = expectedColor;
        expectedShape[1][4] = expectedColor;

        Block block = new SBlock(expectedColor, expectedSize);

        block.rotateLeft();

        block.revertMove();

        assertArrayEquals("Failure reverting move SBblock - arrays not the same", expectedShape, block.getShape());
    }

}