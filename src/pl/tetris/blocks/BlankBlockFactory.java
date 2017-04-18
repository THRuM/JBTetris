package pl.tetris.blocks;

import java.util.List;

public class BlankBlockFactory implements BlocksFactory {
    @Override
    public Block getBlock() {
        return new SquareBlock(Square.BLANK);
    }

    @Override
    public List<Class> getListOfBlocks() {
        return null;
    }
}
