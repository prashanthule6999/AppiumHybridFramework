package utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseTest.TestBase;

public class TestNGListener extends TestBase implements ITestListener {

	ExtentReports extent = ExtentReportListener.getReporterObject();
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
		System.out.println((result.getMethod().getMethodName() + " passed!"));
	}

	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		try {
			test.addScreenCaptureFromPath(Utilities.getScreenshotPath(result.getMethod().getMethodName()),
					result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		System.out.println("Test Suite started!");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("Test Suite ended!"));
		extent.flush();
	}
}