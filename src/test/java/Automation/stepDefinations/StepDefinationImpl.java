package Automation.stepDefinations;

import java.io.IOException;

import org.testng.Assert;

import Atomation.TestComponents.BaseTest;
import Automation.Selenium.pageObject.CartPage;
import Automation.Selenium.pageObject.CheckoutPage;
import Automation.Selenium.pageObject.ConfirmationPage;
import Automation.Selenium.pageObject.LoginPage;
import Automation.Selenium.pageObject.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest {

	public LoginPage login;
	public ProductCatalog productCat;
	public CartPage cart;
	public ConfirmationPage confirmation;
	@Given("I landed on Ecommrce page")
	public void I_Landed_On_Ecommrce_Page() throws IOException {
		login=launchAppliction();
	}
	
	@Given("^Logged in with (.+) and password (.+)$")
	public void Logged_In_With_UserNameAndPassword(String userName,String pass) throws IOException {
	  productCat = login.loginApplication(userName,pass);
	}
	

	@When ("^I add product (.+) to cart$")
	public void add_product_to_cart(String productName) throws InterruptedException {
		productCat.addProductToCart(productName);
		cart = productCat.goToCart();
	
	}
	
	@And ("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String pName) {
		cart.checkOutTheProduct(pName);
		CheckoutPage checkOutPage = cart.goToCheckout();
		checkOutPage.selectCountry("INDIA");
		confirmation = checkOutPage.placeOrder();
	}
	
	@Then ("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmationPage(String msg) {
		String succMSg = confirmation.getConfirmationMessage();
		Assert.assertEquals(succMSg, msg);
		driver.quit();
	}
	
	
	@Then ("{string} error message displayed")
	public void error_message_displayed(String expectedErrorMsg) {
		String errorMsg = login.getErrorMsg();
		Assert.assertEquals(expectedErrorMsg, errorMsg);
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
