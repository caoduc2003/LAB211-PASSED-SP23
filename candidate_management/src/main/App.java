package main;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import controllers.*;
import models.*;
import utils.*;

public class App {
    public static void main(String[] args) {
        CandidateList candidateList = new CandidateList();
        menu(candidateList);
    }

    public static void menu(CandidateList candidateList) {
        candidateList.addAll(Arrays.asList(
                new Experience("01", "Duc", "LDC", 2003, "Bac Giang", "012347823561", "ducldche176120@fpt.edu.vn", 0, 2,
                        "Java"),
                new Experience("02", "John", "Smith", 1990, "New York", "271498121949281", "john@gmail.com", 0, 6,
                        "ReactJS"),
                new Fresher("03", "Luoc", "Do", 1998, "Las Vegas", "1412908849021", "someone@onedrive.com", 1, "2019",
                        "Good", "FPT University"),
                new Fresher("04", "Lisa", "Su", 2000, "Los Angeles", "761724821894", "meomeo@yahoo.com", 1, "2021",
                        "Excellence", "HUST"),
                new Internship("05", "Nam", "Blala", 2003, "Hanoi", "071246712461782", "bla@example.com", 2, "SE", "6",
                        "FPT University")));
        while (true) {
            String[] menu = { "Create Experience", "Create Fresher", "Create Internship", "Searching", "Exit" };
            int choice = Validate.printMenu("CANDIDATE MANAGEMENT SYSTEM", menu);
            switch (choice) {
                case 1:
                    addCandidate(candidateList, choice);
                    break;
                case 2:
                    addCandidate(candidateList, choice);
                    break;
                case 3:
                    addCandidate(candidateList, choice);
                    break;
                case 4:
                    displayAndSearch(candidateList);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }

    }

    public static void addCandidate(CandidateList candidateList, int choice) {
        System.out.println("============ ADD CANDIDATE ============");
        String id;
        while (true) {
            id = Validate.inputString("Enter ID: ");
            if (candidateList.checkID(id)) {
                System.err.println("ID already exists in database!");
            } else {
                break;
            }
        }
        String firstName = Validate.inputString("Enter first name: ");
        String lastName = Validate.inputString("Enter last name: ");
        int birthDate = Validate.inputIntLimit(1900, Calendar.getInstance().get(Calendar.YEAR), "Enter birth date: ");
        String address = Validate.inputString("Enter address: ");
        String phoneNum = Validate.inputPhone("Enter phone number: ");
        String email = Validate.inputEmail("Enter email: ");
        int candidateType = choice - 1;
        if(candidateList.checkCandidateExist(firstName, lastName, birthDate, address, phoneNum, email, candidateType)){
           String continueChoice =  Validate.printChoices("Seem like this candidate information same as one of the candidates in database! Do you want to add this candidate? (Y/N): ", new String[] {"Y","N"});
            if(continueChoice.equalsIgnoreCase("N")){
                System.err.println("Aborted!");
                return;
            }
        }
        Candidate candidate = new Candidate(id, firstName, lastName, birthDate, address, phoneNum, email,
                candidateType);
        switch (candidateType) {
            case 0:
                addExperience(candidateList, candidate);
                break;
            case 1:
                addFresher(candidateList, candidate);
                break;
            case 2:
                addInternship(candidateList, candidate);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        System.out.println("Add candidate successfully!");
    }

    public static void addExperience(CandidateList candidateList, Candidate candidate) {
        int yearExp = Validate.inputExp("Enter year of experience: ", candidate.getBirthDate());
        String proSkill = Validate.inputString("Enter professional skill: ");
        candidateList.add(new Experience(candidate.getId(), candidate.getFirstName(), candidate.getLastName(),
                candidate.getBirthDate(), candidate.getAddress(), candidate.getPhoneNum(), candidate.getEmail(),
                candidate.getCandidateType(), yearExp, proSkill));
    }

    public static void addFresher(CandidateList candidateList, Candidate candidate) {
        String graduationDate = Validate.inputGraduationDate("Enter graduation year: ", candidate.getBirthDate());
        String graduationRank = Validate.inputGraduationRank();
        String education = Validate.inputString("Enter education: ");
        candidateList.add(new Fresher(candidate.getId(), candidate.getFirstName(), candidate.getLastName(),
                candidate.getBirthDate(), candidate.getAddress(), candidate.getPhoneNum(), candidate.getEmail(),
                candidate.getCandidateType(), graduationDate, graduationRank, education));
    }

    public static void addInternship(CandidateList candidateList, Candidate candidate) {
        String major = Validate.inputString("Enter major: ");
        String semester = Validate.inputString("Enter semester: ");
        String universityName = Validate.inputString("Enter university name: ");
        candidateList.add(new Internship(candidate.getId(), candidate.getFirstName(), candidate.getLastName(),
                candidate.getBirthDate(), candidate.getAddress(), candidate.getPhoneNum(), candidate.getEmail(),
                candidate.getCandidateType(), major, semester, universityName));
    }

    public static void displayAndSearch(CandidateList candidateList) {
        System.out.println("============== LIST CANDIDATE ==============");
        printCandidate(candidateList);
        String name = Validate.inputString("Enter name to search (First name or Last name): ");
        int candidateType = Validate.inputIntLimit(0, 2, "Enter candidate type(0-Experience,1-Fresher,2-Internship): ");
        Candidate[] candidates = candidateList.getCandidatesByName(name, candidateType);
        if (candidates.length == 0) {
            System.out.println("No candidate found!");
        } else {
            System.out.println("SEARCH RESULT: ");
            System.out.printf("%-20s%-10s%-15s%-20s%-20s%-5s", "NAME", "BIRTH", "ADDRESS", "PHONE", "EMAIL", "TYPE");
            System.out.println();
            for (Candidate candidate : candidates) {
                System.out.println(candidate);
            }
        }
    }

    public static void printCandidate(ArrayList<Candidate> candidateList) {
        System.out.println();
        AtomicInteger count = new AtomicInteger(0);
        System.out.println("EXPERIENCE:");
        candidateList.stream().filter(candidate -> candidate instanceof Experience).forEach(candidate -> {
            System.out.println(count.incrementAndGet() + ". " + candidate.getFullName() + " (Candidate ID: "
                    + candidate.getId() + ")");
        });
        count.set(0);
        System.out.println();
        System.out.println("FRESHER:");
        candidateList.stream().filter(candidate -> candidate instanceof Fresher).forEach(candidate -> {
            System.out.println(count.incrementAndGet() + ". " + candidate.getFullName() + " (Candidate ID: "
                    + candidate.getId() + ")");
        });
        count.set(0);
        System.out.println();
        System.out.println("INTERNSHIP:");
        candidateList.stream().filter(candidate -> candidate instanceof Internship).forEach(candidate -> {
            System.out.println(count.incrementAndGet() + ". " + candidate.getFullName() + " (Candidate ID: "
                    + candidate.getId() + ")");
        });
    }

}
