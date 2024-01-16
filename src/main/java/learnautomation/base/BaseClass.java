package learnautomation.base;

import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import browser.Factory.*;
import dataProvider.ConfigReader;

public class BaseClass {

	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		Reporter.log("LOG:INFO- Running Before Class - Setting Up Browser ", true);

		driver = BrowserFactory.getBrowser(ConfigReader.readConfig("browser"), ConfigReader.readConfig("appURL"));
		Reporter.log("LOG:INFO- Browser and Application Up and Running ", true);

	}

	@AfterMethod
	@AfterClass
	public void tearDown() {
		Reporter.log("LOG:INFO- Running After Class - Closing Browser", true);

		BrowserFactory.closeBrowser(driver);
		Reporter.log("LOG:INFO- Browser Closed");
	}
	
	
}
