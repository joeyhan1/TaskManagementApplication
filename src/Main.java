import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //Testing purposes
//        SQLConnection connection = new SQLConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
        MySQLUserAuthentication authentication = new MySQLUserAuthentication();
        String username = "jmyall0";
        String password = "gS7$@p,zgd";
        if(authentication.authenticate(username,password)) {
            System.out.println("It works!");
        } else {
            System.out.println("Didn't work!");
        }
//        connection.closeConnection();
    }
}