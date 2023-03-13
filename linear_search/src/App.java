import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class App {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter number of array: ");
        int n = Integer.parseInt(inputString());
        System.out.print("Enter search value: ");
        int search = Integer.parseInt(inputString());
        int[] arr = generateArray(n);
        displayArray(n, arr);
        int result = search(arr, search);
        if (result == -1) {
            System.out.println("\nNot found");
        } else {
            System.out.print("\nFound " + search + " at index " + result);
        }
    }

    public static String inputString() {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    throw new Exception("Input cannot be empty");
                }
                if (!input.matches("[0-9]+")) {
                    throw new Exception("Input must be a number");
                }
                return input;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }

    public static int[] generateArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(n);
        }
        return arr;
    }

    public static void displayArray(int n, int[] arr) {
        System.out.print("The array: [");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    public static int search(int[] arr, int search) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == search) {
                return i;
            }
        }
        return -1;
    }
}
