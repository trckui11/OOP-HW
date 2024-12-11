package Game;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The screen Pausing feature. This class is using the KeyPressStoppableAnimation class.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        // The background:
        d.setColor(Color.PINK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        // The text:
        d.setColor(Color.CYAN);
        d.drawText(d.getWidth() / 2 - 33 * 7, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
