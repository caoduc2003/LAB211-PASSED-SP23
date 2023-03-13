package utils;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import models.Task;
import java.util.ArrayList;

public class Validate {
    public static final Scanner sc = new Scanner(System.in);
    public static final String PLAN_VALID = "^[0-9]{1,2}\\.5|[0-9]{1,2}\\.0$";

    public static String checkInputString() {
        while (true) {
            try {
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    throw new Exception("Do not empty this!");
                }
                return input;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }

    public static String checkInputTaskTypeId() {
        int choice = checkInputIntLimit(1, 4);;
        String taskCode = null;
        switch (choice) {
            case 1:
                taskCode = "Code";
                break;
            case 2:
                taskCode = "Test";
                break;
            case 3:
                taskCode = "Design";
                break;
            case 4:
                taskCode = "Review";
                break;
        }
        return taskCode;
    }

    public static String checkInputPlanFrom() {
        while (true) {
            try {
                String input = checkInputString();
                if (!(input.matches(PLAN_VALID) && Double.parseDouble(input) >= 8.0
                        && Double.parseDouble(input) <= 17.5)) {
                    throw new Exception("Wrong input! Please input time from 8.0 to 17.5.");
                }
                return input;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }

    public static String checkInputPlanTo(String planFrom) {
        while (true) {
            try {
                String input = checkInputString();
                if (!(input.matches(PLAN_VALID) && Double.parseDouble(input) >= Double.parseDouble(planFrom)
                        && Double.parseDouble(input) >= 8.0 && Double.parseDouble(input) <= 17.5)) {
                    throw new Exception("Wrong input! Please input time greater than " + planFrom + "! ");
                }
                return input;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }

    public static String checkInputDate() {
        while (true) {
            try {
                String dateInput = sc.nextLine().trim();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
                Date date = sdf.parse(dateInput); 
                if (dateInput.equalsIgnoreCase(sdf.format(date))) {
                    return dateInput;
                } else {
                    throw new Exception("Wrong input! Please input dd-MM-yyyy.");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }

    public static int checkInputInt() {
        while (true) {
            try {
                int input = Integer.parseInt(sc.nextLine().trim());
                if (input < 0) {
                    throw new Exception("You have to enter a positive number! Please input again.");
                }
                return input;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }

    public static boolean checkTaskAssigned(ArrayList<Task> t, String assignee, String date, String taskTypeId,
            String planFrom,
            String planTo) {
        for (Task task : t) {
            if (task.getAssign().equalsIgnoreCase(assignee) && task.getDate().equalsIgnoreCase(date)
                    && task.getTaskTypeId().equalsIgnoreCase(taskTypeId)
                    && task.getPlanFrom().equalsIgnoreCase(planFrom)
                    && task.getPlanTo().equalsIgnoreCase(planTo)) {
                return true;
            }
        }
        return false;
    }

    public static int checkInputIntLimit(int min, int max){
        while(true){
            try{
                int input = checkInputInt();
                if(input < min || input > max){
                    throw new Exception("Wrong input! Please input " + min + " - " + max +".");
                }
                return input;
            }catch(Exception e){
                System.err.println(e.getMessage());
                System.out.print("Re-enter: ");
            }
        }
    }

    public static void printMenu(String msg, String[] menu){
        System.out.print(msg);
        for(int i = 0; i < menu.length; i++){
            System.out.printf("\n%d. %s", i+1, menu[i]);
        }
        System.out.print("\nEnter your choice: ");
    }
}
