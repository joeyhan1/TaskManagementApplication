import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * User Interfaces of project screens for the Task Management Application.
 */
public class ProjectScreens {
    private String userRole;
    private SQLConnection connection = null;
    private CurrentUser currentUser = CurrentUser.getInstance();

    /**
     * Constructs a ProjectScreens object with the specified user role.
     *
     * @param role The role of the user.
     */
    public ProjectScreens(String role) {
        userRole = role;
    }

    /**
     * This method generates and displays the add project screen.
     */
    public void getAddProjectScreen() {
        AllActionListener allActionListener = new AllActionListener("ADMIN");
        String[] columnNames = {"Project Name", "Description", "Start Date", "End Date"};
        JFrame frame = new JFrame("Add Project Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        JButton homeButton = new JButton("Home");
        homeButton.setBounds(0, 0, 200, 40);
        frame.add(homeButton);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(200, 0, 200, 40);
        frame.add(addTaskButton);

        JButton checkTaskButton = new JButton("Check Tasks");
        checkTaskButton.setBounds(400, 0, 200, 40);
        frame.add(checkTaskButton);

        JButton checkProjectButton = new JButton("Check Team Projects");
        checkProjectButton.setBounds(600, 0, 200, 40);
        frame.add(checkProjectButton);

        DefaultTableModel model = new DefaultTableModel(columnNames, 1);

        JTable table = new JTable(model);
        table.setRowHeight(40);

        //Editing cells
        table.getColumnModel().getColumn(2).setCellEditor(new DateCellEditor());
        table.getColumnModel().getColumn(3).setCellEditor(new DateCellEditor());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 200, 800, 63);
        frame.getContentPane().add(scrollPane);

        JButton saveButton = new JButton("Add Project");
        saveButton.setBounds(560, 400, 160, 40);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object projectName = table.getValueAt(0, 0);
                Object description = table.getValueAt(0, 1);
                Object startDateObject = table.getValueAt(0, 2);
                Object endDateObject = table.getValueAt(0, 3);

                //Error checking before inserting new project
                if(projectName != null && startDateObject != null && endDateObject != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String projectNameTrimmed = projectName.toString().trim().replaceAll("\\s+", " ");
                    LocalDate startDate = LocalDate.parse(startDateObject.toString(), formatter);
                    LocalDate endDate = LocalDate.parse(endDateObject.toString(), formatter);
                    if(!projectNameTrimmed.isEmpty()) {
                        if(startDate.isBefore(endDate)) {
                            MySQLInsertion mySQLInsertion = new MySQLInsertion();
                            //Testing purposes
//                            System.out.println("Project Name: " + projectNameTrimmed);
//                            System.out.println("Description: " + description);
//                            System.out.println("Start Date: " + startDate);
//                            System.out.println("End Date: " + endDate);
                            mySQLInsertion.insertProject(projectNameTrimmed, description, startDate, endDate);

                        } else {
                            System.out.println("Start date is after end date!");
                            getAddProjectScreen();
                            frame.dispose();
                        }
                    } else {
                        System.out.println("Please input Project Name!");
                    }
                } else {
                    System.out.println("Please ensure that you input Project Name, Start Date and End Date!");
                }
            }
        });

        frame.add(saveButton);

        //Navigation bar action listeners
        homeButton.addActionListener(allActionListener);
        addTaskButton.addActionListener(allActionListener);
        checkTaskButton.addActionListener(allActionListener);
        checkProjectButton.addActionListener(allActionListener);

        frame.setVisible(true);
    }

    /**
     * This method generates and displays the check project screen for admins.
     */
    public void getCheckProjectScreenAdmin() {
        int teamID = getTeam();
        String[] columnNames = {"Project ID", "Project Name", "Description", "Start Date", "End Date"};
        AllActionListener allActionListener = new AllActionListener("ADMIN");
        JFrame resultFrame = new JFrame("Check Project Screen");
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setSize(800, 600);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setLayout(new BorderLayout());

        try {
            connection = new SQLConnection(DatabaseConfig.getProperties("url"), DatabaseConfig.getProperties("username"), DatabaseConfig.getProperties("password"));
            //For Admin
            String query = "SELECT ProjectID, ProjectName, Description, StartDate, EndDate FROM Project WHERE TeamID = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, teamID);
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

            //Read only columns (Project ID)
            int[] nonEditableColumns = {0};
            for(int column : nonEditableColumns) {
                table.getColumnModel().getColumn(column).setCellRenderer(new NonEditableCellRenderer());
                table.getColumnModel().getColumn(column).setCellEditor(new NonEditableCellEditor());
            }

            //Dates
            table.getColumnModel().getColumn(3).setCellEditor(new DateCellEditor());
            table.getColumnModel().getColumn(4).setCellEditor(new DateCellEditor());

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

            JButton checkTaskButton = new JButton("Check Tasks");
            checkTaskButton.setPreferredSize(new Dimension(preferredButtonWidth, preferredButtonHeight));
            buttonPanel.add(checkTaskButton);

            JButton addProjectButton = new JButton("Add Project");
            addProjectButton.setPreferredSize(new Dimension(preferredButtonWidth, preferredButtonHeight));
            buttonPanel.add(addProjectButton);

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
            checkTaskButton.addActionListener(allActionListener);
            addProjectButton.addActionListener(allActionListener);

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Write SQL update statements to update the database with the new values
                    String updateTask = "UPDATE Project SET ProjectName = ?, Description = ?, StartDate = ?, EndDate = ? WHERE ProjectID = ?";
                    try(Connection con = DriverManager.getConnection(DatabaseConfig.getProperties("url"), DatabaseConfig.getProperties("username"), DatabaseConfig.getProperties("password"))) {
                        PreparedStatement updateStatementProject = con.prepareStatement(updateTask);
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        int rowCount = model.getRowCount();

                        //Update database with changes for each row in the table
                        for(int i = 0; i < rowCount; i++) {
                            Integer projectID = (Integer) model.getValueAt(i, 0);
                            String projectName = (String) model.getValueAt(i, 1);
                            String description = (String) model.getValueAt(i, 2);
                            Object startDateObject = model.getValueAt(i, 3);
                            Object endDateObject = model.getValueAt(i, 4);

                            //Testing Purposes
                            System.out.println("Project Name: " + projectName);
                            System.out.println("Description: " + description);
                            System.out.println("Start Date: " + startDateObject);
                            System.out.println("End Date: " + endDateObject);

                            //Updating project tables
                            updateStatementProject.setString(1, projectName);
                            updateStatementProject.setString(2, description);
                            if(startDateObject == null) {
                                updateStatementProject.setNull(3, Types.DATE);
                            } else {
                                try {
                                    LocalDate startDate = LocalDate.parse(startDateObject.toString(), formatter);
                                    updateStatementProject.setDate(3, Date.valueOf(startDate));
                                } catch(Exception pe) {
                                    //Testing purposes
//                                    System.out.println("No Changes");
                                    updateStatementProject.setDate(3, Date.valueOf(startDateObject.toString()));
                                }
                            }
                            if(endDateObject == null) {
                                updateStatementProject.setNull(4, Types.DATE);
                            } else {
                                try {
                                    LocalDate endDate = LocalDate.parse(endDateObject.toString(), formatter);
                                    updateStatementProject.setDate(4, Date.valueOf(endDate));
                                } catch(Exception pe) {
                                    //Testing purposes
//                                    System.out.println("No Changes");
                                    updateStatementProject.setDate(4, Date.valueOf(endDateObject.toString()));
                                }
                            }
                            updateStatementProject.setInt(5, projectID);
                            updateStatementProject.executeUpdate();
                        }

                        updateStatementProject.close();
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
            if (connection != null) {
                connection.closeConnection();
            }
        }

        resultFrame.setVisible(true);
    }

    /**
     * This method generates and displays the check project screen for employees.
     */
    public void getCheckProjectScreenEmployee() {
        int teamID = getTeam();
        String[] columnNames = {"Project ID", "Project Name", "Description", "Start Date", "End Date"};
        AllActionListener allActionListener = new AllActionListener("EMPLOYEE");
        JFrame resultFrame = new JFrame("Check Project Screen");
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setSize(800, 600);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setLayout(new BorderLayout());

        try {
            connection = new SQLConnection(DatabaseConfig.getProperties("url"), DatabaseConfig.getProperties("username"), DatabaseConfig.getProperties("password"));
            //For Employee
            String query = "SELECT ProjectID, ProjectName, Description, StartDate, EndDate FROM Project WHERE TeamID = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setInt(1, teamID);
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

                // Set the column width to the maximum width found
                column.setPreferredWidth(maxWidth + table.getIntercellSpacing().width);
            }

            //Read only columns
            int[] nonEditableColumns = {0, 1, 2, 3 ,4};
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

            JButton checkTaskButton = new JButton("Check Tasks");
            checkTaskButton.setPreferredSize(new Dimension(preferredButtonWidth, preferredButtonHeight));
            buttonPanel.add(checkTaskButton);

            resultFrame.add(buttonPanel, BorderLayout.NORTH);

            //Navigation bar action listeners
            homeButton.addActionListener(allActionListener);
            checkTaskButton.addActionListener(allActionListener);

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
     * Gets the team ID of the current user.
     *
     * @return The team ID of the current user, or 0 if an error occurs.
     */
    private int getTeam() {
        try {
            String teamID = null;
            String username = currentUser.getUsername();
            connection = new SQLConnection(DatabaseConfig.getProperties("url"), DatabaseConfig.getProperties("username"), DatabaseConfig.getProperties("password"));
            String query = "SELECT * FROM user WHERE username = ?";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                teamID = resultSet.getString("TeamID");
                System.out.println("Team: " + teamID);
                return Integer.parseInt(teamID);
            }
            return 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if(connection != null) {
                connection.closeConnection();
            }
        }
    }
}