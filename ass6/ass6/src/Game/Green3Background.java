// 216303990 Arbel Feldman

package Game;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Level Green 3's background.
 */
public class Green3Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        // building:
        d.setColor(new Color(24, 143, 3));
        d.fillRectangle(0, 0, GameLevel.SCREEN_WIDTH, GameLevel.SCREEN_HEIGHT);
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(118, 150, 10, 330);
        d.setColor(Color.GRAY);
        d.fillRectangle(103, 330, 40, 400);
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(60, 400, 125, 200);

        // point:
        d.setColor(Color.ORANGE);
        d.fillCircle(123, 150, 12);
        d.setColor(Color.RED);
        d.fillCircle(123, 150, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(123, 150, 4);

        // windows:
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(71 + 23 * i, 412 + 38 * j, 11, 26);
            }
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
