import javax.swing.*;
import java.awt.*;

/**
 * The BaseFrame class is a JFrame that sets up the basic properties for the frame
 * and provides a method to create a main panel that can be overridden by subclasses.
 */
public class BaseFrame extends JFrame {

    /**
     * Constructs a BaseFrame and sets up the frame properties.
     */
    public BaseFrame() {
        // Set frame properties
        setTitle("Purple Monkey Employee Management System");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create a base JPanel that subclasses will extend
        JPanel mainPanel = createMainPanel();
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Creates the main panel to be added to the frame.
     * This method is intended to be overridden by subclasses to provide custom panels.
     *
     * @return a JPanel to be added to the frame
     */
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        return panel;
    }
}