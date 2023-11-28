package Gamepackage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class JetonManager {

    private static HashMap<String,SavedGameState> map = new HashMap<>();

    private static final SavedGameState DEFAULT_VALUES = new SavedGameState(1000, 1000);
    private static final String SAVE_PATH_FILE = "Jeton_data.jetons";

    public static void Save() {

        try (FileOutputStream output = new FileOutputStream(SAVE_PATH_FILE)) {

            try(ObjectOutputStream oos = new ObjectOutputStream(output)) {
                oos.writeObject(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void Load() {
        try (FileInputStream input  = new FileInputStream(SAVE_PATH_FILE)) {

            try(ObjectInputStream ois = new ObjectInputStream(input)) {
                map = (HashMap<String, SavedGameState>)ois.readObject();
            } catch (ClassNotFoundException e) {
                map = new HashMap<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void Set(String name, SavedGameState state) {
        map.put(name, state);
    }

    public static SavedGameState Get(String name) {
        return map.getOrDefault(name, DEFAULT_VALUES);
    }
    
}
