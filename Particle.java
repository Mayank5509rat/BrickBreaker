package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Particle {
    private int x, y;
    private int size;
    private int alpha;
    private int xSpeed, ySpeed;

    public Particle(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = (int) (Math.random() * 5 + 5); // Random size
        this.alpha = 255;
        this.xSpeed = (int) (Math.random() * 10 - 5); // Random horizontal movement
        this.ySpeed = (int) (Math.random() * 10 - 5); // Random vertical movement
    }

    public boolean update() {
        x += xSpeed;
        y += ySpeed;
        alpha -= 10; // Gradually disappear
        return alpha > 0;
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(255, 215, 0, Math.max(alpha, 0))); // Golden effect
        g.fillOval(x, y, size, size);
    }
}
