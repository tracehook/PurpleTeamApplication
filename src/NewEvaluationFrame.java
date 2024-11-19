import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewEvaluationFrame extends BaseFrame {

    // Declare JTextFields and JTextAreas as class members
    private JTextField supervisorNameField;
    private JTextField supervisorIDField;
    private JTextField employeeNameField;
    private JTextField employeeIDField;
    private JTextField evaluationDateField;
    private JTextArea question1Field;
    private JTextArea question2Field;
    private JTextArea question3Field;
    private JTextArea question4Field;
    private JTextArea question5Field;
    private JTextArea recommendationsField;

    public NewEvaluationFrame() {
        super();
        setTitle("Employee Evaluation Form");
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#424249")); // Setting the background color
        panel.setLayout(new BorderLayout()); // Use BorderLayout for top, center, bottom arrangement

        // Panel for all input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components

        // Initialize JTextFields and JTextAreas
        supervisorNameField = createTextField();
        supervisorIDField = createTextField();
        employeeNameField = createTextField();
        employeeIDField = createTextField();
        evaluationDateField = createTextField();
        evaluationDateField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // Format the date
        evaluationDateField.setEditable(false); // Make the field non-editable

        question1Field = createTextArea();
        question2Field = createTextArea();
        question3Field = createTextArea();
        question4Field = createTextArea();
        question5Field = createTextArea();
        recommendationsField = createTextArea();

        // Add components to input panel
        addComponent(inputPanel, gbc, "Supervisor Name:", supervisorNameField, 0);
        addComponent(inputPanel, gbc, "Supervisor ID:", supervisorIDField, 1);
        addComponent(inputPanel, gbc, "Employee Name:", employeeNameField, 2);
        addComponent(inputPanel, gbc, "Employee ID:", employeeIDField, 3);
        addComponent(inputPanel, gbc, "Evaluation Date:", evaluationDateField, 4);

        // Add questions with text areas
        addComponent(inputPanel, gbc, "Feelings while performing specific tasks, explain why:", new JScrollPane(question1Field), 5);
        addComponent(inputPanel, gbc, "If you could do one task all day, explain why:", new JScrollPane(question2Field), 6);
        addComponent(inputPanel, gbc, "Tasks you're good at:", new JScrollPane(question3Field), 7);
        addComponent(inputPanel, gbc, "Tasks you dread, explain why:", new JScrollPane(question4Field), 8);
        addComponent(inputPanel, gbc, "Tasks you look forward to, explain why:", new JScrollPane(question5Field), 9);
        addComponent(inputPanel, gbc, "Recommendations/Notes:", new JScrollPane(recommendationsField), 10);

        // Create a JScrollPane for the input panel
        JScrollPane scrollPane = new JScrollPane(inputPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER); // Add scrollable area in the center

        // Add Home button (not in the scrollable area)
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
            }
        });
        panel.add(homeButton, BorderLayout.NORTH); // Add Home button at the top

        // Create and add the Save button at the bottom (not in the scrollable area)
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve values from input fields
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

                // Validate fields with regular expressions (placeholders)
                if (!validateName(supervisorName) || !validateName(employeeName)) {
                    JOptionPane.showMessageDialog(panel, "Invalid name. Please enter valid names.");
                    return;
                }

                if (!validateID(supervisorID) || !validateID(employeeID)) {
                    JOptionPane.showMessageDialog(panel, "Invalid ID. Please enter valid IDs.");
                    return;
                }

                // Create a new Evaluation object and save it
                Evaluation newEvaluation = new Evaluation(supervisorName, supervisorID, employeeName, employeeID, evaluationDate, feelings, favoriteTask, goodAtTasks, dreadedTasks, lookedForwardTasks, recommendations);
                newEvaluation.saveToFile();
                clearFields();
                JOptionPane.showMessageDialog(panel, "Evaluation created and saved successfully.");
                dispose();
            }
        });
        panel.add(saveButton, BorderLayout.SOUTH); // Add Save button at the bottom

        return panel;
    }

    // Helper method to create JTextField with a minimum size
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setMinimumSize(new Dimension(300, 25));
        return textField;
    }

    // Helper method to create JTextArea with a fixed size
    private JTextArea createTextArea() {
        return new JTextArea(3, 20);
    }

    // Helper method to add components to the panel
    private void addComponent(JPanel panel, GridBagConstraints gbc, String label, Component component, int gridY) {
        gbc.gridx = 0;
        gbc.gridy = gridY; // First column
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1; // Second column
        gbc.weightx = 1.0; // Allow the component to expand
        panel.add(component, gbc);
        gbc.weightx = 0; // Reset weight for the next components
    }

    // Placeholder for name validation
    private boolean validateName(String name) {
        return Pattern.matches("[A-Za-z ]+", name); // Name should contain only alphabets
    }

    private boolean validateID(String id) {
        return Pattern.matches("[0-9]+", id); // ID should contain only numbers
    }

    private void clearFields() {
        supervisorNameField.setText("");
        supervisorIDField.setText("");
        employeeNameField.setText("");
        employeeIDField.setText("");
        evaluationDateField.setText("");
        question1Field.setText("");
        question2Field.setText("");
        question3Field.setText("");
        question4Field.setText("");
        question5Field.setText("");
        recommendationsField.setText("");
    }
}
