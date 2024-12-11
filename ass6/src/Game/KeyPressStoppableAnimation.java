// 216303990 Arbel Feldman

package Game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A class that handles al the animations that are stopped with a key-press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private AnimationRunner animationRunner;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key-press stoppable animation.
     *
     * @param sensor          the keyboard
     * @param key             the key that stops the animation
     * @param animation       the animation
     * @param animationRunner the animation runner
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation, AnimationRunner animationRunner) {
        this.animation = animation;
        this.key = key;
        this.animationRunner = animationRunner;
        this.sensor = sensor;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!shouldReallyStop()) {
            isAlreadyPressed = false;
            d = animationRunner.gui().getDrawSurface();
            animation.doOneFrame(d);
            animationRunner.gui().show(d);

            while (!shouldStop()) {
                continue;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        if (isAlreadyPressed) {
            return false;
        }
        if (key.equals(" ")) {
            return sensor.isPressed(KeyboardSensor.SPACE_KEY);
        }
        return sensor.isPressed(key);
    }

    /**
     * The real shouldStop method. the other one has a condition to prevent stopping the animation
     * if the key is pressed upon the creation of the animation.
     *
     * @return true if the stopping key is pressed, false else.
     */
    public boolean shouldReallyStop() {
        if (key.equals(" ")) {
            return sensor.isPressed(KeyboardSensor.SPACE_KEY);
        }
        return sensor.isPressed(key);
    }
}
