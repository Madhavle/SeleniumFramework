package Automation.Selenium.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.Selenium.Uitility.AbstarctComponants;

public class OrderPage extends AbstarctComponants {

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

		}

		@FindBy(css = "tr.ng-star-inserted > td:nth-child(3)")
		List<WebElement> orderItems;

		By orderFirstItemBy = By.cssSelector("tr:first-child > td:nth-child(3)");
		By cartProductName = By.xpath("//div[@class='cartSection']/h3");

		
		public Boolean verifyOrders(String productName) {
			waitForVisibility(orderFirstItemBy);
			Boolean itemPresent = orderItems.stream().anyMatch(item -> item.getText().equals(productName));
			return itemPresent;
		}
}