package ui;

import logic.Loader;
import logic.TaskTracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadListener implements ActionListener {

    private UserInterface ui;
    private Loader loader;

    LoadListener() {
        UserInterface ui = new UserInterface(new TaskTracker());
        this.ui = ui;
        this.loader = new Loader(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(this.ui);
        this.loader.load();
    }
}
