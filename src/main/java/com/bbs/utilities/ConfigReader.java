package com.bbs.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class ConfigReader {

	private Properties properties;

    public ConfigReader(String filePath) {
        properties = new Properties();
        
        String currentDir = Paths.get("").toAbsolutePath().normalize().toString();
        System.out.println("Current working directory: " + currentDir);
        filePath = currentDir+"\\"+filePath;
        
        try (InputStream input =new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public Set<String> getAllProperties() {
    	return properties.keySet().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
    }
	
}
