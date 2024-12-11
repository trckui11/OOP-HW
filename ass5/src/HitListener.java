// 216303990 Arbel Feldman

/**
 * Interface HitListener describes elements that listen to HitNotifiers to perform actions when a hit occurs.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public interface HitListener {
    /**
     * The things that happen when a hit is detected.
     *
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit a block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}