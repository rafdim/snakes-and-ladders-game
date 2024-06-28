import java.lang.Math;

public class Player {
    // states
    private String username;
    private int position;
    private int point;

    //methods
    public int rollDice() {
        return (int) (Math.random() * 6 + 1);
    }

    public void moveAround(int x) {
        if(this.position + x > 100) this.position = 100 - (this.position + x) % 100;
        else this.position += x;
    }

    //constructor
    public Player(String username) {
        this.username = username;
        this.position = 0;
        this.point = 0;
    }

    public void setName(String username) {
        this.username = username;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void updatePoint() {
        point++;
    }

    public String getName() {
        return this.username;
    }

    public int getPosition() {
        return this.position;
    }

    public int getPoint() {
        return this.point;
    }
}