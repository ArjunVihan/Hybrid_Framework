package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

public class ExcelReader {

	public static Object[][] readData(String excelFilepath, String sheetname) {

		XSSFWorkbook sheetReaderWorkbook = null;
		try {
			sheetReaderWorkbook = new XSSFWorkbook(new FileInputStream(new File(excelFilepath)));
		} catch (FileNotFoundException e) {
			Reporter.log("LOG:FAIL - File Not Present " + e.getMessage(), true);
			e.printStackTrace();
		} catch (IOException e) {
			Reporter.log("LOG:INFO - Could Not Load the File " + e.getMessage(), true);
		}

		XSSFSheet activeSheet = sheetReaderWorkbook.getSheet(sheetname); // Reading Sheet frm parameter

		// int row = activeSheet.getPhysicalNumberOfRows();
		int row = activeSheet.getLastRowNum();

		int column = activeSheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] signInDetails = new Object[row][column];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				XSSFCell cellValue = activeSheet.getRow(i + 1).getCell(j);

				if (cellValue.getCellType() == CellType.STRING) {
					signInDetails[i][j] = String.valueOf(cellValue);
					// Reporter.log("The Cell Value is : " + cellValue.getStringCellValue(), true);
				} else if (cellValue.getCellType() == CellType.NUMERIC) {
					// Reporter.log("The Cell Value is : " + cellValue.getRawValue(), true);
					signInDetails[i][j] = String.valueOf(cellValue);
				} else if (cellValue.getCellType() == CellType.BOOLEAN) {
					// Reporter.log("The Cell Value is : " + cellValue.getRawValue(), true);
					signInDetails[i][j] = String.valueOf(cellValue);// Testing
				}
			}

		}
		try {
			sheetReaderWorkbook.close();
		} catch (IOException e) {
			 Reporter.log("LOG:INFO - Unable to Close the File "+e.getMessage(),true);
		}
		return signInDetails;
	}

}
