package dev.rooftop.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Helper {

    public static Properties getExternalConfiguration(String filename) {
        Properties properties = new Properties();
        File external = new File(filename);
        try {
            if (external.exists()) {
                properties.load(new FileInputStream(external));
            } else {
                properties.load(
                    Helper.class.getClassLoader().getResourceAsStream("app.properties"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
