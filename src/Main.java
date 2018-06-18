import logic.TaskTracker;
import ui.UserInterface;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        UserInterface ui = new UserInterface(new TaskTracker());
        SwingUtilities.invokeLater(ui);
    }
}
