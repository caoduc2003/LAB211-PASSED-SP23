package models;

public class Internship extends Candidate {
    private String major;
    private String semester;
    private String university;

    public Internship() {
    }

    public Internship(String id, String firstName, String lastName, int birthDate, String address, String phoneNum,
            String email, int candidateType, String major, String semester, String university) {
        super(id, firstName, lastName, birthDate, address, phoneNum, email, candidateType);
        this.major = major;
        this.semester = semester;
        this.university = university;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUniversity() {
        return this.university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public String toString(){
        return String.format("%s|%s|%s|%s", super.toString(), major, semester, university);
    }
}
