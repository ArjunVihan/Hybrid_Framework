package dataProvider;

import org.testng.annotations.DataProvider;

public class DataProviders {
	static String ExcelfilePath = ".\\ReadExcelData\\ReadDataDDT.xlsx";
	static String loginsheetname = "LoginDetails";
	
	@DataProvider(name="readLoginData")
	public static Object[][] ExcelReader()
	{
		Object [][] loginDataObjects=ExcelReader.readData(ExcelfilePath, loginsheetname);
		return loginDataObjects;
	}
	
}
