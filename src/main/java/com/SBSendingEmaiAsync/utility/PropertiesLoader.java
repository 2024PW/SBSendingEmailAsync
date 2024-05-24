package com.SBSendingEmaiAsync.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadProperties(String file) throws IOException {
        Properties properties = new Properties();

        try (InputStream inputStream = PropertiesLoader.class.getResourceAsStream(file)) {
            properties.load(inputStream);
        }

        return properties;
    }
}