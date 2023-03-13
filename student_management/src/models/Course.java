package models;

public class Course {
    public String semester;
    public String courseName;

    public Course(String semester, String courseName) {
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return String.format("%-8s%s | %-8s%s","Semester: ",semester,"Course name: " ,courseName);
    }
}

