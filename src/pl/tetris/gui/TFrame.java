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

    private TGame tGame;

    public TFrame() {
        super("Tetris");
        RootPanel rootPanel = new RootPanel();
        setContentPane(rootPanel);
        rootPanel.settFrame(this);
        setPreferredSize(new Dimension(400, 700));
        addKeyListener(this);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(tGame != null)
            tGame.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void settGame(TGame tGame) {
        this.tGame = tGame;
    }

}