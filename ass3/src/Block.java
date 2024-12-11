// 216303990 Arbel Feldman

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Class Block describes collidable blocks with hitBoxes.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class Block implements Collidable, Sprite {
    private Rectangle hitBox;
    private Color color;

    /**
     * Block constructor.
     *
     * @param hitBox the block's hitBox
     * @param color  the block's color
     */
    public Block(Rectangle hitBox, Color color) {
        this.hitBox = hitBox;
        this.color = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.hitBox;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double newDX = currentVelocity.getDX();
        double newDY = currentVelocity.getDY();
        // When the object hits the top/bottom wall, the dy flips sign:
        if (hitBox.top().isContaining(collisionPoint)
                || hitBox.bottom().isContaining(collisionPoint)) {
            newDY *= -1;
        }
        // When the object hits the left/right wall, the dx flips sign:
        if (hitBox.left().isContaining(collisionPoint)
                || hitBox.right().isContaining(collisionPoint)) {
            newDX *= -1;
        }
        return new Velocity(newDX, newDY);
    }

    @Override
    public void drawOn(DrawSurface d) {
        // The inside:
        d.setColor(color);
        d.fillRectangle((int) Math.round(hitBox.getUpperLeft().getX()),
                (int) Math.round(hitBox.getUpperLeft().getY()),
                (int) Math.round(hitBox.getWidth()),
                (int) Math.round(hitBox.getHeight()));
        // The outline:
        d.setColor(Color.black);
        d.drawRectangle((int) Math.round(hitBox.getUpperLeft().getX()),
                (int) Math.round(hitBox.getUpperLeft().getY()),
                (int) Math.round(hitBox.getWidth()),
                (int) Math.round(hitBox.getHeight()));
    }

    @Override
    public void timePassed() {
        // I don't need this at this point in time.
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
