import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * User Interface of an add task screen for the Task Management Application.
 */
public class AddTaskScreen {
    private SQLConnection connection = null;

    /**
     * This method generates and displays the add task screen.
     */
    public void getScreen() {
        AllActionListener allActionListener = new AllActionListener("ADMIN");
        String[] columnNames = {"Project Name","Task Name", "User ID", "Description", "Priority"};
        ArrayList<String> priorityList = new ArrayList<>();
        priorityList.add("LOW");
        priorityList.add("MEDIUM");
        priorityList.add("HIGH");
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


        DefaultTableModel model = new DefaultTableModel(columnNames, 1);

        JTable table = new JTable(model);
        table.setRowHeight(40);

        //Editing cells
        //Project name
        table.getColumnModel().getColumn(0).setCellRenderer(new ComboBoxCellRenderer(getProjectNames()));
        table.getColumnModel().getColumn(0).setCellEditor(new ComboBoxCellEditor(getProjectNames()));
        //Team members user id
        table.getColumnModel().getColumn(2).setCellRenderer(new ComboBoxCellRenderer(getMembersUserID()));
        table.getColumnModel().getColumn(2).setCellEditor(new ComboBoxCellEditor(getMembersUserID()));
        //Priority
        table.getColumnModel().getColumn(4).setCellRenderer(new ComboBoxCellRenderer(priorityList));
        table.getColumnModel().getColumn(4).setCellEditor(new ComboBoxCellEditor(priorityList));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,200,800,63);
        frame.getContentPane().add(scrollPane);

        JButton saveButton = new JButton("Add Task");
        saveButton.setBounds(560,400,160,40);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getProjectNames();
                Object projectName = table.getValueAt(0,0);
                Object taskName = table.getValueAt(0,1);
                Object userID = table.getValueAt(0,2);
                Object description = table.getValueAt(0, 3);
                Object priority = table.getValueAt(0, 4);

                //Error checking before inserting new task
                if(projectName != null && taskName != null && userID != null && priority != null) {
                    String taskNameTrimmed = taskName.toString().trim().replaceAll("\\s+", " ");
                    if(!taskNameTrimmed.isEmpty()) {
                            MySQLInsertion mySQLInsertion = new MySQLInsertion();
                            String userIDString = userID.toString();
                            //Testing purposes
//                            System.out.println("Project Name: " + projectName);
//                            System.out.println("Task Name: " + taskNameTrimmed);
//                            System.out.println("User ID: " + userIDString);
//                            System.out.println("Description: " + description);
//                            System.out.println("Priority: " + priority);
                            mySQLInsertion.insertTask(projectName,taskNameTrimmed,userIDString,description,priority);
                    } else {
                        System.out.println("Please input Task Name!");
                    }
                } else {
                    System.out.println("Please ensure that you input Project Name, Task Name, User ID and Priority!");
                }
            }
        });

        frame.add(saveButton);

        //Navigation bar action listeners
        homeButton.addActionListener(allActionListener);
        checkTaskButton.addActionListener(allActionListener);
        addProjectButton.addActionListener(allActionListener);
        checkProjectButton.addActionListener(allActionListener);

        frame.setVisible(true);
    }

    /**
     * Retrieves a list of project names associated with the current user.
     *
     * @return An ArrayList containing project names.
     */
    private ArrayList<String> getProjectNames() {
        CurrentUser currentUser = CurrentUser.getInstance();
        ArrayList<String> projectNamesList = new ArrayList<>();
        String username = currentUser.getUsername();
        try {
            connection = new SQLConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
            String query = "SELECT ProjectName FROM project p JOIN team t ON p.TeamID = t.TeamID JOIN user u ON t.TeamID = u.TeamID WHERE u.Username = ?";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
//                System.out.println(resultSet.getString("ProjectName")); //Testing purposes
                projectNamesList.add(resultSet.getString("ProjectName"));
            }
            return projectNamesList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.closeConnection();
            }
        }
    }

    /**
     * Retrieves a list of user IDs for team members associated with the current user's team.
     *
     * @return An ArrayList containing user IDs.
     */
    private ArrayList<String> getMembersUserID() {
        CurrentUser currentUser = CurrentUser.getInstance();
        ArrayList<String> userIDList = new ArrayList<>();
        String username = currentUser.getUsername();
        try {
            connection = new SQLConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
            String query = "SELECT UserID FROM user WHERE TeamID = (SELECT TeamID FROM user WHERE Username = ?);";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
//                System.out.println(resultSet.getString("UserID")); //Testing purposes
                userIDList.add(resultSet.getString("UserID"));
            }
            return userIDList;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.closeConnection();
            }
        }
    }
}
