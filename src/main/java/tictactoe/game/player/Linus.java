package tictactoe.game.player; // package this class belongs

import tictactoe.game.Board; // import board to check empty cells
import tictactoe.game.Position; // import position because getNextMove need to return a position
import tictactoe.game.Token; // import Token to hold X or O

/**
 * Compute player that will always pick the next available cell, reading left to right, top to bottom
 */
public class Linus extends Player { // Simple bot class Linus

    /**
     * Create Linus player
     * @param token Token used by Linus
     */
    public Linus(Token token) { // linus constructor
        super("Linus", token); // calling superclass constructor with fix name Linus
    }

    @Override
    public Position getNextMove(Board board) { // implements Linus logic

        return board.getEmptyCells().get(0); // pick the next position available in reading order, and return it
    }
}