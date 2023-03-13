package controllers;

import java.util.ArrayList;
import models.Candidate;

public class CandidateList extends ArrayList<Candidate> {
    public Candidate[] getCandidatesByName(String name, int candidateType) {
        ArrayList<Candidate> matchingCandidates = new ArrayList<>();
        for (Candidate candidate : this) {
            if (candidate.getCandidateType() == candidateType && candidate.getFirstName().equalsIgnoreCase(name)
                    || candidate.getLastName().equalsIgnoreCase(name)) {
                matchingCandidates.add(candidate);
            }
        }
        return matchingCandidates.toArray(new Candidate[matchingCandidates.size()]);
    }

    public boolean checkID(String id) {
        return this.stream().anyMatch(candidate -> candidate.getId().equalsIgnoreCase(id));
    }

    public boolean checkCandidateExist(String firstName, String lastName,
            int birthDate, String address, String phoneNum, String email, int candidateType) {
        return this.stream().anyMatch(candidate -> candidate.getFirstName().equalsIgnoreCase(firstName)
                && candidate.getLastName().equalsIgnoreCase(lastName)
                && candidate.getBirthDate() == birthDate && candidate.getAddress().equalsIgnoreCase(address)
                && candidate.getPhoneNum().equalsIgnoreCase(phoneNum) && candidate.getEmail().equalsIgnoreCase(email)
                && candidate.getCandidateType() == candidateType);
    }
}
