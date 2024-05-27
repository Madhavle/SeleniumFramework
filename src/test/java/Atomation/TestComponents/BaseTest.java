package Atomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Automation.Selenium.pageObject.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage login;
	public String userName;
	public String password;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Automation\\resources\\GlobalData.properties");

		prop.load(fis);
		
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("Browser");
		
		userName = prop.getProperty("userName");
		password = prop.getProperty("password");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "F:\\support\\chromedriver.exe");
			// Instantiate a ChromeDriver class.
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("headless");
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

		File file = new File(filepath);

		String jsonContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchAppliction() throws IOException {
		driver = initializeDriver();
		login = new LoginPage(driver);
		login.goTo();
		// login.loginMethod(userName,password);
		return login;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

}
