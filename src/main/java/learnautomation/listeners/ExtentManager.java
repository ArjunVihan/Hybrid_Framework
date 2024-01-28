package learnautomation.listeners;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import learnautomation.helper.*;

public class ExtentManager 
{
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() 
	{
    	if (extent == null)
    		createExtentReport();
    	
        return extent;
    }
    
    public static ExtentReports createExtentReport() 
    {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(".//Reports//Automation_"+UtilityHelper.getDatehhMM()+".html");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Learn Automation Login Report");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        return extent;
    }

}
