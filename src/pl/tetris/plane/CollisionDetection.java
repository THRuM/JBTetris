package pl.tetris.plane;


import pl.tetris.blocks.Block;

import java.util.Collection;
import java.util.List;

public interface CollisionDetection {
    int checkSquares(Block block, int x, int y);
    List<Integer> fullLines();
    int[] checkCoordinates(Block block, int x, int y);
    boolean isBlockOnPlane(Block block);

    void setBlocks(Collection<Block> blocks);
}
