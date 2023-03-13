package models;

public class Report {
    private String studentName;
    private String courseName;
    private int numberOfCourses;

    public Report(String studentName, String courseName, int numberOfCourses) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.numberOfCourses = numberOfCourses;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getNumberOfCourses() {
        return numberOfCourses;
    }

    public void setNumberOfCourses(int numberOfCourses) {
        this.numberOfCourses = numberOfCourses;
    }

    @Override
    public String toString() {
        return String.format("%-30s | %-15s | %-15s", studentName, courseName, numberOfCourses);
    }
}
