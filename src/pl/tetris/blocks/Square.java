package pl.tetris.blocks;

import java.awt.*;

/* Możliwe stany planszy */
public enum Square {
    BLANK, GREEN, RED, BLUE;

    public static Color squareToColor(Square square){
        switch (square){
            case RED: return new Color(255,0,0);
            case BLUE: return new Color(17,0,255);
            case GREEN: return new Color(24,178,75);
            case BLANK: return new Color(255,255,255);
        }

        return new Color(255,255,255);
    }
}