package Gamepackage;
public class Card {
    private String value;
    private String  type;

    public Card() {
        value = "";
        type = "";
    }

    public Card(String value, String type) {
        this.type = type;
        this.value = value;
    }

    public  String toString() {
        return value + "-" + type;
    }

    public int getValue() {
        if("AJQK".contains(value)) {
            if (value == "A") {
                return 11;
            }
            return  10;
        }
        return Integer.parseInt(value);
    }

    public  boolean isAce() {
        return  value == "A";
    }

    public String getImgPath() {
        return "./cards/" + toString() + ".png";
    }

    
}
