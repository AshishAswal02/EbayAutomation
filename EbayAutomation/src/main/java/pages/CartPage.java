package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utils.MyUtility;

public class CartPage extends MyUtility{

	
	//Element Declration
	
	String addTo ="//android.widget.Button[@text='Add to cart']"; 
	String goTo = "//android.widget.Button[@text='Go to Cart']";
	
	
	
	/*
	 * Description: Method to add the selected product to the cart
	 * Created By: Ashish Aswal
	 */
	
	public void addToCart() {
		swipeUp();
		waitForElementToBeClickable("xpath", addTo);
		click("xpath", addTo);
	}
	
	
	
	/*
	 * Description: Method to go to cart page
	 * Created By: Ashish Aswal
	 */
	
	public void goToCart() {
		
		waitForElementToBeDisplayed("xpath", goTo);
		click("xpath", goTo);
	}
	
}
