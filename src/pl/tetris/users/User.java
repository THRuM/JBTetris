package pl.tetris.users;


public class User {
    private int points;
    private String name;

    private int blockStart;

    public User(String name, int blockStart){
        this.name = name;
        this.blockStart = blockStart;
        points = 0;
    }

    public void addPoints(int points) { this.points += points; }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public int getBlockStart() {
        return blockStart;
    }
}
