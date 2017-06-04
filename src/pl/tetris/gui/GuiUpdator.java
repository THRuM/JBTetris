package pl.tetris.gui;


import pl.tetris.game.TGame;

public class GuiUpdator extends Thread {

    private TetrisPanel tetrisPanel;
    private HeadPanel headPanel;
    private TGame tGame;
    private RootPanel rootPanel;

    public GuiUpdator(TetrisPanel tetrisPanel, HeadPanel headPanel, TGame tGame, RootPanel rootPanel) {
        super("GuiUpdator");
        this.tetrisPanel = tetrisPanel;
        this.headPanel = headPanel;
        this.tGame = tGame;
        this.rootPanel = rootPanel;
        start();
    }

    @Override
    public void run() {
        do{
            tetrisPanel.refillSquares();
            headPanel.updateScore();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (tGame.isGameRunning());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rootPanel.changePanel("MENU");
    }
}