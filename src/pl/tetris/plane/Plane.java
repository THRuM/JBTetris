package pl.tetris.plane;

import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;
import pl.tetris.users.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class Plane {
    private Square squaresArray[][];
    private Map<User, Block> blocks;
    private CollisionDetection collisionDetection;

    public Plane(int width, int height){
        squaresArray = new Square[height][width];
        blocks = new LinkedHashMap<>();
        this.collisionDetection = new SimpleCollisionDetection(squaresArray);

        for(int i = 0; i < squaresArray.length; i++)
            for(int j = 0; j < squaresArray[i].length; j++){
                squaresArray[i][j] = Square.BLANK;
            }
    }

    Square[][] getPlane(){
        return squaresArray;
    }

    public void addUser(User user){
        blocks.put(user, null);
    }

    public void addBlock(User user, Block block){
        blocks.put(user, block);
        block.setX((squaresArray[0].length / 2) - (block.getShape()[0].length / 2));
        block.setY(0);
        moveBlock(user, Direction.NONE);
    }

    public void moveBlock(User user, Direction direction){
        Block block = blocks.get(user);
        int newCoordinates[];

        switch (direction) {
            case DOWN:
                newCoordinates = checkCoordinates(block, 0, 1);
                break;
            case LEFT:
                newCoordinates = checkCoordinates(block, -1, 0);
                break;
            case RIGHT:
                newCoordinates = checkCoordinates(block, 1, 0);
                break;
            default:
                newCoordinates = checkCoordinates(block, 0, 0);
        }

        cleanBlock(block);

        if(collisionDetection.checkSquares(block, newCoordinates[0], newCoordinates[1])){
            block.setX(newCoordinates[0]);
            block.setY(newCoordinates[1]);

            drawBlock(block);
        }else if(direction == Direction.DOWN) {
            drawBlock(block);
            block = null;
        }else {
            drawBlock(block);
        }
    }

    public void gameStep(){
        for(User user : blocks.keySet())
            moveBlock(user, Direction.DOWN);
    }

    public void rotateBlock(User user, Rotation rotation){
        Block block = blocks.get(user);

        cleanBlock(block);

        if(rotation == Rotation.RIGHT)
            block.rotateRight();
        else
            block.rotateLeft();

        if(collisionDetection.checkSquares(block, block.getX() - block.getShape()[0].length/2, block.getY())) {
            block.setX(block.getX() - block.getShape()[0].length / 2);
            drawBlock(block);
        } else{
            block.revertMove();
            drawBlock(block);
        }

    }

    public String toString(){
        StringBuilder rep = new StringBuilder();
        for(Square row[] : squaresArray) {
            for (Square element : row) {
                rep.append(element);
                rep.append(' ');
            }
            rep.append('\n');
        }
        return rep.toString();
    }

    private void cleanBlock(Block block){
        Square shape[][] = block.getShape();

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++)
                if(shape[i][j] != Square.BLANK)
                    squaresArray[block.getY() + i][block.getX() + j] = Square.BLANK;
    }

    private int[] checkCoordinates(Block block, int x, int y){
        int newCoordinates[] = new int[2];
        newCoordinates[0] = block.getX() + x;
        newCoordinates[1] = block.getY() + y;

        if(newCoordinates[0] < 0)
            newCoordinates[0] = 0;
        else if(newCoordinates[0] > squaresArray[0].length - block.getShape()[0].length)
            newCoordinates[0] = squaresArray[0].length - block.getShape()[0].length;

        if(newCoordinates[1] < 0)
            newCoordinates[1] = 0;
        else if(newCoordinates[1] > squaresArray.length - block.getShape().length)
            newCoordinates[1] = squaresArray.length - block.getShape().length;

        return newCoordinates;
    }

    private void drawBlock(Block block){
        Square shape[][] = block.getShape();

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++)
                if(shape[i][j] != Square.BLANK)
                    squaresArray[block.getY() + i][block.getX() + j] = shape[i][j];
    }
}
