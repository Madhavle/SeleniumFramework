package Automation.Selenium.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.Selenium.Uitility.AbstarctComponants;

public class ProductCatalog extends AbstarctComponants{
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "card-body")
	List<WebElement> products;
	@FindBy(css = "button:last-of-type")
	WebElement addToCartBtn;
	
	
	
    By productBy = By.className("card-body");
	By tosterMsg = By.cssSelector("#toast-container");
	By spinner = By.cssSelector("body > app-root > app-dashboard > ngx-spinner > div > div.ng-tns-c31-3.la-3x.la-ball-scale-multiple.ng-star-inserted");
	
	
	
	
	public List<WebElement> getProductList() throws InterruptedException {
		waitForVisibility(productBy);
		return products;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(addToCartBtnBy).click();
		waitForVisibility(tosterMsg);
		waitForInvisibility(spinner);
		Thread.sleep(2000);
	}
	
}
