package com.zc.webdriver.commonLiberaries;

import static com.zc.webdriver.commonLiberaries.ReadObjectProperties.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;

import  org.openqa.selenium.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.remote.http.W3CHttpCommandCodec;
import org.openqa.selenium.remote.http.W3CHttpResponseCodec;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.google.common.io.Files;


import RegressionClasses.TestClass_Updated;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.remote.http.W3CHttpCommandCodec;
import org.openqa.selenium.remote.http.W3CHttpResponseCodec;


public class WebDriverHelper
{
	
	 static  HttpCommandExecutor executor;
	 static  URL url;
	static  SessionId session_id;
	static RemoteWebDriver driver2;
	public static boolean flag=true;
	public static WebDriver driver;
	public static WebDriverWait driverWait;
	static String sBrowser ;
	static String sNewSession;
	public static ResultSet globalRS;
	public static String existingUrl;
	static String sURL; //= Set_Read_ProperpertiesFile.appConfig.getPropertyValue("URL");
	static DesiredCapabilities capabilities = new DesiredCapabilities();
	//public static CustomReport cReport=new CustomReport(SetProperpertiesFile.appConfig.getPropertyValue("REPORT_PATH"));
	public static CustomReport cReport=new CustomReport("CustomReports\\CustomReport.html");
	public static void invokeBrowser()throws Exception
	{
		//Set_Read_ProperpertiesFile.readAppConfigFile();
		
		//Set_Read_ProperpertiesFile.readAppConfigFile();
		sBrowser = SetProperpertiesFile.appConfig.getPropertyValue("Browser");
		
		sURL = SetProperpertiesFile.appConfig.getPropertyValue("URL");
		sNewSession =  SetProperpertiesFile.appConfig.getPropertyValue("NewSession");
		String env = SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT");
		
		switch(env)
		{
			case "qamars":
				sURL = "https://qamars.zcdev.net/ZCW/Login/Login";
				break;
			case "qavenus":
				sURL = "https://qavenus.zcdev.net/ZCW/Login/Login";
				break;
			case "qapluto":
				sURL = "https://qapluto.zcdev.net/ZCW/Login/Login";
				break;
			case "qaperformance":
				sURL = "https://qaperformance.zcdev.net/ZCW/Login/Login";
				break;
			case "qamercury":
				sURL = "https://qamercury.workforcelogiq.com/zcw/Login/Login?TabId=1";
				break;
			case "qajupiter":
				sURL = "https://qajupiter.zcdev.net/ZCW/Login/Login";
				break;
			case "qaneptune":
				sURL = "https://qaneptune.zcdev.net/ZCW/Login/Login";
				break;
			case "qasaturn":
				sURL = "https://qasaturn.zcdev.net/ZCW/Login/Login";
				break;
			case "qaio":
				sURL = "https://qaio.workforcelogiq.com/ZCW/Login/Login";
				break;
			case "originstagingus":
				sURL = "https://originstagingus.workforcelogiq.com/ZCW/Login/Login";
				break;
			case "stagingus":
				sURL = "https://stagingus.workforcelogiq.com/zcw";
				break;
			case "qajupiterAzr":
				sURL = "https://qajupiter.workforcelogiq.com/ZCW/Login/Login";
				break;
				
		}
		String geckodriverPath=SetProperpertiesFile.appConfig.getPropertyValue("GeckoDriver");
		//String sURL = "https://"+enviroment+".zcdev.net/ZCW/Login/Login";
		switch(sBrowser)
		{
			case "ie":
				System.setProperty("webdriver.ie.driver", SetProperpertiesFile.appConfig.getPropertyValue("IEDriverServer"));
				capabilities.setBrowserName(sBrowser);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setJavascriptEnabled(true);
				driver = new InternetExplorerDriver(capabilities);
				driver.manage().window().maximize();
				break;
				
			case "chrome" :
				if(sNewSession.toLowerCase().equals("true"))
				{
					System.setProperty("webdriver.chrome.driver", SetProperpertiesFile.appConfig.getPropertyValue("ChromeDriver"));
					driver = new ChromeDriver();
				//	capabilities.setBrowserName(sBrowser);
//					ChromeOptions options = new ChromeOptions();
//					options.addArguments("start-maximized");
//					  options.setExperimentalOption("useAutomationExtension", false);
//					//options.addArguments("--lang=" +   "en-GB");                         // To set the browser language
//					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//					driver = new ChromeDriver(options);
//					executor = (HttpCommandExecutor) ((RemoteWebDriver) driver).getCommandExecutor();
//					url = executor.getAddressOfRemoteServer();
//					session_id = ((RemoteWebDriver) driver).getSessionId();
//					SetGetSessionURL.setURLText(url+"");
//					SetGetSessionURL.setSessionIDText(session_id+"");
				}
				else
				{
					existingUrl=SetGetSessionURL.getURLText();
					String existingSessionID=SetGetSessionURL.getSessionText();
					RemoteWebDriver driver2 = createDriverFromSession(new SessionId(existingSessionID), new URL(existingUrl));
					
					/// driver2 = createDriverFromSession(new SessionId(System.getenv("session_id")), new URL(System.getenv("url")));
					 //driver2 = createDriverFromSession(session_id, url);
					driver = (WebDriver)driver2;
					
				}
				break;
			case "chromeRemote" :
				if(sNewSession.toLowerCase().equals("true"))
				{
					System.setProperty("webdriver.chrome.driver", SetProperpertiesFile.appConfig.getPropertyValue("ChromeDriver"));
					capabilities.setBrowserName(sBrowser);
					ChromeOptions options = new ChromeOptions();
					options.addArguments("start-maximized");
					  options.setExperimentalOption("useAutomationExtension", false);
					//options.addArguments("--lang=" +   "en-GB");                         // To set the browser language
					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					 driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), options);
					executor = (HttpCommandExecutor) ((RemoteWebDriver) driver).getCommandExecutor();
					url = executor.getAddressOfRemoteServer();
					session_id = ((RemoteWebDriver) driver).getSessionId();
					SetGetSessionURL.setURLText(url+"");
					SetGetSessionURL.setSessionIDText(session_id+"");
				}
				else
				{
					existingUrl=SetGetSessionURL.getURLText();
					String existingSessionID=SetGetSessionURL.getSessionText();
					RemoteWebDriver driver2 = createDriverFromSession(new SessionId(existingSessionID), new URL(existingUrl));
					
					/// driver2 = createDriverFromSession(new SessionId(System.getenv("session_id")), new URL(System.getenv("url")));
					 //driver2 = createDriverFromSession(session_id, url);
					driver = (WebDriver)driver2;
					
				}
				
				break;
				
			case "firefox" :
				capabilities.setBrowserName(sBrowser);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability("marionette", true);
				FirefoxProfile firefoxprofile = new FirefoxProfile();
				firefoxprofile.setPreference("browser.startup.homepage_override.mstone", "ignore");
				//firefoxprofile.setPreference("startup.homepage_welcome_url.additional",  "about:blank");
				firefoxprofile.setAcceptUntrustedCertificates(true);
			//	firefoxprofile.setAssumeUntrustedCertificateIssuer(true);
				//System.setProperty("webdriver.gecko.driver", "lib//geckodriver");
				//String filePath="lib//geckodriver.exe";
				
				System.setProperty("webdriver.gecko.driver", geckodriverPath);
				driver = new FirefoxDriver();
				
				//Thread.sleep(20000);
				
				break;
			case "RemoteFireFox" :
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability("marionette", true);
		    	capabilities.setCapability(FirefoxDriver.BINARY,new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe").getAbsolutePath());
		   		System.setProperty("webdriver.gecko.driver", geckodriverPath);
		    	driver = new RemoteWebDriver(new URL("http://10.17.90.208:5556/wd/hub"), capabilities);
		   				
				//Thread.sleep(20000);
				
				break;
			case "safari" :
				//driver = new SafariDriver();
				SafariOptions safariOpts = new SafariOptions();
			    DesiredCapabilities cap = DesiredCapabilities.safari();

			   // safariOpts.setUseCleanSession(true);
			    cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			    cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "dismiss");
			    cap.setCapability(SafariOptions.CAPABILITY, safariOpts);
			    cap.setBrowserName("safari");
			    cap.setPlatform(Platform.WIN10);
			    driver = new SafariDriver(cap);		
				//Thread.sleep(20000);
			case "Existing Browser" :
				//Run from CMD
				//C:\Program Files (x86)\Google\Chrome\Application>chrome.exe --remote-debugging-port=9222 --user-data-dir="C:\selenum\AutomationProfile"
				System.setProperty("webdriver.chrome.driver", SetProperpertiesFile.appConfig.getPropertyValue("ChromeDriver"));
				//capabilities.setBrowserName(sBrowser);
				ChromeOptions options1 = new ChromeOptions();
				options1.addArguments("start-maximized");
				 // options1.setExperimentalOption("useAutomationExtension", false);
				//options.addArguments("--lang=" +   "en-GB");                         // To set the browser language
				//capabilities.setCapability(ChromeOptions.CAPABILITY, options1);
			
				//ChromeOptions options = new ChromeOptions();
				options1.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
				
				   
				driver = new ChromeDriver(options1);
				
				
				break;
			case "edge" :
				System.setProperty("webdriver.edge.driver","lib/MicrosoftWebDriver.exe" );
				driver = new EdgeDriver();
				break;
		}
		if(sNewSession.equals("true"))
		{
			driver.get(sURL);
//			String sWaitTime = SetProperpertiesFile.appConfig.getPropertyValue("WAIT_TIME");
//			//	int iWaitTime = Integer.parseInt(sWaitTime);
//				//driver.manage().timeouts().implicitlyWait(iWaitTime, TimeUnit.SECONDS);
				driver.manage().window().maximize();
		}
			
	
		
			
		
	}
	@BeforeSuite
	public static void startDriver()throws Throwable
	{
		
		/*String filePath="lib//geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", filePath);*/
		SetProperpertiesFile.setAllProperpertiesFile();
		//invokeBrowser();
	}
	
	/*@BeforeTest
	public static void onTestStart()	{
	cReport.startTest(method.getName());
		
	}*/
	/*@AfterTest
	public static void stopDriver() throws Throwable
	{
		
		//TestClass_Updated.closeFile();
		driver.close();
		
	}*/
//	@AfterTest
//	public static void closeBrowser() throws Throwable
//	{
//		driver.close();
//		cReport.endTest();
//		
//	}
	public static void waitForAlertPopoup(int waitTime)
	{
		driverWait = new WebDriverWait(driver, waitTime);
		Alert alertPopup = driverWait.until(ExpectedConditions.alertIsPresent());
		alertPopup.accept();
		Reporter.log("Alert is present");
	}
	
	public static void waitForElementPresent(int waitTime,ReadObjectProperties propObject,String proertiesValue)
	{
		LocatorType locator =propObject.getLocatorType(proertiesValue);
		driverWait = new WebDriverWait(driver, waitTime);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(locator.execute(objLocatorValue)));
		//alertPopup.accept();
		Reporter.log("Element is present");
	}
	public static void waitForElementPresent(int waitTime,By by)
	{
		
		driverWait = new WebDriverWait(driver, waitTime);
		driverWait.until(ExpectedConditions.presenceOfElementLocated(by));
		//alertPopup.accept();
		Reporter.log("Element is present");
	}
	public static void waitForElementNotPresent(int waitTime,By by)
	{
		
		driverWait = new WebDriverWait(driver, waitTime);
		driverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		//alertPopup.accept();
		Reporter.log("Element is present");
	}
	
	public static void waitForElementToBeClickable(int waitTime,ReadObjectProperties propObject,String proertiesValue) throws InterruptedException
	{
		LocatorType locator =propObject.getLocatorType(proertiesValue);
		driverWait = new WebDriverWait(driver, waitTime);
		driverWait.until(ExpectedConditions.elementToBeClickable(locator.execute(objLocatorValue)));
		Thread.sleep(5000);
		//alertPopup.accept();
		Reporter.log("Element is ready to click");
	}
	
	public static void waitForPageTitle(int waitTime,String title)
	{
		
		driverWait = new WebDriverWait(driver, waitTime);
		driverWait.until(ExpectedConditions.titleContains(title));
		//alertPopup.accept();
		Reporter.log("Title is present");
	}
	
	 public  void captureScreen()
		{
		 	
			//System.out.println("Calling After Method");
			try
			{
				Calendar currentDate = Calendar.getInstance(); // gets current date instance. 
				SimpleDateFormat formatter=  new SimpleDateFormat("yyyy.MMM.dd HH.mm.ss");
				String dateNow = formatter.format(currentDate.getTime());
		        //String classname = "screenshot";
		        String path=SetProperpertiesFile.appConfig.getPropertyValue("SCREENSHOT_PATH");
				
				   // Get the Screen shot while test case Failed.
			    	String filename =dateNow+".png";
			    	//String imagePath="ScreenShot/"+filename;
		    		File sourceimageFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    		Files.copy(sourceimageFile, new File("ScreenShot/"+filename));
		    		//FileUtils.copyFile(sourceimageFile, new File("ScreenShot/"+methodName+"/"+filename)); 
		    		//Reporter.log("<a href=\"..\\..\\..\\ScreenShot\\"+filename+"\"><img src=\"..\\..\\..\\ScreenShot_Error\\"+filename+"\"width=\"100\" height=\"131\" border=\"0\"></a>");
		    		Reporter.log("<a href='" +path+filename+ "'>screenshot</a>");
			}
			catch (Exception e){}
			
			// close all window except parent window.
			
		}
	 
	 public static boolean isElementPresent(ReadObjectProperties propObject,String proertiesValue)
	 {
		 LocatorType locator =propObject.getLocatorType(proertiesValue);
	    try {
	      driver.findElement(locator.execute(objLocatorValue));
	     
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	 }
	 
	 public static boolean isElementPresent(String xpathStr)
	 {
		
	    try {
	      driver.findElement(By.xpath(xpathStr));
	     
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	 }
	 
	 public static boolean isElementVisible(ReadObjectProperties propObject,String proertiesValue)
	 {
		 LocatorType locator =propObject.getLocatorType(proertiesValue);
		 boolean flag=false;
		 try
		 {
			flag= driver.findElement(locator.execute(objLocatorValue)).isDisplayed();
		 }
		 catch(Exception e)
		 {
			 flag=false;
		 }
		 return flag;
		 
	 }
	 
	 public static void handleLoadingImage() throws Exception
 	 {
 			boolean flag=true;
 			int i=0;
 			while(flag)
 			{
	 			//if( isElementPresent(locator.execute(objLocatorValue)
 				if(isElementPresentWithoutProperties(By.xpath("//div[@id='loadingImageSave']")))
	 			{
				 			try
				 			{
				 				
				 				//isElementPresent(common, proertiesValue)
				 			
					 		//	driver.findElement(By.xpath("//div[@id='loadingImageSave']"));
					 			
					 			
					 			
					 			
					 			Thread.sleep(8000);
					 			highLightElement(driver.findElement(By.xpath("//div[@id='loadingImageSave']")));
					 			waitForElementNotPresent(60, By.xpath("//div[@id='loadingImageSave']"));
					 			flag=true;
					 			i++;
					 			if(i==1)
					 				flag=false;
					 			System.out.println( "i =  "+i);
				 			}
				 			catch(Exception e)
				 			{
				 				flag=false;
				 				Thread.sleep(5000);
				 			}
			 	}
 				try
 				{
 					if(isElementPresentWithoutProperties(By.xpath("//div[@id='ctl00_pnlPopup']")))
 		 			{
 					 			try
 					 			{
 					 				
 					 				//isElementPresent(common, proertiesValue)
 					 			
 						 		
 						 			
 						 			Thread.sleep(8000);
 						 			highLightElement(driver.findElement(By.xpath("//div[@id='ctl00_pnlPopup']")));
 						 			waitForElementNotPresent(60, By.xpath("//div[@id='ctl00_pnlPopup']"));
 						 			flag=true;
 						 			i++;
 						 			if(i==1)
 						 				flag=false;
 						 			System.out.println( "i =  "+i);
 					 			}
 					 			catch(Exception e)
 					 			{
 					 				flag=false;
 					 				Thread.sleep(5000);
 					 			}
 				 	}
 					
 				}
 				catch(Exception e)
 				{
 					
 				}
 				
 			}
 			
 	 } 
		 public static void highLightElement(WebElement element) throws InterruptedException
		 {
			  //Creating JavaScriptExecuter Interface
			   JavascriptExecutor js = (JavascriptExecutor)driver;
			   for (int iCnt = 0; iCnt < 3; iCnt++)
			   {
			      //Execute javascript
			         js.executeScript("arguments[0].style.border='4px groove green'", element);
			         Thread.sleep(1000);
			         js.executeScript("arguments[0].style.border=''", element);
			   }
		 }
				 
		 
		 // Mouse Over to an Element
		    public static void mouseOverElement(ReadObjectProperties propObject,String proertiesValue)throws Exception
		    {
		    	LocatorType locator =propObject.getLocatorType(proertiesValue);
		    	Actions actions = new Actions(driver);
		    	WebElement menuHoverLink = driver.findElement(locator.execute(objLocatorValue));
		    	actions.moveToElement(menuHoverLink);
		    	//Thread.sleep(5000);
		    	//actions.click();
		    	actions.perform();
		    }
	    
		    public static void scrollUp()
		    {
		    	WebDriver driver = new FirefoxDriver();
		    	JavascriptExecutor jse = (JavascriptExecutor)driver;
		    	jse.executeScript("window.scrollBy(0,250)", "");
		    }
	
		    public static void scrollingUPToPage()throws Exception
		    {
		    	// LocatorType locator =propObject.getLocatorType(proertiesValue);
				//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", locator.execute(objLocatorValue));
		    	Robot robot = new Robot();
		    	robot.keyPress(KeyEvent.VK_PAGE_UP);
		    	robot.keyRelease(KeyEvent.VK_PAGE_UP);
		    }
		    
		    public static void handleMultipleWindows(String windowTitle) {
	            Set<String> windows = driver.getWindowHandles();

	            for (String window : windows) {
	                driver.switchTo().window(window);
	                if (driver.getTitle().contains(windowTitle)) {
	                    return;
	                }
	            }
	        }  
		    
		   // roslaunch iri_wam_aff_demo start_demo.launch
		    public static void enterTextForGazebo4()throws AWTException,IOException
			  {
				  int keyInput[] = {
					      KeyEvent.VK_R,KeyEvent.VK_O,KeyEvent.VK_S,KeyEvent.VK_L,KeyEvent.VK_A,KeyEvent.VK_U,KeyEvent.VK_N,KeyEvent.VK_C,KeyEvent.VK_H,
					      KeyEvent.VK_SPACE,
					      KeyEvent.VK_I,KeyEvent.VK_R,KeyEvent.VK_I,
					      KeyEvent.VK_MINUS,
					      KeyEvent.VK_W,KeyEvent.VK_A,KeyEvent.VK_M,
					      KeyEvent.VK_MINUS,
					      KeyEvent.VK_A,KeyEvent.VK_F,KeyEvent.VK_F,
					      KeyEvent.VK_MINUS,
					      KeyEvent.VK_D,KeyEvent.VK_E,KeyEvent.VK_M,KeyEvent.VK_0,
					      KeyEvent.VK_SPACE,
					      KeyEvent.VK_S,KeyEvent.VK_T,KeyEvent.VK_A,KeyEvent.VK_R,KeyEvent.VK_T,
					      KeyEvent.VK_MINUS,
					      KeyEvent.VK_D,KeyEvent.VK_E,KeyEvent.VK_M,KeyEvent.VK_0,
					      KeyEvent.VK_PERIOD,
					      KeyEvent.VK_L,KeyEvent.VK_A,KeyEvent.VK_U,KeyEvent.VK_N,KeyEvent.VK_C,KeyEvent.VK_H,
					      KeyEvent.VK_ENTER
					      
					  };//end keyInput array

				
				  Robot robot = new Robot();
			
				    for (int cnt2 = 0;cnt2 < keyInput.length; cnt2++)
				    {
				      
				      if(keyInput[cnt2]==45)
					  {
					    	 robot.keyPress(KeyEvent.VK_SHIFT);
					    	 robot.keyPress(KeyEvent.VK_MINUS);
					    	 robot.keyRelease(KeyEvent.VK_MINUS);
					    	 robot.keyRelease(KeyEvent.VK_SHIFT);
					  }
				      else
				       	 robot.keyPress(keyInput[cnt2]);
					      //Insert a one-half second delay between
					      // characters.
					      robot.delay(500);
					}//end for loop
				}//end for loop
		    
		    public static String getXPath(WebElement element)
			{
			    return (String) ((JavascriptExecutor) driver).executeScript(
			    "getXPath=function(node)" +
			    "{" +
			        "if (node.id !== '')" +
			        "{" +
			            "return '//' + node.tagName.toLowerCase() + '[@id=\"' + node.id + '\"]'" +
			        "}" +

			        "if (node === document.body)" +
			        "{" +
			            "return node.tagName.toLowerCase()" +
			        "}" +

			        "var nodeCount = 0;" +
			        "var childNodes = node.parentNode.childNodes;" +

			        "for (var i=0; i<childNodes.length; i++)" +
			        "{" +
			            "var currentNode = childNodes[i];" +

			            "if (currentNode === node)" +
			            "{return getXPath(node.parentNode) + '/' + node.tagName.toLowerCase() + '[' + (nodeCount+1) + ']'" +
			            "}" +

			            "if (currentNode.nodeType === 1 && " +
			                "currentNode.tagName.toLowerCase() === node.tagName.toLowerCase())" +
			            "{" +
			                "nodeCount++" +
			            "}" +
			        "}" +
			    "};" +

			    "return getXPath(arguments[0]);", element);
			
		 }
		    
	    public static String AddSubtractDateMonths(int day,int month)
		 {
	    	Calendar c = Calendar.getInstance();
			
			
			 int preMonth = c.get(Calendar.MONTH);
			 preMonth++;
			 month=preMonth+month;
			 c.add(Calendar.DAY_OF_MONTH, day);
			 return (month+"/"+c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.YEAR));
			 
		 }
	    
	    public static boolean isElementPresentWithoutProperties(By by)
		 {
			
		    try {
		      driver.findElement(by);
		     
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		 }
	 
	    public static void typeKeys(String str,Robot r)
	    {
	    	for(int i=0;i<str.length();i++)
	    	{
	    		typeCharacter(r, ""+str.charAt(i));
	    	}
	    }
	    
	    public static void typeCharacter(Robot robot, String letter)
	    {
	    	for(int i =0;i<letter.length();i++)
	    	{
			    try
			    {
				    boolean upperCase = Character.isUpperCase( letter.charAt(0) );
				    char chr = letter.charAt(i);
				    String chrStr=chr+"";
				    String variableName = "VK_" + chrStr.toUpperCase();
				    Class clazz = KeyEvent.class;
				    Field field = clazz.getField( variableName );
				    int keyCode = field.getInt(null);
			
				    robot.delay(1000);
			
				    if (upperCase) robot.keyPress( KeyEvent.VK_SHIFT );
			
				    robot.keyPress( keyCode );
				    robot.keyRelease( keyCode );
				    if(i>2)
				    	break;
			
				    if (upperCase) robot.keyRelease( KeyEvent.VK_SHIFT );
				    }
			    catch(Exception e)
			    {
			    	System.out.println(e);
			    }
			    finally
			    {
			    	robot.keyRelease( KeyEvent.VK_SHIFT );
			    }
	    	}
	    }
	    public static void waitForElementClick(int waitTime,ReadObjectProperties propObject,String proertiesValue)
		{
			LocatorType locator =propObject.getLocatorType(proertiesValue);
			driverWait = new WebDriverWait(driver, waitTime);
			driverWait.until(ExpectedConditions.elementToBeClickable(locator.execute(objLocatorValue)));
			//alertPopup.accept();
			Reporter.log("Element is present");
		}
	    
	    public static  void TypeInField(WebElement element, String value) throws InterruptedException{
	        String val = value; 
	       
	        element.clear();

	        for (int i = 0; i < val.length(); i++){
	            char c = val.charAt(i);
	            String s = new StringBuilder().append(c).toString();
	            element.sendKeys(s);
	            Thread.sleep(1000);
	        }       
	    }
	    
	    public static void handleLoadingImage(int maxTimeInSeconds) throws InterruptedException
        {
           

          
            Thread.sleep(2000);
            //Playback.PlaybackSettings.WaitForReadyLevel = WaitForReadyLevel.UIThreadOnly;
            if (maxTimeInSeconds > 1000)
                maxTimeInSeconds = maxTimeInSeconds / 1000;
            //var sDate = DateTime.Now;
            try
            {

                WebElement  sImgOne = driver.findElement(By.xpath("//*[@class='k-loading-image']"));
                if (sImgOne == null)
                    sImgOne = driver.findElement(By.xpath("//*[@id='spinningManImg']"));

                //var sImgOne1 = GenericFunctions.CreateObject(Global.GlobalBW, "HtmlImage|id=spinningManImg");
                boolean flag = true;
                int i = 0;

                int i1 = 0;
                while (sImgOne != null)
                {

                    Thread.sleep(2000);
                    sImgOne = driver.findElement(By.xpath("//*[@class='k-loading-image']"));
                    if (sImgOne == null)
                        sImgOne = driver.findElement(By.xpath("//*[@id='spinningManImg']"));
                    i++;

                    if (i > maxTimeInSeconds / 2)
                        break;


                }


            }
            catch (Exception ex)
            {
                try
                {


                    WebElement sImgOne = driver.findElement(By.xpath("//*[@id='spinningManImg']"));

                    //var sImgOne1 = GenericFunctions.CreateObject(Global.GlobalBW, "HtmlImage|id=spinningManImg");
                    boolean flag = true;
                    int i = 0;

                    int i1 = 0;
                    while (sImgOne != null)
                    {

                        Thread.sleep(2000);

                        sImgOne = driver.findElement(By.xpath("//*[@id='spinningManImg']"));
                        i++;

                        if (i > maxTimeInSeconds / 2)
                            break;


                    }


                }
                catch (Exception ex1)
                {
                   //ex1.printStackTrace();
                }
               
            }

          
           
        }
	    
	    public static RemoteWebDriver createDriverFromSession(final SessionId sessionId, URL command_executor){
		    CommandExecutor executor = new HttpCommandExecutor(command_executor) {

		    @Override
		    public Response execute(Command command) throws IOException {
		        Response response = null;
		        if (command.getName() == "newSession") {
		            response = new Response();
		            response.setSessionId(sessionId.toString());
		            response.setStatus(0);
		            response.setValue(Collections.<String, String>emptyMap());

		            try {
		                Field commandCodec = null;
		                commandCodec = this.getClass().getSuperclass().getDeclaredField("commandCodec");
		                commandCodec.setAccessible(true);
		                commandCodec.set(this, new W3CHttpCommandCodec());

		                Field responseCodec = null;
		                responseCodec = this.getClass().getSuperclass().getDeclaredField("responseCodec");
		                responseCodec.setAccessible(true);
		                responseCodec.set(this, new W3CHttpResponseCodec());
		            } catch (NoSuchFieldException e) {
		                e.printStackTrace();
		            } catch (IllegalAccessException e) {
		                e.printStackTrace();
		            }

		        } else {
		            response = super.execute(command);
		        }
		        return response;
		    }
		    };

		    return new RemoteWebDriver(executor, new DesiredCapabilities());
		}

	   
}
