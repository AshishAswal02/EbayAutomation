package tests;

import Utils.MyUtility;
import java.util.List;

import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.SearchPage;

import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;



public class ProductTest extends MyUtility{

	
	
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
		
		driverInit();
	}

	
	
	
	/*
	 * Description: Test method for executing the Ebay App
	 * Created By: Ashish Aswal 
	 */

	@Test
	public void launcher() {
		
		List<String>  item_details_before = Lists.newArrayList();
		List<String>  item_details_after = Lists.newArrayList();
		
		
		new HomePage().searchProduct();
		new SearchPage().selectProduct();
		item_details_before = new SearchPage().saveDetails();
		new CartPage().addToCart();
		new CartPage().goToCart();
		item_details_after = new CheckoutPage().saveDetails();
		new CheckoutPage().nowCheckout();
		
		assertEquals(item_details_before, item_details_after);
		
		
	}
	
	
	
	/*
	 * Description: After Method to tear down the driver 
	 * Created By: Ashish Aswal 
	 */
	
	@AfterTest
	public void afterMethod() {
	driver.quit();
	}
	


}
