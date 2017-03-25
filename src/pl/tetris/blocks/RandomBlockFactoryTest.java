package pl.tetris.blocks;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RandomBlockFactoryTest {

    private List<Class> expectedList;

    @Before
    public void setUp() throws Exception {
        expectedList = new ArrayList<>();
    }

    @Test
    public void getBlock() throws Exception {
        expectedList.add(LBlock.class);
        expectedList.add(LBlock.class);
        expectedList.add(SBlock.class);
        expectedList.add(SBlock.class);
        expectedList.add(SquareBlock.class);
        expectedList.add(SquareBlock.class);
        expectedList.add(TowerBlock.class);
        expectedList.add(TowerBlock.class);

        BlocksFactory blocksFactory = new RandomBlockFactory();

        assertArrayEquals("Failed to setup block factory - lists not the same", expectedList.toArray(), blocksFactory.getListOfBlocks().toArray());
    }

    @Test
    public void getBlockCustomSetup() throws Exception {
        expectedList.add(LBlock.class);
        expectedList.add(LBlock.class);
        expectedList.add(SBlock.class);
        expectedList.add(SBlock.class);
        expectedList.add(SBlock.class);
        expectedList.add(SquareBlock.class);
        expectedList.add(SquareBlock.class);
        expectedList.add(TowerBlock.class);

        Map<Class, Double> weightMap = new LinkedHashMap<>();
        weightMap.put(LBlock.class, 0.2);
        weightMap.put(SBlock.class, 0.4);
        weightMap.put(SquareBlock.class, 0.3);
        weightMap.put(TowerBlock.class, 0.1);

        BlocksFactory blocksFactory = new RandomBlockFactory(weightMap);

        assertArrayEquals("Failed to setup block factory - lists not the same", expectedList.toArray(), blocksFactory.getListOfBlocks().toArray());
    }

}