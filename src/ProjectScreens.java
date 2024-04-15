import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
        AllActionListener allActionListener = new AllActionListener("ADMIN");
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

        DefaultTableModel model = new DefaultTableModel(columnNames, 1);

        JTable table = new JTable(model);
        table.setRowHeight(40);

        //Editing cells
        table.getColumnModel().getColumn(2).setCellEditor(new DateCellEditor());
        table.getColumnModel().getColumn(3).setCellEditor(new DateCellEditor());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,200,800,63);
        frame.getContentPane().add(scrollPane);

        JButton saveButton = new JButton("Add Project");
        saveButton.setBounds(560,400,160,40);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object projectName = table.getValueAt(0,0);
                Object description = table.getValueAt(0,1);
                Object startDateObject = table.getValueAt(0,2);
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
                            mySQLInsertion.insertProject(projectNameTrimmed,description,startDate,endDate);

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
