package ui;

import logic.TaskTracker;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class UserInterface implements Runnable {

    private JFrame frame;
    private TaskTracker taskTracker;

    public UserInterface(TaskTracker taskTracker) {
        this.taskTracker = taskTracker;
        this.frame = new JFrame("Sticky Notes!");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        frame.setJMenuBar(createMenu());
        frame.getContentPane().add(createTabs());

        // Set action listeners only after all components have been created
        JButton add = (JButton) this.getTodoButtons().getComponent(0);
        JButton clear = (JButton) this.getTodoButtons().getComponent(1);
        JButton undo = (JButton) this.getTodoButtons().getComponent(2);
        add.addActionListener(new AddListener(this.getTodoInput(), this.getTodoChecklist(),
                this.taskTracker));
        clear.addActionListener(new ClearListener(this.getTodoChecklist(), this.taskTracker));
        undo.addActionListener(new UndoListener(this.getTodoChecklist(), this.taskTracker));
        JTextField input = this.getTodoInput();
        input.addActionListener(new AddListener(this.getTodoInput(), this.getTodoChecklist(),
                this.taskTracker));

        this.frame.pack();
    }

    @Override
    public void run() {
        // Only add a LoadListener after running, or else an infinite chain is created
        JMenuItem open = this.getFileMenu().getItem(0);
        JMenuItem save = this.getFileMenu().getItem(1);
        open.addActionListener(new LoadListener());
        save.addActionListener(new SaveListener(this.getNotes(), this.getTaskTracker()));

        this.frame.setVisible(true);
    }

    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        fileMenu.add(load);
        fileMenu.add(save);
        menuBar.add(fileMenu);

//        Implement style options later
//        JMenu viewMenu = new JMenu("Options");
//        viewMenu.setMnemonic(KeyEvent.VK_O);
//        JMenuItem font = new JMenuItem("Font");
//        JMenuItem fontSize = new JMenuItem("Font size");
//        JMenuItem color = new JMenuItem("Color scheme");
//        viewMenu.add(font);
//        viewMenu.add(fontSize);
//        viewMenu.add(color);
//        menuBar.add(viewMenu);

        return menuBar;
    }

    private JTabbedPane createTabs() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("To-Do", createTodo());
        tabbedPane.addTab("Notes", createNotes());
        return tabbedPane;
    }

    private JPanel createTodo() {
        // Create the buttons on the bottom
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 3));
        JButton add = new JButton("Add");
        JButton clear = new JButton("Clear completed");
        JButton undo = new JButton("Undo clear");
        buttons.add(add);
        buttons.add(clear);
        buttons.add(undo);
        // Finish bottom with input and buttons
        JPanel input = new JPanel();
        input.setLayout(new GridLayout(2, 1));
        JTextField inputField = new JTextField();
        input.add(inputField);
        input.add(buttons);
        // Create checklist on the top
        JPanel checklist = new JPanel();
        BoxLayout box =  new BoxLayout(checklist, BoxLayout.Y_AXIS);
        checklist.setLayout(box);
        JScrollPane scrollPane = new JScrollPane(checklist);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // Add everything to the main panel
        JPanel todo = new JPanel();
        todo.setLayout(new BorderLayout());
        todo.add(scrollPane, BorderLayout.CENTER);
        todo.add(input, BorderLayout.SOUTH);
        return todo;
    }

    private JPanel createNotes() {
        JPanel notes = new JPanel();
        notes.setLayout(new BorderLayout());
        JTextArea text = new JTextArea();
        text.setLineWrap(true);
        notes.add(text);
        return notes;
    }

    public TaskTracker getTaskTracker() {
        return taskTracker;
    }

    public JTextArea getNotes() {
        JTabbedPane tabbedPane = (JTabbedPane) this.frame.getContentPane().getComponent(0);
        JPanel notesTab = (JPanel) tabbedPane.getComponentAt(1);
        return (JTextArea) notesTab.getComponent(0);
    }

    // Returns checklist (JPanel) containing JCheckbox items
    public JPanel getTodoChecklist() {
        Container container = this.frame.getContentPane();
        JTabbedPane tabbedPane = (JTabbedPane) container.getComponent(0);
        JPanel todoTab = (JPanel) tabbedPane.getComponentAt(0);
        JScrollPane scrollPane = (JScrollPane) todoTab.getComponent(0);

        JViewport viewport = scrollPane.getViewport();
        return (JPanel) viewport.getComponent(0);
    }

    private JTextField getTodoInput() {
        JTabbedPane tabbedPane = (JTabbedPane) this.frame.getContentPane().getComponent(0);
        JPanel todoTab = (JPanel) tabbedPane.getComponentAt(0);
        JPanel input = (JPanel) todoTab.getComponent(1);
        return (JTextField) input.getComponent(0);
    }

    private JPanel getTodoButtons() {
        JTabbedPane tabbedPane = (JTabbedPane) this.frame.getContentPane().getComponent(0);
        JPanel todoTab = (JPanel) tabbedPane.getComponentAt(0);
        JPanel input = (JPanel) todoTab.getComponent(1);
        return (JPanel) input.getComponent(1);
    }

    private JMenu getFileMenu() {
        JMenuBar menuBar = this.frame.getJMenuBar();
        return menuBar.getMenu(0);
    }
}
