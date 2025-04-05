package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    private JButton startButton, highScoresButton, exitButton;

    public MainMenu() {
        setTitle("Brick Breaker - Main Menu");
        setSize(700, 600);
        setLayout(null);

        // Adding background image
        JLabel backgroundLabel = new JLabel(new ImageIcon("C:\\mayank rathore/background.jpg"));
        backgroundLabel.setBounds(0, 0, 700, 600);
        add(backgroundLabel);

        startButton = new JButton("Start Game");
        startButton.setBounds(250, 200, 200, 50);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.addActionListener(this);
        add(startButton);
        backgroundLabel.add(startButton);

        highScoresButton = new JButton("High Scores");
        highScoresButton.setBounds(250, 280, 200, 50);
        highScoresButton.addActionListener(this);
        add(highScoresButton);
        backgroundLabel.add(highScoresButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(250, 360, 200, 50);
        exitButton.addActionListener(this);
        add(exitButton);
        backgroundLabel.add(exitButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            JFrame frame = new JFrame("Brick Breaker Game");
            BrickBreakerGame game = new BrickBreakerGame(8, 14, 1);
            frame.setBounds(10, 10, 700, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(game);
            frame.setVisible(true);
            dispose();
        } else if (e.getSource() == highScoresButton) {
            new HighScoresPage();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
