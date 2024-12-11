// 216303990 Arbel Feldman

package Game;

import Misc.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The Game's flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the animation runner
     * @param ks the keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * Runs levels in order.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        // The score:
        Counter score = new Counter(0);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        GameLevel lastLevel = null;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner, score);
            lastLevel = level;

            level.initialize();
            scoreIndicator.addToGame(level);

            while (level.remainingBalls().getValue() > 0
                    && level.remainingBlocks().getValue() > 0) {
                level.run();
            }

            if (level.remainingBalls().getValue() == 0) {
                return;
            }
        }
        // if all the levels end, you win:
        EndScreen winScreen = new EndScreen(lastLevel.sprites(), true, score);
        KeyPressStoppableAnimation endScreen =
                new KeyPressStoppableAnimation(keyboardSensor, " ", winScreen, animationRunner);
        animationRunner.run(endScreen);
        animationRunner.gui().close();
    }
}
