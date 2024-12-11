// 216303990 Arbel Feldman

import Game.LevelInformation;
import Game.DirectHit;
import Game.WideEasy;
import Game.Green3;
import Game.AnimationRunner;
import Game.GameFlow;
import Game.GameLevel;

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Ass6Game activates the game.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class Ass6Game {

    /**
     * The entry point of the game.
     *
     * @param args levels order. if empty - 1, 2, 3.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        int notLevels = 0;
        for (int i = 0; i < args.length; i++) {
            // Gets rid of args that aren't levels:
            if (!args[i].equals("1") && !args[i].equals("2") && !args[i].equals("3")) {
                args[i] = "0";
                notLevels++;
            }
        }
        for (String arg: args) {
            int level = Integer.parseInt(arg);
            switch (level) {
                case 1:
                    levels.add(new DirectHit());
                    break;
                case 2:
                    levels.add(new WideEasy());
                    break;
                case 3:
                    levels.add(new Green3());
                default:
                    break;
            }
        }
        // if there are no levels in the args, play 1,2,3:
        if (notLevels == args.length) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
        }
        System.out.println();

        GUI gui = new GUI("Arkanoid", GameLevel.SCREEN_WIDTH, GameLevel.SCREEN_HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui, GameLevel.FPS);
        KeyboardSensor keyboard = gui.getKeyboardSensor();

        GameFlow gameFlow = new GameFlow(runner, keyboard);
        gameFlow.runLevels(levels);
    }
}
