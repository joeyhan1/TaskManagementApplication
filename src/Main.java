import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //Testing purposes
        SQLConnection connection = new SQLConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
        connection.closeConnection();
    }
}