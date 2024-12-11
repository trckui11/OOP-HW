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
 * The level "Wide Easy".
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed((i + 1) * 11, 6));
        }
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed((i + 1) * -11, 6));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return (int) (GameLevel.SCREEN_WIDTH * 0.75);
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.RED, Color.RED,
                Color.ORANGE, Color.ORANGE,
                Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN,
                Color.BLUE, Color.BLUE,
                Color.PINK, Color.PINK,
                Color.CYAN, Color.CYAN};
        List<Block> blocks = new ArrayList<>();
        double length = (GameLevel.SCREEN_WIDTH - 2 * GameLevel.BLOCK_THICKNESS) / 15.0;
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(new Point(GameLevel.BLOCK_THICKNESS + i * length,
                    400), length, GameLevel.BLOCK_THICKNESS);
            Block block = new Block(rect, colors[i]);
            blocks.add(block);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
