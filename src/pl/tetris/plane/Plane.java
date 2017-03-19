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

    private int[] checkCoords(int horizontal, int vertical) {
        int calcCoords[] = new int[2];
        System.arraycopy(coords, 0, calcCoords, 0, coords.length);

        calcCoords[0] += horizontal;
        calcCoords[1] += vertical;

        if(calcCoords[0] < 0)
            calcCoords[0] = 0;
        else if(calcCoords[0] > tetrisArray[0].length - block.getShape()[0].length)
            calcCoords[0] = tetrisArray[0].length - block.getShape()[0].length;

        if(calcCoords[1] < 0)
            calcCoords[1] = 0;
        else if(calcCoords[1] > tetrisArray.length - block.getShape().length)
            calcCoords[1] = tetrisArray.length - block.getShape().length;

        return calcCoords;
    }

    private boolean checkSquares(int x, int y) {
        boolean movePossible = true;
        Square shape[][] = block.getShape();

        cleanBlock(tetrisArray);

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++){
                if(shape[i][j] != Square.BLANK)
                    if(tetrisArray[y + i][x + j] != Square.BLANK) {
                        movePossible = false;
                        break;
                    }
            }

        if(!movePossible)
            drawBlock();

        return movePossible;
    }

    private void drawBlock() {
        Square shape[][] = block.getShape();

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++)
                if(shape[i][j] != Square.BLANK)
                    tetrisArray[coords[1] + i][coords[0] + j] = shape[i][j];
    }

    public void moveBlock(int horizontal, int vertical) {

        int newCoords[] = checkCoords(horizontal, vertical);

        if(checkSquares(newCoords[0], newCoords[1])) {
            coords[0] = newCoords[0];
            coords[1] = newCoords[1];

            drawBlock();
        }
    }

    private void cleanBlock(Square array[][]) {
        Square shape[][] = block.getShape();

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++)
                if(shape[i][j] != Square.BLANK)
                    array[coords[1] + i][coords[0] + j] = Square.BLANK;
    }

    public void addBlock(Block block) {
        Square shape[][] = block.getShape();
        this.block = block;
        coords[0] = (tetrisArray[0].length / 2) - (shape[0].length / 2);
        coords[1] = 0;

        /*
        for(int i=0; i < shape.length; i++)
            System.arraycopy(shape[i], 0, tetrisArray[i], coords[0], shape[i].length);
            */
        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++)
                if(shape[i][j] != Square.BLANK){
                    tetrisArray[coords[1] + i][coords[0] + j] = shape[i][j];
                }

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
