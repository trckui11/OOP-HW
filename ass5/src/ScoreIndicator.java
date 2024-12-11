// 216303990 Arbel Feldman

import biuoop.DrawSurface;

/**
 * This class keeps track of the score in the game.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructor.
     *
     * @param score the Counter that marks the score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(Game.SCREEN_WIDTH / 2 - 40, 17, "Score: " + score.getValue(), 20);
    }

    @Override
    public void timePassed() {
        // this doesn't need to do anything.
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
