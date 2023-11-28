package Gamepackage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuWindow{

    private int menuWidth;
    private int menuheight;
    private JFrame frame;

    public MenuWindow() {
        menuWidth = 1300;
        menuheight = 740;

        Button button  = new Button();

        JetonManager.Load();

        frame = new JFrame("EDU CASIONO");

        frame.setVisible(true);
        frame.setSize(menuWidth,menuheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMenu();

        button.buttonPanel.setBounds(0, menuheight - 50, menuWidth, 75);
        button.starButton.setFocusable(false);
        button.buttonPanel.add(button.starButton);

        button.loadButton.setFocusable(false);
        button.buttonPanel.add(button.loadButton);

        frame.add(button.buttonPanel, BorderLayout.SOUTH);

        button.starButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnterNameWindow enterName = new EnterNameWindow();
                enterName.EnterNameWindowNew();
                frame.setVisible(false);
            }
        
        });

        button.loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnterNameWindow namewindow = new EnterNameWindow();
                namewindow.EnterNameWindowLoad();

                namewindow.setVisible(false);

                frame.setVisible(false);
            }   
        });

        frame.revalidate();

    }

    public void setMenu() {
        ImageIcon bgIcon = new ImageIcon("educasino.jpg");
        JLabel bgLabel = new JLabel(bgIcon);
        frame.getContentPane().add(bgLabel, BorderLayout.CENTER);
    }
}
