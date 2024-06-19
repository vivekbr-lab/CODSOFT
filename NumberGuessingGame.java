import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int start = 1;
        int end = 100;
        int attemptsLimit = 10;
        int rounds = 0;
        int score = 0;
        boolean playAgain = true;

        while (playAgain) {
            int randomNumber = random.nextInt(end - start + 1) + start;
            System.out.println("\nRound " + (rounds + 1) + "!");
            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < attemptsLimit) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
                
                if (userGuess < randomNumber) {
                    System.out.println("Too low!");
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct!");
                    guessedCorrectly = true;
                    score++;
                    break;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The number was " + randomNumber + ".");
            }

            rounds++;
            System.out.println("Score: " + score + " / " + rounds + " rounds won");

            System.out.print("Do you want to play again? (yes/no): ");
            scanner.nextLine();  // Consume the newline
            String playAgainResponse = scanner.nextLine().toLowerCase();
            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
