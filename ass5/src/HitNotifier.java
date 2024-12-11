// 216303990 Arbel Feldman

/**
 *
 */
public interface HitNotifier {
    /**
     *
     * @param hl
     */
    void addHitListener(HitListener hl);

    /**
     *
     * @param hl
     */
    void removeHitListener(HitListener hl);
}