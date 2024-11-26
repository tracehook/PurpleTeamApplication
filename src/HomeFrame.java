import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The HomeFrame class extends BaseFrame and provides the main home screen with options to manage employees and evaluations.
 */
public class HomeFrame extends BaseFrame {
    private ActiveUser activeUser;

    /**
     * Constructs a HomeFrame with the specified active user.
     *
     * @param activeUser the active user
     */
    public HomeFrame(ActiveUser activeUser) {
        super();
        this.activeUser = activeUser;
        setTitle("Home");
    }

    /**
     * Creates the main panel to be added to the frame.
     *
     * @return a JPanel to be added to the frame
     */
    @Override
    protected JPanel createMainPanel() {
        JPanel homePanel = new JPanel(new GridBagLayout());
        homePanel.setBackground(Color.decode("#424249"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);

        // Load logo
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/assets/purpleMonkey.png"));
        JLabel imageLabel = new JLabel(imageIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.NORTH;
        homePanel.add(imageLabel, gbc);

        // Create buttons
        JButton newEmployee = new JButton("New Employee");
        JButton viewEmployee = new JButton("View Employees");
        JButton newEval = new JButton("New Evaluation");
        JButton viewEval = new JButton("View Evaluations");

        // size of buttons
        Dimension buttonSize = new Dimension(300, 500);
        newEmployee.setPreferredSize(buttonSize);
        viewEmployee.setPreferredSize(buttonSize);
        newEval.setPreferredSize(buttonSize);
        viewEval.setPreferredSize(buttonSize);

        // Add buttons to panel
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        homePanel.add(newEmployee, gbc);
        gbc.gridx = 1;
        homePanel.add(viewEmployee, gbc);
        gbc.gridx = 2;
        homePanel.add(newEval, gbc);
        gbc.gridx = 3;
        homePanel.add(viewEval, gbc);

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