import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Starts with LoginFrame, all other actions should be added in the subclasses themselves
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
