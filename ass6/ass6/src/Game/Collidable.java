// 216303990 Arbel Feldman

package Game;

import Misc.Ball;
import Misc.Point;
import Misc.Rectangle;
import Misc.Velocity;

import biuoop.DrawSurface;

/**
 * The interface Game.Collidable is used for any object that has a hitBox and can be collided with.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public interface Collidable {
    /**
     * Gets the collision rectangle (the hitBox).
     *
     * @return the collision rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Upon collision, the method takes a velocity and changes it according to where the object hit the collidable.
     *
     * @param collisionPoint  the point of collision.
     * @param currentVelocity the velocity before changes.
     * @param hitter the ball that hit the collidable.
     * @return the new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Draws the collidable on a draw surface.
     *
     * @param d the draw surface
     */
    void drawOn(DrawSurface d);
}