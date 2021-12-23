
package com.zc.webdriver.preRequisite;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
//import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class CreateMUMPFB_Pearson extends WebDriverHelper
{
	
	ResultSet rs;
	@Test
	public void createMarkupMatrixPFBForRateCard()throws Throwable
	{
			flag=true;
			String testName=this.getClass().getSimpleName();
			cReport.startTest(testName);
			Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
			waitForElementPresent(60,common, "SEARCH_LINK");
			String sQuery="select * From CreateMarkupMatrix where sTestCaseName='"+testName+"'";
			rs=MSAccessCon.testCon(sQuery);
			Division.navigateToDivisionDetailPage(rs);
			Division.clickOnMarkupMatrixMenuOnClientDetail();
			handleLoadingImage();
			Division.addNewMarkupMatrix(rs);
			Division.addPayCodesInMarkupMatrix(rs);
			Common.logout();
		//Common.login(ResultSet rs);
	}

	

}
