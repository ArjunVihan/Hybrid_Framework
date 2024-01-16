package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import learnautomation.helper.UtilityHelper;



public class HomePage {

WebDriver driver;
	
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private By clickOnManageBy = By.xpath("//span[normalize-space()='Manage']");
	
	private By menuIcon=By.xpath("//img[@alt='menu']");
	
	private By signOut=By.xpath("//button[@class='nav-menu-item']");
	
	
	public boolean isManageDisplayed()
	{
		 boolean status=UtilityHelper.waitForElement(driver, clickOnManageBy).isDisplayed();
	 
	    return status;
	}
	
	
	
	public void signOutFromApplication()
	{
		UtilityHelper.waitForElement(driver, menuIcon).click();
	
		UtilityHelper.waitForElement(driver, signOut).click();

	}
	
	
}
