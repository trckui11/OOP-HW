// 216303990 Arbel Feldman

package Misc;

import java.util.List;

/**
 * Class Shapes.Line describes line segments between 2 points.
 * Fields - starting point and ending point.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class Line {
    private Point start;
    private Point end;
    static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * Constructor using 2 points.
     *
     * @param start starting point of the line segment.
     * @param end   ending point of the line segment.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor using 4 coordinates.
     *
     * @param x1 X value of the starting point.
     * @param y1 Y value of the starting point.
     * @param x2 X value of the ending point.
     * @param y2 Y value of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Method lineSetup ensures the start point will always have the
     * smaller X value.
     *
     * @return the line
     */
    public Line lineSetup() {
        Line newLine = new Line(this.start, this.end);
        if (newLine.start.getX() > newLine.end.getX()) {
            // This switches the start and end points of the line:
            Point tempStart = newLine.start;
            newLine.start = newLine.end;
            newLine.end = tempStart;
        }
        return newLine;
    }

    /**
     * Method length finds the length of the line
     * using the distance method in class Shapes.Point.
     *
     * @return the length (double)
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Method middle finds the middle point of the line.
     * if calculates the average of the x and y values.
     *
     * @return the middle point (Shapes.Point type)
     */
    public Point middle() {
        // Average x and y values:
        double midX = Math.abs(start.getX() + end.getX()) / 2;
        double midY = Math.abs(start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Field start getter.
     *
     * @return starting point of the line.
     */
    public Point start() {
        return new Point(start.getX(), start.getY());
    }

    /**
     * Field end getter.
     *
     * @return ending point of the line.
     */
    public Point end() {
        return new Point(end.getX(), end.getY());
    }

    /**
     * Checks if 2 double values are equal.
     * Due to java's inaccuracies, the points may have a very small difference but still considered the same.
     * To detect this, I must use a small threshold.
     * x equals y if their difference is small enough: |x - y| < threshold.
     *
     * @param num1 one of the values.
     * @param num2 the other.
     * @return a boolean according to if they are equal or not.
     */
    public static boolean isEqual(double num1, double num2) {
        return Math.abs(num1 - num2) < COMPARISON_THRESHOLD;
    }

    /**
     * Checks if a value is less than or equal to another, up to a small threshold, because of java's inaccuracies.
     *
     * @param num1 one of the values.
     * @param num2 the other.
     * @return a boolean according to if they are in order or not.
     */
    public static boolean isLessThan(double num1, double num2) {
        return (num1 <= num2) || isEqual(num1, num2);
    }

    /**
     * Checks if 1 double value is between 2 others (or equal to one of them),
     * up to a small threshold, because of java's inaccuracies.
     * Basically: num1 <= num2 <= num3 ? true : false
     *
     * @param num1 the smallest values.
     * @param num2 the middle one.
     * @param num3 the biggest one.
     * @return a boolean according to if they are in order or not.
     */
    public static boolean isBetween(double num1, double num2, double num3) {
        return (num1 <= num2 && num2 <= num3) || isEqual(num1, num2) || isEqual(num2, num3);
    }

    /**
     * Checks if the current and inputted lines are intersecting, using line equations in the x-y plane.
     *
     * @param other 2nd line.
     * @return boolean according to intersection.
     */
    public boolean isIntersecting(Line other) {
        Line line1 = this.lineSetup();
        Line line2 = other.lineSetup();
        // all points' coordinates:
        double x1 = line1.start.getX();
        double y1 = line1.start.getY();
        double x2 = line1.end.getX();
        double y2 = line1.end.getY();

        double x3 = line2.start.getX();
        double y3 = line2.start.getY();
        double x4 = line2.end.getX();
        double y4 = line2.end.getY();

        // The X values are already sorted with "lineSetup", but not the Y:
        double minY12 = Math.min(y1, y2);
        double maxY12 = Math.max(y1, y2);
        double minY34 = Math.min(y3, y4);
        double maxY34 = Math.max(y3, y4);

        /*
         * First, I need to deal with cases where the at least one
         * of the lines is vertical, because they have no slope.
         */

        // If both lines are vertical:
        if (isEqual(x1, x2) && isEqual(x3, x4)) {
            // Checking if they overlap:
            return isEqual(x1, x3) && maxY12 >= minY34 && maxY34 >= minY12;
            // If only one is vertical:
        } else if (isEqual(x1, x2)) {
            // The slope of the other line:
            double m2 = (y3 - y4) / (x3 - x4);
            // Substituting the X value of the vertical line:
            double yInt = m2 * (x1 - x3) + y3;
            // Checking if the intersection is on the actual line:
            return isBetween(minY12, yInt, maxY12) && (x3 <= x1 && x1 <= x4);
        } else if (isEqual(x3, x4)) {
            // The slope of the other line:
            double m1 = (y1 - y2) / (x1 - x2);
            // Substituting the X value of the vertical line:
            double yInt = m1 * (x3 - x1) + y1;
            // Checking if the intersection is on the actual line:
            return isBetween(minY34, yInt, maxY34) && (x1 <= x3 && x3 <= x2);
        }

        // Here none of them is vertical. The lines' slopes:
        double m1 = (y1 - y2) / (x1 - x2);
        double m2 = (y3 - y4) / (x3 - x4);
        // Checking if they are parallel:
        if (isEqual(m1, m2)) {
            /*
             * Checking if the line's equations are equal (using y intersect):
             * y - y1 = m1 * (x - x1)
             * substitute x = 0
             * y = y1 - m1 * x1
             */
            if (isEqual(y1 - m1 * x1, y3 - m2 * x3)) {
                // Checking if the lines overlap:
                return maxY12 >= minY34 && maxY34 >= minY12;
            }
            // If they aren't equal and parallel then they do not intersect:
            return false;
        }

        /*
         * Here the lines aren't parallel or vertical, so I find intersection:
         * intersection coordinates calculated using line equations:
         * current line: y = m1 * (x - x1) + y1
         * other line: y = m2 * (x - x3) + y3
         * intersection:
         * m1 * (x - x1) + y1 = m2 * (x - x3) + y3
         * after solving: xInt = (m1 * x1 - m2 * x3 + y3 - y1) / (m1 - m2)
         * xInt and yInt are the intersection coordinates.
         */
        double xInt = (m1 * x1 - m2 * x3 + y3 - y1) / (m1 - m2);
        double yInt = m1 * (xInt - x1) + y1;

        // Checking if the intersection point is on the actual line:
        return isBetween(x1, xInt, x2) && isBetween(minY12, yInt, maxY12)
                && isBetween(x3, xInt, x4) && isBetween(minY34, yInt, maxY34);
    }

    /**
     * Finds the intersection point between the current and inputted lines, using line equations in the x-y plane.
     *
     * @param other 2nd line.
     * @return the intersection point if found, null otherwise.
     */
    public Point intersectionWith(Line other) {
        Line line1 = this.lineSetup();
        Line line2 = other.lineSetup();

        if (line1.isIntersecting(other)) {
            // all points' coordinates:
            double x1 = line1.start.getX();
            double y1 = line1.start.getY();
            double x2 = line1.end.getX();
            double y2 = line1.end.getY();

            double x3 = line2.start.getX();
            double y3 = line2.start.getY();
            double x4 = line2.end.getX();
            double y4 = line2.end.getY();

            // First, I need to deal with cases where the lines are vertical, because they have no slope.

            // If both lines are vertical:
            if (isEqual(x1, x2) && isEqual(x3, x4)) {
                // If they intersect at a single point:
                if (line1.start.equals(line2.end)) {
                    return line1.start;
                }
                if (line1.end.equals(line2.start)) {
                    return line1.end;
                }
                // If they overlap:
                return null;
                // If only one is vertical:
            } else if (isEqual(x1, x2)) {
                // The slope of the other line:
                double m2 = (y3 - y4) / (x3 - x4);
                // Substituting the X value of the vertical line:
                double yIntersection = m2 * (x1 - x3) + y3;
                return new Point(x1, yIntersection);
            } else if (isEqual(x3, x4)) {
                // The slope of the other line:
                double m1 = (y1 - y2) / (x1 - x2);
                // Substituting the X value of the vertical line:
                double yIntersection = m1 * (x3 - x1) + y1;
                return new Point(x3, yIntersection);
            }
            // Here none of them is vertical. The lines' slopes:
            double m1 = (y1 - y2) / (x1 - x2);
            double m2 = (y3 - y4) / (x3 - x4);
            // If they are parallel (in this case, overlapping):
            if (isEqual(m1, m2)) {
                // If they intersect at a single point:
                if (line1.start.equals(line2.end)) {
                    return line1.start;
                }
                if (line1.end.equals(line2.start)) {
                    return line1.end;
                }
                // If they overlap:
                return null;
            }

            /*
             * Intersection coordinates calculated using line equations, exactly like in the "isIntersecting" function.
             * substituting result: yInt = m1 * (xInt - x1) + y1
             */
            double xIntersection = (m1 * x1 - m2 * x3 + y3 - y1) / (m1 - m2);
            double yIntersection = m1 * (xIntersection - x1) + y1;
            return new Point(xIntersection, yIntersection);
        }
        // If they are not intersecting at all:
        return null;
    }

    /**
     * Checks if the referenced and inputted lines are equal.
     *
     * @param other 2nd line.
     * @return boolean - true if equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        // Either start=start and end=end or start=end and end=start:
        return this.start.equals(other.start) && this.end.equals(other.end)
                || this.start.equals(other.end) && this.end.equals(other.start);
    }

    /**
     * Checks if a point is on a line segment.
     *
     * @param point the point
     * @return true if the point is on the line, false otherwise.
     */
    public boolean isContaining(Point point) {
        Line line = new Line(this.start, this.end);
        line.lineSetup();
        double x1 = line.start.getX();
        double y1 = line.start.getY();
        double x2 = line.end.getX();
        double y2 = line.end.getY();

        double x3 = point.getX();
        double y3 = point.getY();

        // The X values are already sorted with "lineSetup", but not the Y:
        double minY = Math.min(y1, y2);
        double maxY = Math.max(y1, y2);

        // If the line is vertical:
        if (isEqual(x1, x2) && isEqual(x3, x1) && isBetween(minY, y3, maxY)) {
            return true;
        }
        // Shapes.Line slope:
        double m = (y1 - y2) / (x1 - x2);
        // Y intercept:
        double b = -m * x1 + y1;
        // Checking if the point is on the infinite line:
        if (isEqual(y3, m * x3 + b)) {
            // If so, is it on the line segment?
            return isBetween(x1, x3, x2);
        }
        return false;
    }

    /**
     * finds the closest intersection of a given line with a rectangle to the line's starting point.
     *
     * @param rect the rectangle (a "hitBox").
     * @return the closest intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        int length = intersections.size();
        if (length == 0) {
            return null;
        } else {
            double[] distances = new double[length];
            for (int i = 0; i < length; i++) {
                distances[i] = intersections.get(i).distance(this.start);
            }

            // Find index of the smallest value in distances array:
            int minDistanceIndex = 0;
            for (int i = 1; i < length; i++) {
                if (distances[i] < distances[minDistanceIndex]) {
                    minDistanceIndex = i;
                }
            }

            return intersections.get(minDistanceIndex);
        }
    }
}
