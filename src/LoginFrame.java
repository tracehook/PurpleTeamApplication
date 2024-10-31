import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class LoginFrame extends BaseFrame {

    @Override
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexible component arrangement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components

        // Header label
        JLabel headerLabel = new JLabel("Login");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font style and size
        gbc.gridx = 0;
        gbc.gridy = 0; // Set to a valid row (0 in this case)
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        gbc.weightx = 1.0; // Allow the label to expand
        panel.add(headerLabel, gbc);

        // Create username and password labels and fields
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton submitButton = new JButton("Submit");

        // Add components to the panel with positioning
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(submitButton, gbc);

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Validate username and password
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Username cannot be empty.");
                    usernameField.requestFocus(); // Set focus back to username field
                    return;
                }

                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Password cannot be empty.");
                    passwordField.requestFocus(); // Set focus back to password field
                    return;
                }

                if (!validateUsername(username)) {
                    JOptionPane.showMessageDialog(panel, "Invalid username. Only alphanumeric characters are allowed.");
                    return;
                }

                if (!validatePassword(password)) {
                    JOptionPane.showMessageDialog(panel, "Invalid password. Password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a number, and a special character.");
                    return;
                }

                ActiveUser activeUser = new ActiveUser(username, password);
                if (activeUser.verifyUser()) {
                    JOptionPane.showMessageDialog(panel, "Login successful.");
                    dispose(); // Close the login frame
                    new HomeFrame(activeUser); // Open the home frame
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid username or password. Please try again.");
                }
            }
        });

        // Set focus to username field when the frame is shown
        usernameField.requestFocus();

        return panel;
    }

    // Method to validate username
    private boolean validateUsername(String username) {
        return Pattern.matches("[A-Za-z0-9]+", username); // Username should contain only alphabets and numbers
    }

    // Method to validate password
    private boolean validatePassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,}$";
        return Pattern.matches(passwordRegex, password);
    }
}
