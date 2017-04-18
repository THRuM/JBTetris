package pl.tetris.plane;


import pl.tetris.blocks.Block;

import java.util.List;

public interface CollisionDetection {
    boolean checkSquares(Block block, int x, int y);
    List<Integer> fullLines();
    int[] checkCoordinates(Block block, int x, int y);
}
