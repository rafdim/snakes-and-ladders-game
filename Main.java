import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.*;



public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException  {
        File file = new File("Lish_Grooves.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        File victoryFile = new File("tadaa.wav");
        AudioInputStream victoryStream = AudioSystem.getAudioInputStream(victoryFile);
        Clip victoryClip = AudioSystem.getClip();
        victoryClip.open(victoryStream);

        SwingUtilities.invokeLater(() -> {
            // Number of players input
            int numPlayer = Integer.parseInt(JOptionPane.showInputDialog("Enter number of players: "));
            String[] players = new String[numPlayer];
            ArrayList<Player> playersObject = new ArrayList<>();
            for (int i = 0; i < numPlayer; i++) {
                players[i] = JOptionPane.showInputDialog("Enter player " + (i + 1) + " name: ");
                Player player = new Player(players[i]);
                playersObject.add(player);
            }

            SnakeAndLadder g1 = new SnakeAndLadder(100);
            g1.initiateGame();
            g1.setPlayers(playersObject);

            Frame frame = new Frame();
            frame.setTitle("Snake and Ladder Game");

            // Create board panel
            int boardSize = 10;  // 10x10 board
            JPanel boardPanel = new JPanel();
            boardPanel.setLayout(new GridLayout(boardSize, boardSize));
            boardPanel.setBackground(Color.darkGray);

            JButton[][] board = new JButton[boardSize][boardSize];
            int numTile = 0;
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if(i % 2 == 0 && i != 0 && j == 0) numTile = numTile + 10; // Odd row first column
                    else if(i % 2 == 0 && i != 0 && j != 0) numTile = numTile + 1; // Odd row other columns
                    else if(i % 2 != 0 && j == 0) numTile = numTile + 10; // Even row first column
                    else if(i % 2 != 0 && j != 0) numTile = numTile - 1; // Even row other columns
                    else numTile++;  // First row
                    JButton tile = new JButton();
                    tile.setPreferredSize(new Dimension(60, 60));  // set preferred size for buttons
                    board[i][j] = tile;
                    boardPanel.add(tile);

                    tile.setBackground(Color.darkGray);
                    tile.setForeground(Color.white);
                    tile.setFont(new Font("Calibri", Font.BOLD, 10));
                    tile.setFocusable(false);
                    tile.setText("" + numTile);
                }
            }

            // Create text panel
            JLabel textLabel = new JLabel("Snake and Ladder");
            textLabel.setBackground(Color.lightGray);
            textLabel.setForeground(Color.black);
            textLabel.setFont(new Font("Calibri", Font.BOLD, 25));
            textLabel.setHorizontalAlignment(JLabel.LEFT);

            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BorderLayout());
            textPanel.add(textLabel, BorderLayout.CENTER);

            // Create control panel
            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
            JLabel turnLabel = new JLabel();
            turnLabel.setFont(new Font("Calibri", Font.BOLD, 25));
            JButton rollDiceButton = new JButton("Roll Dice");
            rollDiceButton.setFont(new Font("Calibri", Font.BOLD, 25));
            controlPanel.add(turnLabel);
            controlPanel.add(rollDiceButton);
            JButton startAudio = new JButton("Music On");
            startAudio.addActionListener(e -> clip.start());
            controlPanel.add(startAudio);
            JButton stopAudio = new JButton("Music Off");
            stopAudio.addActionListener(e -> clip.stop());
            controlPanel.add(stopAudio);

            // Add panels to frame
            frame.setLayout(new BorderLayout());
            frame.add(textPanel, BorderLayout.NORTH);
            frame.add(boardPanel, BorderLayout.CENTER);
            frame.add(controlPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);

            // Initialize player positions on the board
            updateBoard(board, playersObject);

            rollDiceButton.addActionListener(e -> {
                // Get the current player in turn
                int t1 = g1.getTurn();
                g1.setStatus(1);
                Player playerInTurn = g1.getPlayers().get(t1);
                turnLabel.setText("Player in turn is " + playerInTurn.getName());

                // Player rolls the dice
                int x = playerInTurn.rollDice();
                g1.movePlayer(playerInTurn, x);
                updateBoard(board, playersObject);

                // Check if the player has won
                if (playerInTurn.getPosition() == 100) {
                    g1.setStatus(3);
                    JOptionPane.showMessageDialog(frame, "The winner is: " + playerInTurn.getName());
                    rollDiceButton.setEnabled(false);  // Disable the roll dice button
                    clip.stop();
                    victoryClip.start();
                }
            });
        });
    }

    // Method to update the board based on player positions
    public static void updateBoard(JButton[][] board, ArrayList<Player> players) {
        int boardSize = 10;
        int numTile = 0;
        // Set the numbers
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if(i % 2 == 0 && i != 0 && j == 0) numTile = numTile + 10; // Odd row first column
                else if(i % 2 == 0 && i != 0 && j != 0) numTile = numTile + 1; // Odd row other columns
                else if(i % 2 != 0 && j == 0) numTile = numTile + 10; // Even row first column
                else if(i % 2 != 0 && j != 0) numTile = numTile - 1; // Even row other columns
                else numTile++;  // First row
                board[i][j].setText("" + numTile); // Reset tile number
            }
        }

        // Set the players' position
        for (Player player : players) {
            int position = player.getPosition();
            if(position != 0) {
                int row = 0;
                if(position != 100) row = (int) ((position-1) / boardSize);
                else row = 9;
                int col = 0;
                if(position > 1 && row % 2 == 0) col = (position-1) % boardSize; // odd row
                else if(position > 1 && row % 2 != 0) col = 9-((position-1) % boardSize); // even row
                board[row][col].setText(player.getName());
            }
        }
    }
}