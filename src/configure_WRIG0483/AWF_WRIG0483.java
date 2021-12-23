package configure_WRIG0483;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;

import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;
@Listeners(com.zc.webdriver.commonLiberaries.Listener.class)
public class AWF_WRIG0483 extends WebDriverHelper
{
	
	ResultSet rs;
	@Test(priority =1)
	public void setApprovalWorkFlowForWRIG0483()throws Throwable
	{
		//Set Initial Work order , Amended Offer Letter and Pepco Holdings-Beeline as per division
		  		
		SetProperpertiesFile.setAllProperpertiesFile();
		flag=true;
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		
		
	/*	invokeBrowser();
		String sQuery1="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
		rs=MSAccessCon.testCon(sQuery1);
		Division.navigateToDivisionDetailPage(rs);*/
		
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		handleLoadingImage();
	
		String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
		rs=MSAccessCon.testCon(sQuery);
		Division.navigateToDivisionDetailPage(rs);
		Division.clickOnApprovalGroupsOnClientDetail();
		Division.addApprovalGroupName("Primary Owner");
		handleLoadingImage();
		Common.verifySuccessMessage("Approval group has been added successfully.");
		Division.addContactToApprovalGroup("Primary Owner", rs);
		Division.addApprovalWorkFlow(rs,"=");
		Common.logout();
	}
	
	

}
