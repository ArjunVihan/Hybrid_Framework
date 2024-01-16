package browser.Factory;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import dataProvider.ConfigReader;

public class BrowserFactory {

	
	public static WebDriver driver;
	public static WebDriver getBrowser(String browserName, String URL) {
		

		Reporter.log(browserName, true);
		if (browserName.equalsIgnoreCase("chrome") || browserName.contains("google chrome")
				|| browserName.contains("gc")) {
			driver = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("Firefox") || browserName.contains("mozilla")
				|| browserName.contains("gecko")) {
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("edge") || browserName.contains("IE")
				|| browserName.contains("microsoft edge")) {
			driver = new EdgeDriver();
		} else
			System.out.println("LOG:INFO - Sorry We Do Not Support Browser " + browserName);

		driver.manage().window().maximize();
		driver.get(URL);

		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.readConfig("pageLoadTime"))));
		driver.manage().timeouts()
				.scriptTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.readConfig("scriptTime"))));
		System.out.println("LOG:INFO - Application Started");

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.readConfig("implicitWait"))));
		return driver;
	}

	
public static WebDriver getDriver() {
		
		return driver;
	}
	
	public static void closeBrowser(WebDriver driver) {
		driver.quit();
	}
	
	
	
}
