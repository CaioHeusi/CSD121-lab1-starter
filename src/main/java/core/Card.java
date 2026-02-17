/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent one playing card.

    You MAY change this to a record/enum as you see fit.
 */
package core;

/**
 * A playing card with rank and suit from a standard 52 card deck
 * A card has rank and suit
 */

public record Card(Rank rank, Suit suit) {

    public String toString() { // this together suit and rank
        return rank.display + suit.symbol;
    }

    public enum Suit { // enum to store suits
        CLUBS("♣"),
        DIAMONDS("♦"),
        HEARTS("♥"),
        SPADES("♠");

        public final String symbol; // suit symbol

        Suit(String symbol) { // enum constructor
            this.symbol = symbol; // save symbol in the field
        }
    }

    public enum Rank { // enum to store ranks
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("10"),
        JACK("J"),
        QUEEN("Q"),
        KING("K"),
        ACE("A");

        public final String display; // short rank text

        Rank(String display) { // enum constructor
            this.display = display; // save rank text in the field

        }


    /*
    public int compareRank(Card other) {
        return Integer.compare(this.rank.value(), other.rank.value());
    }
     */
    }
}
