package models;

public class Experience extends Candidate {
    private int yearExperience;
    private String proSkill;

    public Experience() {
    }

    

    public Experience(String id, String firstName, String lastName, int birthDate, String address, String phoneNum,
            String email, int candidateType, int yearExperience, String proSkill) {
        super(id, firstName, lastName, birthDate, address, phoneNum, email, candidateType);
        this.yearExperience = yearExperience;
        this.proSkill = proSkill;
    }



    public int getYearExperience() {
        return yearExperience;
    }

    public void setYearExperience(int yearExperience) {
        this.yearExperience = yearExperience;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    @Override
    // toString using polymorphism
    public String toString() {
        return String.format("%s|%d|%s", super.toString(), yearExperience, proSkill);
    }
}
