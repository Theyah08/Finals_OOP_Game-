import java.util.Random;
import java.util.Scanner;

// RockPaperScissors.java — Subclass demonstrating inheritance and overriding
public class RockPaperScissors extends GameBase {
    private Scoreboard scoreboard = new Scoreboard(); // Track all players
    private Scanner sc = new Scanner(System.in);       // Read user input
    private Random random = new Random();              // Random for messages

    // Overridden method to show polymorphism (calls parent method)
    @Override
    public String checkWinner(String playerChoice, String computerChoice) {
        return super.checkWinner(playerChoice, computerChoice);
    }

    // Main menu loop
    public void startGame() {

        scoreboard.loadHistory();
        
        while (true) {
            // Display main menu
            System.out.println("======================================");
            System.out.println("---- Rock Paper Scissors Game ----");
            System.out.println("======================================");
            System.out.println("1. Play Game");
            System.out.println("2. History");
            System.out.println("3. Exit");
            System.out.println("======================================");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine(); // Read menu choice

            switch (choice) {
                case "1": play(); break;           // Start a game
                case "2": viewPlayerHistoryById(); break; // Show player history
                case "3":                           // Exit game
                    System.out.println("Goodbye! Thanks for playing!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }

    // Method to search and display player history by ID
    private void viewPlayerHistoryById() {
        System.out.print("\nEnter your Player ID (e.g., B01): ");
        String playerId = sc.nextLine().trim();
        scoreboard.searchPlayer(playerId);
    }

    // Play the game rounds
    private void play() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        Player player = new Player(name); // Create a new player

        // Ask how many rounds to play
        int rounds = 0;
        while (true) {
            System.out.print("How many rounds do you want to play? ");
            String input = sc.nextLine();
            try {
                rounds = Integer.parseInt(input);
                if (rounds <= 0) { 
                    System.out.println("Please enter a positive number."); 
                    continue; 
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Please enter a valid number.\n");
            }
        }

        // Welcome message
        System.out.println("\nWelcome, " + player.getName() + "! Your Player ID is " + player.getId());
        System.out.println("Type 'exit' anytime to stop the game.\n");

        // Loop through each round
        for (int r = 1; r <= rounds; r++) {
            System.out.println("ROUND " + r);
            System.out.print("Choose [Rock / Paper / Scissors] : ");
            String playerChoice = sc.nextLine().trim();

            if (playerChoice.equalsIgnoreCase("exit")) {
                System.out.println("You exited early. Returning to main menu.\n");
                break;
            }

            // Validate input
            if (!playerChoice.equalsIgnoreCase("Rock") &&
                !playerChoice.equalsIgnoreCase("Paper") &&
                !playerChoice.equalsIgnoreCase("Scissors")) {
                System.out.println("Invalid choice! Please enter Rock, Paper, or Scissors.\n");
                r--; // Repeat round
                continue;
            }

            String compChoice = cutiesChoice(); // Get computer choice
            System.out.println("Computer choice: " + compChoice);

            String result = checkWinner(playerChoice, compChoice); // Determine winner
            player.recordResult(r, playerChoice, compChoice, result); // Save result

            // Display round message
            switch (result) {
                case "Player":
                    String[] winMsgs = {
                        "Nice one! You won this round!",
                        "That was smooth~~~ you beat the cutie coders!",
                        "VICTORYYYYY!",
                        "You're on fire! Well played!"
                    };
                    System.out.println(winMsgs[random.nextInt(winMsgs.length)]);
                    break;
                case "Computer":
                    String[] loseMsgs = {
                        "OH! Better luck next time!",
                        "The cuties strikes back!",
                        "You lost this round, but keep going!"
                    };
                    System.out.println(loseMsgs[random.nextInt(loseMsgs.length)]);
                    break;
                case "Draw":
                    String[] drawMsgs = {
                        "It’s a tie! Great minds think alike.",
                        "Same choice like TWINS!"
                    };
                    System.out.println(drawMsgs[random.nextInt(drawMsgs.length)]);
                    break;
            }

            System.out.println("Current Score: " + player.getWins() + "/" + r + "\n");
        }

        // Show final score
        System.out.println("Game Over, " + player.getName() + "! Final Score: " +
                player.getWins() + "/" + rounds);

        // Save player to scoreboard
        scoreboard.addPlayer(player);
    }

    // Main method — entry point of the program
    public static void main(String[] args) {
        GameBase game = new RockPaperScissors(); // Upcasting
        ((RockPaperScissors) game).startGame();  // Start the game
    }
}
