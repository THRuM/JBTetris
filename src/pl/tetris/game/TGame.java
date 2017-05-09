package pl.tetris.game;

import javafx.scene.input.KeyCode;
import pl.tetris.plane.Direction;
import pl.tetris.plane.Plane;
import pl.tetris.plane.Rotation;
import pl.tetris.users.User;

import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.Timer;
import java.awt.event.ActionListener;


public class TGame {/*
    User placeholder1 = new User(toString());
    User placeholder2 = new User(toString());


    public int level = 2000;

//    Timer GTime = new Timer(level, ActionListener.gameStep() );


    public static void StartGame{
        GTime.start();
        Plane GameBoard = new Plane(20, 70);

        for (int i = 0; i <1000 ; i++) {
            try{
                GameBoard.gameStep();
                }catch(EndGameException e){
                break;
                }

        }
    }

0*/

    public TGame(){
        keyToUser.put(KeyEvent.VK_UP, user1);
        keyToUser.put(KeyEvent.VK_DOWN, user1);
        keyToUser.put(KeyEvent.VK_RIGHT, user1);
        keyToUser.put(KeyEvent.VK_LEFT, user1);

        keyToDirection.put(KeyEvent.VK_UP, Direction.NONE );
        keyToDirection.put(KeyEvent.VK_DOWN, Direction.DOWN );
        keyToDirection.put(KeyEvent.VK_RIGHT, Direction.RIGHT );
        keyToDirection.put(KeyEvent.VK_LEFT, Direction.LEFT);


    }
    Plane plane = new Plane(30,70);

    User user1 = new User("Pestka");
    User user2 = new User("Maciek");

    HashMap<Integer, User> keyToUser = new HashMap<>();

    HashMap<Integer, Direction> keyToDirection = new HashMap<>();

    public void keyPressed(int keyCode) throws EndGameException {
        User activeUser = keyToUser.get(keyCode);
        Direction direction = keyToDirection.get(keyCode);
        if (direction == Direction.NONE){
            plane.rotateBlock(activeUser, Rotation.LEFT);
        }
        else {
            plane.moveBlock(activeUser, direction);
        }
    }
}