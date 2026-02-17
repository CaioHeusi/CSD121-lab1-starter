/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate one player in the game.

    You MAY change this to a record/enum as you see fit.
 */
package core;

/**
 * A player in the high card game
 * Each player has a name and hand of cards
 */
public class Player {
    private final String name; // player name
    private final CardStack hand = new CardStack(); // player hand with a personal card stack

    public Player(String name) { // constructor to salve the name in field
        this.name = name;
    }

    public String name() { // method to access the name
        return name;
    }

    public CardStack hand() { // method to access the hand
        return hand;
    }

    public String toString() { // when player was printed, his name will appear, returning his name as a text
        return name;
    }
/*
    public int cardsLeft() {
        return hand.size();
    }

    public boolean hasCards() {
        return hand.hasCards();
    }

    public Card drawTopCard() {
        return hand.drawTop();
    }

    public String toString() {
        return name;
    }
*/
}