package dev.rooftop.core.config;

import dev.rooftop.core.utils.Helper;
import java.util.Properties;

public class AppConfig {

    private static AppConfig INSTANCE;

    private final Properties properties;

    private AppConfig(String fileName) {
        properties = Helper.getExternalConfiguration(fileName);
    }

    public static AppConfig getInstance() {
        return getInstance("app.properties");
    }

    public static AppConfig getInstance(String fileName) {
        if (INSTANCE == null) {
            INSTANCE = new AppConfig(fileName);
        }
        return INSTANCE;
    }

    public String getUrl() {
        String url = properties.getProperty("rooftop-career-switch.url");
        if (url == null) {
            url = "https://rooftop-career-switch.herokuapp.com";
        }
        return url;
    }

    public String getUserEmail() {
        String email = properties.getProperty("rooftop-career-switch.user");
        if (email == null) {
            email = "usuario@gmail.com";
        }
        return email;
    }
}
