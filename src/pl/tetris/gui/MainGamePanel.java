package pl.tetris.gui;

import pl.tetris.game.TGame;

import javax.swing.*;
import java.awt.*;
import pl.tetris.game.TGame;

/**
 * Created by Łukasz on 15.05.2017.
 */
public class MainGamePanel extends JPanel{

    private TetrisPanel tetrisPanel;
    private HeadPanel headPanel;
    private TGame tGame;

    public MainGamePanel(TGame tGame, RootPanel rootPanel){
        super(new BorderLayout());
        this.tGame = tGame;
        tetrisPanel = new TetrisPanel(tGame.getPlaneSize()[0],tGame.getPlaneSize()[1]);
        tetrisPanel.setPlane(tGame.getPlane());

        headPanel = new HeadPanel(tGame);

        add(tetrisPanel, BorderLayout.CENTER);
        add(headPanel, BorderLayout.PAGE_START);


        new GuiUpdator(tetrisPanel, headPanel, tGame, rootPanel);
        this.tGame.start();
    }
}