package pl.tetris.gui;

import pl.tetris.game.TGame;
import pl.tetris.users.User;

import javax.swing.*;
import java.awt.*;

public class HeadPanel extends JPanel {

    private JButton[] scoresButton;
    private User[] users;
    private TGame tGame;

    public HeadPanel(TGame tGame){
        super(new GridLayout(1,2));
        super.setSize(new Dimension(400,100));
        this.tGame = tGame;

        if(tGame.getGameMode() == 1 || tGame.getGameMode() == 2){
            if(tGame.getGameMode() == 1){
                scoresButton = new JButton[1];
                users = new User[1];
            }else {
                scoresButton = new JButton[2];
                users = new User[2];
            }
            scoresButton[0] = new JButton();
            scoresButton[0].setEnabled(false);
            users[0] = tGame.getUser1();
            scoresButton[0].setText(users[0].getName() + ": 0");
            super.add(scoresButton[0]);
        }

        if(tGame.getGameMode() == 2){
            scoresButton[1] = new JButton();
            scoresButton[1].setEnabled(false);
            users[1] = tGame.getUser2();
            scoresButton[1].setText(users[1].getName() + ": 0");
            super.add(scoresButton[1]);
        }
    }

    public void updateScore(){
        for(int i=0; i < scoresButton.length; i++){
            scoresButton[i].setText(users[i].getName() + " : " + users[i].getPoints());
        }
    }
}