package app;

import java.util.ArrayList;
import java.util.Scanner;
import models.Task;
import utils.Validate;

public class App {
    public static final Scanner sc = new Scanner(System.in);

    public static void addTask(ArrayList<Task> t, int id) {
        System.out.println("\n");
        System.out.println("========== ADD TASK ==========");
        System.out.print("Enter requirement name: ");
        String requirementName = Validate.checkInputString();
        System.out.print("Enter task type: ");
        String taskTypeId = Validate.checkInputTaskTypeId();
        System.out.print("Enter date: ");
        String date = Validate.checkInputDate();
        System.out.print("Plan from: ");
        String planFrom = Validate.checkInputPlanFrom();
        System.out.print("Plan to: ");
        String planTo = Validate.checkInputPlanTo(planFrom);
        System.out.print("Enter assignee: ");
        String assign = Validate.checkInputString();
        System.out.print("Enter reviewer: ");
        String reviewer = Validate.checkInputString();
        if (Validate.checkTaskAssigned(t, assign, date, taskTypeId, planFrom, planTo)) {
            System.err.println("Task assigned to " + assign + "! Please choose another assignee!");
            return;
        }
        t.add(new Task(id, taskTypeId, requirementName, date, planFrom, planTo, assign, reviewer));
        System.out.println("Added successfully!");
    }

    public static void deleteTask(ArrayList<Task> t, int id) {
        if (t.isEmpty()) {
            System.err.println("Empty list!");
            return;
        }
        System.out.println("\n");
        System.out.println("========== DELETE TASK ==========");
        System.out.print("\nEnter id to delete: ");
        int idDelete = Validate.checkInputInt();
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i).getId() == idDelete) {
                t.remove(i);
                for (int j = i; j < t.size(); j++) {
                    t.get(j).setId(t.get(j).getId() - 1);
                }
                System.out.println("Deleted!");
                return;
            }
        }
        System.out.println("Not found!");
    }

    public static void displayTask(ArrayList<Task> t) {
        if (t.isEmpty()) {
            System.err.println("Empty list!");
            return;
        }
        System.out.println("\n");
        System.out.println("========== TASK LIST ==========");
        System.out.printf("%-5s%-20s%-20s%-20s%-20s%-20s%-20s\n", "ID", "Name", "Task Type", "Date",
                "Time", "Assignee", "Reviewer");
        for (Task task : t) {
            System.out.printf("%-5d%-20s%-20s%-20s%-20s%-20s%-20s\n", task.getId(), task.getRequirementName(),
                    task.getTaskTypeId(), task.getDate(),
                    Double.parseDouble(task.getPlanTo()) - Double.parseDouble(task.getPlanFrom()), task.getAssign(),
                    task.getReviewer());
        }
        System.out.println("\n");
    }

    public static void menu() {
        ArrayList<Task> t = new ArrayList<>();
        // add 3 task for test
        t.add(new Task(1, "Code", "Git", "07-02-2023", "8.0", "9.0", "Luoc Do", "PM"));
        t.add(new Task(2, "Test", "Create bla bla", "07-02-2023", "9.0", "13.5", "CuongLD", "Lead"));
        t.add(new Task(3, "Design", "Tracking bugs", "07-02-2023", "10.0", "17.5", "BinhLT", "Tester"));
        int id = 1;
        while (true) {
            System.out.print("\n");
            String[] menu = { " Add task", " Delete task", " Display task", " Exit" };
            Validate.printMenu("========== TASK MANAGEMENT ==========", menu);
            switch (Validate.checkInputIntLimit(1, 4)) {
                case 1:
                    addTask(t, id);
                    id++;
                    break;
                case 2:
                    deleteTask(t, id);
                    id--;
                    break;
                case 3:
                    displayTask(t);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        menu();
    }
}
