import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewEvaluationFrame extends BaseFrame {

    private JTextArea textArea; // Declare JTextArea at the class level

    public ViewEvaluationFrame() {
        setTitle("All Evaluations");
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for better placement of components
        panel.setBackground(Color.decode("#424249")); // Setting the background color

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align buttons to the left
        buttonPanel.setBackground(Color.WHITE); // Match the main panel's background

        // Create a "Home" button
        JButton homeButton = new JButton("Home");
        buttonPanel.add(homeButton); // Add the home button to the button panel

        // Add action listener to the "Home" button
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
            }
        });

        // Add the button panel to the top of the main panel
        panel.add(buttonPanel, BorderLayout.NORTH);

        // Initialize and configure the JTextArea for evaluation information
        textArea = new JTextArea();
        textArea.setEditable(false); // Make the text area read-only
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Use monospaced font for alignment
        textArea.setBackground(Color.LIGHT_GRAY); // Light gray background for text area
        textArea.setLineWrap(true); // Wrap text in case of long lines
        textArea.setWrapStyleWord(true);

        // Add the JTextArea to a JScrollPane for scrolling capability
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add the scroll pane to the panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Call displayData() to load evaluation data into the text area
        displayData();

        return panel;
    }

    public void displayData() {
        // Read evaluation data from the file and display it in the JTextArea
        try (BufferedReader reader = new BufferedReader(new FileReader("Evaluations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {  //Will need to be fixed. evaluations.txt may have the correct data
                String[] data = line.split(";");
                textArea.append("Supervisor ID: " + data[0] + "    Supervisor Name: " + data[1] + "   Employee ID: " + data[2] + 
                    "    Employee Name: " + data[3] + "   Evaluation Date: " + data[4]
                    + " Feelings while performing tasks:: " + data[5] + "  If you could do one task all day: "  +data[6] + 
                    "  Tasks you're good at:: " + data[7] + "  Tasks you dread:: " + data[8]  + " Tasks you look forward to: " +
                    data[9] + " Recommendations/Notes: " + data[10] + "\n" + "\n"); // Append each line of the file to the JTextArea
            }
        } catch (IOException e) {
            textArea.setText("Error reading file: " + e.getMessage()); // Display error message if file reading fails
        }
    }
}
