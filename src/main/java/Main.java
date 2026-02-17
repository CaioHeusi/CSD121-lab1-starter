import core.HighCardGame;
import ui.Console;

public class Main {

    static void main() {
        /*
        String p1 = Console.prompt("Player 1 name: ");
        String p2 = Console.prompt("Player 2 name: ");
        */

        String p1 = ""; // variable to store the player name, starting empty
        while (p1 == null || p1.isBlank()) { // while loop to repeat until user enter a valid name
            p1 = Console.prompt("Player 1 name: ");
            if (p1 == null) p1 = ""; // this is useful for change a null for an empty string, avoiding errors
            p1 = p1.trim(); // useful to remove spaces at sides

            if (p1.isBlank()) { // this is a conditional to check if after trimmed still empty
                Console.println("Please enter a name."); // telling user what's wrong
            }
        }

        String p2 = ""; // same thing from p1
        while (p2 == null || p2.isBlank()) {
            p2 = Console.prompt("Player 2 name: ");
            if (p2 == null) p2 = "";
            p2 = p2.trim();

            if (p2.isBlank()) {
                Console.println("Please enter a name.");
            }
        }

        HighCardGame game = new HighCardGame(p1, p2); // create the game, shuffle and distribute cards
/*
        Console.println("Cards at Start:");
        Console.println(game.player1().name() + ": " + game.player1().cardsLeft());
        Console.println(game.player2().name() + ": " + game.player2().cardsLeft());
        Console.println("");
*/
        boolean keepPlaying = true; // control if user still wants to play
        int round = 1; // round counter

        while (keepPlaying && game.canPlayRound()) { // main game loop that keeps running and givin cards while user want to play

            Console.println(""); // just to jump lines
            Console.println("Round " + round); // show the number of rounds

            HighCardGame.RoundResult r = game.playRound(); // play one round and get the result

            Console.println(game.player1().name() + " drew " + r.c1()); // show players cards
            Console.println(game.player2().name() + " drew " + r.c2());

            if (r.winner() == null) { // if winner is null, tie
                Console.println("Tie!");
            } else { // if it's not tie (null)
                Console.println("Winner: " + r.winner().name()); // print winner name
            }

            // Console.println("");

            // Console.println("Cards left -> " + game.player1().name() + ": " + game.player1().cardsLeft() + " | " + game.player2().name() + ": " + game.player2().cardsLeft());

            Console.println("============================================");

            while (true) { // while loop to keep asking user if he wants to play until press the right answer
                String ans = Console.prompt("Play another round? (y/n): ");
                if (ans == null) { // conditional to check if it is null
                    ans = ""; // change for empty
                }

                ans = ans.trim().toLowerCase(); // Trim to remove spaces and '.toLowerCase' to make user input lower case

                if (ans.equals("y")) { // conditional to check if 'y' was typed
                    keepPlaying = true;
                    break; // exit validation loop (if)
                }

                if (ans.equals("n")) { // conditional to check if 'n' was typed
                    keepPlaying = false;
                    break;
                }

                Console.println("Type y or n."); // if anything was typed instead of 'y' or 'n', it will alert and repeat

            }

            round += 1; // increase round counter
            Console.println("");
        }

        if (!game.canPlayRound()) { // with this the user will know why the game over
            Console.println("Game ended because someone ran out of cards.");
        } else {
            Console.println("Stopped by player.");
        }
    }
}
