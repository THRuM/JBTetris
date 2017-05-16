package pl.tetris.gui;

import pl.tetris.blocks.Square;
import pl.tetris.plane.Plane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;

/**
 * Created by Tomasz on 05.05.2017.
 */
public class TetrisPanel extends JPanel {
    private int width;
    private int height;
    private JButton squares[][];
    private Plane plane;

    public TetrisPanel(int width, int height){
        super(new GridLayout(height, width));
        super.setMinimumSize(new Dimension(400,600));
        this.width = width;
        this.height = height;
        squares = new JButton[height][width];
        fillSquares();
    }

    public void setPlane(Plane plane){
        this.plane = plane;
    }

    public void refillSquares(){
        Square squareArray[][] = plane.getPlane();
        Square singleSqare;
        System.out.println("Wątek TetrisPanel");
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                singleSqare = squareArray[i][j];
                squares[i][j].setBackground(Square.squareToColor(singleSqare));
                if(singleSqare != Square.BLANK)
                    squares[i][j].setBorder(new SoftBevelBorder(1));
            }
        }
    }

    private void fillSquares(){
        int squareHeight = super.getHeight()/height;
        int squareWidth = super.getWidth()/width;

        for(int i=0; i < squares.length; i++){
            for(int j=0; j < squares[i].length; j++){
                JButton button = new JButton();
                button.setBackground(Square.squareToColor(Square.BLANK));
                button.setSize(squareWidth, squareHeight);
                button.setMargin(new Insets(0,0,0,0));
                button.setEnabled(false);
                button.setBorder(new SoftBevelBorder(1));

                squares[i][j] = button;
                super.add(button);
            }
        }
    }
}