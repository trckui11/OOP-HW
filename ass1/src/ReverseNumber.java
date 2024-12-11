// 216303990 Arbel Feldman

/**
 * Class ReverseNumber receives a number and prints it reversed.
 *
 * @version 19.0.2
 * @author Arbel Feldman - arbel.feldman@live.biu.ac.il
 * @since 2023-01-17
 */
public class ReverseNumber {
    private static final int BASE_10 = 10;

    /**
     * Method reverseNum takes a number and returns it written backwards.
     *
     * @param n - the number to be reversed.
     * @return result - the backwards number.
     */
    public static int reverseNum(int n) {
        int result = 0;
        // Saves the original number:
        int original = Math.abs(n);
        if (n == 0) {
            return 0;
        }
        // the sign of the number:
        int sign = Math.abs(n) / n;
        // makes n positive:
        n = Math.abs(n);
        // the log calculates the length of the number:
        for (int i = 0; i < Math.log10(original) + 1; i++) {
            // Adds last digit of n times according power in the new number:
            double add = (n % BASE_10)
                         * (Math.pow(BASE_10, Math.floor(Math.log10(n))));
            // Checks for overflow:
            if (result + add > Math.pow(2, 31)) {
                return 0;
            }
            result += add;
            // Deletes the last digit of n:
            n /= BASE_10;
        }

        return result * sign;
    }

    /**
     * main Method receives a number and prints in reversed.
     *
     * @param args - the number to be reversed.
     */
    public static void main(String[] args) {
        // Convert the arg into int:
        int n = Integer.parseInt(args[0]);
        System.out.println("reverse number: " + reverseNum(n));
    }
}
