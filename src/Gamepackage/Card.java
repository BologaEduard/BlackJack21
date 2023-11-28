package Gamepackage;

/**
 * The Card class represents a playing card with a specific value and type.
 */
public class Card {
    /** The value of the card (e.g., 2, 3, ..., 10, J, Q, K, A). */
    private String value;
    /** The type of the card (e.g., hearts, diamonds, clubs, spades). */
    private String  type;

    /** Default constructor that initializes the card with empty values. */
    public Card() {
        value = "";
        type = "";
    }

    /**
     * Constructor that creates a card with the specified value and type.
     *
     * @param value The value of the card.
     * @param type  The type of the card.
     */
    public Card(String value, String type) {
        this.type = type;
        this.value = value;
    }

    /**
     * Returns a string representation of the card, combining its value and type.
     *
     * @return The string representation of the card.
     */
    public  String toString() {
        return value + "-" + type;
    }

    /**
     * Returns the numerical value of the card.
     *
     * @return The numerical value of the card.
     */
    public int getValue() {
        if("AJQK".contains(value)) {
            if (value == "A") {
                return 11;
            }
            return  10;
        }
        return Integer.parseInt(value);
    }

    /**
     * Checks if the card is an Ace.
     *
     * @return true if the card is an Ace, false otherwise.
     */
    public  boolean isAce() {
        return  value == "A";
    }

    /**
     * Returns the file path of the card's image.
     *
     * @return The file path of the card's image.
     */
    public String getImgPath() {
        return "./cards/" + toString() + ".png";
    }

    
}
