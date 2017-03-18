package pl.tetris.blocks;

public class SBlock implements Block {

    private Square shape[][];
    private Square backupShape[][];

    public SBlock(Square color, int weight) {
        shape = new Square[2][weight];
        backupShape = null;

        for(int i = 0; i < shape[0].length/2; i++)
            shape[0][i] = color;
        for(int i = shape[1].length/2; i < shape[1].length; i++)
            shape[1][i] = color;
    }

    @Override
    public Square[][] getShape() {
        return shape;
    }

    @Override
    public void rotateRight() {
        backupShape = shape;
        shape = new Square[shape[0].length][shape.length];

        for(int i=0, k = shape[0].length - 1; i < backupShape.length; i++, k--)
            for(int j=0; j < backupShape[i].length; j++){
                shape[j][k] = backupShape[i][j];
            }
    }

    @Override
    public void rotateLeft() {
        backupShape = shape;
        shape = new Square[shape[0].length][shape.length];

        for(int i=0, k=0; i < backupShape.length; i++, k++)
            for(int j=backupShape[i].length - 1, l = 0; j >= 0; j--, l++)
                shape[l][k] = backupShape[i][j];
    }

    @Override
    public void revertMove() {
        if(backupShape != null)
            shape = backupShape;
    }
}
