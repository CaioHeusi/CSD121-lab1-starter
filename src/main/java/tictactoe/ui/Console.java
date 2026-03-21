package tictactoe.ui; // this file is part of ui package

import tictactoe.game.*; // import all from game
import com.diogonunes.jcolor.AnsiFormat; // import AnsiFormat to style text in terminal
import tictactoe.game.player.HumanPlayer; // import human player
import tictactoe.game.player.Player; // import Player superclass
import tictactoe.game.player.Linus; // import Linus basic AI
import tictactoe.game.player.Omola; // import Omola advanced AI

import java.text.ParseException;  // import ParseException to handle text to position conversion error

import static com.diogonunes.jcolor.Attribute.*; // import color and style attributes used by jcolor
import static com.diogonunes.jcolor.Ansi.colorize; // import function that colorize text

/**
 * Helper methods for doing console-based user interaction
 */
public class Console { // class with helper methods to interact with console

    // Define some colors and text styles for use in the console
    private static final AnsiFormat fPrompt = new AnsiFormat(GREEN_TEXT(), BOLD());
    private static final AnsiFormat fAlert = new AnsiFormat(YELLOW_TEXT());

    public static void println(String message) { // print a line in console
        IO.println(message); // sends message to IO class prints
    }

    /**
     * Prompt the user for input using the given promptMessage
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) { // show one prompt and read user answer
        return IO.readln(fPrompt.format(promptMessage)); // formats prompt and read input
    }

    /**
     * Display an alert message to the user
     * @param message The message to display
     */
    public static void printAlert(String message) { // show alert message
        IO.println(fAlert.format(message)); // prints the message with alert style
    }

    /**
     * Format the given game board for color display in an ANSI terminal
     * @param board A tictactoe game board
     */
    public static void showBoard(Board board) { // show board with X and O colorized
        var sb = new StringBuilder(); // create a StringBuilder to build final output
        for (var c : board.toString().toCharArray()) { // for loop to go through each character of board representation
            if ( c == 'X' ) { // if it is X
                sb.append(colorize("X", BRIGHT_CYAN_TEXT())); // show in cyan
            } else if ( c == 'O' ) { // if it is O
                sb.append(colorize("O", BRIGHT_MAGENTA_TEXT())); // show it in magenta
            } else {
                sb.append(c); // any other character will keep normal color
            }
        }
        IO.println(sb.toString()); // print ready built board
    }

    /**
     * Repeatedly prompt the user to select a player for the given token
     * until the select a valid one of a set of valid players
     * @param whichPlayer The player for which to prompt
     * @return A player object representing the user's chosen player
     */
    public static Player promptForPlayer(Token whichPlayer) { // ask user who will play with each token

        // help message showing how to pick a human or a bot
        var helpMessage = """
                Enter your name, or use: 
                @Linus - will pick first available position. (Basic)
                @Omola - looks one move ahead. (Advanced)
                """;

        while ( true ) { // while loop to keep asking user until receives a valid message
            var input = prompt("Who will play " + whichPlayer + "? ").trim(); // read user input and remove extra spaces

            // Handle computer players
            if ( input.startsWith("@") ) { // if start with @, it is a compute player, so should be treated as
                input = input.substring(1).trim().toLowerCase(); // remove @, remove spaces and convert to lower case

                switch ( input ) { // decides which bot create
                    // TODO: add cases here for the different computer players you implement
                    case "linus" -> { // if user picked linus
                        return new Linus(whichPlayer); // return linus with corresponding token
                    }
                    case "omola" -> { // if user picked omola
                        return new Omola(whichPlayer); // return omola with corresponding token
                    }
                    default -> printAlert(helpMessage); //if the input was not valid, show help message to user
                }
            } else { // if don't start with @, treat as human player input
                return new HumanPlayer(input, whichPlayer); // create and return a human player with given name
            }
        }
    }


    /**
     * Repeatedly prompt the user for a position on which to place their next token.
     * If they enter an invalid response or an already-taken position they are re-prompted.
     * @param prompt The prompt to display to the user
     * @param board The current state of the game board
     * @return The position selected by the user
     */
    public static Position promptForPosition(String prompt, Board board) { // ask which position player want to pick


        final String helpMessage = "Input must be in the format 'row column', e.g., '1 2' or 't m' for the top middle cell."; // help message to invalid input

        while ( true ) { // while loop to ask user until receives a valid position
            var input = IO.readln(fPrompt.format(prompt)).trim(); // read user input

            // The .parse method may throw if the user entered invalid location text, so we try/catch
            try { // try to convert text ini position

                var pos = Position.parse(input); // do entry parse

                if (! board.isEmptyAt(pos)) { // if the position is already occupied
                    printAlert("That position is already taken."); // print user a message
                    continue; // ask another
                }

                return pos; // if the position is valid and empty, return this position
            } catch ( ParseException e ) { // if any error happens while converting
                printAlert(helpMessage); // show a help message
            }
        }
    }
}
