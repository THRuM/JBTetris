
package pl.tetris.gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import pl.tetris.game.TGame;
import pl.tetris.gui.TFrame;

/**
 * Created by Tomasz on 29.05.2017.
 */
public class MenuGui extends JPanel implements ActionListener {
    private JButton singlePlayerButton;
    private JButton multiplayerButton;
    private JButton quitButton;


    public MenuGui()

    {
        singlePlayerButton = new JButton("SinglePlayer");
        multiplayerButton = new JButton("Mutliplayer");
        quitButton = new JButton("Quit");

        singlePlayerButton.addActionListener(this);
        multiplayerButton.addActionListener(this);
        quitButton.addActionListener(this);



    }
    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();

        if (source == singlePlayerButton){
            TGame game = new TGame(1);

        }

        else if(source == multiplayerButton){
            TGame game = new TGame(2);
        }
        else if(source == quitButton){
            System.exit(0);
        }
    }
}

