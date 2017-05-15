package pl.tetris.blocks;

public interface Block {
    //Zwraca reprezentacje tablicową klocka
    Square[][] getShape();
    //Obrót klocka w prawo
    void rotateRight();
    //Obrót klocka w lewo
    void rotateLeft();
    //Przywraca poprzedni stan klocka
    void revertMove();

    default boolean isPointInBlock(int y, int x) {
        int localX = x - this.getX();
        int localY = y - this.getY();

        Square shape[][] = this.getShape();

        if(localY >= shape.length || localY < 0)
            return false;

        if(localX >= shape[0].length || localX < 0)
            return false;

        if(shape[localY][localX] != Square.BLANK)
            return true;

        return false;
    }

    int getX();
    void setX(int x);
    int getY();
    void setY(int y);
}
