package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseTest.TestBase;
import utils.AndroidActions;

public class FormPage extends TestBase {

	// Page Objects (Object Repository) : Page Factory
	@FindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@FindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;

	@FindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;

	@FindBy(id = "android:id/text1")
	private WebElement selectCountryDropDown;

	@FindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement submitFormBtn;

	// Page Constructor : Initializing the Page Objects
	public FormPage() {
		PageFactory.initElements(driver, this);
	}

	// Page Actions
	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}

	public void setGender(String gender) {
		if (gender.equalsIgnoreCase("Male"))
			maleOption.click();
		else
			femaleOption.click();
	}

	public void setCountry(String countryName) {
		selectCountryDropDown.click();
		AndroidActions.scrollToElementAndClickAction(countryName);
	}

	public void submitForm() {
		submitFormBtn.click();
	}
}