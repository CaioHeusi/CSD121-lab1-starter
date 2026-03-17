package tictactoe.game.player;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.game.Token;

/**
 * One MOve Look-Ahead.
 * Omola will pick a winning move if available,
 * otherwise blocks the opponent's winning move,
 * otherwise picks the first available position.
 */
public class Omola extends Player {

    /**
     * Create an Omola player with given token
     * @param token token used by Omola
     */
    public Omola(Token token) {
        super("Omola", token);
    }

    @Override
    public Position getNextMove(Board board) {
        var emptyCells = board.getEmptyCells();

        // If Omola can win now, take that move
        for (var pos : emptyCells) {
            var boardCopy = new Board(board);
            boardCopy.place(pos, token());

            if (boardCopy.getWinner().filter(winner -> winner == token()).isPresent()) {
                return pos;
            }
        }

        // otherwise block if opponent could win at next move
        var opponent = token().opponent();

        for (var pos : emptyCells) {
            var boardCopy = new Board(board);
            boardCopy.place(pos, opponent);

            if (boardCopy.getWinner().filter(winner -> winner == opponent).isPresent()) {
                return pos;
            }
        }

        // otherwise, just pick the first empty cell available
        return emptyCells.get(0);
    }
}