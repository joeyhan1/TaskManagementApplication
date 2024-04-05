import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A class to get the database configs.
 */
public class DatabaseConfig {
    private static final String CONFIG_FILE = "db.properties";

    /**
     * Retrieves the specified property from the configuration file.
     *
     * @param type The type of property to retrieve ("url", "username", or "password").
     * @return The value of the specified property, or {@code null} if the property is not found.
     */
    public static String getProperties(String type) {
        Properties props = new Properties();
        try(FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
            switch(type) {
                case "url":
                    return props.getProperty("db.url");
                case "username":
                    return props.getProperty("db.username");
                case "password":
                    return props.getProperty("db.password");
                default:
                    System.out.println("Please pick between url, username or password!");
            }
            return null;
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
