package app;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import utils.Validate;

public class App {
    public static final Scanner sc = new Scanner(System.in);
    private static final char[] CHARS = {'1', 'A', 'a', 'B', 'b', 'C',
        'c', '2', 'D', 'd', 'E', 'e', 'F', 'f', '3', 'G', 'g', 'H', 'h',
        'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', '4', 'M', 'm', 'N', 'n',
        'O', 'o', '5', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',
        '6', '7', 'U', 'u', 'V', 'v', 'U', 'u', 'W', 'w', '8', 'X', 'x',
        'Y', 'y', 'Z', 'z', '9'}; // stupid captcha
    private static final int CAPTCHA_LENGTH = 6;

    public static void main(String[] args) {
        langSelector();
    }

    public static void langSelector() {
        Locale vietnamese = Locale.forLanguageTag("vi");
        Locale english = Locale.forLanguageTag("en");
        String[] choice = { " Vietnamese", " English", " Exit" };
        Validate.printMenu("Choose your language: ",choice);
        switch (Validate.checkInputIntLimit(1, 3, english)) {
            case 1:
                login(vietnamese);
                break;
            case 2:
                login(english);
                break;
            case 3:
                System.out.println("Goodbye!");
                System.exit(0);
        }
    }

    public static void login(Locale lang){
        System.out.println("================ LOGIN ================");
        getMessage(lang, "enterAccountNumber");
        int accNum = Integer.parseInt(Validate.checkInputAccount(lang));
        getMessage(lang, "enterPassword");
        String password = Validate.checkInputPassword(lang);
        getMessage(lang, "Captcha");
        String captchaTemp = captchaGenerator();
        printCaptcha(captchaTemp);
        getMessage(lang, "enterCaptcha");
        String captcha = Validate.checkInputCaptcha(captchaTemp,lang);
        getMessage(lang, "loginSuccess");
    }

    public static void getMessage(Locale curLocale, String key) {
        // get resource bundle for current locale (vi) or (en)
        ResourceBundle messages = ResourceBundle.getBundle("lang/" + curLocale, curLocale);
        System.out.printf("%s",messages.getString(key));
    }

    public static String captchaGenerator(){
        StringBuilder captcha = new StringBuilder();
        for(int i = 0; i < CAPTCHA_LENGTH; i++){
            captcha.append(CHARS[(int) (Math.random() * (CHARS.length - 1))]);
        }
        return captcha.toString();
    }

    public static void printCaptcha(String captcha){
        System.out.print(captcha);
        System.out.println();
    }
}
