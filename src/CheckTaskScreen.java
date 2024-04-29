import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * User Interface of a check task screen for the Task Management Application.
 */
public class CheckTaskScreen {
    private String userRole;
    private SQLConnection connection = null;
    private CurrentUser currentUser = CurrentUser.getInstance();

    /**
     * Constructs a CheckTaskScreen object with the specified user role.
     * @param role The role of the user.
     */
    public CheckTaskScreen(String role) {
        userRole = role;
    }

    /**
     * This method calls the private method to get the check task screen for admins.
     */
    public void getCheckTaskScreenAdmin() {
        displayAdminTaskScreen();
    }

    /**
     * This method calls the private method to get the check task screen for employees.
     */
    public void getCheckTaskScreenEmployee() {
        displayEmployeeTaskScreen();
    }

    /**
     * This method generates and displays the check task screen for admin users.
     */
    private void displayAdminTaskScreen() {
        String[] columnNames = {"Task ID","Project Name", "Task Name", "Description", "Status", "Priority", "Start Date", "Completion Date", "Deadline Date", "Progress Percentage"};
        AllActionListener allActionListener = new AllActionListener("ADMIN");
        String username = currentUser.getUsername();
        ArrayList<String> priorityList = new ArrayList<>();
        priorityList.add("LOW");
        priorityList.add("MEDIUM");
        priorityList.add("HIGH");
        ArrayList<String> statusList = new ArrayList<>();
        statusList.add("NOT-STARTED");
        statusList.add("IN-PROGRESS");
        statusList.add("COMPLETED");
        JFrame resultFrame = new JFrame("Check Task Screen");
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setSize(800, 600);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setLayout(new BorderLayout());

        try {
            connection = new SQLConnection(DatabaseConfig.getProperties("url"), DatabaseConfig.getProperties("username"), DatabaseConfig.getProperties("password"));
            //For Admin
            String query = "SELECT " + "Task.TaskID, " + "Project.ProjectName, " + "Task.TaskName, " + "Task.Description AS TaskDescription, " +
                            "Task.Status, " + "Task.Priority, " + "Task.StartDate AS TaskStartDate, " + "Task.CompletionDate AS TaskCompletionDate, "
                            + "Deadline.DeadlineDate, " + "Progress.PercentageComplete " + "FROM " + "User " + "JOIN " + "Team ON User.TeamID = Team.TeamID " +
                            "JOIN " + "Task ON User.UserID = Task.UserID " + "LEFT JOIN " + "Project ON Task.ProjectID = Project.ProjectID " +
                            "LEFT JOIN " + "Deadline ON Task.TaskID = Deadline.TaskID " + "LEFT JOIN " + "Progress ON Task.TaskID = Progress.TaskID " +
                            "WHERE " + "Team.TeamID = (" + "SELECT " + "TeamID " + "FROM " + "User " + "WHERE " + "Username = ?" + ");";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            for(String columnName : columnNames) {
                model.addColumn(columnName);
            }

            while(resultSet.next()) {
                Object[] rowData = new Object[columnNames.length];
                for(int i = 0; i < columnNames.length; i++) {
                    rowData[i] = resultSet.getObject(i + 1);
                }
                model.addRow(rowData);
            }

            JTable table = new JTable(model);

            //Set auto-resize mode to make columns adjust automatically
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            //Auto-adjust column widths based on content
            for(int i = 0; i < table.getColumnCount(); i++) {
                TableColumn column = table.getColumnModel().getColumn(i);
                int maxWidth = 0;

                //Calculate the maximum width of column cells
                for(int j = 0; j < table.getRowCount(); j++) {
                    TableCellRenderer renderer = table.getCellRenderer(j, i);
                    Component comp = table.prepareRenderer(renderer, j, i);
                    maxWidth = Math.max(comp.getPreferredSize().width, maxWidth);
                }

                //Calculate the maximum width of the header
                TableCellRenderer headerRenderer = column.getHeaderRenderer();
                if(headerRenderer == null) {
                    headerRenderer = table.getTableHeader().getDefaultRenderer();
                }
                Component headerComp = headerRenderer.getTableCellRendererComponent(table, column.getHeaderValue(), false, false, -1, i);
                maxWidth = Math.max(maxWidth, headerComp.getPreferredSize().width);

                //Set the column width to the maximum width found
                column.setPreferredWidth(maxWidth + table.getIntercellSpacing().width);
            }

            //Read only columns (Task ID and Project Name)
            int[] nonEditableColumns = {0, 1};
            for(int column : nonEditableColumns) {
                table.getColumnModel().getColumn(column).setCellRenderer(new NonEditableCellRenderer());
                table.getColumnModel().getColumn(column).setCellEditor(new NonEditableCellEditor());
            }
            //Status
            table.getColumnModel().getColumn(4).setCellRenderer(new ComboBoxCellRenderer(statusList));
            table.getColumnModel().getColumn(4).setCellEditor(new ComboBoxCellEditor(statusList));
            //Priority
            table.getColumnModel().getColumn(5).setCellRenderer(new ComboBoxCellRenderer(priorityList));
            table.getColumnModel().getColumn(5).setCellEditor(new ComboBoxCellEditor(priorityList));
            //Dates
            table.getColumnModel().getColumn(6).setCellEditor(new DateCellEditor());
            table.getColumnModel().getColumn(7).setCellEditor(new DateCellEditor());
            table.getColumnModel().getColumn(8).setCellEditor(new DateCellEditor());

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            resultFrame.add(scrollPane, BorderLayout.CENTER);

            //Add buttons above the scroll pane
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 0));

            int preferredButtonWidth = 200;
            int preferredButtonHeight = 40;

            JButton homeButton = new JButton("Home");
            homeButton.setPreferredSize(new Dimension(preferredButtonWidth, preferredButtonHeight));
            buttonPanel.add(homeButton);

            JButton addTaskButton = new JButton("Add Task");
            addTaskButton.setPreferredSize(new Dimension(preferredButtonWidth, preferredButtonHeight));
            buttonPanel.add(addTaskButton);

            JButton addProjectButton = new JButton("Add Project");
            addProjectButton.setPreferredSize(new Dimension(preferredButtonWidth, preferredButtonHeight));
            buttonPanel.add(addProjectButton);

            JButton checkProjectButton = new JButton("Check Team Projects");
            checkProjectButton.setPreferredSize(new Dimension(preferredButtonWidth, preferredButtonHeight));
            buttonPanel.add(checkProjectButton);

            resultFrame.add(buttonPanel, BorderLayout.NORTH);

            //Add buttons below the scroll pane
            JPanel saveButtonPanel = new JPanel();
            saveButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton saveButton = new JButton("Save Changes");
            saveButton.setPreferredSize(new Dimension(160, 40)); // Set preferred button size
            saveButtonPanel.add(saveButton);

            resultFrame.add(saveButtonPanel, BorderLayout.SOUTH);

            //Navigation bar action listeners
            homeButton.addActionListener(allActionListener);
            addTaskButton.addActionListener(allActionListener);
            addProjectButton.addActionListener(allActionListener);
            checkProjectButton.addActionListener(allActionListener);

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Write SQL update statements to update the database with the new values
                    String updateTask = "UPDATE Task SET TaskName = ?, Description = ?, Status = ?, Priority = ?, StartDate = ?, CompletionDate = ? WHERE TaskID = ?";
                    String updateDeadline = "UPDATE Deadline SET DeadlineDate = ? WHERE TaskID = ?";
                    String updateProgressPercentage = "UPDATE Progress SET PercentageComplete = ? WHERE TaskID = ?";
                    try(Connection con = DriverManager.getConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"))) {
                        PreparedStatement updateStatementTask = con.prepareStatement(updateTask);
                        PreparedStatement updateStatementDeadline = con.prepareStatement(updateDeadline);
                        PreparedStatement updateStatementProgress = con.prepareStatement(updateProgressPercentage);
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        int rowCount = model.getRowCount();

                        //Update database with changes for each row in the table
                        for(int i = 0; i < rowCount; i++) {
                            Integer taskID = (Integer) model.getValueAt(i, 0);
                            String projectName = (String) model.getValueAt(i, 1);
                            String taskName = (String) model.getValueAt(i, 2);
                            String description = (String) model.getValueAt(i, 3);
                            String status = (String) model.getValueAt(i, 4);
                            String priority = (String) model.getValueAt(i, 5);
                            Object startDateObject = model.getValueAt(i, 6);
                            Object completionDateObject = model.getValueAt(i, 7);
                            Object deadlineDateObject = model.getValueAt(i, 8);
                            Object progressPercentage = model.getValueAt(i, 9);

                            //Testing Purposes
//                            System.out.println("Task ID: " + taskID);
//                            System.out.println("Project Name: " + projectName);
//                            System.out.println("Task Name: " + taskName);
//                            System.out.println("Description: " + description);
//                            System.out.println("Status: " + status);
//                            System.out.println("Priority: " + priority);
//                            System.out.println("Start Date: " + startDateObject.toString());
//                            System.out.println("Completion Date: " + completionDateObject.toString());
//                            System.out.println("Deadline Date: " + deadlineDateObject.toString());
//                            System.out.println("Progress Percentage: " + progressPercentage.toString());


                            //Updating task tables
                            updateStatementTask.setString(1, taskName);
                            updateStatementTask.setString(2, description);
                            updateStatementTask.setString(3, status);
                            updateStatementTask.setString(4, priority);
                            if(startDateObject == null) {
                                updateStatementTask.setNull(5, Types.DATE);
                            } else {
                                try {
                                    LocalDate startDate = LocalDate.parse(startDateObject.toString(), formatter);
                                    updateStatementTask.setDate(5, Date.valueOf(startDate));
                                } catch(Exception pe) {
                                    //Testing purposes
//                                    System.out.println("No Changes");
                                    updateStatementTask.setDate(5, Date.valueOf(startDateObject.toString()));
                                }
                            }
                            if(completionDateObject == null) {
                                updateStatementTask.setNull(6, Types.DATE);
                            } else {
                                try {
                                    LocalDate completionDate = LocalDate.parse(completionDateObject.toString(), formatter);
                                    updateStatementTask.setDate(6, Date.valueOf(completionDate));
                                } catch(Exception pe) {
                                    //Testing purposes
//                                    System.out.println("No Changes");
                                    updateStatementTask.setDate(6, Date.valueOf(completionDateObject.toString()));
                                }
                            }
                            updateStatementTask.setInt(7, taskID);
                            updateStatementTask.executeUpdate();

                            //Updating deadline tables
                            if(deadlineDateObject == null) {
                                updateStatementDeadline.setNull(1, Types.DATE);
                            } else {
                                try {
                                    LocalDate deadlineDate = LocalDate.parse(deadlineDateObject.toString(), formatter);
                                    updateStatementDeadline.setDate(1, Date.valueOf(deadlineDate));
                                } catch(Exception pe) {
                                    //Testing purposes
//                                    System.out.println("No Changes");
                                    updateStatementDeadline.setDate(1, Date.valueOf(deadlineDateObject.toString()));
                                }
                            }
                            updateStatementDeadline.setInt(2, taskID);
                            updateStatementDeadline.executeUpdate();

                            //Updating progress tables
                            updateStatementProgress.setObject(1, progressPercentage);
                            updateStatementProgress.setInt(2, taskID);
                            updateStatementProgress.executeUpdate();
                        }

                        updateStatementTask.close();
                    } catch(SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(resultFrame, "Error saving changes to the database.", "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        if(connection != null) {
                            connection.closeConnection();
                        }
                    }
                }
            });

            resultSet.close();
            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(resultFrame, "Error retrieving data from database.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if(connection != null) {
                connection.closeConnection();
            }
        }

        resultFrame.setVisible(true);
    }


    /**
     * This method generates and displays the check task screen for admin users.
     */
    private void displayEmployeeTaskScreen() {
        String[] columnNames = {"Task ID","Project Name", "Task Name", "Description", "Status", "Priority", "Start Date", "Completion Date", "Deadline Date", "Progress Percentage"};
        AllActionListener allActionListener = new AllActionListener("EMPLOYEE");
        String username = currentUser.getUsername();
        JFrame resultFrame = new JFrame("Check Task Screen");
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setSize(800, 600);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setLayout(new BorderLayout());

        try {
            connection = new SQLConnection(DatabaseConfig.getProperties("url"), DatabaseConfig.getProperties("username"), DatabaseConfig.getProperties("password"));
            //For Employee
            String query = "SELECT Task.TaskID, Project.ProjectName, Task.TaskName, Task.Description AS TaskDescription, " +
                    "Task.Status, Task.Priority, Task.StartDate AS TaskStartDate, " +
                    "Task.CompletionDate AS TaskCompletionDate, Deadline.DeadlineDate, " +
                    "Progress.PercentageComplete " +
                    "FROM User " +
                    "JOIN Task ON User.UserID = Task.UserID " +
                    "JOIN Project ON Task.ProjectID = Project.ProjectID " +
                    "LEFT JOIN Deadline ON Task.TaskID = Deadline.TaskID " +
                    "LEFT JOIN Progress ON Task.TaskID = Progress.TaskID " +
                    "WHERE User.Username = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            for(String columnName : columnNames) {
                model.addColumn(columnName);
            }

            while(resultSet.next()) {
                Object[] rowData = new Object[columnNames.length];
                for(int i = 0; i < columnNames.length; i++) {
                    rowData[i] = resultSet.getObject(i + 1);
                }
                model.addRow(rowData);
            }


            JTable table = new JTable(model);
            //Set auto-resize mode to make columns adjust automatically
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            //Auto-adjust column widths based on content
            for(int i = 0; i < table.getColumnCount(); i++) {
                TableColumn column = table.getColumnModel().getColumn(i);
                int maxWidth = 0;

                //Calculate the maximum width of column cells
                for(int j = 0; j < table.getRowCount(); j++) {
                    TableCellRenderer renderer = table.getCellRenderer(j, i);
                    Component comp = table.prepareRenderer(renderer, j, i);
                    maxWidth = Math.max(comp.getPreferredSize().width, maxWidth);
                }

                //Calculate the maximum width of the header
                TableCellRenderer headerRenderer = column.getHeaderRenderer();
                if(headerRenderer == null) {
                    headerRenderer = table.getTableHeader().getDefaultRenderer();
                }
                Component headerComp = headerRenderer.getTableCellRendererComponent(table, column.getHeaderValue(), false, false, -1, i);
                maxWidth = Math.max(maxWidth, headerComp.getPreferredSize().width);

                //Set the column width to the maximum width found
                column.setPreferredWidth(maxWidth + table.getIntercellSpacing().width);
            }

            //Read only columns
            int[] nonEditableColumns = {0, 1, 2, 3, 4, 5, 6, 7, 8};
            for(int column : nonEditableColumns) {
                table.getColumnModel().getColumn(column).setCellRenderer(new NonEditableCellRenderer());
                table.getColumnModel().getColumn(column).setCellEditor(new NonEditableCellEditor());
            }

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            resultFrame.add(scrollPane, BorderLayout.CENTER);

            //Add buttons above the scroll pane
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 0));

            int preferredButtonWidth = 200;
            int preferredButtonHeight = 40;

            JButton homeButton = new JButton("Home");
            homeButton.setPreferredSize(new Dimension(preferredButtonWidth, preferredButtonHeight));
            buttonPanel.add(homeButton);

            JButton checkProjectButton = new JButton("Check Team Projects");
            checkProjectButton.setPreferredSize(new Dimension(preferredButtonWidth, preferredButtonHeight));
            buttonPanel.add(checkProjectButton);

            resultFrame.add(buttonPanel, BorderLayout.NORTH);

            //Navigation bar action listeners
            homeButton.addActionListener(allActionListener);
            checkProjectButton.addActionListener(allActionListener);

            resultSet.close();
            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(resultFrame, "Error retrieving data from database.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if(connection != null) {
                connection.closeConnection();
            }
        }

        resultFrame.setVisible(true);
    }
}
