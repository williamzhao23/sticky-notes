package ui;

import logic.TaskTracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoListener implements ActionListener {

    private JPanel checklist;
    private TaskTracker taskTracker;

    UndoListener(JPanel checklist, TaskTracker taskTracker) {
        this.checklist = checklist;
        this.taskTracker = taskTracker;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String task = this.taskTracker.undo();
        if (!task.isEmpty()) {
            this.checklist.add(new JCheckBox(task));
        }
        this.checklist.revalidate();
        this.checklist.repaint();
    }
}
