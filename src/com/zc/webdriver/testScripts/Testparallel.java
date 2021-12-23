package com.zc.webdriver.testScripts;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
 
public class Testparallel {
 
    //protected ThreadLocal<RemoteWebDriver> threadDriver = null;

  
    	public static void main(String[] args) throws Exception {
    	/*	SetProperpertiesFile.setAllProperpertiesFile();
    	WebDriver driver;
    	      
    	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    	capabilities.setCapability(FirefoxDriver.BINARY,new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe").getAbsolutePath());
    	String geckodriverPath=SetProperpertiesFile.appConfig.getPropertyValue("GeckoDriver");
		System.setProperty("webdriver.gecko.driver", geckodriverPath);
    	driver = new RemoteWebDriver(new URL("http://10.17.90.208:5556/wd/hub"), capabilities);
        
      
       driver.get("http://google.com");
       System.out.println(driver.getTitle());*/
    		
    	
    		 /*   String sDate1="30/Jul/1998";  
    		    DateFormat date1=new SimpleDateFormat("dd/MMM/yyyy");  
    		    Date startDate = date1.parse(sDate1);
    		    
    		    SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
    		    int month = Integer.parseInt(dateFormat.format(startDate));
    		
    		    String startDateString1 = date1.format(startDate);
    		    System.out.println(sDate1+"\t"+startDateString1);  
    		    
    		    System.out.println(month);
    	*/
    		
    		/*DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
    		Date date = new Date();
    		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
    		String dateString = dateFormat.format(date).toString();
    		System.out.println(dateString);*/
    		
    		 // create a calendar
    		/*Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.DATE, 60);
    		SimpleDateFormat format1 = new SimpleDateFormat("dd/MMM/yyyy");
    	
    		String formatted = format1.format(cal.getTime());
    		System.out.println(formatted);*/
    		
    		
    		String str="0.00%)";
    		String str1[]=str.split("%");
    		System.out.println(str1[0]);
 			   
 			 
    	
   
	     }
    }
