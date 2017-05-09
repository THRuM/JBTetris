package pl.tetris.blocks;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface BlocksFactory {
    Block getBlock();
    List<Class> getListOfBlocks();
}
