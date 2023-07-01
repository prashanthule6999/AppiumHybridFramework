package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseTest.TestBase;
import io.appium.java_client.android.AndroidDriver;
import utils.AndroidActions;
import utils.Utilities;

public class CartPage extends TestBase {

	List<WebElement> listOfCartProductsName;

	// Page Objects (Object Repository)
	@FindBy(xpath = "//android.widget.TextView[@text='Cart']")
	private WebElement cartPageTitle;

	@FindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> cartProductsName;

	@FindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> cartProductPrice;

	@FindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmountLbl;

	@FindBy(className = "android.widget.CheckBox")
	private WebElement termsCheckBox;

	@FindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement termsBtn;

	@FindBy(id = "com.androidsample.generalstore:id/alertTitle")
	private WebElement termsAlertTitle;

	@FindBy(xpath = "//android.widget.Button[@text='CLOSE']")
	private WebElement closeBtn;

	// Page Constructor
	public CartPage(AndroidDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Page Actions
	public void verifyPageTitle(String webEleAttribute, String webEleValue) {
		Utilities.verifyTextByAttributeContains(cartPageTitle, webEleAttribute, webEleValue);
	}

	public void verifyAddedProductName(String productName) {

		listOfCartProductsName = cartProductsName;

		int cartProductsCount = listOfCartProductsName.size();
		System.out.println("Cart : Total Product  :- " + cartProductsCount);

		String actualProductName = listOfCartProductsName.get(1).getText();
		Assert.assertEquals(actualProductName, productName);
	}

	public void verifyAddedProductPrice() {

		int cartProductsCount = listOfCartProductsName.size();
		double sum = 0;
		List<WebElement> listOfCartProductPrice = cartProductPrice;
		for (int i = 0; i < cartProductsCount; i++) {
			String price = listOfCartProductPrice.get(i).getText();
			double f = Utilities.stringToDouble(price);
			sum = sum + f;
		}
		System.out.println("Total Price of Items Added to Cart : " + sum);

		String actualTotalCartPrice = totalAmountLbl.getText().trim();
		Assert.assertEquals(Utilities.stringToDouble(actualTotalCartPrice), sum);
	}

	public void clickTermsCheckBox() {
		termsCheckBox.click();
		AndroidActions.longPressAction(termsBtn, 2000);
	}

	public void verifyText(String expectedText) {
		String actualText = termsAlertTitle.getText();
		Assert.assertEquals(actualText, expectedText);
		closeBtn.click();
	}

	public void clickProceed() throws Exception {
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(20000);
	}
}