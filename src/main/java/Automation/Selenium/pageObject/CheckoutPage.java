package Automation.Selenium.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Automation.Selenium.Uitility.AbstarctComponants;

public class CheckoutPage extends AbstarctComponants {
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(css = ".payment__title")
	WebElement paymentLabel;
	
	@FindBy(css = "body > app-root > app-order > section > div > div > div.col-md-7 > div > div > div.payment__info > div.payment__cc > form > div > div:nth-child(2) > div:nth-child(1) > select:nth-child(2)")
	WebElement monthDropDown;
	
	@FindBy(css = ".input.ddl:last-of-type")
	WebElement yearDropDown;
	
	@FindBy(xpath = "//div[@class='payment__cc']//div[2]//input[1]")
	public WebElement cvvBox;
	
	@FindBy(xpath = "(//input[@type='text'])[3]")
	public WebElement nameOnCard;
	
	@FindBy(xpath = "//a[contains(text(),'Place Order')]")
	public WebElement placeOrderBtn;
	
	
	By paymentLabelBy = By.cssSelector(".payment__title");
	By countryBy = By.cssSelector(".form-group > input.txt.text-validated");
	By countryList = By.cssSelector(".ta-results.list-group.ng-star-inserted > button > span");
    By monthBy = By.xpath("//select[@class='input ddl'][1]");
    By placeOrderBtnBy = By.xpath("//a[contains(text(),'Place Order')]");
	
	public void selectCountry(String country) {
		waitForVisibility(paymentLabelBy);
		waitForVisibility(countryBy);
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(countryBy),country).build().perform();
	    waitForVisibility(countryList);
		List<WebElement> searchedList = driver.findElements(countryList);
		WebElement exactMatch = searchedList.stream().filter(s -> s.getText().equalsIgnoreCase(country)).findFirst()
				.orElse(null);
		exactMatch.click();
	}
	
	public void selectMonthAndYear(String month,String year) {
		waitForVisibility(monthBy);
		Select selectMonth = new Select(monthDropDown);
		selectMonth.selectByVisibleText(month);

		Select select1 = new Select(yearDropDown);
		select1.selectByVisibleText(year);
		
	}
	
	public ConfirmationPage placeOrder() {
		waitForVisibility(placeOrderBtnBy);
		placeOrderBtn.click();
		return new ConfirmationPage(driver);
	}
	
	

}
