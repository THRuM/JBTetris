package pl.tetris.gui2;

/**
 * Created by Łukasz on 09.05.2017.
 */
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
public class TFrame extends JFrame {

        public TFrame() {
            super("KeyListener Test");

            setPreferredSize(new Dimension(400, 600));

            setContentPane(new TetrisPanel(30,70));
            pack();
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    }

