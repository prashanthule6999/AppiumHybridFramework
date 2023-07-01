package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import baseTest.TestBase;
import io.appium.java_client.AppiumBy;

public class AndroidActions extends TestBase {

	public static void longPressAction(WebElement webEle, int duration) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) webEle).getId(), "duration", duration));
	}

	public static void scrollToEndAction() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (canScrollMore);
	}

	public static void scrollToElementAction(String elementName) {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
						+ elementName + "\").instance(0))"));
	}

	public static void scrollToElementAndClickAction(String elementName) {
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
						+ elementName + "\").instance(0))"))
				.click();
	}

	public static void swipeAction(WebElement webEle, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) webEle).getId(), "direction", direction, "percent", 0.75));
	}

	public static void dragDropActions(WebElement webEle, int x, int y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) webEle).getId(), "endX", x, "endY", y));
	}
}