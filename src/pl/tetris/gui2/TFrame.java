package pl.tetris.gui2;

/**
 * Created by Łukasz on 09.05.2017.
 */
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class TFrame extends JFrame implements KeyListener {

        public TFrame() {
            super("KeyListener Test");

            setPreferredSize(new Dimension(400, 600));
            setContentPane(new TetrisPanel(30,70));
            addKeyListener(this);
            pack();
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println(" you press: " + c);
    }

    }

