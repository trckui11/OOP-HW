// 216303990 Arbel Feldman

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Class Ball describes balls on a draw surface.
 * the balls have a center (x-y point), radius and color.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class Ball {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity = new Velocity(0, 0);

    /**
     * Constructor with point center.
     *
     * @param center - the ball's center point.
     * @param r - the ball's radius.
     * @param color - the ball's color.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructor with X and Y values.
     *
     * @param x - the ball's X value.
     * @param y - the ball's Y value.
     * @param r - the ball's radius.
     * @param color - the ball's color.
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * X value getter.
     *
     * @return the ball's X value.
     */
    public int getX() {
        return (int) Math.round(center.getX());
    }

    /**
     * Y value getter.
     *
     * @return the ball's Y value.
     */
    public int getY() {
        return (int) Math.round(center.getY());
    }

    /**
     * Radius getter.
     *
     * @return the ball's radius.
     */
    public int getSize() {
        return radius;
    }

    /**
     * Color getter.
     *
     * @return the ball's Color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Field center getter.
     *
     * @return the ball's center point.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Field center setter.
     *
     * @param center - new center.
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Draw the ball on the given draw surface.
     *
     * @param surface - the given draw surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) Math.round(center.getX()),
                           (int) Math.round(center.getY()), radius);
    }

    /**
     * Changes the ball's velocity with a velocity object.
     *
     * @param v - new velocity.
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * Changes the ball's velocity with a dx and dy values.
     *
     * @param dx - new dx.
     * @param dy - new dy.
     */
    public void setVelocity(double dx, double dy) {
        // Rate of change:
        velocity = new Velocity(dx, dy);
    }

    /**
     * Ball's velocity getter.
     *
     * @return current ball's velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Method moveOneStep moves the ball one dx and dy according to
     * its velocity. It also makes sure the ball is within the bounds
     * of the GUI screen.
     */
    public void moveOneStep() {
        // This moves the ball 1 dx and 1 dy:
        center = getVelocity().applyToPoint(center);

        /*
        If the ball's center's distance from the frame's border
        is less than its radius, it flips the sign of the dx or
        dy accordingly, and correct its position:
         */
        if (getX() < radius) {
            setVelocity(-getVelocity().getDX(), getVelocity().getDY());
            setCenter(new Point(radius, getY()));
        }
        if (getX() > BouncingBallAnimation.SCREEN_WIDTH - radius) {
            setVelocity(-getVelocity().getDX(), getVelocity().getDY());
            setCenter(new Point(BouncingBallAnimation.SCREEN_WIDTH - radius, getY()));
        }
        if (getY() < radius) {
            setVelocity(getVelocity().getDX(), -getVelocity().getDY());
            setCenter(new Point(getX(), radius));
        }
        if (getY() > BouncingBallAnimation.SCREEN_HEIGHT - radius) {
            setVelocity(getVelocity().getDX(), -getVelocity().getDY());
            setCenter(new Point(getX(), BouncingBallAnimation.SCREEN_HEIGHT - radius));
        }
    }
}
