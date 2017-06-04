package pl.tetris.gui;


import javax.swing.*;
import java.awt.*;

public class RootPanel extends JPanel{
    private CardLayout cardLayout = new CardLayout();
    private Menu menu = new Menu(this);
    private MainGamePanel mainGamePanel;
    private TFrame tFrame;

    public RootPanel(){
        super();
        setLayout(cardLayout);

        add(menu, "MENU");
        cardLayout.show(this,"MENU");
    }

    public void addGamePanel(MainGamePanel mainGamePanel){
        this.mainGamePanel = mainGamePanel;
        add(mainGamePanel, "GAME");
    }

    public void changePanel(String panel){
        cardLayout.show(this, panel);
        tFrame.requestFocus();
    }

    public void settFrame(TFrame tFrame) {
        this.tFrame = tFrame;
    }

    public TFrame gettFrame() {
        return tFrame;
    }
}