package pages;

import Utils.MyUtility;
import Utils.ReportGen;



public class HomePage extends MyUtility  {
	
	//Element Declration
	
	String search_box = "com.ebay.mobile:id/search_box";
	String search = "com.ebay.mobile:id/search_src_text";
	
	
	
	
	/*
	 * Description: Method to Search a Product
	 * Created By: Ashish Aswal 
	 */
	
	public void searchProduct(String input_val, ReportGen report) 
	{
		
		waitForElementToBeClickable("id", search_box, report);
		click("id", search_box, report);
		waitForElementToBeDisplayed("id", search, report);
		enterValue("id", search, input_val, report);
		
	}
	

	
}
