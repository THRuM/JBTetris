package pl.tetris.plane;

import org.junit.Before;
import org.junit.Test;
import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;
import pl.tetris.blocks.SquareBlock;
import pl.tetris.blocks.TowerBlock;
import pl.tetris.users.User;

import static org.junit.Assert.*;

public class PlaneTest {

    private Square squarePlane[][];
    private int height = 30;
    private int width = 10;
    private Square expectedColor = Square.BLUE;
    private Square expectedColor2 = Square.RED;
    private int expectedSize = 2;
    private int expectedSize2 = 4;
    private User user1;
    private User user2;

    @Before
    public void setUp() throws Exception {
        squarePlane = new Square[height][width];
        for(int i = 0; i < squarePlane.length; i++)
            for(int j = 0; j < squarePlane[i].length; j++)
                squarePlane[i][j] = Square.BLANK;

        user1 = new User("User1");
        user2 = new User("User2");
    }

    //Tests for single user
    @Test
    public void addBlock() throws Exception {

        squarePlane[0][4] = expectedColor;
        squarePlane[0][5] = expectedColor;
        squarePlane[1][4] = expectedColor;
        squarePlane[1][5] = expectedColor;

        Plane plane = new Plane(width, height);
        plane.addUser(user1);

        Block block = new SquareBlock(expectedColor, expectedSize);

        plane.addBlock(user1, block);

        assertArrayEquals("Failed adding block for single user - arrays not the same", squarePlane, plane.getPlane());

    }

    @Test
    public void moveBlock() throws Exception {
        squarePlane[1][3] = expectedColor;
        squarePlane[1][4] = expectedColor;
        squarePlane[2][3] = expectedColor;
        squarePlane[2][4] = expectedColor;

        Plane plane = new Plane(width, height);
        plane.addUser(user1);

        Block block = new SquareBlock(expectedColor, expectedSize);

        plane.addBlock(user1, block);

        plane.moveBlock(user1, Direction.DOWN);
        plane.moveBlock(user1, Direction.LEFT);

        assertArrayEquals("Failed moving block for single user - arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void gameStep() throws Exception {
        squarePlane[2][4] = expectedColor;
        squarePlane[2][5] = expectedColor;
        squarePlane[3][4] = expectedColor;
        squarePlane[3][5] = expectedColor;

        Plane plane = new Plane(width, height);
        plane.addUser(user1);

        Block block = new SquareBlock(expectedColor, expectedSize);

        plane.addBlock(user1, block);

        plane.gameStep();
        plane.gameStep();

        assertArrayEquals("Failed performing game step for single user - arrays not the same", squarePlane, plane.getPlane());
    }

    //Tests for multiplayer
    @Test
    public void addBlockForMultiplayer() throws Exception {
        squarePlane[0][4] = expectedColor2;
        squarePlane[0][5] = expectedColor2;
        squarePlane[1][4] = expectedColor2;
        squarePlane[1][5] = expectedColor2;

        squarePlane[2][4] = expectedColor;
        squarePlane[2][5] = expectedColor;
        squarePlane[3][4] = expectedColor;
        squarePlane[3][5] = expectedColor;

        Plane plane = new Plane(width, height);
        plane.addUser(user1);
        plane.addUser(user2);

        Block block1 = new SquareBlock(expectedColor, expectedSize);
        Block block2 = new SquareBlock(expectedColor2, expectedSize);

        plane.addBlock(user1, block1);
        plane.addBlock(user2, block2);

        plane.gameStep();
        plane.gameStep();

        assertArrayEquals("Failed performing game step for multiple users - arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void moveBlockForMultiplayer() throws Exception {
        squarePlane[0][2] = expectedColor2;
        squarePlane[0][3] = expectedColor2;
        squarePlane[1][2] = expectedColor2;
        squarePlane[1][3] = expectedColor2;

        squarePlane[2][6] = expectedColor;
        squarePlane[2][7] = expectedColor;
        squarePlane[3][6] = expectedColor;
        squarePlane[3][7] = expectedColor;

        Plane plane = new Plane(width, height);
        plane.addUser(user1);
        plane.addUser(user2);

        Block block1 = new SquareBlock(expectedColor, expectedSize);
        Block block2 = new SquareBlock(expectedColor2, expectedSize);

        plane.addBlock(user1, block1);
        plane.addBlock(user2, block2);

        plane.gameStep();
        plane.gameStep();

        plane.moveBlock(user1, Direction.RIGHT);
        plane.moveBlock(user1, Direction.RIGHT);

        plane.moveBlock(user2, Direction.LEFT);
        plane.moveBlock(user2, Direction.LEFT);

        assertArrayEquals("Failed moving blocks for multiple users - arrays not the same", squarePlane, plane.getPlane());
    }


    @Test
    public void moveBlockForMultiplayerCollision() throws Exception {
        squarePlane[2][2] = expectedColor;
        squarePlane[2][3] = expectedColor;
        squarePlane[3][2] = expectedColor;
        squarePlane[3][3] = expectedColor;

        squarePlane[2][4] = expectedColor2;
        squarePlane[2][5] = expectedColor2;
        squarePlane[3][4] = expectedColor2;
        squarePlane[3][5] = expectedColor2;

        Plane plane = new Plane(width, height);
        plane.addUser(user1);
        plane.addUser(user2);

        Block block1 = new SquareBlock(expectedColor, expectedSize);
        Block block2 = new SquareBlock(expectedColor2, expectedSize);

        plane.addBlock(user1, block1);
        plane.addBlock(user2, block2);

        plane.moveBlock(user1, Direction.LEFT);
        plane.moveBlock(user1, Direction.LEFT);

        plane.gameStep();
        plane.gameStep();

        plane.moveBlock(user1, Direction.RIGHT);
        plane.moveBlock(user2, Direction.LEFT);

        assertArrayEquals("Failed moving blocks for multiple users - arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void gameStepForMultiplayer() throws Exception {
        squarePlane[0][4] = expectedColor;
        squarePlane[0][5] = expectedColor;
        squarePlane[1][4] = expectedColor;
        squarePlane[1][5] = expectedColor;

        squarePlane[2][5] = expectedColor2;
        squarePlane[3][5] = expectedColor2;
        squarePlane[4][5] = expectedColor2;
        squarePlane[5][5] = expectedColor2;

        Plane plane = new Plane(width, height);
        plane.addUser(user1);
        plane.addUser(user2);

        Block block1 = new SquareBlock(expectedColor, expectedSize);
        Block block2 = new TowerBlock(expectedColor2, expectedSize2);

        plane.addBlock(user1, block2);
        plane.addBlock(user2, block1);

        plane.gameStep();
        plane.gameStep();

        assertArrayEquals("Failed performing game step for multiple users - arrays not the same", squarePlane, plane.getPlane());
    }
}