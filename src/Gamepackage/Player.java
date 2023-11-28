package Gamepackage;
import java.util.ArrayList;

public class Player {

    protected ArrayList<Card> hand;

    public int handValue = 0;
    public int acecount = 0;
    public int jetons = 1000;
    public int bet = 0;

    public Player() {
        hand = new ArrayList<Card>();
    }
    
    // Add a card to the player's hand
    public void addCard(Card card) {
        hand.add(card);
        handValue += card.getValue();
    }

    // Get the player's hand value
    protected void setHandValue(int value) {
        handValue = value;
    }


    // Get the player's hand (list of cards)
    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getHandValue() {
        return handValue;
    }

    // Check if the player's hand contains an Ace
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

    public int AceToOne() {
        while (handValue > 21 && acecount > 0) {
            handValue -= 10;
            acecount--;
        }
        return handValue;
    }

    public boolean outOfMoney(){
        return jetons == 0;
    }

    public void setBet(int bet){
        this.bet = bet;
    }

}
