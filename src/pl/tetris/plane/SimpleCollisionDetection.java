package pl.tetris.plane;

import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;

import java.util.ArrayList;
import java.util.List;

public class SimpleCollisionDetection implements CollisionDetection {

    private Square squaresArray[][];

    public SimpleCollisionDetection(Square array[][]){
        this.squaresArray = array;
    }

    @Override
    public boolean checkSquares(Block block, int x, int y) {
        boolean movePossible = true;
        Square shape[][] = block.getShape();

        for (int i = 0; i < shape.length; i++)
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != Square.BLANK)
                    if (squaresArray[y + i][x + j] != Square.BLANK) {
                        movePossible = false;
                        break;
                    }
            }

        return movePossible;
    }

    @Override
    public int[] checkCoordinates(Block block, int x, int y){
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
            //newCoordinates[1] = squaresArray.length - block.getShape().length;
            newCoordinates[1] = -1;

        return newCoordinates;
    }

    @Override
    public List<Integer> fullLines() {
        List<Integer> fullLines = new ArrayList<>(1);

        boolean lineFull = true;

        for(int i = 0; i < squaresArray.length; i++) {
            for (int j = 0; j < squaresArray[i].length; j++) {
                if (squaresArray[i][j] == Square.BLANK)
                    lineFull = false;
            }
            if(lineFull)
                fullLines.add(i);
            lineFull = true;
        }

        return fullLines;
    }
}
