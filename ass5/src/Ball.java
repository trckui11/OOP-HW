// 216303990 Arbel Feldman

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Class Ball describes balls on a draw surface. the balls have:
 * a center (x-y point), radius, color, velocity and an environment.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment environment;

    /**
     * Constructor with point center.
     *
     * @param center the ball's center point.
     * @param r      the ball's radius.
     * @param color  the ball's color.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructor with X and Y values.
     *
     * @param x     the ball's X value.
     * @param y     the ball's Y value.
     * @param r     the ball's radius.
     * @param color the ball's color.
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
     * Center getter.
     *
     * @return the ball's center point.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Center setter.
     *
     * @param center new center.
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Field environment setter.
     *
     * @param environment the environment
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) Math.round(center.getX()), (int) Math.round(center.getY()), radius);
    }

    /**
     * Changes the ball's velocity with a velocity object.
     *
     * @param v new velocity.
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * Changes the ball's velocity with a dx and dy values.
     *
     * @param dx new dx.
     * @param dy new dy.
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
        double x = center.getX();
        double y = center.getY();
        double dx = velocity.getDX();
        double dy = velocity.getDY();
        /*
        This calculates the trajectory of the ball using its velocity.
        100000 is just an arbitrarily large number to make the line "infinite"
        (long enough to ensure it goes past the screen's border)
         */
        Line trajectory = new Line(center, new Point(x + 100000 * dx, y + 100000 * dy));
        CollisionInfo collision = environment.getClosestCollision(trajectory);

        if (collision != null) {
            double speed = Math.sqrt(dx * dx + dy * dy);
            // This checks if the ball is close enough to the collision point:
            if (Line.isLessThan(center.distance(collision.collisionPoint()), speed)) {
                setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(), velocity));
            }
        }
        // This moves the ball 1 dx and 1 dy:
        center = getVelocity().applyToPoint(center);
    }
    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Removing the ball from the game.
     *
     * @param g the game.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}
