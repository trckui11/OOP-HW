// 216303990 Arbel Feldman

/**
 * This class listens to blocks in order to know when to add or reduce points.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the score keeper.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}