package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;
import tictactoe.ui.Console;

/**
 * Abstract base class for all TicTacTore players
 */
public abstract class Player {

    private final String name;
    private final Token token;

    /**
     * Create a player with given name and token
     * @param name Player name
     * @param token Player token
     */
    public Player(String name, Token token) {
        this.name = name;
        this.token = token;
    }

    /**
     * @return Player name
     */
    public String name() {
        return name;
    }

    /**
     * @return Player token
     */
    public Token token() {
        return token;
    }

    /**
     * Will tell the next move for this player
     * @param board current board state
     * @return chosen position
     */
    public abstract Position getNextMove(Board board);
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
