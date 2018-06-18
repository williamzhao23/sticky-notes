package ui;

import logic.Saver;
import logic.TaskTracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveListener implements ActionListener {

    private Saver saver;

    SaveListener(JTextArea notes, TaskTracker taskTracker) {
        this.saver = new Saver(notes, taskTracker);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.saver.save();
    }
}
