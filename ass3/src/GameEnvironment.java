// 216303990 Arbel Feldman

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * Class GameEnvironment holds all the collidables in the game environment.
 *
 * @author Arbel Feldman arbel.feldman@live.biu.ac.il
 * @version JDK 19.0.2
 * @since 2022-09-20
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * adds a given collidable to the environment.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Gets the closest collision point of a trajectory with the collidable.
     * (closest to the starting point of the trajectory)
     *
     * @param trajectory the trajectory.
     * @return the closest collision point.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Get all the intersections with the trajectory:
        List<Point> collisions = new ArrayList<>();
        for (Collidable collidable : collidables) {
            Point closestInt = trajectory.closestIntersectionToStartOfLine(
                               collidable.getCollisionRectangle());
            collisions.add(closestInt);
        }

        // Finds the distances of the collisions from the trajectory's start:
        int length = collisions.size();
        double[] distances = new double[length];
        for (int i = 0; i < length; i++) {
            if (collisions.get(i) != null) {
                distances[i] = collisions.get(i).distance(trajectory.start());
            } else {
                // If there is no collision here, put a negative number:
                distances[i] = -1;
            }
        }

        // Finds the first positive value in the array:
        int minDistIndex = 0;
        for (int i = 1; i < length; i++) {
            if (distances[i] != -1) {
                minDistIndex = i;
                break;
            }
        }
        // If there are none, return null:
        if (distances[minDistIndex] == -1) {
            return null;
        }
        // Find the index of the smallest distance:
        for (int i = 1; i < length; i++) {
            if (distances[i] < distances[minDistIndex] && distances[i] != -1) {
                minDistIndex = i;
            }
        }

        if (collisions.isEmpty()) {
            return null;
        }

        return new CollisionInfo(collisions.get(minDistIndex),
                                 collidables.get(minDistIndex));
    }

    /**
     * Displays all the collidables on the draw surface.
     *
     * @param surface the draw surface
     */
    public void display(DrawSurface surface) {
        for (Collidable collidable : collidables) {
            collidable.drawOn(surface);
        }
    }
}