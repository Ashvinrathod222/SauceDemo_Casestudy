package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config {
	private static Properties properties;

	public static void loadProperties() {
		try {
			FileInputStream fis = new FileInputStream("src/test/resourcesone/testdata.properties");
			properties = new Properties();
			properties.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load testdata.properties", e);
		}
	}

	public static String get(String key) {
		if (properties == null) {
			loadProperties();
		}
		return properties.getProperty(key);
	}
}
