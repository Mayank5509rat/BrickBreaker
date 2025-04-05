package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

public class MapGenerator {

    public int[][] map;
    public int brickWidth;
    public int brickHeight;
    public int totalBricks;

    public MapGenerator(int row, int col, int level) {
        row = Math.max(row, 10);
        col = Math.max(col, 15);

        map = new int[row][col];
        brickWidth = 280 / (col / 2);
        brickHeight = 150 / (row / 2);

        // Initialize bricks: Border bricks, normal bricks, indestructible, and special bricks
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0 || i == row - 1 || j == col - 1) {
                    map[i][j] = 1; // Border bricks
                } else if (level >= 1 && Math.random() < 0.3) {
                    map[i][j] = 3; // Special bricks with 30% chance
                } else if (level >= 1 && Math.random() < 0.2) {
                    map[i][j] = 2; // Indestructible bricks
                } else {
                    map[i][j] = 1; // Normal bricks
                }
            }
        }
        totalBricks = getTotalBricks();
    }

    public void draw(Graphics2D g) {
        int startX = (700 - (brickWidth * map[0].length)) / 2;
        int startY = 50;

        for (int i = 0; i < map.length - 4; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    int xOffset = (i % 2 == 1) ? brickWidth / 2 : 0;
                    int brickX = startX + j * brickWidth + xOffset;
                    int brickY = startY + i * brickHeight;

                    if (map[i][j] == 1) {
                        g.setColor(new Color(205, 133, 63)); // Normal brick
                    } else if (map[i][j] == 2) {
                        g.setColor(Color.GRAY); // Indestructible brick
                    } else if (map[i][j] == 3) {
                        g.setColor(new Color(30, 144, 255)); // Special brick (Blue)
                    }

                    g.fillRect(brickX, brickY, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(0.5f));
                    g.setColor(Color.BLACK);
                    g.drawRect(brickX, brickY, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        if (map[row][col] != 2) { // Don't remove indestructible bricks
            map[row][col] = value;
            totalBricks = getTotalBricks();
        }
    }

    public int getTotalBricks() {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1 || map[i][j] == 3) {
                    count++;
                }
            }
        }
        return count;
    }

    public void checkCollision(int ballX, int ballY, BrickBreakerGame game, Ball ball, List<Ball> ballsToAdd) {
        int startX = (700 - (brickWidth * map[0].length)) / 2;
        int startY = 50;

        for (int i = 0; i < map.length - 4; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    int xOffset = (i % 2 == 1) ? brickWidth / 2 : 0;
                    int brickX = startX + j * brickWidth + xOffset;
                    int brickY = startY + i * brickHeight;

                    Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballRect = new Rectangle(ballX, ballY, 20, 20);

                    if (ballRect.intersects(brickRect)) {
                        if (map[i][j] == 1) {
                            setBrickValue(0, i, j);
                            game.score += 5;
                        } else if (map[i][j] == 3) {
                            setBrickValue(0, i, j);
                            game.score += 10;

                            // Double the ball by adding a new ball
                            ballsToAdd.add(new Ball(ball.x, ball.y, ball.xDir, ball.yDir));
                        }

                        // Adjust ball direction on collision
                        if (ballX + 19 <= brickX || ballX + 1 >= brickX + brickWidth) {
                            ball.xDir = -ball.xDir;
                        } else {
                            ball.yDir = -ball.yDir;
                        }
                        return;
                    }
                }
            }
        }
    }
} 
