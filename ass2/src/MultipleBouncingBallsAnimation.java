// 216303990 Arbel Feldman

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;

/**
 * Class MultipleBouncingBallsAnimation displays an animation of many Ball
 * objects bouncing on the screen.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class MultipleBouncingBallsAnimation {
    static final int FULL_DEGREES = 360;
    static final int MAX_SPEED = 25;
    static final int MIN_SPEED = 1;
    static final int MAX_RADIUS = 50;


    /**
     * Method drawAnimation takes care of the GUI and displaying the
     * balls' animation.
     *
     * @param balls - the array of balls to animate.
     */
    private static void drawAnimation(Ball[] balls) {
        GUI gui = new GUI("Many Balls", BouncingBallAnimation.SCREEN_WIDTH, BouncingBallAnimation.SCREEN_HEIGHT);
        Sleeper sleeper = new Sleeper();

        // this loop makes it look like an animation, each iteration is 1 frame:
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {
                ball.drawOn(d);
                ball.moveOneStep();
            }
            gui.show(d);
            // 1/fps = # of secs for 1 frame, but this needs ms, so 1000/fps:
            sleeper.sleepFor(1000 / BouncingBallAnimation.FRAME_RATE);
        }
    }

    /**
     * The main Method receives a list of ball sizes, and displays them.
     * For each ball, it randomizes its position and velocity.
     *
     * @param args - a list of ball sizes.
     */
    public static void main(String[] args) {
        Random rand = new Random(); // create a random number generator
        Ball[] balls = new Ball[args.length];
        for (int i = 0; i < args.length; i++) {
            // Make sure the radius is in bound, and correct accordingly:
            int radius = (int) Math.round(Double.parseDouble(args[i]));
            if (radius == 0) {
                radius = 1;
            } else if (radius < 0) {
                radius = Math.abs(radius);
                System.out.println("Radius must be positive."
                                   + "Your input has been adjusted.");
            }
            if (radius > BouncingBallAnimation.SCREEN_WIDTH / 2 - 1) {
                radius = BouncingBallAnimation.SCREEN_WIDTH / 2 - 1;
                System.out.println("Radius can't be over "
                        + (BouncingBallAnimation.SCREEN_WIDTH / 2 - 1)
                        + ". Your input has been adjusted.");
            }

            /*
            Random coordinates for the balls. They can only be inside the frame,
            radius units away from the border:
             */
            int x = rand.nextInt(BouncingBallAnimation.SCREEN_WIDTH
                                 - 2 * radius) + radius;
            int y = rand.nextInt(BouncingBallAnimation.SCREEN_HEIGHT
                                 - 2 * radius) + radius;

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
            balls[i] = ball;
        }
        drawAnimation(balls);
    }
}
