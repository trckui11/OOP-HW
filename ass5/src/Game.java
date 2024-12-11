// 216303990 Arbel Feldman

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;

/**
 * The Game Class describes a game with collidables and sprites on a GUI.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Color background;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
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
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = new Counter(0);
        this.background = Color.WHITE;
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
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);

        // The balls:
        for (int i = 0; i < 3; i++) {
            Ball ball = new Ball(450, 540, 8, Color.BLACK);
            ball.setEnvironment(environment);
            int dx = rand.nextInt(9) - 4;
            int dy = -rand.nextInt(2) - 3;
            ball.setVelocity(dx, dy);
            ball.addToGame(this);
            remainingBalls.increase(1);
        }

        // The borders:
        Block ceiling = new Block(new Rectangle(new Point(0, 0), SCREEN_WIDTH, BLOCK_THICKNESS), Color.GRAY);
        Block floor = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT), SCREEN_WIDTH, BLOCK_THICKNESS), Color.GRAY);
        Block leftWall = new Block(new Rectangle(new Point(0, BLOCK_THICKNESS),
                BLOCK_THICKNESS, SCREEN_HEIGHT - BLOCK_THICKNESS), Color.GRAY);
        Block rightWall = new Block(new Rectangle(new Point(SCREEN_WIDTH - BLOCK_THICKNESS,
                BLOCK_THICKNESS), BLOCK_THICKNESS, SCREEN_HEIGHT - BLOCK_THICKNESS), Color.GRAY);
        ceiling.addToGame(this);
        // The floor detects when to remove a ball:
        floor.addToGame(this);
        floor.addHitListener(ballRemover);
        leftWall.addToGame(this);
        rightWall.addToGame(this);

        // The paddle:
        Paddle paddle = new Paddle(new Rectangle(new Point((SCREEN_WIDTH - Paddle.LENGTH) / 2.0,
                SCREEN_HEIGHT - 2 * BLOCK_THICKNESS), Paddle.LENGTH, BLOCK_THICKNESS), keyboard, Color.ORANGE);
        paddle.addToGame(this);

        // The blocks:
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j < 13 - i; j++) {
                Point upLeft = new Point(SCREEN_WIDTH - BLOCK_LENGTH * j - BLOCK_THICKNESS, 100 + i * BLOCK_THICKNESS);
                Rectangle hitBox = new Rectangle(upLeft, BLOCK_LENGTH, BLOCK_THICKNESS);
                Block block = new Block(hitBox, colors[i]);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTracker);
                remainingBlocks.increase(1);
            }
        }

        // The score:
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);
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
            // The background:
            d.setColor(background);
            d.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            if (remainingBlocks.getValue() == 0) {
                // A win gives 100 points:
                score.increase(100);
                this.sprites.drawAllOn(d);
                gui.show(d);
                sleeper.sleepFor(2000);
                gui.close();
                return;
            }
            if (remainingBalls.getValue() == 0) {
                sleeper.sleepFor(2000);
                gui.close();
                return;
            }

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

    /**
     * Remove a collidable from the list.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove a sprite from the list.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}