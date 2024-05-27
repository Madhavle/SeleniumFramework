package Automation.Selenium.pageObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.Selenium.Uitility.AbstarctComponants;

public class LoginPage extends AbstarctComponants {
	
	WebDriver driver;
	public ProductCatalog produtcat;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail")
	WebElement username;
	@FindBy(id = "userPassword")
	WebElement password;
	@FindBy(id = "login")
	WebElement loginButton;
	@FindBy(css = "#toast-container > div > div")
	WebElement errorMsg;
	
	
	public ProductCatalog loginApplication() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Automation\\resources\\GlobalData.properties");

		prop.load(fis);
		String userNameFromProprties = prop.getProperty("userName");
		String passwordFromProprties = prop.getProperty("password");
		
		username.sendKeys(userNameFromProprties);
		password.sendKeys(passwordFromProprties);
		loginButton.click();
		produtcat = new ProductCatalog(driver);
		return produtcat;
		
	}
	
	public String getErrorMsg() {
		waitForAppper(errorMsg);
		String msg = errorMsg.getText();
		return msg;
	}
	
	public ProductCatalog loginApplication(String userName,String passWord) throws IOException {
		
		username.sendKeys(userName);
		password.sendKeys(passWord);
		loginButton.click();
		produtcat = new ProductCatalog(driver);
		return produtcat;
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		
	}


}
