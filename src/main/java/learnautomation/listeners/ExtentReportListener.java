package learnautomation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import learnautomation.helper.UtilityHelper;

public class ExtentReportListener implements ITestListener {

	ExtentReports extentReports = ExtentManager.getInstance();
	ExtentTest extentTest;

	public void onTestSuccess(ITestResult result) {

		if (dataProvider.ConfigReader.readConfig("screenshotOnSuccess").equalsIgnoreCase("true")) {
			extentTest.pass("Test Passed", MediaEntityBuilder
					.createScreenCaptureFromBase64String(UtilityHelper.captureScreenshotAsByte(browser.Factory.BrowserFactory.getDriver()))
					.build());

		} else {
			extentTest.pass("Test Passed");
		}

	}

	
	public void onTestFailure(ITestResult result) {

		if (dataProvider.ConfigReader.readConfig("screenshotOnFailure").equalsIgnoreCase("true")) {
			extentTest.fail("Test Failed " + result.getThrowable().getMessage(), MediaEntityBuilder
					.createScreenCaptureFromBase64String(UtilityHelper.captureScreenshotAsByte(browser.Factory.BrowserFactory.getDriver()))
					.build());

		} else {
			extentTest.fail("Test Case Failed " + result.getThrowable().getMessage());

		}

	}

	public void onTestSkipped(ITestResult result)

	{
		if (dataProvider.ConfigReader.readConfig("screenshotOnSkip").equalsIgnoreCase("true")) {
			extentTest.skip("Test CAse Skipped " + result.getThrowable().getMessage(), MediaEntityBuilder
					.createScreenCaptureFromBase64String(UtilityHelper.captureScreenshotAsByte(browser.Factory.BrowserFactory.getDriver()))
					.build());

		} else {
			extentTest.skip("Test Case Skipped " + result.getThrowable().getMessage());

		}

	}

	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getMethod().getMethodName());

	}

	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}
