package tictactoe.game.player; // package that this class belongs to

import tictactoe.game.Board; // import class board to use in getNextMove()
import tictactoe.game.Position; // import class Position to getNextMove() return a position
import tictactoe.game.Token; // import token to hold X or O

/**
 * Abstract base class for all TicTacTore players
 */
public abstract class Player { // base class for all Players type

    private final String name; // store player's name
    private final Token token; // store player's token like, X or O

    /**
     * Create a player with given name and token
     * @param name Player name
     * @param token Player token
     */
    public Player(String name, Token token) { // constructor of Player class
        this.name = name; // store the name in name attribute
        this.token = token; // store token in token attribute
    }

    /**
     * @return Player name
     */
    public String name() { // return player's name

        return name; // return stored name
    }

    /**
     * @return Player token
     */
    public Token token() { // return player's token

        return token; // return stored token
    }

    /**
     * Will tell the next move for this player
     * @param board current board state
     * @return chosen position
     */
    public abstract Position getNextMove(Board board); // each subclass needs to implement your own way to getNextMove()


    // old code which was human logic direct in Player, that were moved to HumanPlayer.java
//    /**
//     * Prompts the player to pick their next move.
//     * Will continue to prompt until the player picks a valid move (i.e. an empty position on the board)
//     * @param board The current state of the board
//     * @return The (valid) position on the board where the player wants to place their token
//     */
//    public Position getNextMove(Board board) {
//        while (true) {
//            var prompt = "%s's turn (%s). Enter your move (row column): ".formatted(this.name(), this.token());
//            var pos = Console.promptForPosition(prompt, board);
//
//            if (board.isEmptyAt(pos)) {
//                return pos;
//            }
//            Console.printAlert("That position is not valid. Please enter a valid position.");
//        }
//    }

}
