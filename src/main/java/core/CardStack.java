/*
    NOTE:

    Add to this type any variables and/or methods required
    to represent/manipulate a stack (deck/hand) of playing cards.

    You MAY change this to a record/enum as you see fit.
 */
package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * A stack of cards used as a deck or a player's hand
 * The top of the stack is index 0
 */
public class CardStack {

    private final List<Card> cards = new ArrayList<>(); // encapsulated list of cards

    public void createFullDeck() { // create a deck with 52 cards
        cards.clear(); // clean any card that already had
        for (Card.Suit s : Card.Suit.values()) { // go through all suits
            for (Card.Rank r : Card.Rank.values()) { // go through all ranks
                cards.add(new Card(r, s)); // add a card with rank and suit
            }
        }
    }

    public void shuffle() { // method from Collections ready to shuffle, I just enjoyed it
        Collections.shuffle(cards);
    }

    public Card draw() { // buy or remove the card from the top of the deck
        return cards.isEmpty() ? null : cards.remove(0); // if it's empty return null, otherwise remove index 0
    }

    public void add(Card card) { // add a card at final of the list
        cards.add(card);
    }

    public boolean hasCards() { // verify if still has cards
        return !cards.isEmpty(); // true means that is not empty
    }

    public int size() { // the quantity of cards
        return cards.size(); // return the size of list
    }
}