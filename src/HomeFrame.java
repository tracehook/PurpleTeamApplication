import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends BaseFrame {
    private ActiveUser activeUser;

    public HomeFrame(ActiveUser activeUser) {
        super();
        this.activeUser = activeUser;
        setTitle("Home");
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel homePanel = new JPanel(new GridBagLayout());
        homePanel.setBackground(Color.decode("#424249")); // Setting the background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // add spacing between buttons

        // Create buttons
        JButton newEmployee = new JButton("New Employee");
        JButton viewEmployee = new JButton("View Employees");
        JButton newEval = new JButton("New Evaluation");
        JButton viewEval = new JButton("View Evaluations");
        

        // Set preferred size for buttons to make them larger
        Dimension buttonSize = new Dimension(300, 500); // Adjust width and height here
        newEmployee.setPreferredSize(buttonSize);
        viewEmployee.setPreferredSize(buttonSize);
        newEval.setPreferredSize(buttonSize);
        viewEval.setPreferredSize(buttonSize);

        // Add buttons to panel with GridBagLayout
        gbc.gridx = 0; // y position 0
        homePanel.add(newEmployee, gbc);
        gbc.gridx = 1; // y position 1
        homePanel.add(viewEmployee, gbc);
        gbc.gridx = 2; // y position 2
        homePanel.add(newEval, gbc);
        gbc.gridx = 3; // y position 3
        homePanel.add(viewEval, gbc);

        // Add action listeners to buttons
        newEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activeUser.getAccessLevel() == 1) {
                    new NewEmployeeFrame();
                } else {
                    JOptionPane.showMessageDialog(null, "You must be a manager to create new Employees.");
                }
            }
        });

        viewEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewEmployeesFrame(activeUser);
            }
        });

        newEval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activeUser.getAccessLevel() == 1) {
                    new NewEvaluationFrame();
                } else {
                    JOptionPane.showMessageDialog(null, "You must be a manager to create new Employees.");
                }
            }
        });

        viewEval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewEvaluationFrame();
            }
        });
        return homePanel;
    }
}
