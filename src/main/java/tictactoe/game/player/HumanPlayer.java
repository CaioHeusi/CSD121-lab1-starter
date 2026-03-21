package tictactoe.game.player; // package that this class belongs

import tictactoe.game.Position; // import position because getNextMove() return a position
import tictactoe.game.Board; // import board to look actual game state
import tictactoe.game.Token; // import token to hold X or O
import tictactoe.ui.Console; // import console because human player need to type which move he wants in terminal

/**
 * Human player that will pick a cell through console
 */
public class HumanPlayer extends Player { // class which represents a human player

    /**
     * Create a human player with given name and token
     * @param name Player name
     * @param token Token used by player
     */
    public HumanPlayer(String name, Token token) { // human player constructor

        super(name, token); // call superclass constructor to store name and token
    }

    /**
      * Prompts the player to pick their next move
      * Will continue to prompt until the player picks a valid move (i.e. an empty position on the board)
      * @param board The current state of the board
      * @return The (valid) position on the board where the player wants to place their token
      */
    @Override
    public Position getNextMove(Board board) { // implements human player logic
        while (true) { // while loop keep asking user to pick next move until he enters a valid position
            var prompt = "%s's turn (%s). Enter your move (row column): ".formatted(this.name(), this.token()); // formatted message telling whose turn
            var pos = Console.promptForPosition(prompt, board); // ask position to user

            if (board.isEmptyAt(pos)) { // if the cell that user choose are empty
                return pos; // return that chosen position
            }
            Console.printAlert("That position is not valid. Please enter a valid position."); // if the position was not valid, display this message and repeats the loop
        }
    }
}
