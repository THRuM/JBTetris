package pl.tetris.blocks;

public class SquareBlock implements Block {

    private Square shape[][];
    private int x;
    private int y;

    public SquareBlock(Square color, int size){
        shape = new Square[size][size];

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++)
                shape[i][j] = color;
    }

    @Override
    public Square[][] getShape() {
        return shape;
    }

    @Override
    public void rotateRight() {
        //Zbędne
    }

    @Override
    public void rotateLeft() {
        //Zbędne
    }

    @Override
    public void revertMove() {
        //Zbędne
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
