package Gamepackage;
import java.util.ArrayList;

/**
 * The Player class represents a participant in the Blackjack game.
 * It includes methods for managing the player's hand, calculating hand value,
 * handling aces, and managing player-specific attributes such as jetons and bets.
 */
public class Player {
    /** The list of cards in the player's hand. */
    protected ArrayList<Card> hand;

    public int handValue = 0;
    public int acecount = 0;
    public int jetons = 1000;
    public int bet = 0;

    /**
     * Constructs a new instance of the Player class.
     * Initializes the player's hand as an empty list.
     */
    public Player() {
        hand = new ArrayList<Card>();
    }

    /**
     * Adds a card to the player's hand and updates the hand value.
     *
     * @param card The card to be added to the player's hand.
     */
    public void addCard(Card card) {
        hand.add(card);
        handValue += card.getValue();
    }

    /**
     * Sets the player's hand value to the specified value.
     *
     * @param value The new value to set for the player's hand.
     */
    protected void setHandValue(int value) {
        handValue = value;
    }


    /**
     * Retrieves the list of cards in the player's hand.
     *
     * @return The list of cards in the player's hand.
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getHandValue() {
        return handValue;
    }

    /**
     * Checks if the player's hand contains an Ace.
     *
     * @return true if the player's hand contains an Ace, false otherwise.
     */
    public boolean hasAce() {
        for (Card card : hand) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    public void clearHand() {
        hand.clear();
        handValue = 0;
    }

    /**
     * Converts Aces in the player's hand from 11 to 1 if necessary.
     *
     * @return The adjusted hand value after converting Aces.
     */
    public int AceToOne() {
        while (handValue > 21 && acecount > 0) {
            handValue -= 10;
            acecount--;
        }
        return handValue;
    }
    /**
     * Checks if the player is out of jetons (money).
     *
     * @return true if the player is out of money, false otherwise.
     */
    public boolean outOfMoney(){
        return jetons == 0;
    }

    /**
     * Sets the bet amount for the current round.
     *
     * @param bet The bet amount to set.
     */
    public void setBet(int bet){
        this.bet = bet;
    }

}
