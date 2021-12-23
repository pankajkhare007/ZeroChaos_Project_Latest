
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

public class CreateRateCard_FixBillMaxMarkup1 extends WebDriverHelper{

	ResultSet rs;
	@Test
	public void createRateCardFBMM1ForPearson()throws Throwable
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
			Division.navigateToRateCardPage(rs);
			Division.addRateCardGroup(rs);
			Division.addNewRateCardForFixBillMaxMarkup(rs, "OFF", "30");;
			Division.selectSupplierNameOnRateCardDetail("Aquent");
			handleLoadingImage();
			WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
			handleLoadingImage();
			Common.verifySuccessMessage("New Rate Card successfully added.");	
			Common.logout();
						
		//Common.login(ResultSet rs);
	}



}
