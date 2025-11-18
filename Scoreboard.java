import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

// Scoreboard.java — Tracks all players and allows viewing history
public class Scoreboard {
    private List<Player> players = new ArrayList<>(); // List of all players

    // Add a player to the scoreboard
    public void addPlayer(Player player) {
        players.add(player);
    }

    // Search for a player by ID and display their history
    public void searchPlayer(String id) {
        Player found = null;

        for (Player p : players) {
            if (p.getId().equalsIgnoreCase(id)) {
                found = p;
                break;
            }
        }

        if (found == null) {
            System.out.println("\nNo history found for ID: " + id + "\n");
            return;
        }

        // Display player stats
        System.out.println("\n----- Player History -----");
        System.out.println("Player ID : " + found.getId());
        System.out.println("Name      : " + found.getName());
        System.out.println("Wins      : " + found.getWins());
        System.out.println("Losses    : " + found.getLosses());
        System.out.println("Draws     : " + found.getDraws());
        System.out.println();
        System.out.println("Detailed Round History:");
        System.out.println("-------------------------");

        // Display round-by-round history
        for (String h : found.getHistory()) {
            System.out.println(h);
        }

        System.out.println("-------------------------\n");
    }

    

    public void loadHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader("history.txt"))) {
            String line;
            while ((line = br.readLine()) != null){
                String[] parts = line.split(" - ", 2);
                if (parts.length < 2) continue;

                String playerId = parts[0];
                String entry = parts[1];

                String playerName = entry.split(":")[1].trim();
    

                Player player = findOrCreatePlayer(playerId, playerName);
                player.getHistory().add(entry);
            }  
        } catch (Exception e) {
            System.out.println("Error history cannot be load: " + e.getMessage());
        }
        
    }

    private Player findOrCreatePlayer(String playerId, String playerName) {
    for (Player p : players) {
        if (p.getId().equalsIgnoreCase(playerId)) {
            return p;
        }
    }
    Player newPlayer = new Player(playerName); // Use real name
    newPlayer.setId(playerId);

    players.add(newPlayer);
    return newPlayer;
}

}
