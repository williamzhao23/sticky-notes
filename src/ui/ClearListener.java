package ui;

import logic.TaskTracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class ClearListener implements ActionListener {

    private JPanel checklist;
    private TaskTracker taskTracker;

    ClearListener(JPanel checklist, TaskTracker taskTracker) {
        this.checklist = checklist;
        this.taskTracker = taskTracker;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Integer> toRemove = new ArrayList<Integer>();
        for (int i = 0; i < this.checklist.getComponents().length; i++) {
            JCheckBox checkBox = (JCheckBox) this.checklist.getComponent(i);
            if (checkBox.isSelected()) {
                toRemove.add(i);
            }
        }
        Collections.reverse(toRemove);
        for (int index : toRemove) {
            this.taskTracker.remove(index);
            this.checklist.remove(index);
        }
        this.checklist.revalidate();
        this.checklist.repaint();
    }
}
