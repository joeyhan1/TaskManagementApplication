import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class DatabaseConfig {
    private static final String CONFIG_FILE = "db.properties";

    /**
     * Method to get the information from the database properties file
     *
     * @param type Type of database property information
     * @return
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
                    System.out.println("Please pick between url, username and password!");
            }
            return null;
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
