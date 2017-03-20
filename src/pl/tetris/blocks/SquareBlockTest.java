package pl.tetris.blocks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquareBlockTest {

    private Square expectedShape[][];
    private int expectedSize = 2;
    private Square expectedColor = Square.BLUE;

    @Before
    public void setUp() throws Exception {
        expectedShape = new Square[expectedSize][expectedSize];

        for(int i=0; i < expectedShape.length; i++)
            for(int j=0; j < expectedShape[i].length; j++)
                expectedShape[i][j] = expectedColor;
    }

    @Test
    public void getShape() throws Exception {
        Block block = new SquareBlock(expectedColor, expectedSize);

        assertArrayEquals("Failure creating SquareBlock - arrays not the same", expectedShape, block.getShape());
    }

    @Test
    public void rotateRight() throws Exception {
        Block block = new SquareBlock(expectedColor, expectedSize);

        block.rotateRight();

        assertArrayEquals("Failure rotating SquareBlock right - arrays not the same", expectedShape, block.getShape());
    }

    @Test
    public void rotateLeft() throws Exception {
        Block block = new SquareBlock(expectedColor, expectedSize);

        block.rotateLeft();

        assertArrayEquals("Failure rotating SquareBlock left - arrays not the same", expectedShape, block.getShape());
    }

    @Test
    public void revertMove() throws Exception {
        Block block = new SquareBlock(expectedColor, expectedSize);

        block.rotateLeft();

        block.revertMove();

        assertArrayEquals("Failure reverting move SquareBlock - arrays not the same", expectedShape, block.getShape());
    }

}