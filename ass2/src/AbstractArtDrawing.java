// 216303990 Arbel Feldman

import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * Class AbstractArtDrawing uses GUI to draw art.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class AbstractArtDrawing {
    static final int NUM_OF_LINES = 10;
    static final int POINT_RADIUS = 3;
    static final int SCREEN_WIDTH = 400;
    static final int SCREEN_HEIGHT = 300;

    /**
     * Method drawMidpoint draws an inputted line's midpoint in blue.
     *
     * @param d - the drawing surface.
     * @param line - the line.
     */
    public void drawMidpoint(DrawSurface d, Line line) {
        Point midpoint = line.middle();
        d.setColor(Color.BLUE);
        d.fillCircle((int) Math.round(midpoint.getX()),
                     (int) Math.round(midpoint.getY()),
                     POINT_RADIUS);
    }

    /**
     * Method drawRandomLines randomizes 2 points and draws the line segment
     * between them on the drawing surface and adds it to an array, and
     * repeats this 10 times.
     *
     * @param d - the drawing surface.
     * @param lines - the array for the lines
     */
    public void drawRandomLines(DrawSurface d, Line[] lines) {
        for (int i = 0; i < NUM_OF_LINES; i++) {
            Random rand = new Random(); // create a random number generator.
            // randomize coordinates that fit the GUI screen:
            int x1 = rand.nextInt(SCREEN_WIDTH) + 1;
            int y1 = rand.nextInt(SCREEN_HEIGHT) + 1;
            int x2 = rand.nextInt(SCREEN_WIDTH) + 1;
            int y2 = rand.nextInt(SCREEN_HEIGHT) + 1;

            d.setColor(Color.BLACK);
            d.drawLine(x1, y1, x2, y2);
            lines[i] = new Line(x1, y1, x2, y2);
        }
    }

    /**
     * Method drawSpecialPoints takes the lines array that the
     * "drawRandomLines" method created and draws all of their midpoints using
     * the "drawMidpoint" method, and also draws all the lines' intersections.
     *
     * @param d - the drawing surface.
     * @param lines - the array of lines.
     */
    public void drawSpecialPoints(DrawSurface d, Line[] lines) {
        for (int i = 0; i < NUM_OF_LINES; i++) {
            drawMidpoint(d, lines[i]);
            // this loop starts at i + 1 as to not repeat pairs of lines:
            for (int j = i + 1; j < NUM_OF_LINES; j++) {
                Point intersection = (lines[i]).intersectionWith(lines[j]);
                if (intersection != null) {
                    d.setColor(Color.RED);
                    d.fillCircle((int) Math.round(intersection.getX()),
                                 (int) Math.round(intersection.getY()),
                                 POINT_RADIUS);
                }
            }
        }
    }

    /**
     * The main Method creates all the setup - GUI, drawing surface, art
     * object and lines array. then it draws the lines and all their
     * intersections and midpoints.
     *
     * @param args - none.
     */
    public static void main(String[] args) {
        // Create a GUI window with title "Random Lines":
        GUI gui = new GUI("Random Lines", SCREEN_WIDTH, SCREEN_HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        AbstractArtDrawing art = new AbstractArtDrawing();

        // The array for the 10 lines:
        Line[] lines = new Line[NUM_OF_LINES];
        art.drawRandomLines(d, lines);
        art.drawSpecialPoints(d, lines);

        gui.show(d);
    }
}
