/**
 * A singleton class representing the current user in the task management application.
 * It ensures that there is only one instance of the CurrentUser throughout the application.
 */
public class CurrentUser {
    private static CurrentUser instance;
    private String username;

    /**
     * Private constructor to prevent instantiation outside the class
     */
    private CurrentUser() {
        this.username = null;
    }

    /**
     * Returns the single instance of the CurrentUser class.
     * If the instance does not exist, it creates one.
     *
     * @return The instance of CurrentUser
     */
    public static CurrentUser getInstance() {
        if(instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    /**
     * Sets the username of the current user.
     *
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the username of the current user.
     *
     * @return The username of the current user
     */
    public String getUsername() {
        return this.username;
    }
}
