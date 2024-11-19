import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class NewEmployeeFrame extends BaseFrame {

    // Declare JTextField variables as class members
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

    public NewEmployeeFrame() {
        super();
        setTitle("New Employee Form");
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#424249")); // Setting the background color
        panel.setLayout(new BorderLayout());

        // Panel for all input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components

        // Initialize JTextFields and set minimum sizes
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

        // Add Back button (not in the scrollable area)
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
            }
        });

        panel.add(homeButton, BorderLayout.NORTH); // Add Back button at the top

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

        // Create a JScrollPane for the input panel
        JScrollPane scrollPane = new JScrollPane(inputPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER); // Add scrollable area in the center

        // Create and add the Save button at the bottom (not in the scrollable area)
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve values from input fields
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

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || accessLevel.isEmpty() || dateHired.isEmpty() || currentJob.isEmpty() || softSkills.isEmpty() || hardSkills.isEmpty() || talents.isEmpty() || virtues.isEmpty() || pastJobs.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please fill out all fields.");
                    return;
                }
                
                if (checkDuplicate(username)) {
                    JOptionPane.showMessageDialog(panel, "Username already exists. Please choose a different username.");
                    return;
                }

                // Validate fields with regular expressions (placeholders)
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

                // If all validations pass, create new Employee object
                Employee newEmployee = new Employee(firstName, lastName, email, username, password, accessLevel,
                                    dateHired, currentJob, softSkills, hardSkills, talents, virtues, pastJobs);
                newEmployee.saveToFile();
                clearFields();
                JOptionPane.showMessageDialog(panel, "Employee created and saved successfully, they can now login using the username and password provided.");
                dispose();
            }
        });
        panel.add(saveButton, BorderLayout.SOUTH); // Add Save button at the bottom

        return panel;
    }

    // Helper method to create JTextField with minimum size
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setMinimumSize(new Dimension(200, 30));
        return textField;
    }

    // Helper method to add components to the panel
    private void addComponent(JPanel panel, GridBagConstraints gbc, String label, JTextField textField, int gridY) {
        gbc.gridx = 0; gbc.gridy = gridY; // First column
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1; // Second column
        gbc.weightx = 1.0; // Allow the text field to expand
        panel.add(textField, gbc);
        gbc.weightx = 0; // Reset weight for the next components
    }

    // Placeholder for name validation
    private boolean validateName(String name) {
        return Pattern.matches("[A-Za-z]+", name); // Name should contain only alphabets
    }

    // Placeholder for email validation
    private boolean validateEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }

    // Placeholder for password validation
    private boolean validatePassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,}$";
        return Pattern.matches(passwordRegex, password);
    }    

    // Placeholder for access level validation (0 = Employee, 1 = Manager)
    private boolean validateAccessLevel(String accessLevel) {
        return accessLevel.equals("0") || accessLevel.equals("1");
    }

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

    private boolean checkDuplicate(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                // Check if there are enough elements in the array
                if (data.length > 4) {
                    // Trim whitespace from both the username and the data
                    if (data[4].trim().equalsIgnoreCase(username.trim())) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Return false if no match found
    }
}
