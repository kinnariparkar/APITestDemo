package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Base class to setup test configurations
 */
public class TestSetup {
	public String url;
	String info;
	public Logger logger;
	public Properties prop = new Properties();
	public static Response response;

	/**
	 * This method loads configuration from config folder to load API base URL and
	 * logging properties
	 */
	@BeforeClass
	public void setup() {
		try {

			String rootPath = System.getProperty("user.dir") + "/src/test/resources/config/";

			PropertyConfigurator.configure(rootPath + "log4j.properties");
			logger = Logger.getLogger(TestSetup.class);// added Logger
			logger.setLevel(Level.DEBUG);
			prop.load(new FileInputStream(new File(rootPath + "config.properties")));

			url = prop.getProperty("url");
			info = prop.getProperty("info");
			logger.info("===================================");
			logger.info("Info --- " + info);
			logger.info("===================================");

			RestAssured.baseURI = url;

		} catch (FileNotFoundException f) {
			logger.error("Error: " + f);
		} catch (IOException io) {
			logger.error("Error: " + io);
		}
	}

}
