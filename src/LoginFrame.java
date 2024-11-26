import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/**
 * The LoginFrame class extends BaseFrame and provides a form for user login.
 */
public class LoginFrame extends BaseFrame {

    /**
     * Creates the main panel to be added to the frame.
     *
     * @return a JPanel to be added to the frame
     */
    @Override
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#424249"));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Load logo
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/assets/purpleMonkey.png"));
        JLabel imageLabel = new JLabel(imageIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(imageLabel, gbc);

        JLabel headerLabel = new JLabel("Login");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        panel.add(headerLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton submitButton = new JButton("Submit");

        // add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // check if username and password are empty/valid
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Username cannot be empty.");
                    usernameField.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Password cannot be empty.");
                    passwordField.requestFocus();
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
                    dispose();
                    new HomeFrame(activeUser);
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid username or password. Please try again.");
                }
            }
        });

        usernameField.requestFocus();

        return panel;
    }

    /**
     * Validates the username.
     *
     * @param username the username to validate
     * @return true if the username is valid, false otherwise
     */
    private boolean validateUsername(String username) {
        return Pattern.matches("[A-Za-z0-9]+", username);
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
}