package utils;

import java.util.Locale;
import java.util.Scanner;
import app.App;

public class Validate {
    private static final Scanner sc = new Scanner(System.in);
    private static final String ACCOUNT_NUMBER_VALID = "^\\d{10}$";

    public static void printMenu(String msg, String[] choice) {
        for (int i = 0; i < choice.length; i++) {
            System.out.printf("\n%d. %s", i + 1, choice[i]);
        }
        System.out.printf("\n%s", msg);
    }

    public static String checkInputString(Locale curLang) {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    throw new Exception();
                }
                return input;
            } catch (Exception e) {
                App.getMessage(curLang, "emptyInput");
                System.out.println();
                App.getMessage(curLang, "reInput");
            }
        }
    }

    public static int checkInputIntLimit(int min, int max, Locale curLang) {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new Exception();
                }
                return result;
            } catch (Exception e) {
                App.getMessage(curLang, "errCheckInputIntLimit");
                System.out.println();
                App.getMessage(curLang, "reInput");
            }
        }
    }

    public static String checkInputAccount(Locale curLang) {
        while (true) {
            try {
                String input = checkInputString(curLang);
                if (!input.matches(ACCOUNT_NUMBER_VALID)) {
                    throw new Exception();
                }
                return input;
            } catch (Exception e) {
                App.getMessage(curLang, "errCheckInputAccount");
                System.out.println();
                App.getMessage(curLang, "reInput");
            }
        }
    }

    public static String checkInputPassword(Locale curLang) {
        while (true) {
            try {
                String input = checkInputString(curLang);
                if (!input.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,32}$")) {
                    throw new Exception();
                }
                return input;
            } catch (Exception e) {
                App.getMessage(curLang, "errCheckInputPassword");
                System.out.println();
                App.getMessage(curLang, "reInput");
            }
        }
    }

    public static String checkInputCaptcha(String captcha, Locale curLang) {
        while (true) {
            try {
                String input = checkInputString(curLang);
                if (!input.equalsIgnoreCase(captcha)) {
                    throw new Exception();
                }
                return input;
            } catch (Exception e) {
                App.getMessage(curLang, "errCheckCaptchaInput");
                System.out.println();
                App.getMessage(curLang, "reInput");
            }
        }
    }
}
