package pl.tetris.plane;

import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleCollisionDetection implements CollisionDetection {

    private Square squaresArray[][];
    private Collection<Block> blocks;

    @Override
    public void setBlocks(Collection<Block> blocks) {
        this.blocks = blocks;
    }

    public SimpleCollisionDetection(Square array[][]){
        this.squaresArray = array;
    }

    @Override
    public synchronized int checkSquares(Block block, int x, int y) {
        //0 - False, 1 - True, 2 - FalseLock
        int movePossible = 1;
        Square shape[][] = block.getShape();

        if(!isBlockOnPlane(block))
            return 0;

        for (int i = 0; i < shape.length; i++)
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != Square.BLANK)
                    if (squaresArray[y + i][x + j] != Square.BLANK) {
                        if(isPointOnActiveBlock(block,(y+i),(x+j))){
                            movePossible = 2;
                            break;
                        } else {
                            movePossible = 0;
                            break;
                        }
                    }
            }

        return movePossible;
    }

    private synchronized boolean isPointOnActiveBlock(Block localBlock, int x, int y){
        for(Block block : blocks){
            if(block != localBlock)
                if(block.isPointInBlock(x, y)){
                    return true;
                }
        }
        return false;
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
    public synchronized List<Integer> fullLines() {
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

    @Override
    public synchronized boolean isBlockOnPlane(Block block){
        boolean blockOnPlane = true;
        Square shape[][] = block.getShape();


        for(int i=0; i<shape.length; i++)
            for(int j=0; j < shape[i].length; j++){
                if((block.getY()+i) > (squaresArray.length-1))
                    blockOnPlane = false;

                if((block.getX() + j) > (squaresArray[0].length-1))
                    blockOnPlane = false;
            }

        return blockOnPlane;
    }
}
