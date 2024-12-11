// 216303990 Arbel Feldman

package Game;

import biuoop.DrawSurface;

/**
 * This class is in charge of displaying the level names.
 */
public class LevelName implements Sprite {
    private String levelName;

    /**
     * Instantiates a object.
     *
     * @param levelName the level name
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(GameLevel.SCREEN_WIDTH / 2, 20, "Level Name: " + levelName, 20);
    }

    @Override
    public void timePassed() {
        // this doesn't need to do anything.
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
