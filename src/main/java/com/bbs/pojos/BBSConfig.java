package com.bbs.pojos;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.bbs.utilities.ConfigReader;

public class BBSConfig {

	private Map<String, String> configData = new HashMap<>();
	
	private ConfigReader configReader = new ConfigReader("bbsconfig.properties");
	
	// Grab all properties from config.properties file.
	// This file needs to be in the root directory
	public BBSConfig() {
		Set<String> properties=configReader.getAllProperties();
		for (String property:properties) {
			configData.put(property, configReader.getProperty(property));
		}
	}
	
	public String getValue(String key) {
		return configData.get(key);
	}
	
}
