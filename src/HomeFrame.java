import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends BaseFrame {

    @Override
    protected JPanel createMainPanel() {
        JPanel homePanel = new JPanel();
        // Buttons stacked on top of each other
        homePanel.setLayout(new GridLayout(4, 1, 10, 10)); // Grid layout with 2 rows and 2 columns

        // Create buttons
        JButton button1 = new JButton("New Employee");
        JButton button2 = new JButton("View Employees");
        JButton button3 = new JButton("New Evaluation");
        JButton button4 = new JButton("View Evaluations");

        // Add action listeners to buttons
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // New Employee button
                // Add code here
                // Create new class that extends BaseFrame and add the specific stuff to it and then call it here
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // View Employees button
                // Add code here
                // Create new class that extends BaseFrame and add the specific stuff to it and then call it here
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // New Evaluation button
                // Add code here
                // Create new class that extends BaseFrame and add the specific stuff to it and then call it here
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // View Evaluations button
                // Add code here
                // Create new class that extends BaseFrame and add the specific stuff to it and then call it here
            }
        });

        // Add buttons to the panel
        homePanel.add(button1);
        homePanel.add(button2);
        homePanel.add(button3);
        homePanel.add(button4);

        return homePanel;
    }
}
