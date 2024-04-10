import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * ActionListener implementation for handling actions in various screens.
 */
public class AllActionListener implements ActionListener {
    private String userRole;
    private AddTaskScreen addTaskScreen = new AddTaskScreen();
    private ProjectScreens projectScreens = new ProjectScreens(userRole);
    private CheckTaskScreen checkTaskScreen = new CheckTaskScreen(userRole);

    /**
     * Constructor to initialize the user role.
     * @param role The role of the user (e.g., "ADMIN", "EMPLOYEE").
     */
    public AllActionListener(String role) {
        userRole = role;
    }

    /**
     * Handles action events triggered by UI components.
     * @param e The ActionEvent triggered by the UI component.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Getting the source of the event
        Object source = e.getSource();

        //Find the frame that the button is in
        JFrame frame = findFrame(source);

        //Checking if it is a button
        if(source instanceof JButton) {
            JButton button = (JButton) source;
            String buttonText = button.getText();

            System.out.println("Button pressed: " + buttonText);
            if(userRole.equals("ADMIN")) {
                switch(buttonText) {
                    case "Add Task":
                        addTaskScreen.getScreen();
                        closeFrame(frame);
                        break;
                    case "Check Tasks":
                        checkTaskScreen.getCheckTaskScreen();
                        closeFrame(frame);
                        break;
                    case "Add Project":
                        projectScreens.getAddProjectScreen();
                        closeFrame(frame);
                        break;
                    case "Check Team Projects":
                        projectScreens.getCheckProjectScreen();
                        closeFrame(frame);
                        break;
                    default:
                        System.out.println(buttonText + " does not have anything to do!");
                }
            } else if(userRole.equals("EMPLOYEE")) {
                switch(buttonText) {
                    case "Check Tasks":
                        checkTaskScreen.getCheckTaskScreen();
                        closeFrame(frame);
                        break;
                    case "Check Team Projects":
                        projectScreens.getCheckProjectScreen();
                        closeFrame(frame);
                        break;
                    default:
                        System.out.println(buttonText + " does not have anything to do!");
                }
            }

        }
    }

    /**
     * Finds the JFrame containing the UI component that triggered the action.
     * @param source The source UI component.
     * @return The JFrame containing the UI component, or null if not found.
     */
    private JFrame findFrame(Object source) {
        if (source instanceof Component) {
            Component component = (Component) source;
            while (component != null) {
                if (component instanceof JFrame) {
                    return (JFrame) component;
                }
                component = component.getParent();
            }
        }
        return null;
    }

    /**
     * Closes the specified JFrame.
     * @param frame The JFrame to close.
     */
    private void closeFrame(JFrame frame) {
        if(frame != null) {
            frame.dispose();
        }
    }
}
