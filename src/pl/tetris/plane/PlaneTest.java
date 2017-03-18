package pl.tetris.plane;

import org.junit.Before;
import org.junit.Test;
import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;
import pl.tetris.blocks.SquareBlock;

import static org.junit.Assert.assertArrayEquals;

public class PlaneTest {

    private Square squarePlane[][];

    @Before
    public void setUp() throws Exception {
        squarePlane = new Square[30][10];
        for(int i = 0; i < squarePlane.length; i++)
            for(int j = 0; j < squarePlane[i].length; j++)
                squarePlane[i][j] = Square.BLANK;
    }

    @Test
    public void getPlane() throws Exception {
        Plane plane = new Plane(10,30);
        assertArrayEquals("Failure initializing the plane - Square arrays not same", squarePlane, plane.getPlane());
    }

    @Test
    public void addSquareBlock() throws Exception {

        squarePlane[0][4] = Square.BLUE;
        squarePlane[0][5] = Square.BLUE;
        squarePlane[1][4] = Square.BLUE;
        squarePlane[1][5] = Square.BLUE;

        Plane plane = new Plane(10, 30);
        Block square = new SquareBlock(Square.BLUE, 2);
        plane.addBlock(square);

        assertArrayEquals("Failure adding Square Block - Square arrays not the same", squarePlane, plane.getPlane());
    }

}