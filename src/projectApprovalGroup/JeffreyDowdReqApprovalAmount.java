package projectApprovalGroup;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.*;
import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class JeffreyDowdReqApprovalAmount extends WebDriverHelper
{
	
	ResultSet rs;
	@Test(priority =1)
	public void setReqApprovalAmount()throws Throwable
	{
		SetProperpertiesFile.setAllProperpertiesFile();
		flag=true;
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		waitForElementPresent(100,common, "SEARCH_LINK");
		Common.quickSearch("Customer Contacts", "Jeffrey.Dowd@rci.com");
		handleLoadingImage();
		driver.findElement(By.id("ctl00_contplhDynamic_CreateUpdateContactUsercontrol_PhoneDetails_PhoneNoField")).clear();
		driver.findElement(By.id("ctl00_contplhDynamic_CreateUpdateContactUsercontrol_PhoneDetails_PhoneNoField")).sendKeys("2345634232");
		WebControls.setValuesForWebObjects("WebEdit", division, "Create_UpdateContact_txtReqApprovalAmount","0.00");
		
		driver.findElement(By.id("ctl00_contplhDynamic_CreateUpdateContactUsercontrol_Li1")).click();
		handleLoadingImage();
		
		Common.logout();
	}

}
