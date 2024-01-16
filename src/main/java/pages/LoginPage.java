package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import learnautomation.helper.UtilityHelper;

public class LoginPage {

	protected WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	private By userNameBy = By.name("email1");
	private By pwdBy = By.name("password1");
	private By submitButtonBy = By.className("submit-btn");
	private By welcomeMsg = By.xpath("//h4[@class='welcomeMessage']");
	private String welcomeMessage;

	public void login(String uName, String pwd) {

		WebElement usernameElement = driver.findElement(userNameBy);
		UtilityHelper.highLighter(driver, usernameElement);
		usernameElement.sendKeys(uName);

		WebElement passwordElement = driver.findElement(pwdBy);
		UtilityHelper.highLighter(driver, passwordElement);
		passwordElement.sendKeys(pwd);

		driver.findElement(submitButtonBy).click();

	}

	public boolean verifyLogin() {

		if (UtilityHelper.waitForElement(driver, welcomeMsg).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

}
