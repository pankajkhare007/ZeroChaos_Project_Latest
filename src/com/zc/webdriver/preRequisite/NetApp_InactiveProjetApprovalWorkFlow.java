package com.zc.webdriver.preRequisite;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class NetApp_InactiveProjetApprovalWorkFlow extends WebDriverHelper{

	ResultSet rs;
	@Test(priority =1)
	public void inactiveProjectApprovalWorkFlows()throws Throwable
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
			waitForElementPresent(60, division, "ApprovalWorkFlowTab_ClientDetails");
			Division.clickOnApprovalSetupOnClientDetail();
		//	handleLoadingImage();
			waitForElementPresent(60, division, "Object_ApprovalFlowSetup");
			WebControls.setValuesForWebObjects("WebList", division, "Object_ApprovalFlowSetup", rs.getString("sObjectName"));
			handleLoadingImage();
			Division.removeApprovalWork();
						
			Common.logout();
						
		//Common.login(ResultSet rs);
	}



}
