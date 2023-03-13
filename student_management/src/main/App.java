package main;

import utils.*;

import java.util.*;

import controllers.*;
import models.*;
import utils.*;

public class App {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        StudentList studentList = new StudentList();
        studentList.addAll(Arrays.asList(
                new Student("01", "Nam bla bla", "Spring", "C/C++"),
                new Student("02", "bla bla Nam", "Summer", "C/C++"),
                new Student("03", "duc", "Fall", "C/C++")));
        String[] choices = { "Create", "Find/Sort", "Update/Delete", "Report", "Exit" };
        while (true) {
            System.out.println();
            int choice = Input.printMenu("WELCOME TO STUDENT MANGAGEMENT", choices);
            switch (choice) {
                case 1:
                    createStudent(studentList);
                    break;
                case 2:
                    findSortStudent(studentList);
                    break;
                case 3:
                    editStudent(studentList);
                    break;
                case 4:
                    reportStudent(studentList);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void createStudent(StudentList studentList) {
        int count = 1;
        System.out.println("============ CREATE STUDENT ============");
        while (true) {
            String studentID;
            System.out.printf("Create student %d\n", count);
            while (true) {
                studentID = Input.inputString("Enter student ID: ");
                if (studentList.hasStudent(studentID)) {
                    System.out.println("Student ID already exists.Please re-enter!");
                } else {
                    break;
                }
            }
            String studentName = Input.inputString("Enter student name : ");
            String semester = Input.inputSemester();
            String courseName = Input.inputCourse();
            studentList.add(new Student(studentID, studentName, semester, courseName));
            String continueChoice = Input.printChoices("Do you want to continue (Y/N)?: ", new String[] { "Y", "N" });
            if (continueChoice.equalsIgnoreCase("y")) {
                count++;
            } else {
                break;
            }
        }
    }

    public static void findSortStudent(StudentList studentList) {
        System.out.println("============ FIND STUDENT ============");
        String name = Input.inputString("Enter student name: ");
        Student[] students = studentList.getStudentsByName(name);
        if (students.length == 0) {
            System.out.println("Student not found!");
            return;
        }
        Arrays.sort(students);
        System.out.println("\nResult:");
        System.out.printf("%-5s%-20s%-15s%-15s", "ID", "Student Name", "Semester", "Course");
        for (Student s : students) {
            s.getCourses().forEach(course -> System.out.format("\n%-5s%-20s%-15s%-15s", s.getId(), s.getStudentName(),
                    course.getSemester(), course.getCourseName()));
        }
    }

    public static void editStudent(StudentList studentList) {
        System.out.println("============ EDIT STUDENT ============");
        String id = Input.inputString("Enter student ID: ");
        if (!studentList.hasStudent(id)) {
            System.out.println("Student ID not found!");
            return;
        }
        Student student = studentList.getStudentByID(id);
        System.out.println(student);
        String choice = Input.printChoices("Do you want to update or delete this student? (U/D): ",
                new String[] { "U", "D" });
        if (choice.equalsIgnoreCase("U")) {
            updateStudent(student);
        } else {
            deleteStudent(studentList, student);
        }
        return;
    }

    public static void updateStudent(Student student) {
        System.out.println("============ UPDATE STUDENT ============");
        String choice = Input.printChoices("Do you want to update this student name or not? (Y/N): ",
                new String[] { "Y", "N" });
        if (choice.equalsIgnoreCase("Y")) {
            String studentName = Input.inputString("Enter new student name: ");
            student.setStudentName(studentName);
        }
        choice = Input.printChoices("Do you want to add, update, delete course or not? (A/U/D/N): ",
                new String[] { "A", "U", "D", "N" });
        switch (choice) {
            case "A":
                addStudentCourse(student);
                break;
            case "U":
                updateStudentCourse(student);
                break;
            case "D":
                deleteStudentCourse(student);
                break;
            case "N":
                return;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public static void addStudentCourse(Student student) {
        if (student.getCourses().stream().anyMatch(c -> c.getSemester().equalsIgnoreCase("No semester"))) {
            student.getCourses().clear();
        }
        String semester = Input.inputSemester();
        String courseName = Input.inputCourse();
        if (student.hasCourse(semester, courseName)) {
            System.out.println("Course already exists!");
        } else {
            student.addCourse(semester, courseName);
        }
        System.out.println("Course added!");
    }

    public static void updateStudentCourse(Student student) {
        if (student.hasCourse("No semester", "No course")) {
            System.out.println("Student has no course!");
            return;
        }
        String[] courses = student.getCourses().stream().map(c -> c.getCourseName() + " (" + c.getSemester() + ")")
                .toArray(String[]::new);
        int choiceCourse = Input.printMenu("Choose course to update", courses);
        Course course = student.getCourses().get(choiceCourse - 1);
        System.out.println(course);
        String newSemester = Input.inputSemester();
        String newCourseName = Input.inputCourse();
        if (student.hasCourse(newSemester, newCourseName)) {
            System.out.println("Course already exists!");
        } else {
            course.setSemester(newSemester);
            course.setCourseName(newCourseName);
        }
        System.out.println("Course updated!");
    }

    public static void deleteStudentCourse(Student student) {
        if (student.hasCourse("No semester", "No course")) {
            System.out.println("Student has no course!");
            return;
        }
        boolean danger = false;
        String[] courseDelete = student.getCourses().stream().map(c -> c.getCourseName() + " (" + c.getSemester() + ")")
                .toArray(String[]::new);
        if (courseDelete.length == 1) {
            danger = true;
        }
        int choiceCourseDelete = Input.printMenu("Choose course to delete", courseDelete);
        String choice = Input.printChoices("Are you sure to delete this course? (Y/N): ",
                new String[] { "Y", "N" });
        if (choice.equalsIgnoreCase("Y")) {
            if (danger) {
                student.getCourses().remove(choiceCourseDelete - 1);
                student.addCourse("No semester", "No course");
                System.out.println("Course deleted!");
                return;
            }
            student.getCourses().remove(choiceCourseDelete - 1);
            System.out.println("Course deleted!");
            return;
        } else {
            return;
        }

    }

    public static void deleteStudent(StudentList studentList, Student student) {
        System.out.println("Removing student " + student.getStudentName() + "...");
        studentList.remove(student);
        System.out.println("Student deleted!");
    }

    public static void reportStudent(StudentList studentList) {
        System.out.format("%-30s | %-15s | %-15s\n", "Student name", "Course", "Total");

        ArrayList<Report> reports = new ArrayList<>();

        for (Student student : studentList) {
            HashMap<String, Integer> courseCount = new HashMap<>(); // key = course name, value = count of course
            if (student.hasCourse("No semester", "No course")) {
                reports.add(new Report(student.getStudentName(), "No course", 0));
                continue;
            }
            student.getCourses().forEach(course -> courseCount.put(course.getCourseName(),
                    courseCount.getOrDefault(course.getCourseName(), 0) + 1)); // count course, if course exists,
                                                                               // present count + 1, else default count
                                                                               // = 0 + 1(because it is first time)

            for (String courseName : courseCount.keySet()) { // get key in view of the set
                reports.add(new Report(student.getStudentName(), courseName, courseCount.get(courseName)));
            }
        }
        reports.forEach(System.out::println);
    }
}
