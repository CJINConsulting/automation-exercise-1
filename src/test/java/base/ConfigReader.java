package base;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();
    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) properties.load(input);
            else throw new RuntimeException("config.properties not found");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config: " + e.getMessage());
        }
    }
    public static String get(String key) { return properties.getProperty(key); }
}