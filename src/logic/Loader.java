package logic;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import ui.UserInterface;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private UserInterface ui;

    public Loader(UserInterface ui) {
        this.ui = ui;
    }

    public void load() {
        // Load the file, if it exists
        File file = new File("save.json");
        if (!file.exists()) {
            return;
        }
        // Modify received, blank ui to the saved settings
        String content = "";
        try {
            content = FileUtils.readFileToString(file, "utf-8");
        } catch (IOException e) {
            System.err.println("Something went wrong");
        }
        JSONObject json = new JSONObject(content);
        // Setting todo
        List<Object> todo = json.getJSONArray("todo").toList();
        List<String> todoString = new ArrayList<String>();
        for (Object object : todo) {
            todoString.add((String) object);
        }
        this.ui.getTaskTracker().setTodo(todoString);
        for (String task : todoString) {
            this.ui.getTodoChecklist().add(new JCheckBox(task));
        }
        // Setting notes
        String notes = json.getString("notes");
        this.ui.getNotes().setText(notes);
    }
}
