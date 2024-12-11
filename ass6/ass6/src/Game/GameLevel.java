// 216303990 Arbel Feldman

package Game;

import Misc.Point;
import Misc.Rectangle;
import Misc.Ball;
import Misc.Counter;
import Misc.Block;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The Game class describes a game with collidables and sprites on a GUI.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022 -09-20
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;
    static final int BLOCK_THICKNESS = 25;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int FPS = 60;

    /**
     * Constructor.
     *
     * @param levelInfo       the level played.
     * @param keyboardSensor  the keyboard sensor
     * @param animationRunner the animation runner
     * @param score           the game's score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.levelInfo = levelInfo;
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.score = score;
    }

    public SpriteCollection sprites() {
        return sprites;
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
     * Remaining blocks counter.
     *
     * @return the counter
     */
    public Counter remainingBlocks() {
        return remainingBlocks;
    }

    /**
     * Remaining balls counter.
     *
     * @return the counter
     */
    public Counter remainingBalls() {
        return remainingBalls;
    }

    /**
     * Initializes the game - creates a GUI and the sprites and collidables.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);

        // The background:
        levelInfo.getBackground().addToGame(this);

        // The balls:
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(SCREEN_WIDTH / 2, SCREEN_HEIGHT - 60, 5, Color.WHITE);
            ball.setEnvironment(environment);
            ball.setVelocity(levelInfo.initialBallVelocities().get(i));
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
        Paddle paddle = new Paddle(new Rectangle(new Point((SCREEN_WIDTH - levelInfo.paddleWidth()) / 2.0,
                SCREEN_HEIGHT - 2 * BLOCK_THICKNESS), levelInfo.paddleWidth(), BLOCK_THICKNESS), keyboard,
                Color.ORANGE, levelInfo.paddleSpeed());
        paddle.addToGame(this);

        // The blocks:
        for (Block block : levelInfo.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            remainingBlocks.increase(1);
        }

        // Level name:
        LevelName levelName = new LevelName(levelInfo.levelName());
        levelName.addToGame(this);
    }

    /**
     * Runs the game animation.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        Sleeper sleeper = new Sleeper();
        sleeper.sleepFor(1000 * 2 / 3);
        this.running = true;
        this.runner.run(this);
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

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        // Stopping conditions:
        if (remainingBlocks.getValue() == levelInfo.blocks().size() - levelInfo.numberOfBlocksToRemove()) {
            // A win gives 100 points:
            score.increase(100);
            this.running = false;
        }
        if (remainingBalls.getValue() == 0) {
            EndScreen loseScreen = new EndScreen(sprites, false, score);
            KeyPressStoppableAnimation endScreen = new KeyPressStoppableAnimation(keyboard, " ", loseScreen, runner);
            runner.run(endScreen);
            runner.gui().close();
            this.running = false;
        }

        // Pausing the game:
        if (this.keyboard.isPressed("p")) {
            PauseScreen pause = new PauseScreen();
            KeyPressStoppableAnimation pauseScreen = new KeyPressStoppableAnimation(keyboard, " ", pause, runner);
            this.runner.run(pauseScreen);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}