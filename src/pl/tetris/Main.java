package pl.tetris;

import pl.tetris.blocks.Block;
import pl.tetris.blocks.Square;
import pl.tetris.blocks.SquareBlock;
import pl.tetris.gui2.TetrisPanel;
import pl.tetris.plane.Plane;

import javax.swing.*;

public class Main {



    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new TetrisPanel(30,70));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
