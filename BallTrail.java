package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class BallTrail {
    private int x, y;
    private int alpha = 255; // Starting opacity

    public BallTrail(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean update() {
        alpha -= 10; // Gradually fade out
        return alpha > 0;
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(255, 0, 0, Math.max(alpha, 0))); // Red trail effect
        g.fillOval(x, y, 20, 20);
    }
}
