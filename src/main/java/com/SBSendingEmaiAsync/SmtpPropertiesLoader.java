package com.SBSendingEmaiAsync;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SmtpPropertiesLoader {

    public Properties loadProperties() throws IOException {
        Properties properties = new Properties();

        try (   InputStream inputStream = getClass().getResourceAsStream("/smtp.properties")) {
            properties.load(inputStream);
        }

        return properties;
    }
}