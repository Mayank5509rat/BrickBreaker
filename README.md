Brick Breaker Game
Project Title
Brick Breaker Game with Enhanced Visual Effects

Team Member
MAYANK RATHORE

Project Description
This is an advanced version of the classic Brick Breaker game with multiple levels, increasing difficulty, particle effects, and a high score tracking system. Players can progress through levels by breaking all the bricks while avoiding the ball from falling off the screen. Indestructible bricks are added in higher levels for extra challenge.

Features
Multiple Levels: Levels become harder with increased ball speed and more indestructible bricks.

Particle Effects: Visual particles upon breaking bricks.

Ball Trail Effect: Enhanced graphics using ball trails.

High Score Management: Persistent high score storage using a text file.

Dynamic Background: Animated background color transition.

Files and Purpose
BallTrail.java: Implements the ball trail effect for smoother visuals.

BrickBreakerGame.java: Main game logic, including rendering, collisions, level management, and background animation.

HighScoresPage.java: Displays the top scores by reading from highscores.txt.

MainMenu.java: Provides a main menu with options to start the game, view high scores, or exit.

MapGenerator.java: Generates the brick layout for each level, including special indestructible bricks.

Particle.java: Manages particle effects for visual feedback when bricks are destroyed.

ScoreManager.java: Handles high score saving and display.

Libraries Used
Java Swing: For GUI components.

Java AWT: For graphics rendering.

Installation and Setup
Ensure Java (JDK 8 or later) is installed.

Compile the project using javac *.java.

Run the program using java game.MainMenu.

A highscores.txt file will be created automatically if not present.

Screenshots / Video
https://drive.google.com/file/d/1VUoF0DmAvjnkEIiPEB3FwUEBwH80DhFM/view?usp=drive_link

Additional Notes
Delete highscores.txt if you wish to reset high scores.

Game progress resets when the application is closed.# BrickBreaker
