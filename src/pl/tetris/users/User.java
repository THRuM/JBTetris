package pl.tetris.users;


public class User {
    private int points;
    private String name;
    private char down;
    private char right;
    private char left;

    public User(String name){
        this.name = name;
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getDown() {
        return down;
    }

    public void setDown(char down) {
        this.down = down;
    }

    public char getRight() {
        return right;
    }

    public void setRight(char right) {
        this.right = right;
    }

    public char getLeft() {
        return left;
    }

    public void setLeft(char left) {
        this.left = left;
    }
}
