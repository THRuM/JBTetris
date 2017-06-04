package pl.tetris.gui;

import pl.tetris.game.TGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel  {

    private JButton singleplayer;
    private JButton multiplayer;
    private JButton quit;
    private TGame tGame;
    private RootPanel rootPanel;


    public Menu(RootPanel rootPanel){
        super(new GridBagLayout());

        this.rootPanel = rootPanel;

        singleplayer = new JButton("Single Player");
        multiplayer = new JButton("MultiPlayer");
        quit = new JButton("Quit");

        super.add(singleplayer);
        super.add(multiplayer);
        super.add(quit);


        singleplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tGame = new TGame(1);
                rootPanel.gettFrame().settGame(tGame);
                rootPanel.addGamePanel(new MainGamePanel(tGame, rootPanel));
                rootPanel.changePanel("GAME");
            }
        });

        multiplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tGame = new TGame(2);
                rootPanel.gettFrame().settGame(tGame);
                rootPanel.addGamePanel(new MainGamePanel(tGame, rootPanel));
                rootPanel.changePanel("GAME");
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }




}