package com.jwt.jsonwebtokenproject.config;

import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.util.Properties;

public class CustomPropertySource extends PropertiesPropertySource {

    private static final String PROPERTY_FILE_PATH = "custom.properties";

    private Properties properties;
    public CustomPropertySource() throws IOException {

        super("customPropertySource",loadProperties());
        this.properties = loadProperties();
    }

    private static Properties loadProperties() throws IOException {

        Properties properties = new Properties();
        System.out.println("loading properties.");

        FileSystemResource resource = new FileSystemResource(PROPERTY_FILE_PATH);
        System.out.println(resource+" this is resource");

        if (resource.exists()) {
            System.out.println(resource.getInputStream().toString()+" properties");
            properties.load(resource.getInputStream());
        } else {
            throw new IOException("Custom properties file not found: " + PROPERTY_FILE_PATH);
        }
        return properties;
    }

//    public void reloadProperties() throws IOException {
//        Properties newProperties = loadProperties();
//        if (!newProperties.equals(properties)) {
//            this.properties = newProperties;
//        }
//    }

    @Override
    public Object getProperty(String name) {
        return properties.getProperty(name);
    }

}