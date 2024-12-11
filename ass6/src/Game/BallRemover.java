// 216303990 Arbel Feldman

package Game;

import Misc.Ball;
import Misc.Block;
import Misc.Counter;

/**
 * Class Game.BallRemover is in charge of listening to blocks in order to know when to remove a ball.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game the game from which the balls are being removed.
     * @param remainingBalls the number of balls in the game.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
