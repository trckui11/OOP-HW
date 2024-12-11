// 216303990 Arbel Feldman

package Game;

import Misc.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The End screen animation (win or lose).
 */
public class EndScreen implements Animation {
    private SpriteCollection gameScreen;
    private boolean winOrLose;
    private Counter score;

    /**
     * Instantiates a new end screen animation.
     *
     * @param gameScreen the game's sprites
     * @param winOrLose  the win or lose indicator
     * @param score      the game's score
     */
    public EndScreen(SpriteCollection gameScreen, boolean winOrLose, Counter score) {
        this.gameScreen = gameScreen;
        this.winOrLose = winOrLose;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        if (winOrLose) {
            d.setColor(Color.GREEN);
            d.drawText(d.getWidth() / 2 - 28 * 7, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 32);
            return;
        }
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2 - 31 * 7, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
