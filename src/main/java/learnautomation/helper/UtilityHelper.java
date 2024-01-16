package learnautomation.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import dataProvider.ConfigReader;

import org.openqa.selenium.JavascriptExecutor;
import learnautomation.base.BaseClass;
import learnautomation.listeners.ExtentReportListener;
import net.bytebuddy.utility.privilege.GetMethodAction;

public class UtilityHelper extends BaseClass {
	static WebDriver driver;

	public static boolean takescreenshotAsFile(WebDriver driver, String headername) {
		boolean status;
		TakesScreenshot tc = (TakesScreenshot) driver;
		File srcFile = tc.getScreenshotAs(OutputType.FILE);

		File destFile = new File(
				System.getProperty("user.dir") + "\\Screenshots\\" + headername + getDatehhMM() + ".png");
		try {
			FileHandler.copy(srcFile, destFile);
			status = true;

		} catch (IOException e) {

			System.out.println(e.getMessage());
			status = false;
		}
		return status;
	}

	public static String captureScreenshotAsByte(WebDriver driver) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		String screenshotasBase64 = ts.getScreenshotAs(OutputType.BASE64);

		return screenshotasBase64;
	}

	public static String selectOptionValue(By locator, String Searchelement) {
		List<WebElement> dropdownElement = driver.findElements(locator);
		// System.out.println(((WebElement) dropdownElement).getText());
		for (WebElement dropdown : dropdownElement) {
			if (dropdown.getText().contains(Searchelement)) {
				dropdown.click();
				break;
			}
		}
		return Searchelement;
	}

	public static void waitMethod(int timeSeconds) {
		try {
			Thread.sleep(timeSeconds);
		} catch (InterruptedException e) {
			Reporter.log("LOG:ERROR - Thread Interrupted " + e.getMessage(), true);
		}
	}

	public static String getDatehhMM() {
		Date date = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		String getdateString = formatDate.format(date);
		// System.out.println(getdateString);
		return getdateString;
	}

	public static void clickElement(WebDriver driver, By locator) {
		driver.findElement(locator).click();
	}

 
	
	
	public static void highLighter(WebDriver driver,WebElement LocatorEle) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 2px red')",LocatorEle);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			Reporter.log(e.getMessage(),true);
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px black')",LocatorEle);
		
	}
	
	

	public static void UploadFile(By Element, String uploadFilePathBy) {
		driver.findElement(Element).sendKeys(uploadFilePathBy);
	}

	public static WebElement waitForElement(WebDriver driver, By Elementlocator) {
		WebDriverWait waitForElement = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement element = waitForElement.until(ExpectedConditions.visibilityOf(driver.findElement(Elementlocator)));

		String highLight = ConfigReader.readConfig("highlightElement");

		if (highLight.equalsIgnoreCase("true")) {
			highLighter(driver, element);
		}

		return element;
	}

	
}
