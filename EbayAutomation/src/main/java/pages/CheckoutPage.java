package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.collections.Lists;

import Utils.MyUtility;

public class CheckoutPage extends MyUtility{

	
	//Element declaration
	
	String checkout = "com.ebay.mobile:id/shopping_cart_checkout";
	String checkout_name =  "com.ebay.mobile:id/item_title";
	String checkout_price =  "com.ebay.mobile:id/item_price";


	
	
	/*
	 * Description: Method to return the product details after checkout for verification 
	 * Created By: Ashish Aswal
	 */
	
	public List<String> saveDetails() {
		List<String>  item_list = Lists.newArrayList();
		item_list.add(driver.findElement(By.id(checkout_name)).getText()); 
		item_list.add(driver.findElement(By.id(checkout_price)).getText());
		System.out.println("After Checkout:   " + item_list);
		return item_list;
	}

	
	
	/*
	 * Description: Method to checkout
	 * Created By: Ashish Aswal
	 */
	
	public void nowCheckout() {
		waitForElementToBeDisplayed("id", checkout_name);
		swipeUp();
		click("id", checkout);
	}




}
