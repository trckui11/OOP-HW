// 216303990 Arbel Feldman

package Game;

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The Countdown animation before a level starts.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int currentNumber;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the number of seconds the animation is displayed
     * @param countFrom    the number to count from
     * @param gameScreen   the game's sprites to display during the animation
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.currentNumber = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        Color[] countdownColors = {Color.RED, Color.YELLOW, Color.GREEN};

        Sleeper sleeper = new Sleeper();
        sleeper.sleepFor((long) (1000 * numOfSeconds / countFrom));
        d.setColor(countdownColors[(countFrom - currentNumber) % 3]);
        d.drawText(d.getWidth() / 2 - 40, d.getHeight() / 2 + 50, Integer.toString(currentNumber), 150);

        currentNumber -= 1;
    }

    @Override
    public boolean shouldStop() {
        return currentNumber == 0;
    }
}
