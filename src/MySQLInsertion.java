import java.sql.*;
import java.time.LocalDate;

/**
 * A class for inserting projects and tasks into a MySQL database.
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
     * Inserts a task into the database.
     *
     * @param projectName The name of the project the task belongs to.
     * @param taskName    The name of the task.
     * @param userID      The ID of the user assigned to the task.
     * @param description The description of the task.
     * @param priority    The priority of the task.
     */
    public void insertTask(Object projectName, String taskName, String userID, Object description, Object priority) {
        String query = "INSERT INTO Task (TaskName, Description, Status, Priority, UserID, ProjectID) VALUES (?, ?, ?, ?, ?, ?)";
        int projectID = getProjectID(projectName);
        int userIDInteger = Integer.parseInt(userID);
        if(description == null) {
            String descriptionString = "";
            try(Connection con = DriverManager.getConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
                PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, taskName);
                preparedStatement.setString(2, descriptionString);
                preparedStatement.setString(3, "NOT-STARTED");
                preparedStatement.setString(4, priority.toString());
                preparedStatement.setInt(5, userIDInteger);
                preparedStatement.setInt(6, projectID);
                preparedStatement.executeUpdate();
            } catch(SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            try(Connection con = DriverManager.getConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
                PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, taskName);
                preparedStatement.setString(2, description.toString());
                preparedStatement.setString(3, "NOT-STARTED");
                preparedStatement.setString(4, priority.toString());
                preparedStatement.setInt(5, userIDInteger);
                preparedStatement.setInt(6, projectID);
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

    /**
     * Gets the project ID based on the project name.
     *
     * @param projectName The name of the project.
     * @return The ID of the project, or 0 if not found or an error occurs.
     */
    private int getProjectID(Object projectName) {
        try {
            String projectID = null;
            String projectNameString = projectName.toString();
            connection = new SQLConnection(DatabaseConfig.getProperties("url"),DatabaseConfig.getProperties("username"),DatabaseConfig.getProperties("password"));
            String query = "SELECT * FROM project WHERE projectName = ?";
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, projectNameString);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                projectID = resultSet.getString("ProjectID");
                System.out.println("Project ID: " + projectID);
                return Integer.parseInt(projectID);
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
