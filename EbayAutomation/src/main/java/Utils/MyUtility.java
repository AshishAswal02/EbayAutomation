package Utils;

import java.net.URL;
import java.time.Duration;
import org.testng.Assert;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;





public class MyUtility {

	public static AndroidDriver<AndroidElement> driver;
	public DesiredCapabilities caps;


	public MyUtility() {
		caps=new DesiredCapabilities();
	}


	/*
	 * Description: Function to initialize the driver
	 * Created By: Ashish Aswal 
	 */



	public void driverInit()
	{
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "My Phone");; 
		caps.setCapability(MobileCapabilityType.UDID, "emulator-5554"); 
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
		caps.setCapability(MobileCapabilityType.NO_RESET, "true");
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.ebay.mobile");
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.ebay.mobile.activities.MainActivity");



		//Instantiate Android Driver
		try {
			driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}

		catch(Exception e) 
		{
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
			System.out.println("Cause is:" + e.getCause());
			System.out.println("Message is: " + e.getMessage());
		}
	}




	/*
	 * Description: Function to wait for an element to be clickable
	 * Created By: Ashish Aswal
	 * Attribute: elementType- element type String passed is an id or xpath
	 * 			  element - unique element identifier
	 */


	public void waitForElementToBeClickable(String elementType,String element)
	{
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, 60);
			if(elementType.equalsIgnoreCase("id"))
				wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
			else if(elementType.equalsIgnoreCase("xpath"))
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}





	/*
	 * Description: Function to wait for an element to appear on the screen
	 * Created By: Ashish Aswal
	 * Attribute: elementType- element type String passed is an id or xpath
	 * 			  element - unique element identifier
	 */


	public void waitForElementToBeDisplayed(String elementType,String element)
	{
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, 60);
			if(elementType.equalsIgnoreCase("id"))
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
			else if(elementType.equalsIgnoreCase("xpath"))
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}


	/*
	 * Description: Function to click on an element
	 * Created By: Ashish Aswal
	 * Attribute: elementType- element type String passed is an id or xpath
	 * 			  element - unique element identifier
	 */



	public void click(String elementType,String element) 
	{
		try
		{
			if(elementType.equalsIgnoreCase("id"))
				driver.findElement(By.id(element)).click();
			else if(elementType.equalsIgnoreCase("xpath"))
				driver.findElement(By.xpath(element)).click();

		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}




	/*
	 * Description: Reusable function to swipe up on the screen
	 * Created By: Ashish Aswal
	 */

	public void swipeUp() 
	{

		try {
			Dimension scrnSize = driver.manage().window().getSize();
			int startx = (int) (scrnSize.width / 8);
			int starty = (int) (scrnSize.height * 0.85);
			int endy = (int) (scrnSize.height * 0.125);


			(new TouchAction(driver))
			.press(PointOption.point(startx,starty))
			.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
			.moveTo(PointOption.point(startx,endy))
			.release()
			.perform();
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}

	
	
	
	
	/*
	 * Description: Function to feed the value and press enter
	 * Created By: Ashish Aswal
	 * Attribute: elementType- element type String passed is an id or xpath
	 * 			  element - unique element identifier
	 * 			  value - the product we want to search for
	 */



	public void enterValue(String elementType,String element, String value)
	{
		try {
			if(elementType.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(element)).click();
				driver.findElement(By.id(element)).clear();
				driver.findElement(By.id(element)).sendKeys(value);
				driver.pressKey(new KeyEvent(AndroidKey.ENTER));

			}
			else if(elementType.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(element)).click();
				driver.findElement(By.xpath(element)).clear();
				driver.findElement(By.xpath(element)).sendKeys(value);
				driver.pressKey(new KeyEvent(AndroidKey.ENTER));

			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
	}



	
	
	/*
	 * Description: Function to select random product from search list
	 * Created By: Ashish Aswal
	 * Attribute: elementType- element type String passed is an id or xpath
	 * 			  element - unique element identifier
	 */



	public void selectRandomProduct(String elementType,String element) 
	{
		try
		{
			int ls_size;
			int selected_product;
			Random rand = new Random();
			List<AndroidElement> search_results = null;

			if(elementType.equalsIgnoreCase("id"))
				search_results = driver.findElements(By.id(element));
			else if(elementType.equalsIgnoreCase("xpath"))
				search_results = driver.findElements(By.xpath(element));

			ls_size = search_results.size();
			selected_product = rand.nextInt(ls_size);
			search_results.get(selected_product).click();

		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cause is:" + e.getCause());
			System.out.println("Message is: " + e.getMessage());
		}
	}




}
