import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The Evaluation class represents an evaluation with various attributes and provides methods to manage evaluation data.
 */
public class Evaluation {
    private int evaluationID;
    private String supervisorName;
    private String supervisorID;
    private String employeeName;
    private String employeeID;
    private String evalDate;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;
    private String recommendations;

    /**
     * Constructs an Evaluation with default values and assigns a unique evaluation ID.
     */
    public Evaluation() {
        this.evaluationID = getNextEvaluationID();
        this.supervisorName = "";
        this.supervisorID = "";
        this.employeeName = "";
        this.employeeID = "";
        this.evalDate = "";
        this.answer1 = "";
        this.answer2 = "";
        this.answer3 = "";
        this.answer4 = "";
        this.answer5 = "";
        this.recommendations = "";
    }

    /**
     * Constructs an Evaluation with the specified values and assigns a unique evaluation ID.
     *
     * @param supervisorName the name of the supervisor
     * @param supervisorID the ID of the supervisor
     * @param employeeName the name of the employee
     * @param employeeID the ID of the employee
     * @param evalDate the date of the evaluation
     * @param answer1 feelings while doing specific task
     * @param answer2 one task all day
     * @param answer3 tasks you're good at
     * @param answer4 tasks you dread
     * @param answer5 tasks you look forward to
     * @param recommendations the recommendations
     */
    public Evaluation(String supervisorName, String supervisorID, String employeeName, String employeeID, String evalDate, String answer1, String answer2, String answer3, String answer4, String answer5, String recommendations) {
        this.evaluationID = getNextEvaluationID();
        this.supervisorName = supervisorName;
        this.supervisorID = supervisorID;
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.evalDate = evalDate;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.recommendations = recommendations;
    }

    /**
     * Gets the next available evaluation ID by reading the last ID from the file.
     *
     * @return the next available evaluation ID
     */
    private int getNextEvaluationID() {
        String filePath = "Evaluations.txt";
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
                return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * Saves the evaluation information to the file.
     */
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Evaluations.txt", true))) {
            writeToFile(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the evaluation details to the file.
     *
     * @param p the PrintWriter to write the evaluation details
     */
    protected void writeToFile(PrintWriter p) {
        p.println(evaluationID + "; " + supervisorName + "; " + supervisorID + "; " + employeeName + "; " + employeeID + "; " + evalDate + "; " + answer1 + "; " + answer2 + "; " + answer3 + "; " + answer4 + "; " + answer5 + "; " + recommendations);
    }
}