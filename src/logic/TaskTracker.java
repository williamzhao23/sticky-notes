package logic;

import java.util.ArrayList;
import java.util.List;

public class TaskTracker {

    private List<String> todo;
    private List<String> history;

    public TaskTracker() {
        this.todo = new ArrayList<String>();
        this.history = new ArrayList<String>();
    }

    public void add(String task) {
        this.todo.add(task);
    }

    public void remove(int index) {
        this.history.add(this.todo.get(index));
        this.todo.remove(index);
    }

    public String undo() {
        if (this.history.size() > 0) {
            this.todo.add(this.history.get(this.history.size() - 1));
            this.history.remove(this.history.size() - 1);
            return this.todo.get(this.todo.size() - 1);
            // Returns the deleted task that was just restored
        }
        return "";
    }

    List<String> getTodo() {
        return todo;
    }

    void setTodo(List<String> todo) {
        this.todo = todo;
    }
}
