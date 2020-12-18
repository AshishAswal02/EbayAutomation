package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.collections.Lists;

import Utils.MyUtility;
import Utils.ReportGen;

public class SearchPage extends MyUtility {

	
	//Element Declration

	String products = "com.ebay.mobile:id/cell_collection_item";
	String item_name = "com.ebay.mobile:id/textview_item_name";
	String item_price = "com.ebay.mobile:id/textview_item_price";
	String follow_button = "com.ebay.mobile:id/button_follow";
	
	List<String>  item_list = Lists.newArrayList();
	String item_name_text ="";
	String item_price_text = "";
	
	
	
	/*
	 * Description: Method to select a product for purchasing 
	 * Created By: Ashish Aswal
	 */
	
	public void selectProduct(ReportGen report) {
		
		waitForElementToBeDisplayed("id", follow_button);
		swipeUp();
		swipeTillElementShowsUp("id", products, report);
		selectRandomProduct("id",products, report);
		
	}
	
	
	
	/*
	 * Description: Method to return the product details before checkout for verification 
	 * Created By: Ashish Aswal
	 */
	
	public List<String> saveDetails(ReportGen report)
	{
		item_name_text = getText("id", item_name, report);
		item_price_text = getText("id", item_price, report);
		
		item_list.add(item_name_text); 
		item_list.add(item_price_text);
		System.out.println("Before Checkout:  " + item_list);
		return item_list;
	}
	
	

}
