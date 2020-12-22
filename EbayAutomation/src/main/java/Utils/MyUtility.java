package Utils;

import java.net.URL;
import org.testng.Assert;
import java.time.Duration;
import java.io.IOException;
import java.io.FileInputStream;

import java.util.List;
import java.util.Random;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Element;

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

	//Element Declration
	
	public static AndroidDriver<AndroidElement> driver;
	public DesiredCapabilities caps;
	Properties prop;

	public MyUtility() {
		caps=new DesiredCapabilities();
		prop=new Properties();
	}


	/*
	 * Description: Function to initialize the driver
	 * Created By: Ashish Aswal 
	 */



	public void driverInit(ReportGen report)
	{
		try
		{
			prop = loadPropertyFile(System.getProperty("user.dir") + "/src/main/resources//PropertyFiles/capability.properties");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName"));
			caps.setCapability(MobileCapabilityType.UDID, prop.getProperty("udid")); 
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("platformName"));
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("platformVersion"));
			caps.setCapability(MobileCapabilityType.NO_RESET, prop.getProperty("set_reset_true"));
			caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, prop.getProperty("appPackage"));
			caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, prop.getProperty("appActivity"));



			//Instantiate Android Driver
			driver = new AndroidDriver<AndroidElement>(new URL(prop.getProperty("hubUrl")), caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			report.extentReportPass("Driver initialized");
		}

		catch(Exception e) 
		{
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
			System.out.println("Cause is:" + e.getCause());
			System.out.println("Message is: " + e.getMessage());
		}
	}




	/*
	 * Description: Reusable function to wait for an element to be clickable
	 * Created By: Ashish Aswal
	 * Attribute: elementType- element type String passed is an id or xpath
	 * 			  element - unique element identifier
	 */


	public void waitForElementToBeClickable(String elementType,String element, ReportGen report)
	{
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, 60);
			if(elementType.equalsIgnoreCase("id"))
				wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
			else if(elementType.equalsIgnoreCase("xpath"))
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
			
			report.extentReportPass("Element clickable now");
		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}



	/*
	 * Description: Reusable function to load the property File
	 * Created By: 	Ashish Aswal
	 * Attribute:	path- Path of the property file
	 * 				input - Our input Stream
	 */
	public Properties loadPropertyFile(String path)
	{
		try
		{
			FileInputStream input = new FileInputStream(path);
			prop.load(input);
		}
		catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false, e.getMessage());
		}
		return prop;
	}




	/*
	 * Description: Reusable function to wait for an element to appear on the screen
	 * Created By: 	Ashish Aswal
	 * Attribute: 	elementType- element type String passed is an id or xpath
	 * 			  	element - unique element identifier
	 */


	public void waitForElementToBeDisplayed(String elementType,String element, ReportGen report)
	{
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, 60);
			if(elementType.equalsIgnoreCase("id"))
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
			else if(elementType.equalsIgnoreCase("xpath"))
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
			
			report.extentReportPass("Element visible..");
			
		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}


	
	
	/*
	 * Description: Reusable function to click on an element
	 * Created By: 	Ashish Aswal
	 * Attribute: 	elementType- element type String passed is an id or xpath
	 * 			  	element - unique element identifier
	 * 				report- Object of ReportGen class to generate extent report
	 */


	public void click(String elementType,String element, ReportGen report) 
	{
		try
		{
			if(elementType.equalsIgnoreCase("id"))
				driver.findElement(By.id(element)).click();
			else if(elementType.equalsIgnoreCase("xpath"))
				driver.findElement(By.xpath(element)).click();

			report.extentReportPass(element + " is clicked..");

		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}




	/*
	 * Description: Reusable function to swipe up on the screen
	 * Created By: 	Ashish Aswal
	 * 
	 */

	public void swipeUp(ReportGen report) 
	{

		try {
			Dimension scrnSize = driver.manage().window().getSize();
			int startx = (int) (scrnSize.width / 8);
			int starty = (int) (scrnSize.height * 0.85);
			int endy = (int) (scrnSize.height * 0.4);


			(new TouchAction(driver))
			.press(PointOption.point(startx,starty))
			.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
			.moveTo(PointOption.point(startx,endy))
			.release()
			.perform();
			
			report.extentReportPass("Swiped Up..");
		} 

		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}

	
	
	/*
	 * Description: Reusable function to check an element is present in UI
	 * Created By: 	Ashish Aswal
	 * Attribute: 	elementType- element type String passed is an id or xpath
	 * 				element - unique element identifier
	 */

	public boolean findElement(String elementType, String element, ReportGen report)
	{
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		Boolean found = true;
		try 
		{
			if (elementType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(element));
			}
			
			if (elementType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(element));
			}
			
			report.extentReportPass("Element found");
			return found;


		} catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			found = false;
			return found;
		}
	}

	
	
	/*
	 * Description: Reusable function to scroll and check an element is present in UI
	 * Created By: 	Ashish Aswal
	 * Attribute: 	elementType- element type String passed is an id or xpath
	 * 				element - unique element identifier
	 * 				report- Object of ReportGen class to generate extent report
	 */

	public void swipeTillElementShowsUp(String elementType, String element, ReportGen report) 
	{
		int swipes = 0;

		while (swipes < 4)
		{
			if (findElement(elementType, element, report)) {
				report.extentReportPass("Element located");
				break;
			}
			swipeUp(report);
			swipes ++;
		}
		
		if (swipes > 3) {
			Assert.assertTrue(false, "Cannot find element");
			report.extentReportFail("Cannot find element");	
		}
		
	}
		
		

	
	
	/*
	 * Description: Reusable function to feed the value and press enter
	 * Created By: 	Ashish Aswal
	 * Attribute: 	elementType- element type String passed is an id or xpath
	 * 			  	element - unique element identifier
	 * 			  	value - the product we want to search for
	 * 				report- Object of ReportGen class to generate extent report
	 */



	public void enterValue(String elementType,String element, String value, ReportGen report)
	{
		try {

			if(elementType.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(element)).click();
				driver.findElement(By.id(element)).clear();
				driver.findElement(By.id(element)).sendKeys(value);
				driver.pressKey(new KeyEvent(AndroidKey.ENTER));

				report.extentReportPass(element + " has been enetered with value: " + value);
			}

			else if(elementType.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(element)).click();
				driver.findElement(By.xpath(element)).clear();
				driver.findElement(By.xpath(element)).sendKeys(value);
				driver.pressKey(new KeyEvent(AndroidKey.ENTER));

				report.extentReportPass(element + " has been enetered with value: " + value);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
	}


	/*
	 * Description: Reusable function to fetch the text from an object
	 * Created By: 	Ashish Aswal
	 * Attribute: 	elementType- element type String passed is an id or xpath
	 * 			  	identifier- unique element identifier
	 * 			  	report- Object of ReportGen class to generate extent report
	 */


	public String getText(String elementType,String element,ReportGen report)
	{
		String text = "";
		try
		{
			if(elementType.equalsIgnoreCase("id"))
				text = driver.findElement(By.id(element)).getText();
			else if(elementType.equalsIgnoreCase("xpath"))
				text = driver.findElement(By.xpath(element)).getText();

			report.extentReportPass(text + " is retrived form " + element);

		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			Assert.assertTrue(false, e.getMessage());
		}
		return text;



	}




	/*
	 * Description: Reusable function to select random product from search list
	 * Created By:	Ashish Aswal
	 * Attribute: 	elementType- element type String passed is an id or xpath
	 * 			  	element - unique element identifier
	 * 			  	report- Object of ReportGen class to generate extent report
	 */



	public void selectRandomProduct(String elementType,String element, ReportGen report) 
	{
		try
		{
			int ls_size;
			int selected_product_number;
			Random rand = new Random();
			List<AndroidElement> search_results = null;

			if(elementType.equalsIgnoreCase("id"))
				search_results = driver.findElements(By.id(element));
			else if(elementType.equalsIgnoreCase("xpath"))
				search_results = driver.findElements(By.xpath(element));

			ls_size = search_results.size();
			selected_product_number = rand.nextInt(ls_size);
			search_results.get(selected_product_number).click();

			report.extentReportPass("Product number "+ selected_product_number + " has been selected randomly..");

		}
		catch (Exception e) {
			e.printStackTrace();
			report.extentReportFail(e.getMessage());
			System.out.println("Cause is:" + e.getCause());
			System.out.println("Message is: " + e.getMessage());
		}
	}




}
