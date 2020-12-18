package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.collections.Lists;

import Utils.MyUtility;
import Utils.ReportGen;

public class CheckoutPage extends MyUtility{

	
	//Element declaration
	
	String checkout = "com.ebay.mobile:id/shopping_cart_checkout";
	String checkout_name =  "com.ebay.mobile:id/item_title";
	String checkout_price =  "com.ebay.mobile:id/item_price";
	List<String>  item_list = Lists.newArrayList();
	String checkout_name_text = "";
	String checkout_price_text = "";
	


	/*
	 * Description: Method to checkout
	 * Created By: Ashish Aswal
	 */
	
	public void nowCheckout(ReportGen report) {
		waitForElementToBeDisplayed("id", checkout_name);
		swipeUp();
		click("id", checkout, report);
	}
	
	
	
	
	/*
	 * Description: Method to return the product details after checkout for verification 
	 * Created By: Ashish Aswal
	 */
	
	public List<String> saveDetails(ReportGen report)
	{
		checkout_name_text = getText("id", checkout_name, report);
		checkout_price_text = getText("id", checkout_price, report);
		
		item_list.add(checkout_name_text); 
		item_list.add(checkout_price_text);
		System.out.println("After Checkout:   " + item_list);
		return item_list;
	}

	
	




}
