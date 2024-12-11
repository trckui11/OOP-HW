// 216303990 Arbel Feldman

/**
 * Class Velocity specifies the change in position on
 * the X and Y axes for objects.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022 -09-20
 */
public class Velocity {
    private double dx;
    private double dy;
    static final int RIGHT_ANGLE = 90;

    /**
     * Default Constructor.
     *
     * @param dx change in X value.
     * @param dy change in Y value.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Convert velocity from angle and speed to dx and dy.
     *
     * @param angle the angle (deg) the ball will move (clockwise, 0 is up).
     * @param speed the ball's speed (pixels per frame).
     * @return the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Using polar->cartesian conversion+offset angle by 90deg to get 0=up:
        double dx = speed * Math.cos(Math.toRadians(angle - RIGHT_ANGLE));
        double dy = speed * Math.sin(Math.toRadians(angle - RIGHT_ANGLE));
        return new Velocity(dx, dy);
    }

    /**
     * dx getter.
     *
     * @return current object's dx.
     */
    public double getDX() {
        return dx;
    }

    /**
     * dy getter.
     *
     * @return current object's dy.
     */
    public double getDY() {
        return dy;
    }

    /**
     * Takes a point with position (x,y) and return a new point
     * with position (x+dx, y+dy) - changes its coordinates by the
     * set difference.
     *
     * @param p the point to move.
     * @return the moved point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}
