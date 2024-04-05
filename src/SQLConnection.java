import java.sql.*;

/**
 * A class to connect to a MySQL database.
 */
public class SQLConnection {
    private Connection connection;

    /**
     * Constructor to establish connection to the MySQL database.
     *
     * @param url      URL of the MySQL database
     * @param username Username for database authentication
     * @param password Password for database authentication
     */
    public SQLConnection(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false); //Disable auto-commit to start a transaction
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    /**
     * Method to close the database connection.
     */
    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
