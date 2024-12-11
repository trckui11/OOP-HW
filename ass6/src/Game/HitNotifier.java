// 216303990 Arbel Feldman

package Game;

/**
 * A Game.HitNotifier object is an object that is being listened to for hits.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public interface HitNotifier {
    /**
     * Adds a hit listener to the list of listeners.
     *
     * @param hl the hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * removes a hit listener from the list of listeners.
     *
     * @param hl the hit listener
     */
    void removeHitListener(HitListener hl);
}