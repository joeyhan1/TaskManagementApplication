import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User Interface of a home screen for the Task Management Application.
 */
public class HomeScreen {
    private JFrame frame = new JFrame("Home Screen");

    /**
     * Displays the Home Screen for the administrator.
     */
    public void getAdminScreen() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        JLabel titleLabel = new JLabel("What would you like to do?");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(120, 150, 800,50);
        frame.add(titleLabel);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(25,250,160,40);
        frame.add(addTaskButton);

        JButton checkTaskButton = new JButton("Check Tasks");
        checkTaskButton.setBounds(210,250,160,40);
        frame.add(checkTaskButton);

        JButton addProjectButton = new JButton("Add Project");
        addProjectButton.setBounds(395,250,160,40);
        frame.add(addProjectButton);

        JButton checkProjectButton = new JButton("Check Team Projects");
        checkProjectButton.setBounds(580,250,160,40);
        frame.add(checkProjectButton);

        frame.setVisible(true);
    }

    /**
     * Displays the Home Screen for the employee.
     */
    public void getEmployeeScreen() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        JLabel titleLabel = new JLabel("What would you like to do?");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(120, 150, 800,50);
        frame.add(titleLabel);

        JButton checkTaskButton = new JButton("Check Tasks");
        checkTaskButton.setBounds(210,250,160,40);
        frame.add(checkTaskButton);

        JButton checkProjectButton = new JButton("Check Team Projects");
        checkProjectButton.setBounds(395,250,160,40);
        frame.add(checkProjectButton);

        frame.setVisible(true);
    }
}
