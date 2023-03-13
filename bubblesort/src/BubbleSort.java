import java.util.Random;
import java.util.Scanner;

public class BubbleSort {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter the size of the array: ");
        int size = checkInput();
        int[] array = CreateArray(size);
        boolean test = true;
        Display(array, "Unsorted array: ");
        System.out.print("\n");
        Sort(array, test);
        Display(array, "Sorted array: ");

    }

    public static int checkInput() {
        while (true) {
            try {
                int n = Integer.parseInt(sc.nextLine().trim());
                if (n > 0) {
                    return n;
                } else {
                    System.out.println("Please enter a positive number");
                }
            } catch (Exception e) {
                System.out.println("Please enter a number");
            }
        }
    }

    public static int[] CreateArray(int size) {
        int[] array = new int[size];
        Random rd = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rd.nextInt(100);
        }
        return array;
    }

    public static void Display(int[] array, String msg) {
        checkMsgDisplay(msg);
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    public static void checkMsgDisplay(String msg) {
        if (msg != null) {
            System.out.println(msg);
        }
    }

    public static void Sort(int[] array, boolean test) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    if (test) {
                        Display(array, null);
                        System.out.println(array[j] + " > " + array[j + 1] + " swap");
                    }
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                } else if (test && array[j] < array[j + 1]) {
                    Display(array, null);
                    System.out.println(array[j] + " < " + array[j + 1] + " not swap");
                } else if (test && array[j] == array[j + 1]) {
                    Display(array, null);
                    System.out.println(array[j] + " = " + array[j + 1] + " not swap");
                }
            }
        }
    }
}
