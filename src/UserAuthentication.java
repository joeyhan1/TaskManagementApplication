/**
 * Defines an interface for user authentication.
 */
public interface UserAuthentication {
    /**
     * Authenticates a user with the given username and password.
     *
     * @param username The username of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return {@code true} if the authentication is successful, {@code false} otherwise.
     */
    public boolean authenticate(String username, String password);
}
