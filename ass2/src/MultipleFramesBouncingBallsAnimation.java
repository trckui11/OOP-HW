// 216303990 Arbel Feldman

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;

/**
 * class MultipleFramesBouncingBallsAnimation displays an animation of many Ball
 * objects bouncing on the screen, in two different frames.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class MultipleFramesBouncingBallsAnimation {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int BIG_FRAME_START = 50;
    static final int BIG_FRAME_END = 500;
    static final int SMALL_FRAME_START = 450;
    static final int SMALL_FRAME_END = 600;
    static final int FULL_DEGREES = 360;
    static final int MAX_SPEED = 25;
    static final int MIN_SPEED = 1;
    static final int MAX_RADIUS = 50;

    /**
     * Method moveOneStep moves the ball one dx and dy according to
     * its velocity. It also makes sure the ball is within the bounds
     * of the GUI screen.
     *
     * @param ball - the ball to move.
     * @param frameStart - the X/Y coordinate of the frame's left/up edge.
     * @param frameEnd - the X/Y coordinate of the frame's right/down edge.
     */
    public static void moveOneStep(Ball ball, int frameStart, int frameEnd) {
        Velocity vel = ball.getVelocity();
        int radius = ball.getSize();
        // Move the current center one step and apply it to the ball:
        Point newCenter = vel.applyToPoint(ball.getCenter());
        ball.setCenter(newCenter);

        /*
        If the ball's center's distance from the frame's border
        is less than its radius, it flips the sign of the dx or
        dy accordingly, and correct its position:
         */
        if (ball.getX() < frameStart + radius) {
            ball.setVelocity(-vel.getDX(), vel.getDY());
            ball.setCenter(new Point(frameStart + radius, ball.getY()));
        }
        if (ball.getX() > frameEnd - radius) {
            ball.setVelocity(-vel.getDX(), vel.getDY());
            ball.setCenter(new Point(frameEnd - radius, ball.getY()));
        }
        if (ball.getY() < frameStart + radius) {
            ball.setVelocity(vel.getDX(), -vel.getDY());
            ball.setCenter(new Point(ball.getX(), frameStart + radius));
        }
        if (ball.getY() > frameEnd - radius) {
            ball.setVelocity(vel.getDX(), -vel.getDY());
            ball.setCenter(new Point(ball.getX(), frameEnd - radius));
        }
    }

    /**
     * Method drawAnimation takes care of the GUI and displaying the
     * balls' animation.
     *
     * @param bigFrameBalls - the array of balls that go in the big frame.
     * @param smallFrameBalls - the array of balls that go in the small frame.
     */
    private static void drawAnimation(Ball[] bigFrameBalls, Ball[] smallFrameBalls) {
        GUI gui = new GUI("2 Frames And Balls", SCREEN_WIDTH, SCREEN_HEIGHT);
        Sleeper sleeper = new Sleeper();

        // This loop makes it look like an animation, each iteration is 1 frame:
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            // Set up the big frame:
            d.setColor(Color.GRAY);
            d.fillRectangle(BIG_FRAME_START, BIG_FRAME_START,
                         BIG_FRAME_END - BIG_FRAME_START,
                         BIG_FRAME_END - BIG_FRAME_START);
            for (Ball ball : bigFrameBalls) {
                moveOneStep(ball, BIG_FRAME_START, BIG_FRAME_END);
                ball.drawOn(d);
            }

            // Set up the small frame:
            d.setColor(Color.YELLOW);
            d.fillRectangle(SMALL_FRAME_START, SMALL_FRAME_START,
                         SMALL_FRAME_END - SMALL_FRAME_START,
                         SMALL_FRAME_END - SMALL_FRAME_START);
            for (Ball ball : smallFrameBalls) {
                moveOneStep(ball, SMALL_FRAME_START, SMALL_FRAME_END);
                ball.drawOn(d);
            }

            gui.show(d);
            // 1/fps = # of secs for 1 frame, but this needs ms, so 1000/fps:
            sleeper.sleepFor(1000 / BouncingBallAnimation.FRAME_RATE);
        }
    }

    /**
     * The main Method receives a list of ball sizes,
     * and separates them into 2 frames and displays them.
     * For each ball, it randomizes its position and velocity.
     *
     * @param args - a list of ball sizes.
     */
    public static void main(String[] args) {
        Random rand = new Random(); // create a random number generator.
        Ball[] bigFrameBalls = new Ball[args.length / 2];
        Ball[] smallFrameBalls = new Ball[args.length / 2];
        for (int i = 0; i < args.length; i++) {
            // Make sure the radius is in bound, and correct accordingly:
            int radius = (int) Math.round(Double.parseDouble(args[i]));
            if (radius < 0) {
                radius = Math.abs(radius);
                System.out.println("Radius must be positive. "
                        + "Your input has been adjusted.");
            }
            if (radius > MAX_RADIUS) {
                radius = MAX_RADIUS;
                System.out.println("Radius can't be larger than " + MAX_RADIUS
                                   + ". Your input has been adjusted.");
            } else if (radius == 0) {
                radius = 1;
            }

            int x;
            int y;
            /*
            Random coordinates for the balls. They can only be inside the frame,
            radius units away from the border. This also distinguishes between
            the first and second half of balls.
             */
            if (i < args.length / 2) {
                x = rand.nextInt(BIG_FRAME_END - BIG_FRAME_START
                                - 2 * radius) + radius + BIG_FRAME_START + 1;
                y = rand.nextInt(BIG_FRAME_END - BIG_FRAME_START
                                - 2 * radius) + radius + BIG_FRAME_START + 1;
            } else {
                x = rand.nextInt(SMALL_FRAME_END - SMALL_FRAME_START
                                - 2 * radius) + radius + SMALL_FRAME_START + 1;
                y = rand.nextInt(SMALL_FRAME_END - SMALL_FRAME_START
                                - 2 * radius) + radius + SMALL_FRAME_START + 1;
            }

            // Random angle and an arbitrary random speed factor of up to 5:
            int angle = rand.nextInt(FULL_DEGREES);
            int random = rand.nextInt(5);
            /*
            I want the speed to be 25 at radius 1, and 1 and radius 50+.
            So I need the line equation that goes through the points
            (1,25) and (50,1). After simple calculations I get:
            y = -x/2 + 26 (y is speed and x is radius) + a random factor.
             */
            double slope = (MAX_SPEED - MIN_SPEED) / (1.0 - MAX_RADIUS);
            double speed = radius > MAX_RADIUS ? 1
                           : slope * (radius - MAX_RADIUS) + 1 + random;
            // Make sure MIN_SPEED < speed < MAX_SPEED:
            if (speed < MIN_SPEED) {
                speed = MIN_SPEED;
            } else if (speed > MAX_SPEED) {
                speed = MAX_SPEED;
            }
            Velocity vel = Velocity.fromAngleAndSpeed(angle, speed);

            Ball ball = new Ball(new Point(x, y), radius, Color.BLACK);
            ball.setVelocity(vel);
            // Put each ball in its matching array:
            if (i < args.length / 2) {
                bigFrameBalls[i] = ball;
            } else {
                smallFrameBalls[i - args.length / 2] = ball;
            }
        }

        drawAnimation(bigFrameBalls, smallFrameBalls);
    }
}
