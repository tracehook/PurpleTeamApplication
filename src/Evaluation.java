import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Evaluation {                              // private variables used within the class
    private int evaluationID; // Unique ID for each employee
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

    // constructors
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

    public Evaluation(String supervisorName, String supervisorID, String employeeName,
                      String employeeID, String evalDate, String answer1, String answer2, String answer3, String answer4, String answer5, String recommendations) {
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

    // Method to get the next available employee ID
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
                return 1; // If the file doesn't exist, start from 1
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1; // Fallback value in case of any error
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Evaluations.txt", true))) {
            writeToFile(writer);
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }

    protected void writeToFile(PrintWriter p) {
        p.println(evaluationID + "; " + supervisorName + "; " + supervisorID + "; " + employeeName + "; " + employeeID + "; "
         + evalDate + "; " + answer1 + "; " + answer2 + "; " + answer3 + "; " + answer4 + "; " + answer5 + "; " + recommendations);
    }
}