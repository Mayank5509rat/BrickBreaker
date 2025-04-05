package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.ArrayList;


public class BrickBreakerGame extends JPanel implements KeyListener, ActionListener, MouseListener {
    private boolean play = false;
    public int score = 0;
    public int totalBricks;
    private Timer timer;
    private int delay = 8;
    private int playerX = 310;
    private List<Ball> balls = new ArrayList<>();
    private int level = 1;
    private boolean scoreSaved = false;
    private boolean isPaused = false;
    private MapGenerator map;
    private List<BallTrail> ballTrails = new ArrayList<>();
    private float backgroundHue = 0;

    public BrickBreakerGame(int row, int col, int level) {
        this.level = level;
        initializeLevel(row, col, level);
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void initializeLevel(int row, int col, int level) {
        row = Math.min(row + level, 20);
        col = Math.min(col + level, 20);

        this.level = level;
        map = new MapGenerator(row, col, level);
        totalBricks = map.getTotalBricks();

        // Reset ball list and add the initial ball
        balls.clear();
        balls.add(new Ball(120, 350, -1, -2));
        scoreSaved = false;
    }

    public void pauseGame() {
        isPaused = true;
        timer.stop();
    }

    public void resumeGame() {
        isPaused = false;
        timer.start();
    }

    public void quitGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
        new MainMenu(); // Go back to Main Menu
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background Animation
        backgroundHue += 0.001f;
        if (backgroundHue > 1) backgroundHue = 0;
        Color backgroundColor = Color.getHSBColor(backgroundHue, 0.5f, 0.5f);
        g2d.setColor(backgroundColor);
        g2d.fillRect(1, 1, 692, 592);

        // Draw Buttons
        g2d.setColor(Color.WHITE);
        g2d.drawRect(20, 20, 100, 30);
        g2d.drawString("Pause", 55, 40);

        g2d.drawRect(140, 20, 100, 30);
        g2d.drawString("Resume", 165, 40);

        g2d.drawRect(260, 20, 100, 30);
        g2d.drawString("Quit", 300, 40);

        // Draw Score and Level
        g2d.setFont(new Font("serif", Font.BOLD, 25));
        g2d.drawString("Score: " + score, 560, 30);
        g2d.drawString("Level: " + level, 400, 30);

        // Draw Bricks
        map.draw(g2d);

        // Draw Paddle
        g2d.setColor(Color.GREEN);
        g2d.fillRect(playerX, 550, 100, 8);

        // Draw Balls
        g2d.setColor(Color.RED);
        for (Ball ball : balls) {
            g2d.fillOval(ball.x, ball.y, 20, 20);
            ballTrails.add(new BallTrail(ball.x, ball.y));
        }

        // Ball Trail Effect
        ballTrails.removeIf(trail -> !trail.update());
        for (BallTrail trail : ballTrails) {
            trail.draw(g2d);
        }

        // Game Over Condition
        if (balls.isEmpty()) {
            play = false;
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("serif", Font.BOLD, 30));
            g2d.drawString("Game Over, Score: " + score, 190, 300);
            if (!scoreSaved) {
                ScoreManager.saveScore(score);
                scoreSaved = true;
            }
        }

        // Level Up Condition
        if (totalBricks <= 0) {
            play = false;
            g2d.setColor(Color.GREEN);
            g2d.setFont(new Font("serif", Font.BOLD, 30));
            g2d.drawString("Level Up!", 260, 300);
            if (!scoreSaved) {
                ScoreManager.saveScore(score);
                scoreSaved = true;
            }
            level++;
            initializeLevel(5 + level, 10 + level, level);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (play && !isPaused) {
            List<Ball> ballsToRemove = new ArrayList<>();
            List<Ball> ballsToAdd = new ArrayList<>();

            for (Ball ball : new ArrayList<>(balls)) {
                ball.x += ball.xDir;
                ball.y += ball.yDir;

                if (ball.x < 0 || ball.x > 670) ball.xDir = -ball.xDir;
                if (ball.y < 0) ball.yDir = -ball.yDir;

                if (new Rectangle(ball.x, ball.y, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                    ball.yDir = -ball.yDir;
                }

                if (ball.y > 570) {
                    ballsToRemove.add(ball); // Remove ball if it goes below the paddle
                } else {
                    map.checkCollision(ball.x, ball.y, this, ball, ballsToAdd);
                }
            }

            // Safely remove and add balls after iteration
            balls.removeAll(ballsToRemove);
            balls.addAll(ballsToAdd);
        }
        repaint();
    }

    

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && playerX < 600) playerX += 20;
        if (e.getKeyCode() == KeyEvent.VK_LEFT && playerX > 10) playerX -= 20;
        if (e.getKeyCode() == KeyEvent.VK_SPACE) play = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (x >= 20 && x <= 120 && y >= 20 && y <= 50) {
            pauseGame();
        } else if (x >= 140 && x <= 240 && y >= 20 && y <= 50) {
            resumeGame();
        } else if (x >= 260 && x <= 360 && y >= 20 && y <= 50) {
            quitGame();
        }
    }

    public List<Ball> getBalls() {
    	return balls;
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}

