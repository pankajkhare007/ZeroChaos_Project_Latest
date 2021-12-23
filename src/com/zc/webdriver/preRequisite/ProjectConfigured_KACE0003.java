package com.zc.webdriver.preRequisite;



import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Contract;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.applicationLiberaries.ResourceAdmin;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class ProjectConfigured_KACE0003 extends WebDriverHelper{

	ResultSet rs;
	@Test(priority =1)
	public void projectAndContractSetting()throws Throwable
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
			Common.searchByResourceID(rs);
			ResourceAdmin.navigateToProjectListPageFromResourceDetail();
			ResourceAdmin.clickOnCopytoNewLinkOnProjectHome();
			ResourceAdmin.setProjectStartEndDates();
			ResourceAdmin.setHoursPerWeekOnProjectDetail();
			Common.clickOnSaveButton();		
			handleLoadingImage();
			Common.verifySuccessMessage("Data updated successfully.");	
			
			
			ResourceAdmin.clickOnPayCodesTabOnProjectDetail();
			
			ResourceAdmin.setInactiveThirdActivePayCodeOnPayCodes();
			
			Common.clickOnSaveButton();
			
			ResourceAdmin.selectPayCodeOnPayCodes("Double Time");
			//ResourceAdmin.setCustomerRateOnPayCodes("110");
			
			ResourceAdmin.setResourceRateOnPayCodes("100");
			
			ResourceAdmin.addPayCodeOnPayCodes("500000");
						
			Common.clickOnAddButton();
			
			driver.navigate().refresh();
			handleLoadingImage();
			
			ResourceAdmin.clickOnDetailsTabProjectDetail();
			
			ResourceAdmin.clickOnContractLinkOnProjectDetail();
			
			Contract.clickOnPayrollTabOnContract();
			Contract.setEnableResourceRateOnContractPayroll("Yes");
			Common.clickOnSaveButton();
			Common.verifySuccessMessage("Data saved successfully.");	
			
			driver.navigate().refresh();
			handleLoadingImage();
			
			Contract.clickOnTimeExpenseTabOnContract();
			Contract.setAllowFutureDatedManagerApproval_ContractTimeExp("OFF");
			Common.clickOnSaveButton();
			
			driver.navigate().refresh();
			handleLoadingImage();
			
			Contract.clickOnAccountingTabOnContractAccounting();
			Contract.setEnforceMUMRuleOnContractAccounting("OFF");
			Contract.setRequireMUMRuleOnContractAccounting("OFF");
			Common.clickOnSaveButton();
			Common.verifySuccessMessage("Data saved successfully.");
			
			Common.logout();
			
						
		//Common.login(ResultSet rs);
	}



}
