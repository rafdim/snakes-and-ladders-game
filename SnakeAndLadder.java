import java.lang.reflect.Array;
import java.util.ArrayList;

public class SnakeAndLadder {
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int boardSize;
    // o = the game is not started yet
    // 1 = the game is started
    // 2 = the game is over
    private int status;
    private int playerInTurn;
    private int round;

    public SnakeAndLadder(int boardSize) {
        this.boardSize = boardSize;
        this.players = new ArrayList<Player>();
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.status = 0;
        this.playerInTurn = 0;
        this.round = 1;
    }

    public void initiateGame() {
        // Set the ladder
        int[][] ladders = {
                {2,23},
                {8,34},
                {20,77},
                {32,68},
                {41,79},
                {74,88},
                {82,100},
                {85,95}
        };
        addLadders(ladders);
        // Set the snake
        int[][] snakes = {
                {29,9},
                {38,15},
                {47,5},
                {53,33},
                {62,37},
                {86,54},
                {92,70},
                {97,25}
        };
        addSnakes(snakes);
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setPlayerInTurn(int playerInTurn) {
        this.playerInTurn = playerInTurn;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void addRound() {
        this.round++;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public void addSnake(Snake s) {
        this.snakes.add(s);
    }

    public void addSnakes(int[][] s) {
        for(int i=0;i<s.length;i++) {
            Snake snake = new Snake(s[i][0], s[i][1]);
            addSnake(snake);
        }
    }

    public void addLadder(Ladder l) {
        this.ladders.add(l);
    }

    public void addLadders(int[][] l) {
        for(int i=0;i<l.length;i++) {
            Ladder ladder = new Ladder(l[i][0], l[i][1]);
            addLadder(ladder);
        }
    }

    public int getBoardSize(){
        return boardSize;
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public ArrayList<Snake> getSnakes(){
        return this.snakes;
    }

    public ArrayList<Ladder> getLadders(){
        return this.ladders;
    }

    public int getStatus() {
        return this.status;
    }

    public int getTurn() {
        if (this.status == 0) {
            playerInTurn = (int) (Math.random()*(this.players.size()-0.000001));
            return playerInTurn;
        }
        else {
            playerInTurn = (playerInTurn + 1)%(this.players.size());
            return playerInTurn;
        }
    }

    public int getRound() {
        return this.round;
    }

    public void movePlayer(Player p, int x) {
        p.moveAround(x);
        System.out.println("The new position of " + p.getName() + " is " + p.getPosition());
        for(int i=0;i<ladders.size();i++) {
            Ladder l = this.ladders.get(i);
            if(p.getPosition() == l.getFromPosition()) {
                p.setPosition(l.getToPosition());
                System.out.println(p.getName() + " got ladder from " + l.getFromPosition() + " to " + l.getToPosition());
            }
        }
        for(int j=0;j<snakes.size();j++) {
            Snake s = snakes.get(j);
            if(p.getPosition() == s.getFromPosition()) {
                p.setPosition(s.getToPosition());
                System.out.println(p.getName() + " got snake from " + s.getFromPosition() + " to " + s.getToPosition());
            }
        }
        if(p.getPosition() == 100) {
            this.status = 2;
            System.out.println("The winner is: "+ p.getName());
            // p.updatePoint();
            // this.showOption();
        }
    }

    public void showOption() {
        System.out.println("Round " + this.round + " has over. Do you want to continue to the next round?");
        System.out.println("Type to the console - 1 : Next Round, 2. Check Points");
    }
}