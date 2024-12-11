// 216303990 Arbel Feldman

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Block describes collidable blocks with hitBoxes.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022 -09-20
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle hitBox;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Block constructor.
     *
     * @param hitBox the block's hitBox
     * @param color  the block's color
     */
    public Block(Rectangle hitBox, Color color) {
        this.hitBox = hitBox;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Removes the block from a game.
     *
     * @param game the game.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.hitBox;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newDX = currentVelocity.getDX();
        double newDY = currentVelocity.getDY();
        // When the object hits the top/bottom wall, the dy flips sign:
        if (hitBox.top().isContaining(collisionPoint) || hitBox.bottom().isContaining(collisionPoint)) {
            newDY *= -1;
        }
        // When the object hits the left/right wall, the dx flips sign:
        if (hitBox.left().isContaining(collisionPoint) || hitBox.right().isContaining(collisionPoint)) {
            newDX *= -1;
        }
        this.notifyHit(hitter);
        return new Velocity(newDX, newDY);
    }

    @Override
    public void drawOn(DrawSurface d) {
        // The inside:
        d.setColor(color);
        d.fillRectangle((int) Math.round(hitBox.getUpperLeft().getX()), (int) Math.round(hitBox.getUpperLeft().getY()),
                (int) Math.round(hitBox.getWidth()), (int) Math.round(hitBox.getHeight()));
        // The outline:
        d.setColor(Color.black);
        d.drawRectangle((int) Math.round(hitBox.getUpperLeft().getX()), (int) Math.round(hitBox.getUpperLeft().getY()),
                (int) Math.round(hitBox.getWidth()), (int) Math.round(hitBox.getHeight()));
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

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notifies all listeners that a hit has occurred.
     *
     * @param hitter the ball that hit the block.
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
