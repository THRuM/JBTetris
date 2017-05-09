package pl.tetris.gui2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Tomasz on 05.05.2017.
 */
public class TetrisPanel extends JPanel implements KeyListener{
    private int width;
    private int height;
    private JButton squares[][];

    public TetrisPanel(int width, int height){
        super(new GridLayout(height, width));
        super.setMinimumSize(new Dimension(400,600));
        this.width = width;
        this.height = height;
        squares = new JButton[height][width];

        fillSquares();
    }

    private void fillSquares(){
        int squareHeight = super.getHeight()/height;
        int squareWidth = super.getWidth()/width;

        for(int i=0; i < squares.length; i++){
            for(int j=0; j < squares[i].length; j++){
                JButton button = new JButton();
                button.setBackground(new Color(255,255,255));
                button.setSize(squareWidth, squareHeight);
                button.setMargin(new Insets(0,0,0,0));
                button.setEnabled(false);

                squares[i][j] = button;
                super.add(button);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }
}
