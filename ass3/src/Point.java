// 216303990 Arbel Feldman

/**
 * Class Point describes points in the X-Y plane.
 * it has fields double x, y. These are the coordinates of a Point.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class Point {
    private double x;
    private double y;

    /**
     * Default constructor.
     *
     * @param x the point's X coordinate.
     * @param y the point's Y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Field x getter.
     *
     * @return point's x value.
     */
    public double getX() {
        return x;
    }

    /**
     * Field y getter.
     *
     * @return point's y value.
     */
    public double getY() {
        return y;
    }

    /**
     * Calculates the distance between 2 the referenced and inputted points.
     *
     * @param other 2nd point.
     * @return the distance (double).
     */
    public double distance(Point other) {
        // This uses the distance formula - sqrt((X1 - X2)^2 + (Y1 - Y2)^2):
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Method equals checks if the referenced and inputted points are the same.
     *
     * @param other 2nd point.
     * @return boolean - true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return Line.isEqual(this.x, other.getX())
            && Line.isEqual(this.y, other.getY());
    }
}
