import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User Interface of a login screen for the Task Management Application.
 */
public class LoginScreen {
    private MySQLUserAuthentication authentication = new MySQLUserAuthentication();
    private SQLConnection connection = null;
    private JFrame frame = new JFrame("Task Management Application");
    private HomeScreen homeScreen = new HomeScreen();

    /**
     * Creates the login screen
     */
    public void createScreen() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        JLabel titleLabel = new JLabel("Task Management Application");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(100, 50, 800,40);
        frame.add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(200, 200,100,40);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(300,200,300,40);
        frame.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200,250,100,40);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(300,250,300,40);
        frame.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(300,300,300,40);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Testing purposes
//                System.out.println(usernameField.getText());
//                System.out.println(passwordField.getText());
//                System.out.println("Action Performed: " + e.getActionCommand());
                String username = usernameField.getText();
                String password = passwordField.getText();
                if(authentication.authenticate(username, password)) {
                    CurrentUser currentUser = CurrentUser.getInstance();
                    String userRole = getRole(username, password);
                    switch(userRole) {
                        case "ADMIN":
                            //Setting the username of the current user of the application
                            currentUser.setUsername(username);
                            homeScreen.getAdminScreen();
                            frame.dispose();
                            break;
                        case "EMPLOYEE":
                            //Setting the username of the current user of the application
                            currentUser.setUsername(username);
                            homeScreen.getEmployeeScreen();
                            frame.dispose();
                            break;
                        default:
                            System.out.println("User has no role");
                    }
                } else {
                    System.out.println("Didn't work!");
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
        frame.add(loginButton);

        frame.setVisible(true);
    }

    /**
     * Retrieves the role of the user from the database
     * @param username The username of the user
     * @param password The password of the user
     * @return The role of the user
     */
    public String getRole(String username, String password) {
        try {
            connection = new SQLConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
//                System.out.println(resultSet.getString("Role")); //Testing purposes
                return resultSet.getString("Role");
            }
            return null;
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
