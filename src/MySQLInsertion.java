import java.sql.*;
import java.time.LocalDate;

/**
 * A class for inserting projects into a MySQL database.
 */
public class MySQLInsertion {
    private SQLConnection connection = null;
    private CurrentUser currentUser = CurrentUser.getInstance();

    /**
     * Inserts a project into the database.
     * @param projectName The name of the project.
     * @param description The description of the project.
     * @param startDate The start date of the project.
     * @param endDate The end date of the project.
     */
    public void insertProject(String projectName, Object description, LocalDate startDate, LocalDate endDate) {
        int teamID = getTeam();
        String query = "INSERT INTO Project (ProjectName, Description, StartDate, EndDate, TeamID) VALUES (?, ?, ?, ?, ?)";
        if(description == null) {
            String descriptionString = "";
            try(Connection con = DriverManager.getConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
                 PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, projectName);
                preparedStatement.setString(2, descriptionString);
                preparedStatement.setDate(3, Date.valueOf(startDate));
                preparedStatement.setDate(4, Date.valueOf(endDate));
                preparedStatement.setInt(5, teamID);
                preparedStatement.executeUpdate();
            } catch(SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            try(Connection con = DriverManager.getConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
                 PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, projectName);
                preparedStatement.setString(2, description.toString());
                preparedStatement.setDate(3, Date.valueOf(startDate));
                preparedStatement.setDate(4, Date.valueOf(endDate));
                preparedStatement.setInt(5, teamID);
                preparedStatement.executeUpdate();
            } catch(SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Gets the team ID of the current user.
     * @return The team ID of the current user, or 0 if an error occurs.
     */
    private int getTeam() {
        try {
            String teamID = null;
            String username = currentUser.getUsername();
            connection = new SQLConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
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
