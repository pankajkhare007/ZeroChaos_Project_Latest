package projectApprovalGroup;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;

import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class AaronAdsitReqApprovalAmount extends WebDriverHelper
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
		Common.quickSearch("Customer Contacts", "Aaron.Adsit@wyndhamvo.com");
		handleLoadingImage();
	//	WebControls.setValuesForWebObjects("WebList", division, "CreateUpdateContacts_ReportGroupDDL","Hiring Manager MSP");
		
		WebControls.setValuesForWebObjects("WebEdit", division, "Create_UpdateContact_txtReqApprovalAmount","3000");
		handleLoadingImage();
		//Common.clickOnSaveButton();
		driver.findElement(By.id("ctl00_contplhDynamic_CreateUpdateContactUsercontrol_Li1")).click();
		handleLoadingImage();
		Common.logout();
	}

}
