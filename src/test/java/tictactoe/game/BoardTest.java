package tictactoe.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    // Test parameterless board constructor

    @Test
    void newBoardShouldBeEmpty() {
        var board = new Board(); // create new empty board

        for (var row : Row.values()) { // first will go through all combinations between row and col
            for (var col : Col.values()) {
                assertTrue(board.isEmptyAt(new Position(row, col))); // isEmptyAt should return true in every pos from new board
            }
        }
    }

    @Test
    void isEmptyAtAfterPlacingToken () {
        var board = new Board();
        var pos = new Position(Row.Top, Col.Left);
        assertTrue(board.isEmptyAt(pos)); // test isEmptyAt

        board.place(pos, Token.X);  // test place
        assertFalse(board.isEmptyAt(pos));
    }

    @Test
    void getWinnerReturnsEmptyWhenNoWinner () {
        assertTrue(new Board("X.." + ".O." + "...").getWinner().isEmpty());
    }

    // 8 win conditions per token (X and O)

    @Test
    void getWinnerReturnsTokenWhenRowIsComplete() {
        assertEquals(Token.X, new Board("XXX......").getWinner().orElseThrow()); // test rows of X
        assertEquals(Token.X, new Board("...XXX...").getWinner().orElseThrow());
        assertEquals(Token.X, new Board("......XXX").getWinner().orElseThrow());
        assertEquals(Token.O, new Board("OOO......").getWinner().orElseThrow()); // test rows of O
        assertEquals(Token.O, new Board("...OOO...").getWinner().orElseThrow());
        assertEquals(Token.O, new Board("......OOO").getWinner().orElseThrow());
    }

    @Test
    void getWinnerReturnsTokenWhenColumnIsComplete() {
        assertEquals(Token.X, new Board("X..X..X..").getWinner().orElseThrow()); // test columns of X
        assertEquals(Token.X, new Board(".X..X..X.").getWinner().orElseThrow());
        assertEquals(Token.X, new Board("..X..X..X").getWinner().orElseThrow());
        assertEquals(Token.O, new Board("O..O..O..").getWinner().orElseThrow()); // test columns of O
        assertEquals(Token.O, new Board(".O..O..O.").getWinner().orElseThrow());
        assertEquals(Token.O, new Board("..O..O..O").getWinner().orElseThrow());
    }

    @Test
    void getWinnerReturnsTokenWhenDiagonalsIsComplete() {
        assertEquals(Token.X, new Board("..X.X.X..").getWinner().orElseThrow()); // bug vestige
        assertEquals(Token.X, new Board("X...X...X").getWinner().orElseThrow()); // test diagonal
        assertEquals(Token.O, new Board("..O.O.O..").getWinner().orElseThrow()); // test anti-diagonal
        assertEquals(Token.O, new Board("O...O...O").getWinner().orElseThrow());
    }
}