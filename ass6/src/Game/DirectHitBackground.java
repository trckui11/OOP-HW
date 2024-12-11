// 216303990 Arbel Feldman

package Game;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The background of the Direct hit level.
 */
public class DirectHitBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, GameLevel.SCREEN_WIDTH, GameLevel.SCREEN_HEIGHT);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 140, 50);
        d.drawCircle(400, 140, 75);
        d.drawCircle(400, 140, 100);
        d.drawLine(275, 140, 375, 140);
        d.drawLine(525, 140, 425, 140);
        d.drawLine(400, 115, 400, 15);
        d.drawLine(400, 165, 400, 265);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
