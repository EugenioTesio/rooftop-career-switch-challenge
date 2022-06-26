package dev.rooftop.core.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.rooftop.core.utils.Helper;
import java.util.Properties;
import org.junit.jupiter.api.Test;

public class AppConfigTest {

    @Test
    public void givenDefaultInstance_whenGetProperties_thenReturnAppPropertiesFileValues() {
        assertNotNull(this.getClass().getClassLoader().getResourceAsStream("app.properties"));
        Properties expectedProperties = Helper.getExternalConfiguration("app.properties");
        AppConfig appConfig = AppConfig.getInstance();
        assertEquals(expectedProperties.getProperty("rooftop-career-switch.url"),
            appConfig.getUrl());
        assertEquals(expectedProperties.getProperty("rooftop-career-switch.user"),
            appConfig.getUserEmail());
    }
}
