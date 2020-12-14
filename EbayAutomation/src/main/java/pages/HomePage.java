package pages;

import Utils.MyUtility;



public class HomePage extends MyUtility  {
	
	//Element Declration
	
	String search_box = "com.ebay.mobile:id/search_box";
	String search_text = "com.ebay.mobile:id/search_src_text";
	
	
	
	
	/*
	 * Description: Method to Search a Product
	 * Created By: Ashish Aswal 
	 */
	
	public void searchProduct() 
	{
		
		waitForElementToBeClickable("id", search_box);
		click("id", search_box);
		waitForElementToBeDisplayed("id", search_text);
		enterValue("id",search_text, "Phone");
		
	}
	

	
}
