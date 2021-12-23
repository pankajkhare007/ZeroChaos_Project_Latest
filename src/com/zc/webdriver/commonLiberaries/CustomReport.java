package com.zc.webdriver.commonLiberaries;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.velocity.texen.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CustomReport extends WebDriverHelper
{
	 ExtentReports report;
	 ExtentTest logger;
	 
	 public CustomReport(String reportPath)
	 {
		 report=new ExtentReports(reportPath);
	 }
	 public void startTest(String testName)
	 {
		 logger=report.startTest(testName);
	 }
	 public void testStep(String stepName)
	 {
		 logger.log(LogStatus.INFO, stepName);
	 }
	 public void verificationStep(String result,String stepName,String imageName)
	 {
		 
		 if(result.equalsIgnoreCase("Pass"))
			 logger.log(LogStatus.PASS, stepName);
		 else
			 logger.log(LogStatus.FAIL, stepName);
			 
		 Calendar currentDate = Calendar.getInstance(); // gets current date instance. 
		 SimpleDateFormat formatter=  new SimpleDateFormat("yyyy.MMM.dd HH.mm.ss");
			String dateNow = formatter.format(currentDate.getTime());
			imageName = dateNow;
		 String img = logger.addScreenCapture(captureString(driver, imageName));
		 logger.log(LogStatus.INFO, "", imageName+img);
	 }
	 
	 public void endTest()
	 {
		report.endTest(logger);
		report.flush();
	 }
	 public String captureString(WebDriver driver,String screenShotName)
		{
			try
			{
				TakesScreenshot ts=(TakesScreenshot)driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				//String imagePath=SetProperpertiesFile.appConfig.getPropertyValue("SCREENSHOT_PATH");
				//String imagePath="ScreenShot//";
				String dest ="\\ScreenShot\\"+screenShotName+".png";
				File destination = new File(dest);
				Files.copy(source, destination);
				//FileUtil.file(base, s)
				System.out.println("Screenshot taken");
				return dest;
				
			}
			catch(Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
				return e.getMessage();
			}
			
		}
}
