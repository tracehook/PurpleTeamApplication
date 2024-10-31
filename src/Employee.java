import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Employee {
    private int employeeID; // Unique ID for each employee
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String username;
    private String password; // Stored in plain text (not recommended for production)
    private String accessLevel;
    private String dateHired;
    private String currentJob;
    private String softSkills;
    private String hardSkills;
    private String talents;
    private String virtues;
    private String pastJobs;

    // Constructor
    public Employee() {
        this.employeeID = getNextEmployeeID();
        this.firstName = "";
        this.lastName = "";
        this.emailAddress = "";
        this.username = "";
        this.password = "";
        this.accessLevel = "0"; // Default to employee
        this.dateHired = "";
        this.currentJob = "";
        this.softSkills = "";
        this.hardSkills = "";
        this.talents = "";
        this.virtues = "";
        this.pastJobs = "";
    }

    // Overloaded constructor
    public Employee(String firstName, String lastName, String emailAddress,
                    String username, String password, String accessLevel,
                    String dateHired, String currentJob, String softSkills,
                    String hardSkills, String talents, String virtues, String pastJobs) {
        this.employeeID = getNextEmployeeID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.dateHired = dateHired;
        this.currentJob = currentJob;
        this.softSkills = softSkills;
        this.hardSkills = hardSkills;
        this.talents = talents;
        this.virtues = virtues;
        this.pastJobs = pastJobs;
    }

    // Method to get the next available employee ID
    private int getNextEmployeeID() {
        String filePath = "Employees.txt";
        try {
            if (Files.exists(Paths.get(filePath))) {
                List<String> allLines = Files.readAllLines(Paths.get(filePath));
                if (allLines.isEmpty()) {
                    return 1; // If the file is empty, start from 1
                }
                String lastLine = allLines.get(allLines.size() - 1);
                String[] parts = lastLine.split(";");
                return Integer.parseInt(parts[0].trim()) + 1;
            } else {
                return 1; // If the file doesn't exist, start from 1
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1; // Fallback value in case of any error
    }

    // Method to save the employee information to the file
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Employees.txt", true))) {
            writeToFile(writer);
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }

    // Write the employee details to the file
    protected void writeToFile(PrintWriter p) {
        p.println(employeeID + "; " + firstName + "; " + lastName + "; " + emailAddress + "; " + username + "; " + password
                  + "; " + accessLevel + "; " + dateHired + "; " + currentJob + "; " + softSkills + "; " + hardSkills
                  + "; " + talents + "; " + virtues + "; " + pastJobs);
    }
}
