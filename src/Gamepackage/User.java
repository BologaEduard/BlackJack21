package Gamepackage;
import java.util.ArrayList;

public class User extends Player {

    public Card startcard1;
    public Card startcard2;
    public String name;

    public User() {
        startcard1 = new Card();
        startcard2 = new Card();
        name = " ";
    }

    public User(ArrayList<Card> deck) {
        hand = new ArrayList<Card>();
        startcard1 = deck.remove(deck.size() - 1);
        startcard2 = deck.remove(deck.size() - 1);
        handValue = startcard1.getValue() + startcard2.getValue();
        acecount = (startcard1.isAce() ? 1 : 0) + (startcard2.isAce() ? 1 : 0);
        hand.add(startcard1);
        hand.add(startcard2);

        printUserData();

    }
    
    public void printUserData() {
        System.out.println("USER:");
        System.out.println("User's hand:" + hand);
        System.out.println("Hand value: " + handValue);
        System.out.println("Ace count: " + acecount);
        System.out.println("Jetons: " + jetons);
    }

    public boolean hasBlackjack() {
        if (hand.size() == 2 && handValue == 21) {
            for (Card card : hand) {
                if (card.isAce() || card.getValue() == 10) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getJetons() {
        return jetons;
    }

    public void setUserForNextRound(ArrayList<Card> deck) {
        hand = new ArrayList<Card>();
        startcard1 = deck.remove(deck.size() - 1);
        startcard2 = deck.remove(deck.size() - 1);
        handValue = startcard1.getValue() + startcard2.getValue();
        acecount = (startcard1.isAce() ? 1 : 0) + (startcard2.isAce() ? 1 : 0);
        hand.add(startcard1);
        hand.add(startcard2);
        bet = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void printName() {
        System.out.println("USER NAME: "+ name);
    }

    public void setCard1(Card card) {
        this.startcard1 = card;
    }

    public void setCard2(Card card) {
        this.startcard1 = card;
    }
    
    
}
