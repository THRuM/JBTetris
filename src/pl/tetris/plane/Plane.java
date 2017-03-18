package pl.tetris.plane;

import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;

public class Plane {
    private Square tetrisArray[][];
    private Block block;
    private int coords[];       //0 - x, 1 - y

    public Plane(int width, int height){
        tetrisArray = new Square[height][width];
        coords = new int[2];

        for(int i=0; i < tetrisArray.length; i++)
            for(int j=0; j < tetrisArray[i].length; j++){
                tetrisArray[i][j] = Square.BLANK;
            }
    }

    Square[][] getPlane(){
        return tetrisArray;
    }

    public void moveBlockDown() {
        //Clear previous blocks
        Square shape[][] = block.getShape();

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++)
                tetrisArray[coords[1] + i][coords[0] + j] = Square.BLANK;


        //Move block
        coords[1] += 1;
        for(int i=0; i < shape.length; i++)
            System.arraycopy(shape[i], 0, tetrisArray[coords[1] + i], coords[0], shape[i].length);

    }

    public void addBlock(Block block) {
        Square shapeToAdd[][] = block.getShape();
        this.block = block;
        coords[0] = (tetrisArray[0].length / 2) - (shapeToAdd[0].length / 2);
        coords[1] = 0;

        for(int i=0; i < shapeToAdd.length; i++)
            System.arraycopy(shapeToAdd[i], 0, tetrisArray[i], coords[0], shapeToAdd[i].length);

    }

    public String toString(){
        StringBuilder rep = new StringBuilder();
        for(Square row[] : tetrisArray) {
            for (Square element : row) {
                rep.append(element);
                rep.append(' ');
            }
            rep.append('\n');
        }
        return rep.toString();
    }
}
