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
        JButton newEmployee = new JButton("New Employee");
        JButton viewEmployee = new JButton("View Employees");
        JButton newEval = new JButton("New Evaluation");
        JButton viewEval = new JButton("View Evaluations");

        // Add action listeners to buttons
        newEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewEmployeeFrame();
            }
        });

        viewEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // View Employees button
                // Add code here
                // Create new class that extends BaseFrame and add the specific stuff to it and then call it here
            }
        });

        newEval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewEvaluationFrame();
            }
        });

        viewEval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // View Evaluations button
                // Add code here
                // Create new class that extends BaseFrame and add the specific stuff to it and then call it here
            }
        });

        // Add buttons to the panel
        homePanel.add(newEmployee);
        homePanel.add(viewEmployee);
        homePanel.add(newEval);
        homePanel.add(viewEval);

        return homePanel;
    }
}
