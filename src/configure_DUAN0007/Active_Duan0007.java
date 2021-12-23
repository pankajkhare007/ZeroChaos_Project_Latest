package configure_DUAN0007;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.resourceAdmin;

import java.sql.ResultSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.applicationLiberaries.ResourceAdmin;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

//@Listeners(com.zc.webdriver.commonLiberaries.Listener.class)
public class Active_Duan0007 extends WebDriverHelper
{
	
		ResultSet rs;
		@Test(priority =1)
		public void makeDUAN0007Active()throws Throwable
		{
			//Set Initial Work order , Amended Offer Letter and Pepco Holdings-Beeline as per division
			//invokeBrowser();
//			List<WebElement> lst=driver.findElements(By.xpath("//li[contains(class,icon-)]"));
//			WebElement ele = driver.findElement(By.xpath("//li[contains(class,icon-)]//a//span[text()='Talent']"));
//			ele.click();
////			Actions act = new Actions(driver);
////			act.moveToElement(ele);
			//Thread.sleep(5000);
			invokeBrowser();
			
			driver.navigate().to("https://portaldev2.telnetww.com:9081/sso/clientportal/login/");
			Thread.sleep(10000);
			driver.findElement(By.id("Username")).sendKeys("nandita@mscognition.com");
			driver.findElement(By.id("Password")).sendKeys("Telnet$1100");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    Thread.sleep(10000);
		    driver.findElement(By.xpath("//span[contains(text(),'Services')]")).click();
		    driver.findElement(By.xpath("//ul[@id='servicesSubMenu']/li/a")).click();
		    
		}
}
