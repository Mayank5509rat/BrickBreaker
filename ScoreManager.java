package game;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class ScoreManager {
    private static final String FILE_PATH = "highscores.txt";

    public static void saveScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(String.valueOf(score));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showHighScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            List<Integer> scores = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }
            scores.sort(Collections.reverseOrder());

            StringBuilder scoreDisplay = new StringBuilder("Top High Scores:\n");
            for (int i = 0; i < Math.min(scores.size(), 10); i++) {
                scoreDisplay.append((i + 1)).append(". ").append(scores.get(i)).append("\n");
            }
            JOptionPane.showMessageDialog(null, scoreDisplay.toString(), "High Scores", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No scores available yet.", "High Scores", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
