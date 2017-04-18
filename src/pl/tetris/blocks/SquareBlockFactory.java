package pl.tetris.blocks;

import java.util.List;

public class SquareBlockFactory implements BlocksFactory {
    @Override
    public Block getBlock() {
        return new SquareBlock(Square.GREEN);
    }

    @Override
    public List<Class> getListOfBlocks() {
        return null;
    }
}
