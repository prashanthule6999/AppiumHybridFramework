package testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.TestBase;
import pageObjects.FormPage;
import utils.Utilities;

public class FormPageTest extends TestBase {

	@Test(dataProvider = "getData")
	public void fillFormPage(HashMap<String, String> input) {
		FormPage formPage = new FormPage();
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountry(input.get("country"));
		formPage.submitForm();
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = Utilities.getJsonData(Utilities.TESTDATA_SHEET_PATH);
		return new Object[][] { { data.get(0) } };
	}

}