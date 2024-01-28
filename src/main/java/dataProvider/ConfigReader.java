package dataProvider;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.testng.Reporter;

public class ConfigReader {

	static String valueString;

	public static String readConfig(String key) {

		Properties confProperties = new Properties();
		try {
			confProperties.load(new FileInputStream(new File(".\\Config\\Config.Properties")));

		} catch (IOException e) {

			Reporter.log("LOG:INFO - Unable To Load The Config File " + e.getMessage(), true);
		}
		valueString = (String) confProperties.get(key);
		return valueString;

	}

}
