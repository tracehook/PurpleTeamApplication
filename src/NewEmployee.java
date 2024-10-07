import java.util.List;

// TEST CLASS TO MAKE SURE THE NEW EMPLOYEE OBJECT WORKS

public class NewEmployee {
    private String firstName;
    private String lastName;
    private String email;
    private String dateHired;
    private String currentJob;
    private List<String> softSkills;
    private List<String> hardSkills;
    private List<String> talents;
    private List<String> virtues;
    private List<String> pastJobs;

    public NewEmployee(String firstName, String lastName, String email, String dateHired, String currentJob,
                       List<String> softSkills, List<String> hardSkills, List<String> talents,
                       List<String> virtues, List<String> pastJobs) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateHired = dateHired;
        this.currentJob = currentJob;
        this.softSkills = softSkills;
        this.hardSkills = hardSkills;
        this.talents = talents;
        this.virtues = virtues;
        this.pastJobs = pastJobs;
    }

    // Getters (and setters if needed)
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDateHired() {
        return dateHired;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public List<String> getSoftSkills() {
        return softSkills;
    }

    public List<String> getHardSkills() {
        return hardSkills;
    }

    public List<String> getTalents() {
        return talents;
    }

    public List<String> getVirtues() {
        return virtues;
    }

    public List<String> getPastJobs() {
        return pastJobs;
    }

    // toString method for easier printing
    @Override
    public String toString() {
        return "NewEmployee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateHired='" + dateHired + '\'' +
                ", currentJob='" + currentJob + '\'' +
                ", softSkills=" + softSkills +
                ", hardSkills=" + hardSkills +
                ", talents=" + talents +
                ", virtues=" + virtues +
                ", pastJobs=" + pastJobs +
                '}';
    }
}
