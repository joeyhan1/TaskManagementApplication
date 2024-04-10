import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User Interface of a check task screen for the Task Management Application.
 */
public class CheckTaskScreen {
    private String userRole;

    /**
     * Constructs a CheckTaskScreen object with the specified user role.
     * @param role The role of the user.
     */
    public CheckTaskScreen(String role) {
        userRole = role;
    }

    /**
     * This method generates and displays the check task screen.
     */
    public void getCheckTaskScreen() {
        String[] columnNames = {"Project Name","Task Name", "Description", "Status", "Priority", "Start Date", "Completion Date", "Deadline Date", "Progress Percentage"};
        JFrame frame = new JFrame("Check Task Screen");
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

        JButton addProjectButton = new JButton("Add Project");
        addProjectButton.setBounds(400,0,200,40);
        frame.add(addProjectButton);

        JButton checkProjectButton = new JButton("Check Team Projects");
        checkProjectButton.setBounds(600,0,200,40);
        frame.add(checkProjectButton);

        JButton saveButton = new JButton("Add Project");
        saveButton.setBounds(560,400,160,40);
        frame.add(saveButton);

        DefaultTableModel model = new DefaultTableModel(columnNames, 1);

        JTable table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            int width = (int) table.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(table, column.getHeaderValue(), false, false, -1, i)
                    .getPreferredSize().getWidth();
            column.setPreferredWidth(width);
        }
        table.setRowHeight(80);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,200,795,300);
        frame.getContentPane().add(scrollPane);

        frame.setVisible(true);
    }
}
