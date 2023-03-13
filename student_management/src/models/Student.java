package models;

import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.StringBuilder;

public class Student implements Comparable<Student> {
    private String id;
    private String studentName;
    private ArrayList<Course> courses;

    public Student() {
    }

    public Student(String id, String studentName, String semester, String course) {
        this.id = id;
        this.studentName = studentName;
        this.courses = new ArrayList<>(Arrays.asList(
                new Course(semester, course)));
    }

    public Student(String id, String studentName){
        this.id = id;
        this.studentName = studentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(String semester, String course) {
        courses.add(
                new Course(semester, course));
    }

    public boolean hasCourse(String semester, String course) {
        return courses.stream().anyMatch(c -> c.getSemester().equals(semester) && c.getCourseName().equals(course));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(studentName, student.studentName)
                && Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentName, courses);
    }

    @Override
    public int compareTo(Student another) {
        return this.getStudentName().compareTo(another.getStudentName());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSTUDENT'S INFORMATION: \n");
        sb.append("Student's ID: " + id + "\n");
        sb.append("Student's name: " + studentName + "\n");
        sb.append("Courses of student : \n");
        if (hasCourse("No semester", "No course")) {
            sb.append("This student is not enrolled in any course!\n");
        } else {
            for (Course c : courses) {
                sb.append(c + "\n");
            }
        }
        return sb.toString();
    }
}
