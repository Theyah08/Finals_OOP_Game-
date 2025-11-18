import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Player.java — Represents a player in the game
public class Player {
    private static int nextId = 1; // Auto-incremented ID for each player

    private String id;          // Player ID (e.g., B01)
    private String name;        // Player name
    private int wins;           // Total wins
    private int losses;         // Total losses
    private int draws;          // Total draws
    private List<String> history; // Round-by-round history

    public void setId(String newId) {
        this.id = newId;
    }

    public Player(String name) {
        this.id = String.format("B%02d", nextId++); // Generate ID
        this.name = name;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.history = new ArrayList<>();
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public int getDraws() { return draws; }
    public List<String> getHistory() { return history; }

    // Record the result of a round
    public void recordResult(int round, String playerChoice, String computerChoice, String result) {
        String playerResult;

        if (result.equalsIgnoreCase("Player")) {
            playerResult = "Win";
            wins++;
        } else if (result.equalsIgnoreCase("Computer") || result.equalsIgnoreCase("CUTIES")) {
            playerResult = "Lose";
            losses++;
        } else {
            playerResult = "Draw";
            draws++;
        }

        // Add a readable entry to the history
        String entry = "Round " + round + ": " + name + ":" + playerChoice + " | Computer: " + computerChoice + " | Result: " + playerResult;

        history.add(entry);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("history.txt", true))) {
            bw.write(id + " - "  + name + ", " + entry );
            bw.newLine();
            
        } catch (IOException e) {
            System.out.println("Error history cannot be save " + e.getMessage());
        }
    }



    public int getScore() { return wins; }
}
