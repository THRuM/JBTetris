package pl.tetris.plane;

import org.junit.Before;
import org.junit.Test;
import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;
import pl.tetris.blocks.SquareBlock;
import pl.tetris.blocks.TowerBlock;

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
    public void addSquareBlock() throws Exception {
        squarePlane[0][4] = Square.BLUE;
        squarePlane[0][5] = Square.BLUE;
        squarePlane[1][4] = Square.BLUE;
        squarePlane[1][5] = Square.BLUE;

        Plane plane = new Plane(10, 30);
        Block block = new SquareBlock(Square.BLUE, 2);
        plane.addBlock(block);

        assertArrayEquals("Failure adding Square Block - Square arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void moveSquareBlockDown() throws Exception {
        squarePlane[1][4] = Square.BLUE;
        squarePlane[1][5] = Square.BLUE;
        squarePlane[2][4] = Square.BLUE;
        squarePlane[2][5] = Square.BLUE;

        Plane plane = new Plane(10, 30);
        Block square = new SquareBlock(Square.BLUE, 2);
        plane.addBlock(square);

        plane.moveBlockDown();

        assertArrayEquals("Failure moving Block - Square arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void addTowerBlock() throws Exception {
        squarePlane[0][5] = Square.GREEN;
        squarePlane[1][5] = Square.GREEN;
        squarePlane[2][5] = Square.GREEN;
        squarePlane[3][5] = Square.GREEN;

        Plane plane = new Plane(10,30);
        Block block = new TowerBlock(Square.GREEN, 4);
        plane.addBlock(block);

        assertArrayEquals("Failure adding Tower Block - Square arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void moveTowerBlockDown() throws Exception {
        squarePlane[1][5] = Square.GREEN;
        squarePlane[2][5] = Square.GREEN;
        squarePlane[3][5] = Square.GREEN;
        squarePlane[4][5] = Square.GREEN;

        Plane plane = new Plane(10, 30);
        Block block = new TowerBlock(Square.GREEN, 4);
        plane.addBlock(block);

        plane.moveBlockDown();

        assertArrayEquals("Failure moving Block - Tower arrays not the same", squarePlane, plane.getPlane());
    }

}