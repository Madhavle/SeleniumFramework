package Automation.Selenium.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Automation.Selenium.Uitility.AbstarctComponants;

public class ConfirmationPage extends AbstarctComponants {

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(className = "hero-primary")
	WebElement confirmationMessage;
	
	By confirmationMessageBy = By.className("hero-primary");
	

	
	
	public String getConfirmationMessage() {
	
		waitForVisibility(confirmationMessageBy);
		String succMSg = confirmationMessage.getText();
		return succMSg;
	}

}
