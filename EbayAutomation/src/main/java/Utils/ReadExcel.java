package Utils;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel extends MyUtility {

	//Element Declration

	FileInputStream is;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	String our_value;


	/*
	 * Description: Reusable method for reading the input data of our test based upon the relative test case name
	 * Created By: Ashish Aswal 
	 * Attributes: input - the test case name
	 */
	
	public String readData(String input) 
	{
		Properties prop = new Properties();
		prop = loadPropertyFile(System.getProperty("user.dir") + "/src/main/Resources//PropertyFiles/config.properties");
		int row_num, cell_num;
		String sheetName = "Sheet1";
		
		try 
		{
			is = new FileInputStream(System.getProperty("user.dir") + prop.getProperty("TestDataPath"));
			wb = new XSSFWorkbook(is);
			sheet = wb.getSheet(sheetName);

			for (Row row : sheet) {
				for (Cell cell: row) {
					if (cell.getRichStringCellValue().getString().equals(input)){
						cell_num = cell.getColumnIndex();
						row_num = cell.getRowIndex();

						our_value =  sheet.getRow(row_num).getCell(cell_num + 1).getStringCellValue();
					}
				}
			}



		}catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
		return our_value;
	}



}
