package pl.tetris.plane;

import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;
import pl.tetris.users.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class Plane {
    private Square squaresArray[][];
    private Map<User, Block> blocks;

    public Plane(int width, int height){
        squaresArray = new Square[height][width];
        blocks = new LinkedHashMap<>();

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

        if(direction == Direction.DOWN)
            newCoordinates = checkCoordinates(block, 0, 1);
        else if(direction == Direction.LEFT)
            newCoordinates = checkCoordinates(block, -1, 0);
        else if(direction == Direction.RIGHT)
            newCoordinates = checkCoordinates(block, 1, 0);
        else
            newCoordinates = checkCoordinates(block, 0, 0);

        if(checkSquares(block, newCoordinates[0], newCoordinates[1])){
            block.setX(newCoordinates[0]);
            block.setY(newCoordinates[1]);

            drawBlock(block);
        }
    }

    public void gameStep(){
        Block block;
        for(User user : blocks.keySet()) {
            block = blocks.get(user);
            if(block != null) {
                int newCoordinates[] = checkCoordinates(block, 0, 1);

                if (checkSquares(block, newCoordinates[0], newCoordinates[1])) {
                    block.setX(newCoordinates[0]);
                    block.setY(newCoordinates[1]);

                    drawBlock(block);
                }
            }
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

    private boolean checkSquares(Block block, int x, int y){
        boolean movePossible = true;
        Square shape[][] = block.getShape();

        cleanBlock(block);

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++){
                if(shape[i][j] != Square.BLANK)
                    if(squaresArray[y + i][x + j] != Square.BLANK){
                        movePossible = false;
                        break;
                    }
            }

        if(!movePossible)
            drawBlock(block);

        return movePossible;
    }

    private void drawBlock(Block block){
        Square shape[][] = block.getShape();

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++)
                squaresArray[block.getY() + i][block.getX() + j] = shape[i][j];
    }
}
