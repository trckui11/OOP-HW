// 216303990 Arbel Feldman

/**
 * Class Pow calculates exponentiation in two ways - recursive and iterative.
 *
 * @version 19.0.2
 * @author Arbel Feldman - arbel.feldman@live.biu.ac.il
 * @since 2023-01-17
 */
public class Pow {
    /**
     * Method powRecursive Calculates x to the power of n in a recursive way.
     *
     * @param n - the exponent.
     * @param x - the base.
     * @return each time it returns the next multiple of x,
     * and after n times it stops.
     */
    public static long powRecursive(long n, long x) {
        if (n == 0) {
            return 1;
        }
        return x * powRecursive(n - 1, x);
    }

    /**
     * Method powIter calculates x to the power of n in an iterative way.
     *
     * @param n - the exponent.
     * @param x - the base.
     * @return result - x to the power of n.
     */
    public static long powIter(long n, long x) {
        int result = 1; // x ^ n
        // Multiplies by x, n times:
        for (int i = 0; i < n; i++) {
            result *= x;
        }
        return result;
    }

    /**
     * main method receives two numbers and calculates the
     * first number to the power of the second.
     *
     * @param args - two numbers, the base and the exponent.
     */
    public static void main(String[] args) {
        // Converts the main args into the numbers:
        long x = Long.parseLong(args[0]);
        long n = Long.parseLong(args[1]);

        System.out.println("recursive: " + powRecursive(n, x));
        System.out.println("iterative: " + powIter(n, x));
    }
}