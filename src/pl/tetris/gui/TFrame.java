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

    TGame tGame;

    public TFrame(TGame tGame) {
        super("Tetris");
        this.tGame = tGame;
        MainGamePanel mainGamePanel = new MainGamePanel(tGame);

        setPreferredSize(new Dimension(400, 700));
        setContentPane(mainGamePanel);
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
        tGame.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}




