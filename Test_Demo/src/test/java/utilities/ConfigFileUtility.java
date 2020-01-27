package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileUtility {
	public File src;
	public FileInputStream fis;
	public static Properties prop;

	// Method for get the config property file
	public void loadPropertyFile() throws IOException {
		String filePath = "./resources/testData/ConfigFile.properties";
		//src = new File(System.getProperty("user.dir") + File.separator+"resources"+ File.separator + "testData" + File.separator + configFileName);
		src = new File(filePath);
		fis = new FileInputStream(src);
		prop = new Properties();
		prop.load(fis);
	}

	// Method for Getting the values from config property file
	public String getProperty(String propKey) {
		return prop.getProperty(propKey);
	}
}
