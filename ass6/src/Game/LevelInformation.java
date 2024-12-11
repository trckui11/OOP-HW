// 216303990 Arbel Feldman

package Game;

import Misc.Block;
import Misc.Velocity;

import java.util.List;

/**
 * The interface Level information completely describes different levels.
 */
public interface LevelInformation {
    /**
     * @return The number of balls in the level.
     */
    int numberOfBalls();

    /**
     * @return A list of the balls' initial velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return The paddle's speed.
     */
    int paddleSpeed();

    /**
     * @return The paddle's width.
     */
    int paddleWidth();

    /**
     * @return The level's name.
     */
    String levelName();

    /**
     * @return Whe level's background.
     */
    Sprite getBackground();

    /**
     * @return A list of all the blocks in the level.
     */
    List<Block> blocks();

    /**
     * @return The number of blocks you need to remove before the level is considered "cleared".
     */
    int numberOfBlocksToRemove();
}
