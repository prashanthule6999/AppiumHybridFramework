package testCases;

import org.testng.annotations.Test;

import baseTest.TestBase;
import pageObjects.CartPage;

public class CartPageTest extends TestBase {

	@Test
	public void verifyAddedProduct() throws Exception {
		CartPage cartPage = new CartPage(driver);
		cartPage.verifyPageTitle("text", "Products");
		cartPage.verifyAddedProductName("Jordan 6 Rings");
		cartPage.verifyAddedProductPrice();
		cartPage.clickTermsCheckBox();
		cartPage.verifyText("Terms Of Conditions");
		cartPage.clickProceed();
	}

}