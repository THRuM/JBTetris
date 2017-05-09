package pl.tetris.plane;

import org.junit.Before;
import org.junit.Test;
import pl.tetris.blocks.*;
import pl.tetris.game.EndGameException;
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

    @Test
    public void checkMoves() throws Exception {
        squarePlane[2][4] = expectedColor;
        squarePlane[2][5] = expectedColor;
        squarePlane[3][4] = expectedColor;
        squarePlane[3][5] = expectedColor;

        squarePlane[1][5] = expectedColor2;
        squarePlane[1][6] = expectedColor2;
        squarePlane[2][6] = expectedColor2;
        squarePlane[2][7] = expectedColor2;

        Plane plane = new Plane(width, height);
        plane.addUser(user1);

        Block block1 = new SquareBlock(expectedColor, expectedSize);
        Block block2 = new SBlock(expectedColor2, 3);

        plane.addBlock(user1, block1);

        plane.gameStep();
        plane.gameStep();

        plane.addBlock(user1, block2);

        plane.moveBlock(user1, Direction.RIGHT);

        plane.gameStep();

        assertArrayEquals("Failed performing blocks pre colision for single user - arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void rotateBlockRight() throws Exception {
        squarePlane[0][4] = expectedColor;
        squarePlane[1][4] = expectedColor;
        squarePlane[1][3] = expectedColor;
        squarePlane[2][3] = expectedColor;

        Plane plane = new Plane(width, height);
        plane.addUser(user1);

        Block block = new SBlock(expectedColor, 3);

        plane.addBlock(user1, block);

        plane.rotateBlock(user1, Rotation.RIGHT);

        assertArrayEquals("Failed rotating block right for single user - arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void rotateBlockLeft() throws Exception {
        squarePlane[0][3] = expectedColor;
        squarePlane[0][4] = expectedColor;
        squarePlane[0][5] = expectedColor;
        squarePlane[0][6] = expectedColor;

        Plane plane = new Plane(width, height);
        plane.addUser(user1);

        Block block = new TowerBlock(expectedColor, expectedSize2);

        plane.addBlock(user1, block);

        plane.rotateBlock(user1, Rotation.LEFT);

        assertArrayEquals("Failed rotating block right for single user - arrays not the same", squarePlane, plane.getPlane());
    }

    //Tests for multiplayer
    @Test
    public void addBlockForMultiplayer() throws Exception {
        squarePlane[2][4] = expectedColor2;
        squarePlane[2][5] = expectedColor2;
        squarePlane[3][4] = expectedColor2;
        squarePlane[3][5] = expectedColor2;

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

        plane.moveBlock(user1, Direction.RIGHT);
        plane.moveBlock(user1, Direction.RIGHT);

        plane.addBlock(user2, block2);

        plane.gameStep();
        plane.gameStep();

        assertArrayEquals("Failed performing game step for multiple users - arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void moveBlockForMultiplayer() throws Exception {
        squarePlane[2][2] = expectedColor2;
        squarePlane[2][3] = expectedColor2;
        squarePlane[3][2] = expectedColor2;
        squarePlane[3][3] = expectedColor2;

        squarePlane[2][6] = expectedColor;
        squarePlane[2][7] = expectedColor;
        squarePlane[3][6] = expectedColor;
        squarePlane[3][7] = expectedColor;

        Plane plane = new Plane(width, height);
        plane.setBlocksFactory(new BlankBlockFactory());
        plane.addUser(user1);
        plane.addUser(user2);

        Block block1 = new SquareBlock(expectedColor, expectedSize);
        Block block2 = new SquareBlock(expectedColor2, expectedSize);

        plane.addBlock(user1, block1);

        plane.moveBlock(user1, Direction.RIGHT);
        plane.moveBlock(user1, Direction.RIGHT);

        plane.addBlock(user2, block2);

        plane.moveBlock(user2, Direction.LEFT);
        plane.moveBlock(user2, Direction.LEFT);

        plane.gameStep();
        plane.gameStep();

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

        plane.moveBlock(user1, Direction.LEFT);
        plane.moveBlock(user1, Direction.LEFT);

        plane.addBlock(user2, block2);

        plane.gameStep();
        plane.gameStep();

        plane.moveBlock(user1, Direction.RIGHT);
        plane.moveBlock(user2, Direction.LEFT);

        assertArrayEquals("Failed moving blocks for multiple users - arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void gameStepForMultiplayer() throws Exception {
        squarePlane[2][4] = expectedColor;
        squarePlane[2][5] = expectedColor;
        squarePlane[3][4] = expectedColor;
        squarePlane[3][5] = expectedColor;

        squarePlane[2][2] = expectedColor2;
        squarePlane[3][2] = expectedColor2;
        squarePlane[4][2] = expectedColor2;
        squarePlane[5][2] = expectedColor2;

        Plane plane = new Plane(width, height);
        plane.addUser(user1);
        plane.addUser(user2);

        Block block1 = new SquareBlock(expectedColor, expectedSize);
        Block block2 = new TowerBlock(expectedColor2, expectedSize2);

        plane.addBlock(user1, block2);
        plane.moveBlock(user1, Direction.LEFT);
        plane.moveBlock(user1, Direction.LEFT);
        plane.moveBlock(user1, Direction.LEFT);

        plane.addBlock(user2, block1);

        plane.gameStep();
        plane.gameStep();

        assertArrayEquals("Failed performing game step for multiple users - arrays not the same", squarePlane, plane.getPlane());
    }

    @Test
    public void cleaningLines() throws Exception {
        squarePlane[28][2] = expectedColor;
        squarePlane[29][2] = expectedColor;

        Plane plane = new Plane(width, height);
        plane.setBlocksFactory(new BlankBlockFactory());
        plane.addUser(user1);

        Block block1 = new LBlock(expectedColor);
        Block block3 = new TowerBlock(expectedColor2, 7);

        plane.addBlock(user1, block1);

        for(int i=0; i < height-3; i++)
            plane.moveBlock(user1, Direction.DOWN);

        plane.moveBlock(user1, Direction.LEFT);
        plane.moveBlock(user1, Direction.LEFT);
        plane.moveBlock(user1, Direction.LEFT);
        plane.moveBlock(user1, Direction.LEFT);

        plane.addBlock(user1, block3);
        plane.moveBlock(user1,Direction.RIGHT);
        plane.rotateBlock(user1, Rotation.RIGHT);

        for(int i=0; i < height; i++)
            plane.moveBlock(user1, Direction.DOWN);

        int points = user1.getPoints();

        assertArrayEquals("Failed performing lines cleaning/scoring points - arrays not the same", squarePlane, plane.getPlane());

        assertEquals(1, points);
    }

    @Test
    public void cleaningMultipleLines() throws Exception {

        Plane plane = new Plane(width, height);
        plane.setBlocksFactory(new BlankBlockFactory());
        plane.addUser(user1);

        Block block1 = new SquareBlock(expectedColor);
        Block block2 = new SquareBlock(expectedColor2);
        Block block3 = new SquareBlock(expectedColor);
        Block block4 = new SquareBlock(expectedColor2);
        Block block5 = new SquareBlock(expectedColor);

        plane.addBlock(user1, block1);
        plane.moveBlock(user1,Direction.LEFT);
        plane.moveBlock(user1,Direction.LEFT);
        plane.moveBlock(user1,Direction.LEFT);
        plane.moveBlock(user1,Direction.LEFT);

        for(int i=0; i < height - 2; i++)
            plane.moveBlock(user1, Direction.DOWN);

        plane.addBlock(user1, block2);
        plane.moveBlock(user1,Direction.LEFT);
        plane.moveBlock(user1,Direction.LEFT);

        for(int i=0; i < height - 2; i++)
            plane.moveBlock(user1, Direction.DOWN);

        plane.addBlock(user1, block3);
        for(int i=0; i < height - 2; i++)
            plane.moveBlock(user1, Direction.DOWN);

        plane.addBlock(user1, block4);
        plane.moveBlock(user1, Direction.RIGHT);
        plane.moveBlock(user1, Direction.RIGHT);
        for(int i=0; i < height - 2; i++)
            plane.moveBlock(user1, Direction.DOWN);

        plane.addBlock(user1, block5);
        plane.moveBlock(user1, Direction.RIGHT);
        plane.moveBlock(user1, Direction.RIGHT);
        plane.moveBlock(user1, Direction.RIGHT);
        plane.moveBlock(user1, Direction.RIGHT);

        for(int i=0; i < height - 1; i++)
            plane.moveBlock(user1, Direction.DOWN);

        int points = user1.getPoints();

        assertArrayEquals("Failed performing lines cleaning/scoring points - arrays not the same", squarePlane, plane.getPlane());

        assertEquals(2, points);
    }

    @Test(expected = EndGameException.class)
    public void newBlockEventSinglePlayer() throws Exception {
        Plane plane = new Plane(width, height);
        plane.setBlocksFactory(new SquareBlockFactory());
        plane.addUser(user1);

        for(int i=0; i < 490; i++) {
            plane.gameStep();
        }



    }
}