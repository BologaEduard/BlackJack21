package Gamepackage;
import java.util.ArrayList;
import java.util.Collection;

public class WeakBot extends Player{

     public Card hiddenCard;
     public Card faceduupcard;
    

    public WeakBot(ArrayList<Card> deck) {
        hand = new ArrayList<Card>();
        hiddenCard = deck.remove(deck.size()-1);
        faceduupcard = deck.remove(deck.size() - 1);
        handValue = hiddenCard.getValue() + faceduupcard.getValue();
        acecount = (hiddenCard.isAce() ? 1 : 0) + (faceduupcard.isAce() ? 1 : 0);
        hand.add(faceduupcard);

        printBotData();
    }

    // Get the dealer's face-up card (the first card)
    public Card getFaceUpCard() {
        if (hand.isEmpty()) {
            return null;
        }
        return hand.get(0);
    }

    
    public void decideNextMove(Deck deck) {
        while (handValue < 17) {
            Card newcard = deck.DeckRemovelast(deck.GetNewCardFromDeck());
            handValue += newcard.getValue();
            acecount += newcard.isAce()? 1:0;
            hand.add(newcard);
            if(handValue > 21 && acecount > 0) {
                handValue = AceToOne();
            }
        }
    }

    public void printBotData() {
        System.out.println("BOT:");
        System.out.println("Hidden card: " + hiddenCard);
        System.out.println("Hand:" + hand);
        System.out.println("Hand value: " + handValue);
        System.out.println("Ace count:" + acecount);
        System.out.println("Jetons: " + jetons);
    }

    public int getJetons() {
        return jetons;
    }

    
    public boolean hasBlackjack() {
        if (hand.size() == 2 && hiddenCard.getValue() + faceduupcard.getValue() == 21) {
            for (Card card : hand) {
                if (card.isAce() || card.getValue() == 10) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setBotForNextRound(ArrayList<Card> deck) {
        hand = new ArrayList<Card>();
        hiddenCard = deck.remove(deck.size()-1);
        faceduupcard = deck.remove(deck.size() - 1);
        handValue = hiddenCard.getValue() + faceduupcard.getValue();
        acecount = (hiddenCard.isAce() ? 1 : 0) + (faceduupcard.isAce() ? 1 : 0);
        hand.add(faceduupcard);
        bet = 0;
    }
   
}
