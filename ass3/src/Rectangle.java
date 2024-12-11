// 216303990 Arbel Feldman

import java.util.ArrayList;
import java.util.List;

/**
 * Class Rectangle describes rectangles by their upper left
 * vertex and their width and height.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private double width;
    private double height;

    /**
     * @return the top border of the rectangle.
     */
    public Line top() {
        return new Line(upperLeft, upperRight);
    }

    /**
     * @return the bottom border of the rectangle.
     */
    public Line bottom() {
        return new Line(lowerLeft, lowerRight);
    }

    /**
     * @return the left border of the rectangle.
     */
    public Line left() {
        return new Line(upperLeft, lowerLeft);
    }

    /**
     * @return the right border of the rectangle.
     */
    public Line right() {
        return new Line(lowerRight, upperRight);
    }

    /**
     * Rectangle constructor. Calculates the other vertices.
     *
     * @param upperLeft the upper left vertex.
     * @param width the width
     * @param height the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upperRight = new Point(upperLeft.getX() + width,
                                       upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(),
                                upperLeft.getY() + height);
        this.lowerRight = new Point(upperRight.getX(), lowerLeft.getY());
    }

    /**
     * Upper left vertex setter.
     *
     * @param x the X coordinate of the upper left vertex setter.
     * @param y the Y coordinate of the upper left vertex setter.
     */
    public void setUpperLeft(double x, double y) {
        this.upperLeft = new Point(x, y);
    }

    /**
     * Finds all the intersection points between the rectangle and the line.
     *
     * @param line the line.
     * @return a list of the intersection points.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();

        Point[] points = new Point[4];
        points[0] = line.intersectionWith(top());
        points[1] = line.intersectionWith(bottom());
        points[2] = line.intersectionWith(left());
        points[3] = line.intersectionWith(right());

        // Remove all the null points:
        for (Point p : points) {
            if (p != null) {
                intersections.add(p);
            }
        }
        return intersections;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left vertex.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY());
    }
}