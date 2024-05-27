package Automation.SeleniumFramework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Atomation.TestComponents.BaseTest;
import Atomation.TestComponents.Retry;
import Automation.Selenium.pageObject.CartPage;
import Automation.Selenium.pageObject.ProductCatalog;

public class ErrorValidationTest extends BaseTest {

	String file = System.getProperty("user.dir") + "\\src\\test\\java\\data\\loginDetails.json";

	@Test(dataProvider = "getData", groups = { "ErrorHanding" },retryAnalyzer = Retry.class)
	public void LoginErrorValidation(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCat1 = login.loginApplication(input.get("email"), input.get("password"));
		String errorMsg = login.getErrorMsg();
		Assert.assertEquals("Incorrect email or .", errorMsg);
	}

	@Test()
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String ProductName = "ADIDAS ORIGINAL";
		ProductCatalog productCat = login.loginApplication("b@ml.com","Madhav@123");
		productCat.addProductToCart(ProductName);
		CartPage cart = productCat.goToCart();
		String presentProduct = cart.verifyCartProductName(ProductName);
		Assert.assertEquals(presentProduct, ProductName);

	}

	@DataProvider
	public Object getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(file);
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
