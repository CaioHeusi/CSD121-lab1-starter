package tictactoe;

import org.junit.jupiter.api.Test;
import tictactoe.game.Board;
import tictactoe.game.Col;
import tictactoe.game.Position;
import tictactoe.game.Row;
import tictactoe.game.Token;
import tictactoe.game.player.Linus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinusTest {

    @Test
    void choseTopLeftOnEmptyBoard() {
        var linus = new Linus(Token.X);
        var board = new Board();

        assertEquals(new Position(Row.Top, Col.Left), linus.getNextMove(board));
    }

    @Test
    void choseNextEmptyAvailableCellInReadingOrder() {
        var linus = new Linus(Token.O);
        var board = new Board("XX.O.....");

        assertEquals(new Position(Row.Top, Col.Right), linus.getNextMove(board));
    }

    @Test
    void skipFilledCellAndKeepInReadingOrder() {
        var linus = new Linus(Token.X);
        var board = new Board("XOXOXO...");

        assertEquals(new Position(Row.Bottom, Col.Left), linus.getNextMove(board));
    }

    @Test
    void constructorSetNameAndToken() {
        var linus = new Linus(Token.O);

        assertEquals("Linus", linus.name());
        assertEquals(Token.O, linus.token());
    }
}
