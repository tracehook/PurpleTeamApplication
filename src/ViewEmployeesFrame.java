import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ViewEmployeesFrame class extends BaseFrame and provides a form to view all employees.
 */
public class ViewEmployeesFrame extends BaseFrame {
    private ActiveUser activeUser;
    private JTextArea textArea;
    private JScrollPane tableScrollPane;

    /**
     * Constructs a ViewEmployeesFrame with the specified active user.
     *
     * @param activeUser the active user
     */
    public ViewEmployeesFrame(ActiveUser activeUser) {
        super();
        setTitle("All Employees");
        this.activeUser = activeUser;
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.decode("#424249"));

        JButton homeButton = new JButton("Home");
        buttonPanel.add(homeButton);

        JButton editButton = new JButton("Edit");
        buttonPanel.add(editButton);

        JButton search = new JButton("Search");
        buttonPanel.add(search);

        editButton.addActionListener(e -> {
            if (activeUser.getAccessLevel() == 1) {
                String employeeIdStr = JOptionPane.showInputDialog(null, "Enter Employee ID:", "Edit Employee", JOptionPane.PLAIN_MESSAGE);

                // Get user input and open the EditEmployeeFrame with correct employee information
                if (employeeIdStr != null && !employeeIdStr.trim().isEmpty()) {
                    try {
                        int employeeId = Integer.parseInt(employeeIdStr.trim());
                        new EditEmployeeFrame(employeeId);
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

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(buttonPanel, BorderLayout.NORTH);

        // JTextArea for employee information
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

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
        });

        displayData();

        return panel;
    }

    /**
     * Displays employee data in the text area.
     */
    public void displayData() {
        // Read employee data from the file and display it in a JTable
        Object[][] data2 = new Object[][]{};

        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                data2 = addRow(data2, data);
            }
        } catch (IOException e) {
            textArea.setText("Error reading file: " + e.getMessage());
        }

        String[] columnNames = {"employeeID", "First Name", "Last Name", "email Address", "Username",
                "Password", "Access Level", "Date Hired", "Current Job", "Soft Skills", "Hard Skills", "Talents",
                "Virtues", "Past Jobs"};

        // Create a JTable with the data and column names
        JTable table = new JTable(data2, columnNames);
        tableScrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableScrollPane.setBounds(50, 50, 700, 300);
        tableScrollPane.getViewport().setPreferredSize(table.getPreferredSize());
        this.add(tableScrollPane, BorderLayout.CENTER);
    }

    /**
     * Adds a row to a 2D array.
     *
     * @param original the original 2D array
     * @param newRow the new row to add
     * @return the new 2D array with the added row
     */
    public static Object[][] addRow(Object[][] original, String[] newRow) {
        int numRows = original.length;
        int numCols = 14;

        String[][] newArray = new String[numRows + 1][numCols];

        for (int i = 0; i < numRows; i++) {
            System.arraycopy(original[i], 0, newArray[i], 0, numCols);
        }

        newArray[numRows] = newRow;

        return newArray;
    }

    /**
     * Searches for an employee by ID and displays their information.
     *
     * @param employeeId the employee ID to search for
     */
    public void searchEmployee(int employeeId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Employees.txt"))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (Integer.parseInt(data[0]) == employeeId) {
                    // Clear the existing content in the panel
                    textArea.setText("");

                    // Remove the scroll pane with all employee data
                    if (tableScrollPane != null) {
                        this.remove(tableScrollPane);
                        this.revalidate();
                        this.repaint();
                    }

                    // Display the search result
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
    }
}