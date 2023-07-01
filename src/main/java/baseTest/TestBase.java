package baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import utils.Utilities;

public class TestBase {

	public static AndroidDriver driver;
	public static AppiumDriverLocalService service;
	public static Properties prop;

	@BeforeClass
	public void setUp() {
		// Reading .properties file
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("./src/main/java/resources/config.properties");

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Automatically starting appium server
		service = new AppiumServiceBuilder().withAppiumJS(new File(prop.getProperty("appiumJSPath")))
				.withIPAddress(prop.getProperty("ipAddress")).usingPort(Integer.parseInt(prop.getProperty("port")))
				.build();
		service.start();

		// Setting application
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("androidDeviceName"));
		options.setChromedriverExecutable(prop.getProperty("chromeDriverExecutablePath"));
		options.setApp(prop.getProperty("appPath"));

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
	}

	@AfterClass
	public void tearDown() {
		// Automatically stopping appium server
		driver.quit();
		service.stop();
	}

	/*
	 * @BeforeMethod(alwaysRun = true) public void preSetup() { Resetting
	 * application screen Utilities.startAppActivity(prop.getProperty("appPackage"),
	 * prop.getProperty("appActivity")); }
	 */
}