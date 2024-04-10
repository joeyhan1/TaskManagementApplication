import java.sql.*;

/**
 * Provides user authentication using MySQL database.
 */
public class MySQLUserAuthentication implements UserAuthentication {
    private SQLConnection connection = null;

    @Override
    public boolean authenticate(String username, String password) {
        try {
            connection = new SQLConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); //If resultSet has next, then user is authenticated
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(connection != null) {
                connection.closeConnection();
            }
        }
    }
}
