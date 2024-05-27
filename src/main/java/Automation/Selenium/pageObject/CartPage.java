package Automation.Selenium.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.Selenium.Uitility.AbstarctComponants;

public class CartPage extends AbstarctComponants {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "div.cartSection > h3")
	List<WebElement> cartItems;

	By cartItemsBy = By.cssSelector("div.cartSection > h3");
	By cartProductName = By.xpath("//div[@class='cartSection']/h3");

	public void checkOutTheProduct(String productName) {
		waitForVisibility(cartProductName);
		List<WebElement> cartItems1 = driver.findElements(cartItemsBy);
		WebElement myItem = cartItems1.stream().filter(item -> item.getText().equals(productName)).findFirst()
				.orElse(null);
		myItem.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
	}
	
	public String verifyCartProductName(String productName) {
		waitForVisibility(cartProductName);
		List<WebElement> cartItems1 = driver.findElements(cartItemsBy);
		WebElement myItem = cartItems1.stream().filter(item -> item.getText().equals(productName)).findFirst()
				.orElse(null);
		return myItem.getText();
	}

	public CheckoutPage goToCheckout() {

		CheckoutPage checkout = new CheckoutPage(driver);
		return checkout;

	}
}
