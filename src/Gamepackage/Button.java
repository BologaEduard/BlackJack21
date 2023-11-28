package Gamepackage;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

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
        saveButton.setBackground(new Color(0x41d61c));

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(0xfc0307));

    }

}
