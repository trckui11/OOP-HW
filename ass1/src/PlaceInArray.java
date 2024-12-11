// 216303990 Arbel Feldman

/**
 * Class PlaceInArray takes an increasing array and a target number and
 * prints the first and last occurrence of the number in the array.
 * if the number is not in the array it prints -1 and -1.
 *
 * @version 19.0.2
 * @author Arbel Feldman - arbel.feldman@live.biu.ac.il
 * @since 2023-01-17
 */
public class PlaceInArray {
    /**
     * Method placeInArray finds the first occurrence of n in the array.
     *
     * @param n - the target number.
     * @param array - the sorted array of numbers
     * @return the index of the target number in the array,
     * or -1 if not found.
     */
    public static int placeInArray(int n, int[] array) {
        int index = 0;
        // Goes through the array and find the first occurrence of n:
        for (int i = 0; i < array.length; i++) {
            if (array[index] == n) {
                return index;
            }
            index++;
        }
        // If the number was not found returns -1:
        return -1;
    }

    /**
     * main Method gets an array and number and prints the first and last
     * occurrence of the number in the array (-1 if not found).
     *
     * @param args - an increasing list of numbers and a target number
     *               at the end.
     */
    public static void main(String[] args) {
        // The array of numbers (without the last input, the target):
        int[] numbers = new int[args.length - 1];
        // The last input - the target:
        int target = Integer.parseInt(args[args.length - 1]);
        // The first occurrence of the target:
        int start = placeInArray(target, numbers);
        // The last occurrence of the target:
        int end = start;
        int index = start;

        // Converting the string args into an int array (without the target):
        for (int i = 0; i < args.length - 1; i++) {
            int arg = Integer.parseInt(args[i]);
            numbers[i] = arg;
        }

        // Finds the target's occurrences, replaces them until gets to last:
        while (index != -1) {
            end = index;
            // Replacing target, so doesn't find it again in next iteration:
            numbers[index] = target - 1;
            index = placeInArray(target, numbers);
        }

        System.out.println(target + " start in " + start
                                  + " and end in " + end);
    }
}
