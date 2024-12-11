// 216303990 Arbel Feldman

/**
 * Class TripletOfZero takes an array of numbers and prints 3 of them
 * that sum up to 0. if not found prints -1.
 * it prints either in ascending or descending order according to the input.
 *
 * @version 19.0.2
 * @author Arbel Feldman - arbel.feldman@live.biu.ac.il
 * @since 2023-01-17
 */
public class TripletOfZero {
    /**
     * Method stringsToArray takes an array of strings and converts it into
     * an array of integers.
     *
     * @param numbers - the array of strings to convert.
     * @return intArray - the converted array.
     */
    public static int[] stringsToArray(String[] numbers) {
        int[] intArray = new int[numbers.length - 1];
        // Goes through the array and converts the strings into integers:
        for (int i = 1; i < numbers.length; i++) {
            // "i" starts at 1 to skip the first input (asc/desc)
            int num = Integer.parseInt(numbers[i]);
            intArray[i - 1] = num;
        }
        return intArray;
    }

    /**
     * Method arrayToString takes an array of numbers and converts
     * it into a string.
     *
     * @param numbers - the array to convert.
     * @return arrayString - the converted array.
     */
    public static String arrayToString(int[] numbers) {
        // if the array contains only -1 then a triplet was not found:
        if (numbers[0] == -1 && numbers[1] == -1 && numbers[2] == -1) {
            return "-1";
        }

        String arrayString = "[";
        // Goes through array, concatenates the number to string, with commas:
        for (int i = 0; i < numbers.length - 1; i++) {
            arrayString += numbers[i] + ", ";
        }
        // The last one is outside the loop because there's no comma after it:
        arrayString += numbers[numbers.length - 1] + "]";
        return arrayString;
    }

    /**
     * Method tripletOfZero takes an array and finds 3 numbers within it
     * that sum up to 0.
     *
     * @param numbers - the array of numbers.
     * @return triplet - an array of the 3 numbers that sum up to 0.
     * if such triplet was not found in the array, it returns {-1, -1, -1}.
     */
    public static int[] tripletOfZero(int[] numbers) {
        int[] triplet = {-1, -1, -1};

        // 3 indexes go each through array. if all different and sum=0 return:
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                for (int k = 0; k < numbers.length; k++) {
                    if (i != j && j != k && k != i
                        && numbers[i] + numbers[j] + numbers[k] == 0) {
                        // Save the triplet in an array:
                        triplet[0] = numbers[i];
                        triplet[1] = numbers[j];
                        triplet[2] = numbers[k];
                        return triplet;
                    }
                }
            }
        }
        // If a triplet was not found, it returns the default {-1, -1, -1}:
        return triplet;
    }

    /**
     * Method ascTripletPrint sorts an array in ascending order
     * using bubble sort and prints it.
     *
     * @param numbers - the array to be sorted.
     */
    public static void ascTripletPrint(int[] numbers) {
        // Bubble sort the array in ascending order:
        int n = numbers.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    // swap numbers[j+1] and numbers[j]:
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

        System.out.println("the triplet is: " + arrayToString(numbers));
    }

    /**
     * Method descTripletPrint sorts an array in descending order
     * using bubble sort and prints it.
     *
     * @param numbers - the array to be sorted.
     */
    public static void descTripletPrint(int[] numbers) {
        // Bubble sort the array in descending order:
        int n = numbers.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers[j] < numbers[j + 1]) {
                    // swap numbers[j+1] and numbers[j]:
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

        System.out.println("the triplet is: " + arrayToString(numbers));
    }

    /**
     * Method isAsc checks if the first parameter
     * of an array of strings is "asc".
     *
     * @param order - the array of strings.
     * @return a boolean value - true if it is asc, false otherwise.
     */
    public static boolean isAsc(String[] order) {
        return order[0].equals("asc");
    }

    /**
     * The main method takes an array of integers, finds 3 of them that sum up
     * to 0 and prints them in ascending or descending order according to the
     * input. if the numbers could not be found, it prints -1.
     *
     * @param args - the word "asc" or "desc", and an array of integers.
     */
    public static void main(String[] args) {
        // Converts the args to an array of ints (without the asc/desc):
        int[] numbers = stringsToArray(args);
        // Finds the triplet:
        int[] solution = tripletOfZero(numbers);

        // Checks if the input is asc or desc and prints accordingly:
        if (isAsc(args)) {
            ascTripletPrint(solution);
        } else {
            descTripletPrint(solution);
        }
    }
}
