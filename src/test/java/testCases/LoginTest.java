package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import dataProvider.DataProviders;
import learnautomation.base.BaseClass;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseClass {

	@Test(dataProvider = "readLoginData", dataProviderClass = DataProviders.class)
	public void validLogin(String uName, String pwd) {
		
		LoginPage loginPage = new LoginPage(driver);

		loginPage.login(uName, pwd);
		
		assertTrue(loginPage.verifyLogin(),"Login Failed");
		
		HomePage homePage = new HomePage(driver);
		
		assertTrue(homePage.isManageDisplayed());
						
		homePage.signOutFromApplication();

	}
	
	

}
