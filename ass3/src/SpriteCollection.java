// 216303990 Arbel Feldman

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of sprite that will all be in the game.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add a sprite to the collection.
     *
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notify all the sprites that 1 unit of time has passed in the game,
     * so they can change their state accordingly.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all the sprites on the draw surface.
     *
     * @param d the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}