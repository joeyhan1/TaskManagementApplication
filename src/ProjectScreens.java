import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 * User Interfaces of project screens for the Task Management Application.
 */
public class ProjectScreens {
    private String userRole;

    /**
     * Constructs a ProjectScreens object with the specified user role.
     * @param role The role of the user.
     */
    public ProjectScreens(String role) {
        userRole = role;
    }

    /**
     * This method generates and displays the add project screen.
     */
    public void getAddProjectScreen() {
        String[] columnNames = {"Project Name","Description", "Start Date", "End Date"};
        JFrame frame = new JFrame("Add Project Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        JButton homeButton = new JButton("Home");
        homeButton.setBounds(0,0,200,40);
        frame.add(homeButton);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(200,0,200,40);
        frame.add(addTaskButton);

        JButton checkTaskButton = new JButton("Check Tasks");
        checkTaskButton.setBounds(400,0,200,40);
        frame.add(checkTaskButton);

        JButton checkProjectButton = new JButton("Check Team Projects");
        checkProjectButton.setBounds(600,0,200,40);
        frame.add(checkProjectButton);

        JButton saveButton = new JButton("Add Project");
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

    /**
     * This method generates and displays the check project screen.
     */
    public void getCheckProjectScreen() {
        String[] columnNames = {"Project Name","Description", "Start Date", "End Date"};
        JFrame frame = new JFrame("Check Project Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        JButton homeButton = new JButton("Home");
        homeButton.setBounds(0,0,200,40);
        frame.add(homeButton);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(200,0,200,40);
        frame.add(addTaskButton);

        JButton checkTaskButton = new JButton("Check Tasks");
        checkTaskButton.setBounds(400,0,200,40);
        frame.add(checkTaskButton);

        JButton addProjectButton = new JButton("Add Project");
        addProjectButton.setBounds(600,0,200,40);
        frame.add(addProjectButton);

        JButton saveButton = new JButton("Save Changes");
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
