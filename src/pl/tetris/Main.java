package pl.tetris;

import pl.tetris.game.TGame;
import pl.tetris.gui.TFrame;

import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {

        TGame game = new TGame(2);



        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TFrame(game);
            }
        });
    }
}