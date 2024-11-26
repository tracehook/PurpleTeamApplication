import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The Employee class represents an employee with various attributes and provides methods to manage employee data.
 */
public class Employee {
    private int employeeID; // Unique ID for each employee
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String username;
    private String password;
    private String accessLevel;
    private String dateHired;
    private String currentJob;
    private String softSkills;
    private String hardSkills;
    private String talents;
    private String virtues;
    private String pastJobs;

    /**
     * Constructs an Employee with default values and assigns a unique employee ID.
     */
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

    /**
     * Constructs an Employee with the specified values and assigns a unique employee ID.
     *
     * @param firstName the first name of the employee
     * @param lastName the last name of the employee
     * @param emailAddress the email address of the employee
     * @param username the username of the employee
     * @param password the password of the employee
     * @param accessLevel the access level of the employee
     * @param dateHired the date the employee was hired
     * @param currentJob the current job of the employee
     * @param softSkills the soft skills of the employee
     * @param hardSkills the hard skills of the employee
     * @param talents the talents of the employee
     * @param virtues the virtues of the employee
     * @param pastJobs the past job titles of the employee
     */
    public Employee(String firstName, String lastName, String emailAddress, String username, String password, String accessLevel, String dateHired, String currentJob, String softSkills, String hardSkills, String talents, String virtues, String pastJobs) {
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

    /**
     * Gets the next available employee ID by reading the last ID from the file.
     *
     * @return the next available employee ID
     */
    private int getNextEmployeeID() {
        String filePath = "Employees.txt";
        try {
            if (Files.exists(Paths.get(filePath))) {
                List<String> allLines = Files.readAllLines(Paths.get(filePath));
                if (allLines.isEmpty()) {
                    return 1; // If file is empty, start from 1
                }
                String lastLine = allLines.get(allLines.size() - 1);
                String[] parts = lastLine.split(";");
                return Integer.parseInt(parts[0].trim()) + 1;
            } else {
                return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * Saves the employee information to the file.
     */
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Employees.txt", true))) {
            writeToFile(writer);
            System.out.println("Employee data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the employee details to the file.
     *
     * @param p the PrintWriter to write the employee details
     */
    protected void writeToFile(PrintWriter p) {
        p.println(employeeID + "; " + firstName + "; " + lastName + "; " + emailAddress + "; " + username + "; " + password
                + "; " + accessLevel + "; " + dateHired + "; " + currentJob + "; " + softSkills + "; " + hardSkills
                + "; " + talents + "; " + virtues + "; " + pastJobs);
    }
}