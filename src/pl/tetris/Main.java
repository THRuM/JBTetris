package pl.tetris;

import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;
import pl.tetris.blocks.SquareBlock;
import pl.tetris.plane.Plane;

public class Main {

    public static void main(String[] args) {

        Plane plane = new Plane(20, 50);
        Block bb = new SquareBlock(Square.BLUE, 2);

        plane.addBlock(bb);

        System.out.println(plane);
    }
}
