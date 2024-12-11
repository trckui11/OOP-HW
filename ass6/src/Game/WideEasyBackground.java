// 216303990 Arbel Feldman

package Game;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * The background of the Wide Easy level.
 */
public class WideEasyBackground implements Sprite {
    private Random random;
    public static final int STARS = 250;
    public static final int BUILDINGS = 40;
    public static final int LINES = 150;
    public static final int POPULATION = 5;
    private int lineWidth;
    private int[] starsX = new int[STARS];
    private int[] starsY = new int[STARS];
    private int[] buildingsW = new int[BUILDINGS];
    private int[] buildingsY = new int[BUILDINGS];
    private int[] windowRand;
    private int[] linesX1 = new int[LINES];
    private int[] linesY = new int[LINES];
    private int[] linesX2 = new int[LINES];
    private int[] blackLines = new int[LINES / POPULATION];
    private int[] blackLinesG = new int[LINES / POPULATION];
    private int[] blackLinesB = new int[LINES / POPULATION];



    /**
     * Constructor.
     */
    public WideEasyBackground() {
        this.random = new Random();
        for (int i = 0; i < STARS; i++) {
            starsX[i] = random.nextInt(750) + 25;
            starsY[i] = random.nextInt(375) + 25;
        }

        for (int i = 0; i < BUILDINGS; i++) {
            buildingsW[i] = random.nextInt(30) + 15;
            buildingsY[i] = random.nextInt(105) + 20;
        }

        int wsum = 0;
        int ysum = 0;
        for (int i = 0; i < buildingsW.length; i++) {
            wsum += buildingsW[i];
        }
        for (int i = 0; i < buildingsY.length; i++) {
            ysum += buildingsY[i];
        }
        windowRand = new int[BUILDINGS * wsum * ysum];
        for (int i = 0; i < BUILDINGS * wsum * ysum; i++) {
            windowRand[i] = random.nextInt(7);
        }

        for (int i = 0; i < LINES; i++) {
            linesX1[i] = random.nextInt(85) + 310;
            linesX2[i] = random.nextInt(85) + 405;
            linesY[i] = random.nextInt(170) + 430;
            lineWidth = random.nextInt(4) + 2;
            if (i % POPULATION == 0 && i != (LINES / POPULATION) * POPULATION) {
                blackLines[i / POPULATION] = random.nextInt(200) + 400;
                blackLinesG[i / POPULATION] = random.nextInt(80) + 50;
                blackLinesB[i / POPULATION] = random.nextInt(100) + 70;
            }
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        // moon glow:
        Color[] moonColors = {new Color(0, 64, 86), new Color(0, 72, 94), new Color(0, 80, 103),
                              new Color(0, 87, 107), new Color(0, 99, 118), new Color(0, 110, 127),
                              new Color(188, 241, 245)};
        for (int i = 6; i > 0; i--) {
            d.setColor(moonColors[6 - i]);
            d.fillCircle(400, 240, 55 * i + 107);
        }

        // stars:
        Color[] starsColors = new Color[150];
        for (int i = 80; i < 230; i++) {
            starsColors[i - 80] = new Color(240, 240, i);
        }
        for (int i = 0; i < 100; i++) {
            d.setColor(starsColors[i]);
            d.fillCircle(starsX[i], starsY[i], 1);
        }

        // moon:
        d.setColor(moonColors[6]);
        d.fillCircle(400, 240, 107);

        // sea:
        d.setColor(new Color(8, 108, 140));
        d.fillRectangle(25, 400, 750, 300);

        // buildings:
        int widthSum = 25;
        int iteration = 0;
        for (int i = 0; i < BUILDINGS; i++) {
            d.setColor(Color.BLACK);
            d.fillRectangle(widthSum, 400 - buildingsY[i], buildingsW[i], buildingsY[i]);
            // windows:
            Color[] windowColors = new Color[110];
            for (int l = 100; l < 210; l++) {
                windowColors[l - 100] = new Color(240, 240, l);
            }
            for (int k = 0; k < buildingsW[i] / 4; k++) {
                for (int j = 0; j < buildingsY[i] / 4; j++) {
                    if (windowRand[iteration] == 0) {
                        d.setColor(windowColors[iteration % 110]);
                        d.fillRectangle(4 * k + 2 + widthSum, 400 - 2 - 4 * j, 2, 2);
                    }
                    iteration++;
                }
            }
            widthSum += buildingsW[i];
        }

        // moon reflection:
        for (int i = 0; i < LINES; i++) {
            d.setColor(moonColors[6]);
            d.fillRectangle(linesX1[i], linesY[i], linesX2[i] - linesX1[i], lineWidth);
            if (i % POPULATION == 0 && i != (LINES / POPULATION) * POPULATION) {
                d.setColor(new Color(0, blackLinesG[i / POPULATION], blackLinesG[i / POPULATION] + 25));
                d.fillRectangle(0, blackLines[i / POPULATION] + 2, 800, lineWidth);
            }
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
