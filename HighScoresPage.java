package game;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HighScoresPage extends JFrame {
    public HighScoresPage() {
        setTitle("High Scores");
        setSize(400, 600);
        setLayout(new BorderLayout());

        JTextArea scoreArea = new JTextArea();
        scoreArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(scoreArea);

        try (BufferedReader reader = new BufferedReader(new FileReader("highscores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scoreArea.append(line + "\n");
            }
        } catch (IOException e) {
            scoreArea.append("No high scores available.\n");
        }

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
}
