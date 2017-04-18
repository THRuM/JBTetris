package pl.tetris.plane;

import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;
import pl.tetris.users.User;

import java.util.LinkedHashMap;
import java.util.List;
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
        List<Integer> fullLines;
        int newCoordinates[];

        switch (direction) {
            case DOWN:
                newCoordinates = collisionDetection.checkCoordinates(block, 0, 1);
                break;
            case LEFT:
                newCoordinates = collisionDetection.checkCoordinates(block, -1, 0);
                break;
            case RIGHT:
                newCoordinates = collisionDetection.checkCoordinates(block, 1, 0);
                break;
            default:
                newCoordinates = collisionDetection.checkCoordinates(block, 0, 0);
        }

        if(newCoordinates[1] < 0){
            //Poza mapą
            block = null;
            fullLines = collisionDetection.fullLines();
            int points = cleanLines(fullLines);
            user.addPoints(points);
        }else {

            cleanBlock(block);

            if (collisionDetection.checkSquares(block, newCoordinates[0], newCoordinates[1])) {
                block.setX(newCoordinates[0]);
                block.setY(newCoordinates[1]);

                drawBlock(block);

            } else if (direction == Direction.DOWN) {
                drawBlock(block);
                block = null;
                fullLines = collisionDetection.fullLines();
                int points = cleanLines(fullLines);
                user.addPoints(points);
            } else {
                drawBlock(block);
            }

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

    private int cleanLines(List<Integer> listOfLinesToClean) {
        int points = listOfLinesToClean.size();

        for(int i=0; i < squaresArray.length; i++)
            if(listOfLinesToClean.contains(i))
                removeLine(i);

        return points;
    }

    private void removeLine(int lineNumber){
        Square blankLine[] = new Square[squaresArray[0].length];
        for(int i=0; i < blankLine.length; i++)
            blankLine[i] = Square.BLANK;

        for(int i = lineNumber; i >= 1; i--)
           squaresArray[i] = squaresArray[i-1];

        //System.arraycopy(squaresArray, lineNumber-1, squaresArray, lineNumber, lineNumber);

        squaresArray[0] = blankLine;
    }

    private void cleanBlock(Block block){
        Square shape[][] = block.getShape();

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++)
                if(shape[i][j] != Square.BLANK)
                    squaresArray[block.getY() + i][block.getX() + j] = Square.BLANK;
    }

    private void drawBlock(Block block){
        Square shape[][] = block.getShape();

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++)
                if(shape[i][j] != Square.BLANK)
                    squaresArray[block.getY() + i][block.getX() + j] = shape[i][j];
    }
}
