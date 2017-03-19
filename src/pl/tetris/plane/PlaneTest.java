package pl.tetris.plane;

import org.junit.Before;
import org.junit.Test;
import pl.tetris.blocks.*;

import static org.junit.Assert.*;

public class PlaneTest {

    private Square squarePlane[][];
    private int width = 10;
    private int height = 30;

    @Before
    public void setUp() throws Exception {
        squarePlane = new Square[height][width];
        for(int i = 0; i < squarePlane.length; i++)
            for(int j = 0; j < squarePlane[i].length; j++)
                squarePlane[i][j] = Square.BLANK;
    }

    @Test
    public void addBlock() throws Exception {
        squarePlane[0][4] = Square.BLUE;
        squarePlane[0][5] = Square.BLUE;
        squarePlane[1][4] = Square.BLUE;
        squarePlane[1][5] = Square.BLUE;

        Plane plane = new Plane(width, height);
        Block block = new SquareBlock(Square.BLUE, 2);

        plane.addBlock(block);

        assertArrayEquals("Failure adding Square block - Arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void moveBlock() throws Exception {
        squarePlane[2][6] = Square.BLUE;
        squarePlane[2][7] = Square.BLUE;
        squarePlane[3][6] = Square.BLUE;
        squarePlane[3][7] = Square.BLUE;

        Plane plane = new Plane(width, height);
        Block block = new SquareBlock(Square.BLUE, 2);

        plane.addBlock(block);

        plane.moveBlock(2,2);

        assertArrayEquals("Failure moving Square block - Arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void moveBlockCheckCoords() throws Exception {
        squarePlane[0][0] = Square.BLUE;
        squarePlane[0][1] = Square.BLUE;
        squarePlane[1][0] = Square.BLUE;
        squarePlane[1][1] = Square.BLUE;

        Plane plane = new Plane(width, height);
        Block block = new SquareBlock(Square.BLUE, 2);

        plane.addBlock(block);

        plane.moveBlock(-100, -100);

        assertArrayEquals("Failure moving Square block outside the plane - Arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void moveBlockCheckSquares() throws Exception {
        squarePlane[2][3] = Square.BLUE;
        squarePlane[2][4] = Square.BLUE;
        squarePlane[3][3] = Square.BLUE;
        squarePlane[3][4] = Square.BLUE;

        squarePlane[1][4] = Square.RED;
        squarePlane[1][5] = Square.RED;
        squarePlane[2][5] = Square.RED;
        squarePlane[2][6] = Square.RED;

        Plane plane = new Plane(width, height);
        Block squareBlock = new SquareBlock(Square.BLUE, 2);
        Block sBlock = new SBlock(Square.RED, 3);

        plane.addBlock(squareBlock);
        plane.moveBlock(-1,2);

        plane.addBlock(sBlock);
        plane.moveBlock(0,1);

        plane.moveBlock(0,1);

        assertArrayEquals("Failure moving two block - collision - Arrays not the same", squarePlane, plane.getPlane());
    }
}