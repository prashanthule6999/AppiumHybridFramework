package pageObjects;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseTest.TestBase;
import io.appium.java_client.android.AndroidDriver;
import utils.AndroidActions;
import utils.Utilities;

public class ProductPage extends TestBase {

	// Page Objects (Object Repository)
	@FindBy(xpath = "//android.widget.TextView[@text='Products']")
	private WebElement productPageTitle;

	@FindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement addToCartBtn;

	@FindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> ListOfProducts;

	@FindBy(id = "com.androidsample.generalstore:id/productAddCart")
	private List<WebElement> productAddCartBtn;

	// Page Constructor
	public ProductPage(AndroidDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Page Actions
	public void verifyPageTitle(String webEleAttribute, String webEleValue) {
		Utilities.verifyTextByAttributeContains(productPageTitle, webEleAttribute, webEleValue);
	}

	public void selectProduct(String productName) {
		AndroidActions.scrollToElementAction(productName);
		List<WebElement> listOfProducts = ListOfProducts;

		int productCount = listOfProducts.size();
		System.out.println("Product : Total Product  :- " + productCount);

		for (int i = 0; i < productCount; i++) {
			String product = listOfProducts.get(i).getText();
			if (product.equals(productName)) {
				productAddCartBtn.get(i).click();
				break;
			}
		}
	}

	public void addSelectedProductToCart() {
		addToCartBtn.click();
	}
}