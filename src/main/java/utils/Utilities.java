package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import baseTest.TestBase;

public class Utilities extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 100;
	public static long EXPLICIT_WAIT = 100;
	public static String TESTDATA_SHEET_PATH = prop.getProperty("testDataSheetPath");
	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));

	public static double stringToDouble(String amount) {
		double num = Double.parseDouble(amount.substring(1));
		return num;
	}

	public static void verifyTextByAttributeContains(WebElement webEle, String attribute, String value) {
		wait.until(ExpectedConditions.attributeContains(webEle, attribute, value));
		String actualText = webEle.getText();
		String expectedText = value;
		Assert.assertEquals(actualText, expectedText);
	}

	public static List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {

		// Convert json file content to json string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		// Convert json string to hashMap
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<HashMap<String, String>>> typeRef = new TypeReference<List<HashMap<String, String>>>() {
		};

		List<HashMap<String, String>> data = mapper.readValue(jsonContent, typeRef);
		return data;
	}

	public static String getScreenshotPath(String testCaseName) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/reports/screenshot" + testCaseName + ""
				+ getCurrentTime() + ".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}

	public static String getCurrentTime() {
		DateFormat customDate = new SimpleDateFormat("MM_dd_yyyy HH_mm_ss");
		Date currentDate = new Date();
		return customDate.format(currentDate);
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/reports/screenshot" + System.currentTimeMillis() + ".png"));
	}

	public static void sendkeys(WebElement webEle, String value) {
		webEle.sendKeys(value);
	}

	public static void click(WebElement webEle) {
		webEle.click();
	}
}