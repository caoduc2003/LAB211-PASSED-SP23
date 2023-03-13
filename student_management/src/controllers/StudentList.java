package controllers;

import java.util.ArrayList;

import models.Student;

public class StudentList extends ArrayList<Student> {
    public boolean hasStudent(String id) {
        return this.stream()
                    .anyMatch(s -> s.getId().equals(id));
    }
    
    public Student getStudentByID(String id) {
        return this.stream()
                    .filter(s -> s.getId().equals(id))
                    .findAny()
                    .orElse(null);
    }

    public Student[] getStudentsByName(String name) {
        return this.stream()
                    .filter(s -> s.getStudentName().toLowerCase().contains(name.toLowerCase()))
                    .toArray(Student[]::new);
    }
    
}
