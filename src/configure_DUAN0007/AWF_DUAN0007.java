package configure_DUAN0007;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;

import java.sql.ResultSet;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

@Listeners(com.zc.webdriver.commonLiberaries.Listener.class)
public class AWF_DUAN0007 extends WebDriverHelper
{
	
	ResultSet rs;
	@Test(priority =1)
	public void setApprovalWorkFlowForDUAN0007()throws Throwable
	{
		//Set Initial Work order , Amended Offer Letter and Pepco Holdings-Beeline as per division
		  		
		SetProperpertiesFile.setAllProperpertiesFile();
		flag=true;
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		waitForElementPresent(100,common, "SEARCH_LINK");
		//Thread.sleep(10000);
		String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
		rs=MSAccessCon.testCon(sQuery);
		Division.navigateToDivisionDetailPage(rs);
		Division.clickOnApprovalGroupsOnClientDetail();
		waitForElementPresent(100, common, "ADD_BUTTON");
		Division.addApprovalGroupName("Primary Owner");
		handleLoadingImage();
		Common.verifySuccessMessage("Approval group has been added successfully.");
		Division.addContactToApprovalGroup("Primary Owner", rs);
		Division.addApprovalWorkFlow(rs,"=");
	}
	
	

}
