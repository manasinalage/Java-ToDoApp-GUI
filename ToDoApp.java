package Elevatelab;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToDoApp {

    private JFrame frame;
    private JTextField taskInput;
    private JPanel taskPanel;
    private JScrollPane scrollPane;
    private java.util.List<JPanel> taskItems = new ArrayList<>();

    public ToDoApp() {
      
        frame = new JFrame("📝 To-Do List");
        frame.setSize(450, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setLayout(new BorderLayout(10, 10));

       
        Font font = new Font("SansSerif", Font.PLAIN, 16);

        JLabel header = new JLabel("✨ Manage Your Tasks");
        header.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setForeground(new Color(60, 60, 60));
        frame.add(header, BorderLayout.NORTH);

      
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        taskInput = new JTextField();
        taskInput.setFont(font);
        JButton addButton = new JButton("➕ Add Task");
        addButton.setFont(font);
        addButton.setBackground(new Color(72, 187, 120));
        addButton.setForeground(Color.WHITE);

        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);


        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(taskPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane, BorderLayout.CENTER);

     
        addButton.addActionListener(e -> {
            String taskText = taskInput.getText().trim();
            if (!taskText.isEmpty()) {
                addTask(taskText);
                taskInput.setText("");
                taskInput.requestFocus();
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a task!", "Empty Task", JOptionPane.WARNING_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    private void addTask(String taskText) {
        JPanel taskItem = new JPanel(new BorderLayout());
        taskItem.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(new Color(200, 200, 200))
        ));
        
        taskItem.setBackground(new Color(245, 245, 245));

        JLabel taskLabel = new JLabel(taskText);
        taskLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        JButton deleteButton = new JButton("🗑️ Delete");
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(220, 53, 69));

        deleteButton.addActionListener(e -> {
            taskPanel.remove(taskItem);
            taskPanel.revalidate();
            taskPanel.repaint();
        });

        taskItem.add(taskLabel, BorderLayout.CENTER);
        taskItem.add(deleteButton, BorderLayout.EAST);

        taskPanel.add(taskItem);
        taskItems.add(taskItem);

        taskPanel.revalidate();
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(ToDoApp::new);
    }
}
