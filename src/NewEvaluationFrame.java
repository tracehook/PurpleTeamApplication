import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The NewEvaluationFrame class extends BaseFrame and provides a form to create a new evaluation.
 */
public class NewEvaluationFrame extends BaseFrame {
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

    /**
     * Constructs a NewEvaluationFrame.
     */
    public NewEvaluationFrame() {
        super();
        setTitle("Employee Evaluation Form");
    }

    /**
     * Creates the main panel to be added to the frame.
     *
     * @return a JPanel to be added to the frame
     */
    @Override
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#424249"));
        panel.setLayout(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        supervisorNameField = createTextField();
        supervisorIDField = createTextField();
        employeeNameField = createTextField();
        employeeIDField = createTextField();
        evaluationDateField = createTextField();
        evaluationDateField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // Format the date
        evaluationDateField.setEditable(false);

        question1Field = createTextArea();
        question2Field = createTextArea();
        question3Field = createTextArea();
        question4Field = createTextArea();
        question5Field = createTextArea();
        recommendationsField = createTextArea();

        addComponent(inputPanel, gbc, "Supervisor Name:", supervisorNameField, 0);
        addComponent(inputPanel, gbc, "Supervisor ID:", supervisorIDField, 1);
        addComponent(inputPanel, gbc, "Employee Name:", employeeNameField, 2);
        addComponent(inputPanel, gbc, "Employee ID:", employeeIDField, 3);
        addComponent(inputPanel, gbc, "Evaluation Date:", evaluationDateField, 4);

        addComponent(inputPanel, gbc, "Feelings while performing specific tasks, explain why:", new JScrollPane(question1Field), 5);
        addComponent(inputPanel, gbc, "If you could do one task all day, explain why:", new JScrollPane(question2Field), 6);
        addComponent(inputPanel, gbc, "Tasks you're good at:", new JScrollPane(question3Field), 7);
        addComponent(inputPanel, gbc, "Tasks you dread, explain why:", new JScrollPane(question4Field), 8);
        addComponent(inputPanel, gbc, "Tasks you look forward to, explain why:", new JScrollPane(question5Field), 9);
        addComponent(inputPanel, gbc, "Recommendations/Notes:", new JScrollPane(recommendationsField), 10);

        JScrollPane scrollPane = new JScrollPane(inputPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Home button
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(homeButton, BorderLayout.NORTH);

        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                // Validate fields
                if (!validateName(supervisorName) || !validateName(employeeName)) {
                    JOptionPane.showMessageDialog(panel, "Invalid name. Please enter valid names.");
                    return;
                }

                if (!validateID(supervisorID) || !validateID(employeeID)) {
                    JOptionPane.showMessageDialog(panel, "Invalid ID. Please enter valid IDs.");
                    return;
                }

                // Create a new Evaluation and save it
                Evaluation newEvaluation = new Evaluation(supervisorName, supervisorID, employeeName, employeeID, evaluationDate, feelings, favoriteTask, goodAtTasks, dreadedTasks, lookedForwardTasks, recommendations);
                newEvaluation.saveToFile();
                clearFields();
                JOptionPane.showMessageDialog(panel, "Evaluation created and saved successfully.");
                dispose();
            }
        });
        panel.add(saveButton, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates a JTextField with a minimum size.
     *
     * @return a JTextField with a minimum size
     */
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setMinimumSize(new Dimension(300, 25));
        return textField;
    }

    /**
     * Creates a JTextArea with a fixed size.
     *
     * @return a JTextArea with a fixed size
     */
    private JTextArea createTextArea() {
        return new JTextArea(3, 20);
    }

    /**
     * Adds a label and component to the panel.
     *
     * @param panel the panel to add components to
     * @param gbc the GridBagConstraints for layout
     * @param label the label text
     * @param component the component to add
     * @param gridY the grid y position
     */
    private void addComponent(JPanel panel, GridBagConstraints gbc, String label, Component component, int gridY) {
        gbc.gridx = 0;
        gbc.gridy = gridY;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(component, gbc);
        gbc.weightx = 0;
    }

    /**
     * Validates the name.
     *
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    private boolean validateName(String name) {
        return Pattern.matches("[A-Za-z ]+", name);
    }

    /**
     * Validates the ID.
     *
     * @param id the ID to validate
     * @return true if the ID is valid, false otherwise
     */
    private boolean validateID(String id) {
        return Pattern.matches("[0-9]+", id);
    }

    /**
     * Clears all input fields.
     */
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