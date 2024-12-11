// 216303990 Arbel Feldman

/**
 * Class Ass3Game activates the game.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class Ass3Game {
    /**
     * The entry point of the game.
     *
     * @param args none.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
