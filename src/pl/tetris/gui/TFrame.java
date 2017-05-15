package pl.tetris.gui;

/**
 * Created by Łukasz on 09.05.2017.
 */
import pl.tetris.game.TGame;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class TFrame extends JFrame implements KeyListener {

        int keyCode = 0;

        TGame tGame;

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
            keyCode = e.getKeyCode();
            }

    public void settGame(TGame tGame) {
        this.tGame = tGame;
    }
}




