package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.collections.Lists;

import Utils.MyUtility;

public class SearchPage extends MyUtility {

	
	//Element Declration

	String products = "com.ebay.mobile:id/cell_collection_item";
	String item_name = "com.ebay.mobile:id/textview_item_name";
	String item_price = "com.ebay.mobile:id/textview_item_price";
	String follow_button = "com.ebay.mobile:id/button_follow";
	
	
	
	/*
	 * Description: Method to select a product for purchasing 
	 * Created By: Ashish Aswal
	 */
	
	public void selectProduct() {
		
		waitForElementToBeDisplayed("id", follow_button);
		swipeUp();
		selectRandomProduct("id",products);
		
	}
	
	
	
	/*
	 * Description: Method to return the product details before checkout for verification 
	 * Created By: Ashish Aswal
	 */
	
	public List<String> saveDetails() {
		List<String>  item_list = Lists.newArrayList();
		item_list.add(driver.findElement(By.id(item_name)).getText()); 
		item_list.add(driver.findElement(By.id(item_price)).getText());
		System.out.println("Before Checkout:  " + item_list);
		return item_list;
	}
	
	

}
