import javax.swing.*;
import java.awt.*;

/**
 * User Interface of a home screen for the Task Management Application.
 */
public class HomeScreen {
    private JFrame frame = new JFrame("Home Screen");

    /**
     * Displays the Home Screen for the administrator.
     */
    public void getAdminScreen() {
        AllActionListener allActionListener = new AllActionListener("ADMIN");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        //Listens for any key presses and it notifies the allactionlistener class
        frame.getRootPane().registerKeyboardAction(allActionListener, KeyStroke.getKeyStroke("pressed"), JComponent.WHEN_IN_FOCUSED_WINDOW);

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

        addTaskButton.addActionListener(allActionListener);
        checkTaskButton.addActionListener(allActionListener);
        addProjectButton.addActionListener(allActionListener);
        checkProjectButton.addActionListener(allActionListener);

        frame.setVisible(true);
    }

    /**
     * Displays the Home Screen for the employee.
     */
    public void getEmployeeScreen() {
        AllActionListener allActionListener = new AllActionListener("EMPLOYEE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getRootPane().registerKeyboardAction(allActionListener, KeyStroke.getKeyStroke("pressed"), JComponent.WHEN_IN_FOCUSED_WINDOW);

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

        checkTaskButton.addActionListener(allActionListener);
        checkProjectButton.addActionListener(allActionListener);

        frame.setVisible(true);
    }
}
