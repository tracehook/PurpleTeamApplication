import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {

    public BaseFrame() {
        // Set frame properties
        setTitle("Framework Base Frame");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create a base JPanel that subclasses will extend
        JPanel mainPanel = createMainPanel();
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Method to be overridden by subclasses
    // THIS is where we will add our own code when we use this method in the subclasses.
    protected JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        return panel;
    }
}
