package configure_KACE0003;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.resourceAdmin;

import java.sql.ResultSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.applicationLiberaries.ResourceAdmin;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

@Listeners(com.zc.webdriver.commonLiberaries.Listener.class)
public class Active_Kace0003 extends WebDriverHelper
{
	
		ResultSet rs;
		@Test(priority =1)
		public void makeKACE0003Active()throws Throwable
		{
			//Set Initial Work order , Amended Offer Letter and Pepco Holdings-Beeline as per division
			  		
			SetProperpertiesFile.setAllProperpertiesFile();
			flag=true;
			
			String testName=this.getClass().getSimpleName();
			cReport.startTest(testName);
			invokeBrowser();
			//Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		//	handleLoadingImage();
		//	waitForElementPresent(100,common, "AdminDashboard_DDLDivision");
			
			//String status = Common.getDataFromTable("AdminProjectSearchGrid", "Division Summary", "1215168").getText();
			//System.out.println(status);
			//WebElement ele =Common.getDataFromTable("entryDetails", 3, "Regular Time");
			WebElement ele = Common.getRowElementByCellTextDataFromTable("entryDetails", "Regular Time");
			
			highLightElement(ele);
			String xpath1 = getXPath(ele);
			
			WebElement ele1 = ele.findElement(By.xpath("//*[@class='m-wrap xsmall NumberofHrs decimalField']"));
			highLightElement(ele1);
			ele1.sendKeys("100");
			
		
			
			
			//Division.addApprovalWorkFlow(rs,"=");
		}
}
