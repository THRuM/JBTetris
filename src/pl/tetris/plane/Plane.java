package pl.tetris.plane;

import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;

public class Plane {
    private Square tetrisArray[][];
    private Block block;

    public Plane(int width, int height){
        tetrisArray = new Square[height][width];

        for(int i=0; i < tetrisArray.length; i++)
            for(int j=0; j < tetrisArray[i].length; j++){
                tetrisArray[i][j] = Square.BLANK;
            }
    }

    Square[][] getPlane(){
        return tetrisArray;
    }

    public void addBlock(Block block) {
        Square shapeToAdd[][] = block.getShape();
        this.block = block;

        int x = (tetrisArray[0].length / 2) - (shapeToAdd[0].length / 2);

        for(int i=0; i < shapeToAdd.length; i++){
            System.arraycopy(shapeToAdd[i], 0, tetrisArray[i], x, shapeToAdd[i].length);
        }
    }

    public String toString(){
        StringBuilder repB = new StringBuilder();
        for(Square row[] : tetrisArray) {
            for (Square element : row) {
                repB.append(element);
                repB.append(' ');
            }
            repB.append('\n');
        }

        return repB.toString();
    }
}
