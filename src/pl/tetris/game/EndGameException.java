package pl.tetris.game;

public class EndGameException extends Exception {

    @Override
    public String getMessage() {
        return "Game End!";
    }
}
