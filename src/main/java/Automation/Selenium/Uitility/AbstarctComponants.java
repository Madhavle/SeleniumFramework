package Automation.Selenium.Uitility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automation.Selenium.pageObject.CartPage;
import Automation.Selenium.pageObject.OrderPage;

public class AbstarctComponants {
	WebDriver driver;

	public AbstarctComponants(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;
	public @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement ordersBtn;
	
	public By addToCartBtnBy = By.cssSelector("button:last-of-type");
	By ordersBtnBy = By.xpath("//button[@routerlink='/dashboard/myorders']");
	
	
	public OrderPage goToOrders() {
		waitForVisibility(ordersBtnBy);
		ordersBtn.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
		
	}
	
	public CartPage goToCart() {
		cartBtn.click();
		CartPage cartpage = new CartPage(driver);
		cartBtn.click();
		return cartpage;
	}

	public void waitForVisibility(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForAppper(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForInvisibility(By findBy) {
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait1.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
}
