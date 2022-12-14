package com.travel.around.config;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationProperties {
    private final Properties properties;

    public ApplicationProperties() {
        properties = new Properties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.ALL, "IOException occured while loading properties file::::" + e.getMessage());
        }
    }

    public String readProperties(String keyName) {
        return properties.getProperty(keyName, "There is no key in the properties file");
    }
}
