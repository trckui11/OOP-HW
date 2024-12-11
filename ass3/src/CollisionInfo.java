// 216303990 Arbel Feldman

/**
 * The type Collision info.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * CollisionInfo constructor.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * The point at which the collision occurs.
     *
     * @return The collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }


    /**
     * the collidable object involved in the collision.
     *
     * @return the collidable.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}