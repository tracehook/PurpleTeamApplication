import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewEvaluationFrame extends BaseFrame {

    public NewEvaluationFrame() {
        super();
        setTitle("Employee Evaluation Form");
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components

        // Set preferred size for the input fields
        Dimension fieldSize = new Dimension(300, 25);

        // Create labels and input fields
        JTextField supervisorNameField = new JTextField();
        supervisorNameField.setPreferredSize(fieldSize);

        JTextField supervisorIDField = new JTextField();
        supervisorIDField.setPreferredSize(fieldSize);

        JTextField employeeNameField = new JTextField();
        employeeNameField.setPreferredSize(fieldSize);

        JTextField employeeIDField = new JTextField();
        employeeIDField.setPreferredSize(fieldSize);

        JTextField evaluationDateField = new JTextField();
        evaluationDateField.setPreferredSize(fieldSize);

        JTextArea question1Field = new JTextArea(3, 20);
        JTextArea question2Field = new JTextArea(3, 20);
        JTextArea question3Field = new JTextArea(3, 20);
        JTextArea question4Field = new JTextArea(3, 20);
        JTextArea question5Field = new JTextArea(3, 20);
        JTextArea recommendationsField = new JTextArea(3, 20);

        // Add components to panel with proper positioning
        gbc.gridx = 0; gbc.gridy = 0; // First column
        panel.add(new JLabel("Supervisor Name:"), gbc);
        gbc.gridx = 1;
        panel.add(supervisorNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Supervisor ID:"), gbc);
        gbc.gridx = 1;
        panel.add(supervisorIDField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Employee Name:"), gbc);
        gbc.gridx = 1;
        panel.add(employeeNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1;
        panel.add(employeeIDField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Evaluation Date:"), gbc);
        gbc.gridx = 1;
        panel.add(evaluationDateField, gbc);

        // Add questions with text areas
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Feelings while performing tasks:"), gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(question1Field), gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("If you could do one task all day:"), gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(question2Field), gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Tasks you're good at:"), gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(question3Field), gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        panel.add(new JLabel("Tasks you dread:"), gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(question4Field), gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        panel.add(new JLabel("Tasks you look forward to:"), gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(question5Field), gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        panel.add(new JLabel("Recommendations/Notes:"), gbc);
        gbc.gridx = 1;
        panel.add(new JScrollPane(recommendationsField), gbc);

        // Create and add the Save button, align it across two columns
        JButton saveButton = new JButton("Save");
        gbc.gridx = 0; gbc.gridy = 11;
        gbc.gridwidth = 2; // Make the button span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        panel.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to happen on button click, gather all the input data
                String supervisorName = supervisorNameField.getText();
                String supervisorID = supervisorIDField.getText();
                String employeeName = employeeNameField.getText();
                String employeeID = employeeIDField.getText();
                String evaluationDate = evaluationDateField.getText();
                String feelings = question1Field.getText();
                String favoriteTask = question2Field.getText();
                String goodAtTasks = question3Field.getText();
                String dreadedTasks = question4Field.getText();
                String lookedForwardTasks = question5Field.getText();
                String recommendations = recommendationsField.getText();

                // Print out the evaluation for now
                System.out.println("Evaluation Saved");
                System.out.printf("Supervisor: %s (%s)\nEmployee: %s (%s)\nEvaluation Date: %s\n",
                        supervisorName, supervisorID, employeeName, employeeID, evaluationDate);
                System.out.printf("Feelings: %s\nFavorite Task: %s\nGood at: %s\nDreaded: %s\nLooked Forward: %s\nRecommendations: %s\n",
                        feelings, favoriteTask, goodAtTasks, dreadedTasks, lookedForwardTasks, recommendations);
            }
        });

        return panel;
    }
}
