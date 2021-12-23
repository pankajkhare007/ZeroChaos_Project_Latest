package createApprovalGroup;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class ApprovalGroup_TRC_GM_Toyota extends WebDriverHelper
{
	
	ResultSet rs;
	@Test(priority =1)
	public void createAG_TRCGMForToyota()throws Throwable
	{
		//Set Initial Work order , Amended Offer Letter and Pepco Holdings-Beeline as per division
		  		
		SetProperpertiesFile.setAllProperpertiesFile();
		flag=true;
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		waitForElementPresent(60,common, "SEARCH_LINK");
		//Thread.sleep(10000);
		String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
		rs=MSAccessCon.testCon(sQuery);
		Division.navigateToDivisionDetailPage(rs);
		Division.clickOnApprovalGroupsOnClientDetail();
		Division.addApprovalGroupName(rs);
		Division.addContactToApprovalGroup("Primary Owner", rs);
		Common.logout();
		//Division.addApprovalWorkFlow(rs,"=");
	}
	
	

}