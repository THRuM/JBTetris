package pl.tetris;

import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;
import pl.tetris.blocks.SquareBlock;
import pl.tetris.gui2.TFrame;
import pl.tetris.gui2.TetrisPanel;
import pl.tetris.plane.Plane;

import javax.swing.*;
import java.awt.*;

public class Main {



    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TFrame();
            }
        });
    }
}

