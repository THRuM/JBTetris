package pl.tetris.plane;

import org.junit.Before;
import org.junit.Test;
import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;
import pl.tetris.blocks.SquareBlock;
import pl.tetris.users.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleCollisionDetectionTest {

    private Square squarePlane[][];
    private int height = 30;
    private int width = 10;
    private Square expectedColor = Square.BLUE;
    private Square expectedColor2 = Square.RED;

    @Before
    public void setUp() throws Exception {
        squarePlane = new Square[height][width];
        for(int i = 0; i < squarePlane.length; i++)
            for(int j = 0; j < squarePlane[i].length; j++)
                squarePlane[i][j] = Square.BLANK;

    }

    @Test
    public void checkSquares() throws Exception {
        squarePlane[0][4] = expectedColor;
        squarePlane[0][5] = expectedColor;
        squarePlane[1][4] = expectedColor;
        squarePlane[1][5] = expectedColor;

        Block block = new SquareBlock(expectedColor2);

        CollisionDetection collisionDetection = new SimpleCollisionDetection(this.squarePlane);

        boolean result = collisionDetection.checkSquares(block,3,1);

        assertFalse("Returned result different then expected",result);

        result = collisionDetection.checkSquares(block,2,1);

        assertTrue("Returned result different then expected",result);
    }

    @Test
    public void fullLines() throws Exception {

        List<Integer> expectedLines = new ArrayList<>();

        expectedLines.add(0);
        expectedLines.add(1);
        expectedLines.add(25);
        expectedLines.add(28);


        for(Integer i : expectedLines)
            for(int j=0; j < squarePlane[i].length; j++)
                squarePlane[i][j] = expectedColor;

        CollisionDetection collisionDetection = new SimpleCollisionDetection(this.squarePlane);

        List<Integer> returnedLines = collisionDetection.fullLines();

        assertArrayEquals(expectedLines.toArray(), returnedLines.toArray());
    }

}