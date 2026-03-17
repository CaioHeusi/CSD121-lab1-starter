package tictactoe.game.player;


import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

/**
 * Compute player that will always pick the next available cell, reading left to right, top to bottom
 */
public class Linus extends Player {

    /**
     * Create Linus player
     * @param token Token used by Linus
     */
    public Linus(Token token) {
        super("Linus", token);
    }

    @Override
    public Position getNextMove(Board board) {
        return board.getEmptyCells().get(0);
    }
}