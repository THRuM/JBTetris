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

    public Square[][] getPlane(){
        return tetrisArray;
    }

    public String toString(){
        String rep = "";

        for(Square row[] : tetrisArray) {
            for (Square element : row) {
                rep += element;
            }
            rep += '\n';
        }

        return rep;
    }
}
