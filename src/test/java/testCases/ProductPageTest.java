package testCases;

import org.testng.annotations.Test;
import baseTest.TestBase;
import pageObjects.ProductPage;

public class ProductPageTest extends TestBase {

	@Test
	public void selectProduct() {
		ProductPage productPage = new ProductPage(driver);
		productPage.verifyPageTitle("text", "Products");
		productPage.selectProduct("Jordan 6 Rings");
		productPage.addSelectedProductToCart();
	}

}