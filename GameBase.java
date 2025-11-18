import java.util.Random;

// GameBase.java — Core logic superclass
public class GameBase {
    protected String[] choices = {"Rock", "Paper", "Scissors"}; // Array of game choices
    protected Random random = new Random(); // Random object for computer choice

    // Method for computer to pick a random choice
    public String cutiesChoice() {
        int index = random.nextInt(choices.length); // Random index from 0 to 2
        return choices[index]; // Return computer's choice
    }

    // Method to determine winner (can be overridden in subclass)
    public String checkWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equalsIgnoreCase(computerChoice)) {
            return "Draw"; // Same choice = draw
        } else if (
            (playerChoice.equalsIgnoreCase("Rock") && computerChoice.equalsIgnoreCase("Scissors")) ||
            (playerChoice.equalsIgnoreCase("Paper") && computerChoice.equalsIgnoreCase("Rock")) ||
            (playerChoice.equalsIgnoreCase("Scissors") && computerChoice.equalsIgnoreCase("Paper"))
        ) {
            return "Player"; // Player wins
        } else {
            return "Computer"; // Otherwise, computer wins
        }
    }
}

