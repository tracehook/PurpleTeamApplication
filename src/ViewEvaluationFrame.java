import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ViewEvaluationFrame class extends BaseFrame and provides a form to view all evaluations.
 */
public class ViewEvaluationFrame extends BaseFrame {
    private JTextArea textArea;

    /**
     * Constructs a ViewEvaluationFrame.
     */
    public ViewEvaluationFrame() {
        setTitle("All Evaluations");
    }

    /**
     * Creates the main panel to be added to the frame.
     *
     * @return a JPanel to be added to the frame
     */
    @Override
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.decode("#424249"));

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.decode("#424249"));

        // Home button
        JButton homeButton = new JButton("Home");
        buttonPanel.add(homeButton);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(buttonPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false); // Make the text area read-only
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBackground(Color.LIGHT_GRAY);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane, BorderLayout.CENTER);

        displayData();

        return panel;
    }

    /**
     * Displays evaluation data in the text area from the file.
     */
    public void displayData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Evaluations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                textArea.append("Supervisor ID: " + data[2] + "    Supervisor Name: " + data[1] + "\n" +
                        "Employee ID: " + data[4] + "    Employee Name: " + data[3] + "\n" +
                        "Evaluation Date: " + data[5] + "\n" +
                        "Feelings while performing tasks: " + data[6] + "\n" +
                        "If you could do one task all day: " + data[7] + "\n" +
                        "Tasks you're good at: " + data[8] + "\n" +
                        "Tasks you dread: " + data[9] + "\n" +
                        "Tasks you look forward to: " + data[10] + "\n" +
                        "Recommendations/Notes: " + data[11] + "\n\n");
            }
        } catch (IOException e) {
            textArea.setText("Error reading file: " + e.getMessage());
        }
    }
}