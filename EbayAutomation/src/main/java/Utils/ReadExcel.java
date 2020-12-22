package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.Assert;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel extends MyUtility {

	//Element Declration

	FileInputStream fs;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	String our_value;


	/*
	 * Description: Reusable method for opening the excel file
	 * Created By: Ashish Aswal 
	 */
	
	public String OpenExcel() 
	{
		Properties prop = new Properties();
		prop = loadPropertyFile(System.getProperty("user.dir") + "/src/main/Resources//PropertyFiles/config.properties");
		String sheetName = "Sheet1";
		
		try 
		{
			fs = new FileInputStream(System.getProperty("user.dir") + prop.getProperty("TestDataPath"));
			wb = new XSSFWorkbook(fs);
			sheet = wb.getSheet(sheetName);


		}catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
		return our_value;
	}

	
	
	/*
	 * Description: Reusable method for reading the input data of our test based upon the relative test case name
	 * Created By: Ashish Aswal 
	 * Attributes: input - the test case name
	 */
	
	public String readData(String input) {
		int row_num, cell_num;
		
		OpenExcel();
		
		for (Row row : sheet) {
			for (Cell cell: row) {
				if (cell.getRichStringCellValue().getString().equals(input)){
					cell_num = cell.getColumnIndex();
					row_num = cell.getRowIndex();

					our_value =  sheet.getRow(row_num).getCell(cell_num + 1).getStringCellValue();
				}
			}
		}
		return our_value;
	}

	
	/*
	 * Description: Reusable function to close excel
	 * Created By: Ashish Aswal
	 */
	public void closeExcel() {
		try {
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
