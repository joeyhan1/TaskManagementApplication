import java.sql.*;
import javax.swing.*;

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
        JFrame frame = new JFrame("Task Management Application");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

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
        frame.add(loginButton);

        frame.setVisible(true);
    }
}