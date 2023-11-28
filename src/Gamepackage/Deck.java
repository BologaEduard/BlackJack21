package Gamepackage;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {

        deck = new ArrayList<>();
    }

    public void buildDeck() {
        String[] values = new String[13];

        for(int i=2; i<11; i++)
            values[i-1] = Integer.toString(i);

        values[0] = "A";
        values[10] = "J";
        values[11] = "Q";
        values[12] = "K";

        String[] types = {"C", "D", "H", "S"};

        for(int i=0; i< types.length; i++) {
            for(int j=0; j< values.length; j++) {
                Card card = new Card(values[j], types[i] );
                deck.add(card);
            }
        }

        System.out.println("DECK:");
        System.out.println(deck);

    }

    public  void  shuffleDeck() {
        for(int i=0; i< deck.size(); i++) {
            Random random = new Random();
            int r = random.nextInt(deck.size());

            Card current = deck.get(i);
            Card randomcard  = deck.get(r);

            deck.set(i, randomcard);
            deck.set(r, current);
        }

        System.out.println("SCHUFFELED DECK:");
        System.out.println(deck);

    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public int GetNewCardFromDeck() {
        return deck.size() - 1;
    }

    public Card DeckRemovelast(int last) {
        return deck.remove(last);
    }

    public void printDeck() {
        System.out.print("REMAINED DECK: ");
        for (int i = 0; i < deck.size(); i++) {
            System.out.print(deck.get(i));
            if (i < deck.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

}
