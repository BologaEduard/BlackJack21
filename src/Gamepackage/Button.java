package Gamepackage;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The Button class represents a collection of buttons for a game interface.
 * It includes buttons for actions like hit, stay, bet, next, new game, load game,
 * submit, save, and exit.
 */
public class Button {
    JPanel buttonPanel;
    JButton hitButton;
    JButton stayButton;
    JButton betButton;
    JButton nextButton;
    JButton starButton;
    JButton loadButton;
    JButton submitButton;
    JButton saveButton;
    JButton exitButton;

    /**
     * Constructs a new instance of the Button class.
     * Initializes all the buttons and sets their labels.
     */
    public Button () {
        
        buttonPanel = new JPanel();
        hitButton = new JButton("Hit");
        stayButton = new JButton("Stay");
        betButton = new JButton("Bet");
        nextButton = new JButton("Next");
        starButton = new JButton("New Game");
        loadButton = new JButton("Load Game");
        submitButton = new JButton("Submit");

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(0x00FF3A));

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(0xEFE10404, true));

    }

}
