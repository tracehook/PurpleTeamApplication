import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Starts with HomeFrame, all other actions should be added in the subclasses themselves not here (i think)
        SwingUtilities.invokeLater(HomeFrame::new);
    }
}
