package pl.tetris.blocks;

public class TowerBlock implements Block {

    private Square shape[][];
    private Square backupShape[][];
    private int x;
    private int y;

    public TowerBlock(Square color){
        this(color, 4);
    }

    public TowerBlock(Square color, int height) {
        shape = new Square[height][1];
        backupShape = null;

        for(int i = 0; i < shape.length; i++)
            for(int j = 0; j < shape[i].length; j++)
                shape[i][j] = color;
    }

    @Override
    public Square[][] getShape() {
        return shape;
    }

    @Override
    public void rotateRight() {
        backupShape = shape;
        shape = new Square[backupShape[0].length][backupShape.length];

        for(int i = 0; i < backupShape.length; i++)
            for(int j = 0; j < backupShape[i].length; j++){
                shape[j][i] = backupShape[i][j];
            }
    }

    @Override
    public void rotateLeft() {
        rotateRight();
    }

    @Override
    public void revertMove() {
        if(backupShape != null)
            shape = backupShape;
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
