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

public class TGame extends Thread{

    private User user1;
    private User user2;
    private Plane plane;
    private HashMap<Integer, User> keyToUserHashMap;
    private HashMap<Integer, Direction> keyToDirectionHashMap;
    private int timeTick;
    private int planeSize[];
    private int gameMode;
    private boolean isGameRunning = true;

    public TGame(int gameMode){
        super("TGame");

        planeSize = new int[2];
        planeSize[0] = 20;
        planeSize[1] = 30;
        this.gameMode = gameMode;

        plane = new Plane(planeSize[0],planeSize[1]);

        keyToUserHashMap = new HashMap<>();
        keyToDirectionHashMap = new HashMap<>();
        timeTick = 500;

        if(gameMode == 1 || gameMode == 2){
            user1 = new User("Gracz 1");
            user2 = null;

            plane.addUser(user1);

            //Standardowe mapowania kierunków i userów
            //User1
            keyToUserHashMap.put(KeyEvent.VK_UP, user1);
            keyToUserHashMap.put(KeyEvent.VK_DOWN, user1);
            keyToUserHashMap.put(KeyEvent.VK_RIGHT, user1);
            keyToUserHashMap.put(KeyEvent.VK_LEFT, user1);

            keyToDirectionHashMap.put(KeyEvent.VK_UP, Direction.NONE );
            keyToDirectionHashMap.put(KeyEvent.VK_DOWN, Direction.DOWN );
            keyToDirectionHashMap.put(KeyEvent.VK_RIGHT, Direction.RIGHT );
            keyToDirectionHashMap.put(KeyEvent.VK_LEFT, Direction.LEFT);

        }

        if(gameMode == 2) {
            user2 = new User("Gracz 2");

            plane.addUser(user2);

            keyToUserHashMap.put(KeyEvent.VK_UP, user2);
            keyToUserHashMap.put(KeyEvent.VK_DOWN, user2);
            keyToUserHashMap.put(KeyEvent.VK_RIGHT, user2);
            keyToUserHashMap.put(KeyEvent.VK_LEFT, user2);

            keyToDirectionHashMap.put(KeyEvent.VK_W, Direction.NONE );
            keyToDirectionHashMap.put(KeyEvent.VK_S, Direction.DOWN );
            keyToDirectionHashMap.put(KeyEvent.VK_D, Direction.RIGHT );
            keyToDirectionHashMap.put(KeyEvent.VK_A, Direction.LEFT);
        }
    }

    @Override
    public void run() {
        do{
            try {
                System.out.println("Wątek TGame");
                plane.gameStep();
                Thread.sleep(timeTick);
            } catch (EndGameException | InterruptedException e) {
                isGameRunning = false;
                e.printStackTrace();
            }
        } while (isGameRunning);
    }

    public User getUser1(){
        return user1;
    }

    public User getUser2(){
        return user2;
    }

    public int[] getPlaneSize(){
        return planeSize;
    }

    public Plane getPlane(){
        return plane;
    }

    public int getGameMode(){
        return gameMode;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void keyPressed(int keyCode){
        if(isGameRunning){
            User activeUser = keyToUserHashMap.get(keyCode);
            Direction direction = keyToDirectionHashMap.get(keyCode);

            if (direction == Direction.NONE){
                plane.rotateBlock(activeUser, Rotation.RIGHT);
            }
            else {
                try {
                    plane.moveBlock(activeUser, direction);
                } catch (EndGameException e) {
                    isGameRunning = false;
                    e.printStackTrace();
                }
            }
        }

    }
}
