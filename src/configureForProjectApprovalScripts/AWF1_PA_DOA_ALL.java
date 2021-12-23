package configureForProjectApprovalScripts;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.Global;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class AWF1_PA_DOA_ALL extends WebDriverHelper
{
	
	ResultSet rs;
	@Test(priority =1)
	public void setApprovalWorkFlowForKINI0002()throws Throwable
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
		Global.resultSet=rs;
		Division.navigateToDivisionDetailPage(rs);
		Division.clickOnApprovalGroupsOnClientDetail();
		/*Division.addApprovalGroupName("Primary Owner");
		handleLoadingImage();
		Common.verifySuccessMessage("Approval group has been added successfully.");
		Division.addContactToApprovalGroup("Primary Owner", rs);*/
		Division.addApprovalWorkFlowForKINI0002();
		Common.logout();
	}
	

}
