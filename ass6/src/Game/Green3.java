// 216303990 Arbel Feldman

package Game;

import Misc.Block;
import Misc.Point;
import Misc.Rectangle;
import Misc.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The level "Green 3".
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(45, 7));
        velocities.add(Velocity.fromAngleAndSpeed(-45, 7));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 75;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3Background();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK};
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 11 - i; j++) {
                Point upLeft = new Point(GameLevel.SCREEN_WIDTH - 50 * j - GameLevel.BLOCK_THICKNESS,
                        100 + i * GameLevel.BLOCK_THICKNESS);
                Rectangle hitBox = new Rectangle(upLeft, 50, GameLevel.BLOCK_THICKNESS);
                Block block = new Block(hitBox, colors[i]);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
