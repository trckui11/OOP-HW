// 216303990 Arbel Feldman

package Game;

import Misc.Point;
import Misc.Rectangle;
import Misc.Velocity;
import Misc.Line;
import Misc.Ball;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The paddle in the Arkanoid game.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle hitBox;
    private Color color;
    private int speed;

    /**
     * Game.Paddle constructor.
     *
     * @param hitBox   the paddle's hitbox.
     * @param keyboard the keyboard detector.
     * @param color    the paddle's color.
     * @param speed    the paddle's speed.
     */
    public Paddle(Rectangle hitBox, KeyboardSensor keyboard, Color color, int speed) {
        this.hitBox = hitBox;
        this.keyboard = keyboard;
        this.color = color;
        this.speed = speed;
    }

    /**
     * Moves the paddle left once.
     */
    public void moveLeft() {
        double x = this.hitBox.getUpperLeft().getX();
        double y = this.hitBox.getUpperLeft().getY();
        hitBox.setPosition(x - this.speed, y);
        // prevent the paddle from going off-screen:
        if (Line.isLessThan(x, GameLevel.BLOCK_THICKNESS)) {
            hitBox.setPosition(GameLevel.BLOCK_THICKNESS, y);
        }
    }

    /**
     * Moves the paddle right once.
     */
    public void moveRight() {
        double x = this.hitBox.getUpperLeft().getX();
        double y = this.hitBox.getUpperLeft().getY();
        hitBox.setPosition(x + this.speed, y);
        // prevent the paddle from going off-screen:
        if (Line.isLessThan(GameLevel.SCREEN_WIDTH - GameLevel.BLOCK_THICKNESS - hitBox.getWidth(), x)) {
            hitBox.setPosition(GameLevel.SCREEN_WIDTH - GameLevel.BLOCK_THICKNESS - hitBox.getWidth(), y);
        }
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        Rectangle hitBox = getCollisionRectangle();
        Point upLeft = hitBox.getUpperLeft();
        int width = (int) Math.round(hitBox.getWidth());
        int height = (int) Math.round(hitBox.getHeight());
        // The filling:
        d.setColor(color);
        d.fillRectangle((int) Math.round(upLeft.getX()), (int) Math.round(upLeft.getY()), width, height);
        // The outline:
        d.setColor(Color.black);
        d.drawRectangle((int) Math.round(upLeft.getX()), (int) Math.round(upLeft.getY()), width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.hitBox;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double length = hitBox.getWidth() / 5;
        double padX = this.hitBox.getUpperLeft().getX();
        double hitX = collisionPoint.getX();
        double newDX = currentVelocity.getDX();
        double newDY = currentVelocity.getDY();
        double speed = Math.sqrt(newDX * newDX + newDY * newDY);
        // This detects which region of the paddle the ball hit:
        if (hitBox.top().isContaining(collisionPoint) || hitBox.bottom().isContaining(collisionPoint)) {
            int section = 0;
            if (Line.isBetween(padX, hitX, padX + length)) {
                section = 1;
            } else if (Line.isBetween(padX + length, hitX, padX + 2 * length)) {
                section = 2;
            } else if (Line.isBetween(padX + 2 * length, hitX, padX + 3 * length)) {
                section = 3;
            } else if (Line.isBetween(padX + 3 * length, hitX, padX + 4 * length)) {
                section = 4;
            } else if (Line.isBetween(padX + 4 * length, hitX, padX + 5 * length)) {
                section = 5;
            }

            // The ball's velocity differs according to where it hit the paddle:
            return switch (section) {
                case 1 -> Velocity.fromAngleAndSpeed(300, speed);
                case 2 -> Velocity.fromAngleAndSpeed(330, speed);
                case 4 -> Velocity.fromAngleAndSpeed(30, speed);
                case 5 -> Velocity.fromAngleAndSpeed(60, speed);
                default -> new Velocity(newDX, -newDY);
            };
        }
        // When the object hits the left/right wall, the dx flips sign:
        if (hitBox.left().isContaining(collisionPoint) || hitBox.right().isContaining(collisionPoint)) {
            newDX *= -1;
        }
        return new Velocity(newDX, newDY);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}