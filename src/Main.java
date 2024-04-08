import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Main {
    public static void main(String[] args) {
        //Testing purposes
//        SQLConnection connection = new SQLConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
//        MySQLUserAuthentication authentication = new MySQLUserAuthentication();
//        String username = "jmyall0";
//        String password = "gS7$@p,zgd";
//        if(authentication.authenticate(username,password)) {
//            System.out.println("It works!");
//        } else {
//            System.out.println("Didn't work!");
//        }
//        connection.closeConnection();
        //Java Swing for login screen ui (Testing purposes)
//        JFrame frame = new JFrame("Task Management Application");
//        frame.setSize(800,600);
//        frame.setLocationRelativeTo(null);
//        frame.setLayout(null);
//        frame.setResizable(false);
//
//        JLabel titleLabel = new JLabel("Task Management Application");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
//        titleLabel.setBounds(100, 50, 800,40);
//        frame.add(titleLabel);
//
//        JLabel usernameLabel = new JLabel("Username:");
//        usernameLabel.setBounds(200, 200,100,40);
//        frame.add(usernameLabel);
//
//        JTextField usernameField = new JTextField();
//        usernameField.setBounds(300,200,300,40);
//        frame.add(usernameField);
//
//        JLabel passwordLabel = new JLabel("Password:");
//        passwordLabel.setBounds(200,250,100,40);
//        frame.add(passwordLabel);
//
//        JPasswordField passwordField = new JPasswordField();
//        passwordField.setBounds(300,250,300,40);
//        frame.add(passwordField);
//
//        JButton loginButton = new JButton("Login");
//        loginButton.setBounds(300,300,300,40);
//        frame.add(loginButton);
//
//        frame.setVisible(true);
        //Home screen for administrator (Testing purposes)
//        JFrame frame = new JFrame("Task Management Application");
//        frame.setSize(800,600);
//        frame.setLocationRelativeTo(null);
//        frame.setLayout(null);
//        frame.setResizable(false);
//
//        JLabel titleLabel = new JLabel("What would you like to do?");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
//        titleLabel.setBounds(120, 150, 800,40);
//        frame.add(titleLabel);
//
//        JButton addTaskButton = new JButton("Add Task");
//        addTaskButton.setBounds(25,250,160,40);
//        frame.add(addTaskButton);
//
//        JButton checkTaskButton = new JButton("Check Tasks");
//        checkTaskButton.setBounds(210,250,160,40);
//        frame.add(checkTaskButton);
//
//        JButton addProjectButton = new JButton("Add Project");
//        addProjectButton.setBounds(395,250,160,40);
//        frame.add(addProjectButton);
//
//        JButton checkProjectButton = new JButton("Check Team Projects");
//        checkProjectButton.setBounds(580,250,160,40);
//        frame.add(checkProjectButton);
//
//        frame.setVisible(true);
        //Home screen for employee (Testing purposes)
//        JFrame frame = new JFrame("Task Management Application");
//        frame.setSize(800,600);
//        frame.setLocationRelativeTo(null);
//        frame.setLayout(null);
//        frame.setResizable(false);
//
//        JLabel titleLabel = new JLabel("What would you like to do?");
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
//        titleLabel.setBounds(120, 150, 800,40);
//        frame.add(titleLabel);
//
//        JButton checkTaskButton = new JButton("Check Tasks");
//        checkTaskButton.setBounds(210,250,160,40);
//        frame.add(checkTaskButton);
//
//        JButton checkProjectButton = new JButton("Check Team Projects");
//        checkProjectButton.setBounds(395,250,160,40);
//        frame.add(checkProjectButton);
//
//        frame.setVisible(true);
        //Add Task screen for adminstrator (Testing purposes)
//        String[] columnNames = {"Project Name","Task Name", "User ID", "Description", "Priority"};
//        JFrame frame = new JFrame("Task Management Application");
//        frame.setSize(800,600);
//        frame.setLocationRelativeTo(null);
//        frame.setLayout(null);
//        frame.setResizable(false);
//
//        JButton homeButton = new JButton("Home");
//        homeButton.setBounds(0,0,200,40);
//        frame.add(homeButton);
//
//        JButton checkTaskButton = new JButton("Check Tasks");
//        checkTaskButton.setBounds(200,0,200,40);
//        frame.add(checkTaskButton);
//
//        JButton addProjectButton = new JButton("Add Project");
//        addProjectButton.setBounds(400,0,200,40);
//        frame.add(addProjectButton);
//
//        JButton checkProjectButton = new JButton("Check Team Projects");
//        checkProjectButton.setBounds(600,0,200,40);
//        frame.add(checkProjectButton);
//
//        JButton saveButton = new JButton("Add Task");
//        saveButton.setBounds(560,400,160,40);
//        frame.add(saveButton);
//
//        DefaultTableModel model = new DefaultTableModel(columnNames, 1);
//
//        JTable table = new JTable(model);
//        table.setRowHeight(40);
//
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(0,200,800,63);
//        frame.getContentPane().add(scrollPane);
//
//        frame.setVisible(true);
        //Add Project screen for adminstrator and also Check Team Projects screen (Testing purposes)
//        String[] columnNames = {"Project Name","Description", "Start Date", "End Date"};
//        JFrame frame = new JFrame("Task Management Application");
//        frame.setSize(800,600);
//        frame.setLocationRelativeTo(null);
//        frame.setLayout(null);
//        frame.setResizable(false);
//
//        JButton homeButton = new JButton("Home");
//        homeButton.setBounds(0,0,200,40);
//        frame.add(homeButton);
//
//        JButton addTaskButton = new JButton("Add Task");
//        addTaskButton.setBounds(200,0,200,40);
//        frame.add(addTaskButton);
//
//        JButton checkTaskButton = new JButton("Check Tasks");
//        checkTaskButton.setBounds(400,0,200,40);
//        frame.add(checkTaskButton);
//
//        JButton checkProjectButton = new JButton("Check Team Projects"); //This is changed to addProjectButton for check team projects screen
//        checkProjectButton.setBounds(600,0,200,40);
//        frame.add(checkProjectButton);
//
//        JButton saveButton = new JButton("Add Project");
//        saveButton.setBounds(560,400,160,40);
//        frame.add(saveButton);
//
//        DefaultTableModel model = new DefaultTableModel(columnNames, 1);
//
//        JTable table = new JTable(model);
//        table.setRowHeight(40);
//
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(0,200,800,63);
//        frame.getContentPane().add(scrollPane);
//
//        frame.setVisible(true);
        //Check task screen (Testing purposes)
//        String[] columnNames = {"Project Name","Task Name", "Description", "Status", "Priority", "Start Date", "Completion Date", "Deadline Date", "Progress Percentage"};
//        JFrame frame = new JFrame("Task Management Application");
//        frame.setSize(800,600);
//        frame.setLocationRelativeTo(null);
//        frame.setLayout(null);
//        frame.setResizable(false);
//
//        JButton homeButton = new JButton("Home");
//        homeButton.setBounds(0,0,200,40);
//        frame.add(homeButton);
//
//        JButton addTaskButton = new JButton("Add Task");
//        addTaskButton.setBounds(200,0,200,40);
//        frame.add(addTaskButton);
//
//        JButton addProjectButton = new JButton("Add Project");
//        addProjectButton.setBounds(400,0,200,40);
//        frame.add(addProjectButton);
//
//        JButton checkProjectButton = new JButton("Check Team Projects");
//        checkProjectButton.setBounds(600,0,200,40);
//        frame.add(checkProjectButton);
//
//        JButton saveButton = new JButton("Add Project");
//        saveButton.setBounds(560,400,160,40);
//        frame.add(saveButton);
//
//        DefaultTableModel model = new DefaultTableModel(columnNames, 1);
//
//        JTable table = new JTable(model);
//        TableColumnModel columnModel = table.getColumnModel();
//        for (int i = 0; i < columnModel.getColumnCount(); i++) {
//            TableColumn column = columnModel.getColumn(i);
//            int width = (int) table.getTableHeader().getDefaultRenderer()
//                    .getTableCellRendererComponent(table, column.getHeaderValue(), false, false, -1, i)
//                    .getPreferredSize().getWidth();
//            column.setPreferredWidth(width);
//        }
//        table.setRowHeight(80);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(0,200,795,300);
//        frame.getContentPane().add(scrollPane);
//
//        frame.setVisible(true);
    }
}