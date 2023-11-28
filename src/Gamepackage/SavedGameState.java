package Gamepackage;
import java.io.Serializable;

public class SavedGameState implements Serializable{
    private int userJetons;
    private int botJetons;

    public SavedGameState(int ujetons, int bjetons) {
        userJetons = ujetons;
        botJetons = bjetons;
    }

    public int getuserJetons() {
        return userJetons;
    }

    public int getbotJetons() {
        return botJetons;
    }

    public void setuserJetons(int jetons) {
        userJetons  =jetons;
    }

    public void setbotJetons(int jetons) {
        botJetons  = jetons;
    }
}
