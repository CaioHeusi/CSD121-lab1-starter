package tictactoe.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

class PositionTest {

    @Test
    void parse_twoCharacterFormat() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Left), Position.parse("tl")); // testing abbreviation
        assertEquals(new Position(Row.Top, Col.Left), Position.parse("11")); // testing numeric format

        assertEquals(new Position(Row.Middle, Col.Middle), Position.parse("mc"));
        assertEquals(new Position(Row.Middle, Col.Middle), Position.parse("22"));

        assertEquals(new Position(Row.Bottom, Col.Middle), Position.parse("bm"));
        assertEquals(new Position(Row.Bottom, Col.Middle), Position.parse("32"));
    }

    @Test
    void parse_isCaseInsensitive() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Left), Position.parse("TL")); // parse should accept mix cases upper/lower
        assertEquals(new Position(Row.Top, Col.Left), Position.parse("tL"));
        assertEquals(new Position(Row.Top, Col.Left), Position.parse("tl"));
    }

    @Test
    void parse_twoPartSeparator() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Left), Position.parse("top,left")); // testing comma separator
        assertEquals(new Position(Row.Middle, Col.Middle), Position.parse("middle;center")); // semicolon
        assertEquals(new Position(Row.Top, Col.Right), Position.parse("1 3")); // space
    }

    @Test
    void parse_ignoreExtraSpace() throws ParseException {
        assertEquals(new Position(Row.Top, Col.Left), Position.parse("  tl  ")); // spaces around should not affect
        assertEquals(new Position(Row.Top, Col.Left), Position.parse(" top   left ")); // extra spaces between parts should not affect
        assertEquals(new Position(Row.Bottom, Col.Right), Position.parse("  bottom,   right  ")); // extra spaces with comma must be ignored
        assertEquals(new Position(Row.Middle, Col.Left), Position.parse(" 2 ; 1 ")); // extra spaces around ; must be ignored
    }

    @Test
    void parse_invalidStrings() {
        assertThrows(ParseException.class, () -> Position.parse("")); // empty string is invalid
        assertThrows(ParseException.class, () -> Position.parse("t")); // one char is invalid, need row and col
        assertThrows(ParseException.class, () -> Position.parse("top")); // just 1 part without the other is invalid
        assertThrows(ParseException.class, () -> Position.parse("top x")); // invalid column
        assertThrows(ParseException.class, () -> Position.parse("top,left,extra")); // 3 parts are invalid
        assertThrows(ParseException.class, () -> Position.parse("4 1")); // row out of range
        assertThrows(ParseException.class, () -> Position.parse("1 2 3")); // 3 parts separated by spaces are invalid too
    }
}