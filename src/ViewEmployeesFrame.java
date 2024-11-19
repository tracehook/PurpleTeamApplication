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

        // Create a "Search" button
        JButton search = new JButton("search");
        buttonPanel.add(search); // Add the search button to the button panel

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

        //Initialize and configure the JTextArea for employee information
        textArea = new JTextArea();
        textArea.setEditable(false); // Make the text area read-only
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Use monospaced font for alignment
        textArea.setBackground(Color.LIGHT_GRAY); // Light gray background for text area
        textArea.setLineWrap(true); // Wrap text in case of long lines
        textArea.setWrapStyleWord(true);

        // Add a JScrollPane to the text area (for scrolling)
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the panel

        //Add action listener to the "Search" button 
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = JOptionPane.showInputDialog(null, "Enter Employee ID:", "Search Employee", JOptionPane.PLAIN_MESSAGE);
                if (search != null && !search.trim().isEmpty()) {
                    try {
                        int employeeId = Integer.parseInt(search.trim());
                        // Call searchEmployee() method to search for the employee by ID
                        searchEmployee(employeeId);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Employee ID.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid or no Employee ID entered.");
                }
            }
        }); //Made by copilot

        // Call displayData() to load employee data into the text area
        displayData();

        return panel;
    }

    public void displayData() {
        // Read employee data from the file and display it in a JTable
        Object [][] data2 = new Object[][]{}; // Initialize an empty 2D array for data

        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                data2 = addRow(data2, data);
            }
        } catch (IOException e) {
            textArea.setText("Error reading file: " + e.getMessage()); // Display error message if file reading fails
        }

        String[] columnNames = {"employeeID", "First Name", "Last Name", "email Address", "Username", 
                "Password", "Access Level", "Date Hired", "Current Job", "Soft Skills", "Hard Skills", "Talents", 
                "Virtues", "Past Jobs"};

        // Create a JTable with the data and column names
        JTable table = new JTable(data2, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);
        table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        tableScrollPane.setBounds(50, 50, 700, 300);
        tableScrollPane.getViewport().setPreferredSize(table.getPreferredSize());
        this.add(tableScrollPane, BorderLayout.CENTER);

        
    }

    //Add a row to a 2D array
    public static Object[][] addRow(Object[][] original, String[] newRow) {
        int numRows = original.length;
        int numCols = 14;
    
        // Create a new array with one extra row
        String[][] newArray = new String[numRows + 1][numCols];
    
        // Copy the elements from the original array
        for (int i = 0; i < numRows; i++) {
            System.arraycopy(original[i], 0, newArray[i], 0, numCols);
        }
    
        // Add the new row
        newArray[numRows] = newRow;
    
        return newArray;
        //Would like to thank AI Overview for the help with this method
    }

    public void searchEmployee(int employeeId) {
        // Search for an employee by ID and display their information
        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (Integer.parseInt(data[0]) == employeeId) {
                    textArea.setText("Employee ID: " + data[0] + "\n" +
                            "First Name: " + data[1] + "\n" +
                            "Last Name: " + data[2] + "\n" +
                            "Email Address: " + data[3] + "\n" +
                            "Username: " + data[4] + "\n" +
                            "Password: " + data[5] + "\n" +
                            "Access Level: " + data[6] + "\n" +
                            "Date Hired: " + data[7] + "\n" +
                            "Current Job: " + data[8] + "\n" +
                            "Soft Skills: " + data[9] + "\n" +
                            "Hard Skills: " + data[10] + "\n" +
                            "Talents: " + data[11] + "\n" +
                            "Virtues: " + data[12] + "\n" +
                            "Past Jobs: " + data[13]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                textArea.setText("Employee with ID " + employeeId + " not found.");
            }
        } catch (IOException e) {
            textArea.setText("Error reading file: " + e.getMessage());
        }
    } //Made by copilot
}
