// 216303990 Arbel Feldman

/**
 * Class BlockRemover is in charge of listening to blocks in order to know when to remove them.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game the game from which the blocks are being removed.
     * @param removedBlocks the number of blocks in the game.
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}