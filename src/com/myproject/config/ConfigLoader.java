package com.myproject.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads configuration from config.properties file.
 */
public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getResourceAsStream("./config.properties")) {
            if (input == null) {
                System.err.println("Unable to find config.properties");
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getConfig(String key) {
        return properties.getProperty(key);
    }
}
