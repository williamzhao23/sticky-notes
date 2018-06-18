package logic;

import org.json.JSONObject;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {

    private JTextArea notes;
    private TaskTracker taskTracker;

    public Saver(JTextArea notes, TaskTracker taskTracker) {
        this.notes = notes;
        this.taskTracker = taskTracker;
    }

    public void save() {
        JSONObject json = new JSONObject();
        json.put("notes", this.notes.getText());
        json.put("todo", this.taskTracker.getTodo());
        try {
            FileWriter writer = new FileWriter("save.json");
            writer.write(json.toString());
            writer.close();
        } catch (IOException e) {
            System.err.println("Something went wrong");
        }
    }
}
