package Automation.SeleniumFramework.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Atomation.TestComponents.BaseTest;
import Automation.Selenium.pageObject.CartPage;
import Automation.Selenium.pageObject.CheckoutPage;
import Automation.Selenium.pageObject.ConfirmationPage;
import Automation.Selenium.pageObject.OrderPage;
import Automation.Selenium.pageObject.ProductCatalog;

public class SubmitOrderTest extends BaseTest {

	String ProductName = "ADIDAS ORIGINAL";
	String Country = "India";

	@Test
	public void submitOrderTest() throws IOException, InterruptedException {

		ProductCatalog productCat = login.loginApplication();
		productCat.addProductToCart(ProductName);
		CartPage cart = productCat.goToCart();
		cart.checkOutTheProduct(ProductName);
		CheckoutPage checkOutPage = cart.goToCheckout();
		checkOutPage.selectCountry(Country);
		ConfirmationPage confirmation = checkOutPage.placeOrder();
		String succMSg = confirmation.getConfirmationMessage();
		Assert.assertEquals(succMSg, "THANKYOU FOR THE ORDER.");

	}

	@Test(dependsOnMethods = { "submitOrderTest" })
	public void orderHistoryTest() throws IOException {
		ProductCatalog productCat = login.loginApplication();
		OrderPage order = productCat.goToOrders();
		Boolean productPresentInOrder = order.verifyOrders(ProductName);
		Assert.assertTrue(productPresentInOrder);

	}

}
