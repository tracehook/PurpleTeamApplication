import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The EditEmployeeFrame class extends BaseFrame and provides a form to edit employee details.
 */
public class EditEmployeeFrame extends BaseFrame {

    // Declare JTextField variables as class members
    private JTextField employeeIdField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JTextField accessLevelField;
    private JTextField dateHiredField;
    private JTextField currentJobField;
    private JTextField softSkillsField;
    private JTextField hardSkillsField;
    private JTextField talentsField;
    private JTextField virtuesField;
    private JTextField pastJobsField;

    private int employeeID;

    /**
     * Constructs an EditEmployeeFrame with the specified employee ID.
     *
     * @param employeeID the ID of the employee to be edited
     */
    public EditEmployeeFrame(int employeeID) {
        super();
        this.employeeID = employeeID;
        setTitle("Edit Employee Form");
        JPanel mainPanel = createMainPanel();
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Creates the main panel to be added to the frame.
     *
     * @return a JPanel to be added to the frame
     */
    @Override
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#424249"));

        // Separate panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        populateEmployeeData(employeeID);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());
        panel.add(backButton, BorderLayout.NORTH);

        // Add components to input panel
        addComponent(inputPanel, gbc, "Employee ID:", employeeIdField, 0);
        addComponent(inputPanel, gbc, "First Name:", firstNameField, 1);
        addComponent(inputPanel, gbc, "Last Name:", lastNameField, 2);
        addComponent(inputPanel, gbc, "Email:", emailField, 3);
        addComponent(inputPanel, gbc, "Username:", usernameField, 4);
        addComponent(inputPanel, gbc, "Access Level (0 = Employee, 1 = Manager):", accessLevelField, 5);
        addComponent(inputPanel, gbc, "Date Hired:", dateHiredField, 6);
        addComponent(inputPanel, gbc, "Current Job:", currentJobField, 7);
        addComponent(inputPanel, gbc, "Soft Skills:", softSkillsField, 8);
        addComponent(inputPanel, gbc, "Hard Skills:", hardSkillsField, 9);
        addComponent(inputPanel, gbc, "Talents:", talentsField, 10);
        addComponent(inputPanel, gbc, "Virtues:", virtuesField, 11);
        addComponent(inputPanel, gbc, "Past Job Titles:", pastJobsField, 12);

        // Create a JScrollPane for the input panel
        JScrollPane scrollPane = new JScrollPane(inputPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String employeeID = employeeIdField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String username = usernameField.getText();
            String accessLevel = accessLevelField.getText();
            String dateHired = dateHiredField.getText();
            String currentJob = currentJobField.getText();
            String softSkills = softSkillsField.getText();
            String hardSkills = hardSkillsField.getText();
            String talents = talentsField.getText();
            String virtues = virtuesField.getText();
            String pastJobs = pastJobsField.getText();

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || accessLevel.isEmpty() || dateHired.isEmpty() || currentJob.isEmpty() || softSkills.isEmpty() || hardSkills.isEmpty() || talents.isEmpty() || virtues.isEmpty() || pastJobs.isEmpty() || employeeID.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please fill out all fields.");
                return;
            }

            // rewrite the line that the data is taken in from in the file with the new data


            JOptionPane.showMessageDialog(panel, "Employee details saved successfully.");
            dispose();
        });
        panel.add(saveButton, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Helper method to create JTextField with minimum size.
     *
     * @return a JTextField with minimum size
     */
    private JTextField createTextField() {
        JTextField textField = new JTextField("Initial Text");
        textField.setMinimumSize(new Dimension(200, 30));
        return textField;
    }

    /**
     * Helper method to add components to the panel.
     *
     * @param panel the panel to add components to
     * @param gbc the GridBagConstraints for layout
     * @param label the label text for the component
     * @param textField the JTextField to be added
     * @param gridY the grid y position
     */
    private void addComponent(JPanel panel, GridBagConstraints gbc, String label, JTextField textField, int gridY) {
        gbc.gridx = 0; gbc.gridy = gridY;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(textField, gbc);
        gbc.weightx = 0;
    }

    /**
     * Populates the employee data into the text fields.
     *
     * @param employeeID the ID of the employee to be edited
     */
    private void populateEmployeeData(int employeeID) {
        employeeIdField = createTextField();
        employeeIdField.setText(Integer.toString(employeeID));
        employeeIdField.setEditable(false);

        firstNameField = createTextField();
        lastNameField = createTextField();
        emailField = createTextField();
        usernameField = createTextField();
        accessLevelField = createTextField();
        dateHiredField = createTextField();
        currentJobField = createTextField();
        softSkillsField = createTextField();
        hardSkillsField = createTextField();
        talentsField = createTextField();
        virtuesField = createTextField();
        pastJobsField = createTextField();
        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (Integer.parseInt(data[0]) == employeeID) {
                    for (int i = 0; i < data.length; i++) {
                        System.out.println(data[i]); // Debugging
                    }
                    firstNameField.setText(data[1]);
                    lastNameField.setText(data[2]);
                    emailField.setText(data[3]);
                    usernameField.setText(data[4]);
                    accessLevelField.setText(data[6]);
                    dateHiredField.setText(data[7]);
                    currentJobField.setText(data[8]);
                    softSkillsField.setText(data[9]);
                    hardSkillsField.setText(data[10]);
                    talentsField.setText(data[11]);
                    virtuesField.setText(data[12]);
                    pastJobsField.setText(data[13]);
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading employee data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid data format in file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}