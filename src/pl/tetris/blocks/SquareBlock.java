package pl.tetris.blocks;

public class SquareBlock implements Block {

    private Square shape[][];
    private Square backupShape[][];

    public SquareBlock(Square color){
        shape = new Square[2][2];
        backupShape = null;

        for(int i=0; i < shape.length; i++)
            for(int j=0; j < shape[i].length; j++)
                shape[i][j] = Square.BLUE;
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
        if(backupShape != null)
            shape = backupShape;
    }
}
