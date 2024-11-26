import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * The NewEmployeeFrame class extends BaseFrame and provides a form to create a new employee.
 */
public class NewEmployeeFrame extends BaseFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField accessLevelField;
    private JTextField dateHiredField;
    private JTextField currentJobField;
    private JTextField softSkillsField;
    private JTextField hardSkillsField;
    private JTextField talentsField;
    private JTextField virtuesField;
    private JTextField pastJobsField;

    /**
     * Constructs a NewEmployeeFrame.
     */
    public NewEmployeeFrame() {
        super();
        setTitle("New Employee Form");
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

        firstNameField = createTextField();
        lastNameField = createTextField();
        emailField = createTextField();
        usernameField = createTextField();
        passwordField = createTextField();
        accessLevelField = createTextField();
        dateHiredField = createTextField();
        currentJobField = createTextField();
        softSkillsField = createTextField();
        hardSkillsField = createTextField();
        talentsField = createTextField();
        virtuesField = createTextField();
        pastJobsField = createTextField();

        // Add Back button
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(homeButton, BorderLayout.NORTH);

        // Add components to input panel
        addComponent(inputPanel, gbc, "First Name:", firstNameField, 0);
        addComponent(inputPanel, gbc, "Last Name:", lastNameField, 1);
        addComponent(inputPanel, gbc, "Email:", emailField, 2);
        addComponent(inputPanel, gbc, "Username:", usernameField, 3);
        addComponent(inputPanel, gbc, "Password:", passwordField, 4);
        addComponent(inputPanel, gbc, "Access Level (0 = Employee, 1 = Manager): ", accessLevelField, 5);
        addComponent(inputPanel, gbc, "Date Hired:", dateHiredField, 6);
        addComponent(inputPanel, gbc, "Current Job:", currentJobField, 7);
        addComponent(inputPanel, gbc, "Soft Skills: (Separate with \",\")", softSkillsField, 8);
        addComponent(inputPanel, gbc, "Hard Skills: (Separate with \",\")", hardSkillsField, 9);
        addComponent(inputPanel, gbc, "Talents: (Separate with \",\")", talentsField, 10);
        addComponent(inputPanel, gbc, "Virtues: (Separate with \",\")", virtuesField, 11);
        addComponent(inputPanel, gbc, "Past Job Titles: (Separate with \",\")", pastJobsField, 12);

        JScrollPane scrollPane = new JScrollPane(inputPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String accessLevel = accessLevelField.getText();
                String dateHired = dateHiredField.getText();
                String currentJob = currentJobField.getText();
                String softSkills = softSkillsField.getText();
                String hardSkills = hardSkillsField.getText();
                String talents = talentsField.getText();
                String virtues = virtuesField.getText();
                String pastJobs = pastJobsField.getText();

                // Validate input fields
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || accessLevel.isEmpty() || dateHired.isEmpty() || currentJob.isEmpty() || softSkills.isEmpty() || hardSkills.isEmpty() || talents.isEmpty() || virtues.isEmpty() || pastJobs.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please fill out all fields.");
                    return;
                }

                if (checkDuplicate(username)) {
                    JOptionPane.showMessageDialog(panel, "Username already exists. Please choose a different username.");
                    return;
                }

                if (!validateName(firstName) || !validateName(lastName)) {
                    JOptionPane.showMessageDialog(panel, "Invalid name. Please enter valid names.");
                    return;
                }

                if (!validateEmail(email)) {
                    JOptionPane.showMessageDialog(panel, "Invalid email format.");
                    return;
                }

                if (!validatePassword(password)) {
                    JOptionPane.showMessageDialog(panel, "Password must be at least 8 characters long, contain an uppercase letter, a lowercase letter, a number, and a special character.");
                    return;
                }

                if (!validateAccessLevel(accessLevel)) {
                    JOptionPane.showMessageDialog(panel, "Access Level must be 0 (Employee) or 1 (Manager).");
                    return;
                }

                // If validations pass, create new Employee
                Employee newEmployee = new Employee(firstName, lastName, email, username, password, accessLevel, dateHired, currentJob, softSkills, hardSkills, talents, virtues, pastJobs);
                System.out.println("employee data" + newEmployee);
                newEmployee.saveToFile();
                clearFields();
                JOptionPane.showMessageDialog(panel, "Employee created and saved successfully, they can now login using the username and password provided.");
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
        textField.setMinimumSize(new Dimension(200, 30));
        return textField;
    }

    /**
     * Adds a label and text field to the panel.
     *
     * @param panel the panel to add components to
     * @param gbc the GridBagConstraints for layout
     * @param label the label text
     * @param textField the text field
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
     * Validates the name.
     *
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    private boolean validateName(String name) {
        return Pattern.matches("[A-Za-z]+", name);
    }

    /**
     * Validates the email.
     *
     * @param email the email to validate
     * @return true if the email is valid, false otherwise
     */
    private boolean validateEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }

    /**
     * Validates the password.
     *
     * @param password the password to validate
     * @return true if the password is valid, false otherwise
     */
    private boolean validatePassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    /**
     * Validates the access level.
     *
     * @param accessLevel the access level to validate
     * @return true if the access level is valid, false otherwise
     */
    private boolean validateAccessLevel(String accessLevel) {
        return accessLevel.equals("0") || accessLevel.equals("1");
    }

    /**
     * Clears all fields.
     */
    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        accessLevelField.setText("");
        dateHiredField.setText("");
        currentJobField.setText("");
        softSkillsField.setText("");
        hardSkillsField.setText("");
        talentsField.setText("");
        virtuesField.setText("");
        pastJobsField.setText("");
    }

    /**
     * Checks for duplicate username in the file.
     *
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    private boolean checkDuplicate(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length > 4) {
                    if (data[4].trim().equalsIgnoreCase(username.trim())) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}