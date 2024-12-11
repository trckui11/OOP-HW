// 216303990 Arbel Feldman

package Game;

import biuoop.DrawSurface;

/**
 * The interface Animation describes different types of animations on the screen.
 */
public interface Animation {
    /**
     * A single frame of the animation.
     *
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should the animation stop.
     *
     * @return a boolean result.
     */
    boolean shouldStop();
}
