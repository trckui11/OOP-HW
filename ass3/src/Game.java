// 216303990 Arbel Feldman

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * The Game Class describes a game with collidables and sprites on a GUI.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;
    static final int FPS = 60;
    static final int BLOCK_THICKNESS = 20;
    static final int BLOCK_LENGTH = 50;

    /**
     * Constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * Add a collidable to the collidables list of the game.
     *
     * @param c the collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add a sprite to the sprite list of the game.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes the game - creates a GUI and the sprites and collidables.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Random rand = new Random();

        // The ball:
        Ball ball = new Ball(400, 500, 8, Color.BLACK);
        ball.setEnvironment(environment);
        int dx = rand.nextInt(3) + 2;
        int dy = rand.nextInt(5) + 2;
        ball.setVelocity(0, 3);
        ball.addToGame(this);

        // The borders:
        Block ceiling = new Block(new Rectangle(new Point(0, 0),
                SCREEN_WIDTH, BLOCK_THICKNESS), Color.GRAY);
        Block floor = new Block(new Rectangle(new Point(
                0, SCREEN_HEIGHT - BLOCK_THICKNESS),
                SCREEN_WIDTH, BLOCK_THICKNESS), Color.GRAY);
        Block leftWall = new Block(new Rectangle(new Point(0,
                BLOCK_THICKNESS), BLOCK_THICKNESS,
                SCREEN_HEIGHT - 2 * BLOCK_THICKNESS), Color.GRAY);
        Block rightWall = new Block(new Rectangle(new Point(
                SCREEN_WIDTH - BLOCK_THICKNESS, BLOCK_THICKNESS),
                BLOCK_THICKNESS,
                SCREEN_HEIGHT - 2 * BLOCK_THICKNESS), Color.GRAY);
        ceiling.addToGame(this);
        floor.addToGame(this);
        leftWall.addToGame(this);
        rightWall.addToGame(this);

        // The paddle:
        Paddle paddle = new Paddle(new Rectangle(
                new Point((SCREEN_WIDTH - BLOCK_LENGTH * 1.5) / 2.0,
                        SCREEN_HEIGHT - 2 * BLOCK_THICKNESS),
                BLOCK_LENGTH * 1.5, BLOCK_THICKNESS),
                keyboard, Color.ORANGE);
        paddle.addToGame(this);

        // The blocks:
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW,
                Color.BLUE, Color.PINK, Color.GREEN};
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j < 13 - i; j++) {
                Point upLeft = new Point(
                        SCREEN_WIDTH - BLOCK_LENGTH * j - BLOCK_THICKNESS,
                        100 + i * BLOCK_THICKNESS);
                Rectangle hitBox = new Rectangle(upLeft, BLOCK_LENGTH,
                        BLOCK_THICKNESS);
                Block block = new Block(hitBox, colors[i]);
                block.addToGame(this);
            }
        }
    }

    /**
     * Runs the game animation.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / FPS;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            /*
            the time it takes to perform the work in each iteration may be
            non-negligible. I therefore subtract the time it takes to do the
            work from the sleep time of the iteration:
             */
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}