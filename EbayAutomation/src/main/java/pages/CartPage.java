package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utils.MyUtility;
import Utils.ReportGen;

public class CartPage extends MyUtility{

	//
	//Element Declration
	
	String addTo ="//android.widget.Button[@text='Add to cart']"; 
	String goTo = "//android.widget.Button[@text='Go to Cart']";
	
	
	
	/*
	 * Description: Method to add the selected product to the cart
	 * Created By: Ashish Aswal
	 */
	
	public void addToCart(ReportGen report) {
		swipeTillElementShowsUp("xpath", addTo, report);
		waitForElementToBeClickable("xpath", addTo);
		click("xpath", addTo, report);
	}
	
	
	
	/*
	 * Description: Method to go to cart page
	 * Created By: Ashish Aswal
	 */
	
	public void goToCart(ReportGen report) {
		
		waitForElementToBeDisplayed("xpath", goTo);
		click("xpath", goTo, report);
	}
	
}
