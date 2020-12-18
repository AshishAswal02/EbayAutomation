package Utils;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ReportGen extends MyUtility {

	
	//Declaring the elements
	public ExtentHtmlReporter extentHtmlReport;
	public ExtentReports report;
	public ExtentTest logger;

	
	
	/*
	 * Description: Reusable function to Initialize reporting
	 * Created By: Ashish Aswal
	 */
	
	public void extentReportInit()
	{
		extentHtmlReport=new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "/Reports/TestResult.html"));
		report=new ExtentReports();
		report.attachReporter(extentHtmlReport);
	}

	
	
	
	
	/*
	 * Description: Reusable function to Capture Screenshot
	 * Created By: Ashish Aswal
	 */

	public String captureItsSnap()
	{
		
		String screenshotPath = null; 
		FileInputStream fileInputStreamReader = null;
		TakesScreenshot ts = (TakesScreenshot)driver;

		
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		try 
		{
			fileInputStreamReader = new FileInputStream(sourceFile);
			byte[] bytes = new byte[(int)sourceFile.length()];
			fileInputStreamReader.read(bytes);       
			
			screenshotPath = System.getProperty("user.dir") + "/Reports/screenshots/"+System.currentTimeMillis()+".png";
			File destinationFile = new File(screenshotPath);
			FileUtils.copyFile(sourceFile, destinationFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return screenshotPath;
	}

	

	/*
	 * Description: Reusable function to add passed step in report
	 * Created By: Ashish Aswal 
	 * Attributes: status- Description to be printed in the report
	 */

	public void extentReportPass(String status) 
	{
		String screenshotPath = new ReportGen().captureItsSnap();
		try{
			logger.pass(status, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	/*
	 * Description: Reusable function to add failed step in report
	 * Created By: Ashish Aswal 
	 * Attributes: status- Description to be printed in the report
	 */

	public void extentReportFail(String status) 
	{
		String screenshotPath = new ReportGen().captureItsSnap();
		try{
			this.logger.fail(status, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
