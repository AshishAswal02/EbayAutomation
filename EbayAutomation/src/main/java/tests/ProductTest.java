package tests;

import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.SearchPage;

import Utils.MyUtility;
import Utils.ReadExcel;
import Utils.ReportGen;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;



public class ProductTest extends MyUtility{

	ReportGen extentReport;
	ReadExcel excel;


	/*
	 * Description: Constructor for initializing the class variables and the parent class
	 * Created By: Ashish Aswal
	 */

	public ProductTest() {
		super();
	}



	/*
	 * Description: Before test method to initialize the drivers 
	 * Created By: Ashish Aswal 
	 */

	@BeforeTest
	public void makeTestReady() {

		//Initializing the Report
		extentReport = new ReportGen();
		extentReport.extentReportInit();
		extentReport.logger = extentReport.report.createTest("Ebay Test");

		//Initializing the driver
		driverInit(extentReport);
	}




	/*
	 * Description: Test method for executing the Ebay App
	 * Created By: Ashish Aswal 
	 */

	@Test
	public void launcher() throws Exception {

		List<String>  item_details_before = Lists.newArrayList();
		List<String>  item_details_after = Lists.newArrayList();
		String search_input = "";
		excel = new ReadExcel();
		
		
		search_input = excel.readData("EbayTest");
		new HomePage().searchProduct(search_input, extentReport);
		new SearchPage().selectProduct(extentReport);
		item_details_before = new SearchPage().saveDetails(extentReport);
		new CartPage().addToCart(extentReport);
		new CartPage().goToCart(extentReport);
		item_details_after = new CheckoutPage().saveDetails(extentReport);
		new CheckoutPage().nowCheckout(extentReport);

		assertEquals(item_details_before, item_details_after);

	}

	
	
	
	
	
	
	/*
	 * Description: After Method to tear down the driver and flush the report
	 * Created By: Ashish Aswal 
	 */

	@AfterMethod
	public void afterMethod(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus())
			extentReport.extentReportFail(result.getThrowable().getMessage());
		
		excel.closeExcel();
		driver.quit();
		extentReport.report.flush();
	}



}
