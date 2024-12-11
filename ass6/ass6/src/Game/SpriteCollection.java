// 216303990 Arbel Feldman

package Game;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection of sprite that will all be in the game.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Instantiates a new Game.Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notify all the sprites that 1 unit of time has passed in the game,
     * so they can change their state accordingly.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<>(this.sprites);
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
        List<Sprite> sprites = new ArrayList<>(this.sprites);
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}