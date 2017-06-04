package pl.tetris.gui;

import pl.tetris.game.TGame;
import pl.tetris.users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel  {


    private JButton singleplayer;
    private JButton multiplayer;
    private JButton quit;


    public Menu(TGame tGame){
        super(new GridBagLayout());





        singleplayer = new JButton("Single Player");
        multiplayer = new JButton("MultiPlayer");
        quit = new JButton("Quit");

        super.add(singleplayer);
        super.add(multiplayer);
        super.add(quit);


        singleplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TGame game = new TGame(1);
            }
        });

        multiplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TGame game = new TGame(2);
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