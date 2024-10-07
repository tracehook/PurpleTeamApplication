import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class NewEmployeeFrame extends BaseFrame {

    public NewEmployeeFrame() {
        super();
        setTitle("New Employee Form");
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components

        // Set preferred size for the input fields
        Dimension fieldSize = new Dimension(200, 25);

        // Create labels and input fields
        JTextField firstNameField = new JTextField();
        firstNameField.setPreferredSize(fieldSize);

        JTextField lastNameField = new JTextField();
        lastNameField.setPreferredSize(fieldSize);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(fieldSize);

        JTextField dateHiredField = new JTextField();
        dateHiredField.setPreferredSize(fieldSize);

        JTextField currentJobField = new JTextField();
        currentJobField.setPreferredSize(fieldSize);

        JTextField softSkillsField = new JTextField();
        softSkillsField.setPreferredSize(fieldSize);

        JTextField hardSkillsField = new JTextField();
        hardSkillsField.setPreferredSize(fieldSize);

        JTextField talentsField = new JTextField();
        talentsField.setPreferredSize(fieldSize);

        JTextField virtuesField = new JTextField();
        virtuesField.setPreferredSize(fieldSize);

        JTextField pastJobsField = new JTextField();
        pastJobsField.setPreferredSize(fieldSize);

        // Add components to panel with proper positioning
        gbc.gridx = 0; gbc.gridy = 0; // First column
        panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1; // Second column
        panel.add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        panel.add(lastNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Date Hired:"), gbc);
        gbc.gridx = 1;
        panel.add(dateHiredField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Current Job:"), gbc);
        gbc.gridx = 1;
        panel.add(currentJobField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Soft Skills:"), gbc);
        gbc.gridx = 1;
        panel.add(softSkillsField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Hard Skills:"), gbc);
        gbc.gridx = 1;
        panel.add(hardSkillsField, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Talents:"), gbc);
        gbc.gridx = 1;
        panel.add(talentsField, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        panel.add(new JLabel("Virtues:"), gbc);
        gbc.gridx = 1;
        panel.add(virtuesField, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        panel.add(new JLabel("Past Job Titles:"), gbc);
        gbc.gridx = 1;
        panel.add(pastJobsField, gbc);

        // Create and add the Save button, align it across two columns
        JButton saveButton = new JButton("Save");
        gbc.gridx = 0; gbc.gridy = 10;  // Adjusted for the new row
        gbc.gridwidth = 2; // Make the button span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        panel.add(saveButton, gbc);



        // dont know if these fields need to be created into an object for the database or not
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect input values and create a NewEmployee object
                NewEmployee employee = new NewEmployee(
                        firstNameField.getText(),
                        lastNameField.getText(),
                        emailField.getText(),
                        dateHiredField.getText(),
                        currentJobField.getText(),
                        Arrays.asList(softSkillsField.getText().split(",")),
                        Arrays.asList(hardSkillsField.getText().split(",")),
                        Arrays.asList(talentsField.getText().split(",")),
                        Arrays.asList(virtuesField.getText().split(",")),
                        Arrays.asList(pastJobsField.getText().split(","))
                );

                // Print the new employee details (could be replaced with other actions)
                System.out.println(employee);
            }
        });

        return panel;
    }
}
