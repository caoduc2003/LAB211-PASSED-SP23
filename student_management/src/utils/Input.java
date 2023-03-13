package utils;

import java.util.Scanner;
import java.util.stream.Stream;

public class Input {
    static final Scanner sc = new Scanner(System.in);

    public static void clearBuffer() {
        sc.nextLine();
    }

    public static String inputString(String msg) {
        if (msg != null) {
            System.out.format("%s", msg);
        }
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    throw new Exception("Input cannot be empty");
                }
                return input;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }

    public static String printChoices(String msg, String[] validValues) {
        return inputString(msg, validValues, false);
    }

    public static String inputString(String msg, String[] validValues, boolean caseSensitive) {
        while (true) {
            System.out.format("%s", msg);
            String input = sc.nextLine().trim();
            if (Stream.of(validValues).anyMatch(s -> caseSensitive ? s.equals(input) : s.equalsIgnoreCase(input))) {
                String value = Stream.of(validValues).filter(s -> caseSensitive ? s.equals(input) : s.equalsIgnoreCase(input)).findFirst().orElse(null);
                return value;
            } else {
                System.out.println("Invalid value");
            }
        }
    }

    public static int printMenu(String msg, String[] choices) {
        while (true) {
            try {
                System.out.format("\n\n%s\n", msg);
                for (int i = 0; i < choices.length; i++) {
                    System.out.format("%d. %s\n", i + 1, choices[i]);
                }
                return menuChoices("Enter your choice: ", choices.length);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int menuChoices(String msg, int length) {
        System.out.printf("%s", msg);
        while (true) {
            try {
                int choice = Integer.parseInt(inputString(null));
                if (choice < 1 || choice > length) {
                    throw new Exception("Invalid choice");
                }
                return choice;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.err.println("Invalid choice");
                System.out.print("Re-enter: ");
            }
        }
    }

    public static String inputSemester() {
        return Input.inputString("Enter semester (Spring, Summer, Fall) : ",
                new String[] { "Spring", "Summer", "Fall" }, false);
    }

    public static String inputCourse() {
        return Input.inputString("Enter course name (.Net, Java, C/C++) : ", new String[] { ".Net", "Java", "C/C++" },
                false);
    }
    
}
