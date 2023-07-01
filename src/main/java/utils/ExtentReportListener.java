package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseTest.TestBase;

public class ExtentReportListener extends TestBase {

	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;

	private static final String OUTPUT_FOLDER = "./reports/";
	//private static final String FILE_NAME = "ExtentReport_" + Utilities.getCurrentTime() + ".html";

	public static ExtentReports getReporterObject() {

		Path path = Paths.get(OUTPUT_FOLDER);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}

		reporter = new ExtentSparkReporter(OUTPUT_FOLDER);

//		reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		//reporter.config().setDocumentTitle(prop.getProperty("documentTitle"));
		//reporter.config().setReportName(prop.getProperty("reportName"));
		reporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		//extent.setSystemInfo("Tester Name", prop.getProperty("testerName"));
		//extent.setReportUsesManualConfiguration(true);
		return extent;
	}
}