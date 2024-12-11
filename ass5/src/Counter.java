// 216303990 Arbel Feldman

/**
 * Class Counter counts.
 */
public class Counter {
    private int counter;

    /**
     * Constructor.
     *
     * @param number starting value.
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * Increase value.
     *
     * @param number the number to increase by.
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * decrease value.
     *
     * @param number the number to decrease by.
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * Value getter.
     *
     * @return the value.
     */
    public int getValue() {
        return counter;
    }
}