package ui;

import logic.TaskTracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddListener implements ActionListener {

    private JTextField input;
    private JPanel checklist;
    private TaskTracker taskTracker;

    AddListener(JTextField input, JPanel checklist, TaskTracker taskTracker) {
        this.input = input;
        this.checklist = checklist;
        this.taskTracker = taskTracker;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = this.input.getText();
        if (!text.isEmpty()) {
            this.taskTracker.add(text);
            this.checklist.add(new JCheckBox(text));
            this.input.setText("");
            this.checklist.revalidate();
            this.checklist.repaint();
        }
    }
}
