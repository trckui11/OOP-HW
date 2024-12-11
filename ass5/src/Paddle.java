// 216303990 Arbel Feldman

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
    static final int MOVE_SENSITIVITY = 5;
    static final double LENGTH = 1.5 * Game.BLOCK_LENGTH;

    /**
     * Paddle constructor.
     *
     * @param hitBox   the paddle's hitbox.
     * @param keyboard the keyboard detector.
     * @param color    the paddle's color.
     */
    public Paddle(Rectangle hitBox, KeyboardSensor keyboard, Color color) {
        this.hitBox = hitBox;
        this.keyboard = keyboard;
        this.color = color;
    }

    /**
     * Moves the paddle left once.
     */
    public void moveLeft() {
        double x = this.hitBox.getUpperLeft().getX();
        double y = this.hitBox.getUpperLeft().getY();
        hitBox.setPosition(x - MOVE_SENSITIVITY, y);
        // prevent the paddle from going off-screen:
        if (Line.isLessThan(x, Game.BLOCK_THICKNESS)) {
            hitBox.setPosition(Game.BLOCK_THICKNESS, y);
        }
    }

    /**
     * Moves the paddle right once.
     */
    public void moveRight() {
        double x = this.hitBox.getUpperLeft().getX();
        double y = this.hitBox.getUpperLeft().getY();
        hitBox.setPosition(x + MOVE_SENSITIVITY, y);
        // prevent the paddle from going off-screen:
        if (Line.isLessThan(Game.SCREEN_WIDTH - Game.BLOCK_THICKNESS - LENGTH, x)) {
            hitBox.setPosition(Game.SCREEN_WIDTH - Game.BLOCK_THICKNESS - LENGTH, y);
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
        double length = (Game.BLOCK_LENGTH * 1.5) / 5;
        double padX = this.hitBox.getUpperLeft().getX();
        double hitX = collisionPoint.getX();
        double newDX = currentVelocity.getDX();
        double newDY = currentVelocity.getDY();
        double speed = Math.sqrt(newDX * newDX + newDY * newDY);
        // When the object hits the top/bottom wall, the dy flips sign:
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
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}