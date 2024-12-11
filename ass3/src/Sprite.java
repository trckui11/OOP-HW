// 216303990 Arbel Feldman

import biuoop.DrawSurface;

/**
 * The interface Sprite describes sprites on a draw surface.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public interface Sprite {
    /**
     * Draw the sprite on the draw surface.
     *
     * @param d the draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that 1 unit of time has passed in the game,
     * so it can change its state accordingly.
     */
    void timePassed();

    /**
     * Add the sprite to the game object.
     *
     * @param g the game
     */
    void addToGame(Game g);
}