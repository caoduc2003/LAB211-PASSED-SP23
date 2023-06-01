package utils;

import java.util.Calendar;
import java.util.Scanner;
import java.util.stream.Stream;

public class Validate {
    private static final String EMAIL_VALID = "\"^[a-z][a-z0-9_\\\\.]{5,32}@[a-z0-9]{2,}(\\\\.[a-z0-9]{2,4}){1,2}\"";
    private static final String PHONE_VALID = "\\b\\d{10,}\\b";
    private static final String YEAR_VALID = "\\b(19|20)\\d{2}\\b";
    private static final Scanner sc = new Scanner(System.in);

    public static String inputString(String msg) {
        if (msg != null) {
            System.out.print(msg);
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

    public static String inputString() {
        return inputString(null);
    }

    public static String printChoices(String msg, String[] validValues) {
        return inputString(msg, validValues, false);
    }

    public static String inputString(String msg, String[] validValues, boolean caseSensitive) {
        while (true) {
            System.out.format("%s", msg);
            String input = sc.nextLine().trim();
            if (Stream.of(validValues).anyMatch(s -> caseSensitive ? s.equals(input) : s.equalsIgnoreCase(input))) {
                String value = Stream.of(validValues)
                        .filter(s -> caseSensitive ? s.equals(input) : s.equalsIgnoreCase(input)).findFirst()
                        .orElse(null);
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
                System.out.print("Re-enter: ");
            }
        }
    }

    public static int inputIntLimit(int min, int max) {
        return inputIntLimit(min, max, null);
    }

    public static int inputIntLimit(int min, int max, String msg) {
        if (msg != null) {
            System.out.format("%s", msg);
        }
        while (true) {
            try {
                int result = Integer.parseInt(inputString(null));
                if (result < min || result > max) {
                    throw new Exception();
                }
                return result;
            } catch (Exception e) {
                System.err.println("Invalid input! Please input in range [" + min + ", " + max + "]");
                System.out.print("Re-enter: ");
            }
        }
    }

    public static String inputPhone(String msg) {
        System.out.print(msg);
        while (true) {
            try {
                String phone = inputString();
                if (!phone.matches(PHONE_VALID)) {
                    throw new Exception("Invalid phone number");
                }
                return phone;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }

    public static String inputEmail(String msg) {
        System.out.print(msg);
        while (true) {
            try {
                String email = inputString(null);
                if (!email.matches(EMAIL_VALID)) {
                    throw new Exception("Invalid email");
                }
                return email;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }

    public static int inputExp(String msg, int birthDate) {
        int age = Calendar.getInstance().get(Calendar.YEAR) - birthDate;
        System.out.print(msg);
        while (true) {
            try {
                int exp = Integer.parseInt(inputString());
                if (exp < 0 || exp > age) {
                    throw new Exception("Invalid experience!");
                }
                return exp;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }

    public static String inputGraduationRank() {
        return printChoices("Enter graduation rank (Excellence, Good, Fair, Poor): ",
                new String[] { "Excellence", "Good", "Fair", "Poor" });
    }

    public static String inputGraduationDate(String msg, int birthDate) {
        int timeNow = Calendar.getInstance().get(Calendar.YEAR);
        System.out.print("Enter graduation year: ");
        while (true) {
            try {
                String graduationDate = inputString();
                if (!graduationDate.matches(YEAR_VALID)) {
                    throw new Exception("Invalid graduation date");
                }
                int graduationYear = Integer.parseInt(graduationDate);
                if (graduationYear <= birthDate || graduationYear > timeNow) {
                    throw new Exception("Invalid graduation date");
                }
                return graduationDate;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }
}
