import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 * User Interface of an add task screen for the Task Management Application.
 */
public class AddTaskScreen {

    /**
     * This method generates and displays the add task screen.
     */
    public void getScreen() {
        String[] columnNames = {"Project Name","Task Name", "User ID", "Description", "Priority"};
        JFrame frame = new JFrame("Add Task Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        JButton homeButton = new JButton("Home");
        homeButton.setBounds(0,0,200,40);
        frame.add(homeButton);

        JButton checkTaskButton = new JButton("Check Tasks");
        checkTaskButton.setBounds(200,0,200,40);
        frame.add(checkTaskButton);

        JButton addProjectButton = new JButton("Add Project");
        addProjectButton.setBounds(400,0,200,40);
        frame.add(addProjectButton);

        JButton checkProjectButton = new JButton("Check Team Projects");
        checkProjectButton.setBounds(600,0,200,40);
        frame.add(checkProjectButton);

        JButton saveButton = new JButton("Add Task");
        saveButton.setBounds(560,400,160,40);
        frame.add(saveButton);

        DefaultTableModel model = new DefaultTableModel(columnNames, 1);

        JTable table = new JTable(model);
        table.setRowHeight(40);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,200,800,63);
        frame.getContentPane().add(scrollPane);

        frame.setVisible(true);
    }
}
