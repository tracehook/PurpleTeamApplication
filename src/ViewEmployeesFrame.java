import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewEmployeesFrame extends BaseFrame {
    private ActiveUser activeUser;
    private JTextArea textArea; // Declare JTextArea at the class level

    public ViewEmployeesFrame(ActiveUser activeUser) {
        super();
        setTitle("All Employees");
        this.activeUser = activeUser;
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for better placement of components
        panel.setBackground(Color.WHITE); // Set a light background for better readability

        // Create a panel for buttons to keep them together
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align buttons to the left
        buttonPanel.setBackground(Color.WHITE); // Match the main panel's background

        // Create a "Home" button
        JButton homeButton = new JButton("Home");
        buttonPanel.add(homeButton); // Add the home button to the button panel
        
        // Create an "Edit" button
        JButton editButton = new JButton("Edit");
        buttonPanel.add(editButton); // Add the edit button to the button panel

        // Add action listener to the "Edit" button
        editButton.addActionListener(e -> {
            if (activeUser.getAccessLevel() == 1) {
                String employeeIdStr = JOptionPane.showInputDialog(null, "Enter Employee ID:", "Edit Employee", JOptionPane.PLAIN_MESSAGE);
        
                if (employeeIdStr != null && !employeeIdStr.trim().isEmpty()) {
                    try {
                        int employeeId = Integer.parseInt(employeeIdStr.trim());
                        new EditEmployeeFrame(employeeId); // Open `EditEmployeeFrame` without closing `ViewEmployeesFrame`
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Employee ID.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid or no Employee ID entered.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "You must be a manager to edit Employees.");
            }
        });
        

        // Add action listener to the "Home" button
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
            }
        });

        // Add the button panel to the top of the main panel
        panel.add(buttonPanel, BorderLayout.NORTH);

        // Initialize and configure the JTextArea for employee information
        textArea = new JTextArea();
        textArea.setEditable(false); // Make the text area read-only
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Use monospaced font for alignment
        textArea.setBackground(Color.LIGHT_GRAY); // Light gray background for text area
        textArea.setLineWrap(true); // Wrap text in case of long lines
        textArea.setWrapStyleWord(true);

        // Add a JScrollPane to the text area (for scrolling)
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the panel

        // Call displayData() to load employee data into the text area
        displayData();

        return panel;
    }

    // will, display text from the file here, use textArea.append() to add text to the frame that i have set to hold the data
    public void displayData() {
        // Read employee data from the file and display it in the JTextArea
        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n"); // Append each line of the file to the JTextArea
            }
        } catch (IOException e) {
            textArea.setText("Error reading file: " + e.getMessage()); // Display error message if file reading fails
        }
    }
}
