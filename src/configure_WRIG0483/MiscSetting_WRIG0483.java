package configure_WRIG0483;


import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;

import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class MiscSetting_WRIG0483 extends WebDriverHelper
{
	
	ResultSet rs;
	@Test(priority =1)
	public void setWorkOrderAndOfferLetterOnMiscSetting()throws Throwable
	{
			flag=true;	
			String testName=this.getClass().getSimpleName();
			cReport.startTest(testName);
			Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
			waitForElementPresent(90,common, "SEARCH_LINK");
			//Thread.sleep(10000);;
			//String queryForDuplicateRecords= "Select count(*) From PreRequisiteForProject Where sTestCaseName = '"+testName+"'";
			//rs=MSAccessCon.testCon(queryForDuplicateRecords);
		//	int dupRecords=rs.getInt(1);
		
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			
			rs=MSAccessCon.testCon(sQuery);
			flag=false;
			Division.navigateToDivisionDetailPage(rs);
			Division.clickOnApprovalGroupsOnClientDetail();
			Division.clickOnMiscSettionMenuOnClientDetail();
			Division.configureProjectOptionSectionForAmendment();
			Division.setDisplayAmendmentReasonOnProjectAmendment_Misc("OFF");
			Division.setInitialOfferLetterOnMisc("-SELECT-");
			Division.setAmendedOfferLetterOnMisc("Amended Offer Letter");
			Division.setOverrideOfferLetterWorkOrderOnRateChangeOnMisc("OFF");
			Division.setAutoTerminatateProjectAndResource("ON");
			Division.clickOnSaveButtonOnMiscSetting();
			handleLoadingImage();
			Common.verifySuccessMessage("Data updated successfully.");
			
		
			Common.logout();
		//Common.login(ResultSet rs);
	}

	

}
