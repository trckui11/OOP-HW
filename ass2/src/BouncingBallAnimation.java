// 216303990 Arbel Feldman

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * Class BouncingBallAnimation displays an animation of a Ball object bouncing
 * on the screen.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class BouncingBallAnimation {
    static final int SCREEN_WIDTH = 200;
    static final int SCREEN_HEIGHT = 200;
    static final int FRAME_RATE = 20;
    static final int RADIUS = 30;

    /**
     * Method drawAnimation takes care of the GUI and displaying the
     * ball's animation.
     *
     * @param start - the ball's starting point.
     * @param dx - the ball's change in X value for each frame.
     * @param dy - the ball's change in Y value for each frame
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Ball", SCREEN_WIDTH, SCREEN_HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball((int) Math.round(start.getX()),
                             (int) Math.round(start.getY()),
                             RADIUS, Color.BLACK);
        ball.setVelocity(dx, dy);
        // this loop makes it look like an animation, each iteration is 1 frame:
        while (true) {
            ball.moveOneStep(); // move the ball 1 dx and 1 dy each frame.
            DrawSurface ds = gui.getDrawSurface();
            ball.drawOn(ds);
            gui.show(ds);
            // 1/fps = # of secs for 1 frame, but this needs ms, so 1000/fps:
            sleeper.sleepFor(1000 / FRAME_RATE);
        }
    }

    /**
     * The main method gets a ball's starting point and velocity,
     * then displays it.
     *
     * @param args - the ball's X value, Y value, dx and dy.
     */
    public static void main(String[] args) {
        /*
        I convert the args to double first in case they are not integers,
        then round them and convert to int:
         */
        int x = (int) Math.round(Double.parseDouble(args[0]));
        int y = (int) Math.round(Double.parseDouble(args[1]));
        double dx = 0;
        double dy = 0;
        // In case the speed has not been inputted:
        if (args.length > 2) {
            dx = Double.parseDouble(args[2]);
        }
        if (args.length > 3) {
            dy = Double.parseDouble(args[3]);
        }

        // Make sure x and y values are in bound, and correct accordingly:
        if (x < RADIUS || x > SCREEN_WIDTH - RADIUS) {
            System.out.println("X value must be between " + RADIUS
                               + " and " + (SCREEN_WIDTH - RADIUS)
                               + ". your input has been adjusted.");
            if (x < RADIUS) {
                x = RADIUS;
            } else {
                x = SCREEN_WIDTH - RADIUS;
            }
        }
        if (y < RADIUS || y > SCREEN_HEIGHT - RADIUS) {
            System.out.println("Y value must be between " + RADIUS
                               + " and " + (SCREEN_HEIGHT - RADIUS)
                               + ". your input has been adjusted.");
            if (y < RADIUS) {
                y = RADIUS;
            } else {
                y = SCREEN_HEIGHT - RADIUS;
            }
        }
        // Make sure dx and dy values are in bound, and correct accordingly:
        if (Math.abs(dx) > RADIUS) {
            dx = Math.signum(dx) * RADIUS;
            System.out.println("dx must be between "
                               + (-RADIUS) + " and " + RADIUS
                               + ". your input has been adjusted.");
        }
        if (Math.abs(dy) > RADIUS) {
            dy = Math.signum(dy) * RADIUS;
            System.out.println("dy must be between "
                               + (-RADIUS) + " and " + RADIUS
                               + ". your input has been adjusted.");
        }

        drawAnimation(new Point(x, y), dx, dy);
    }
}
