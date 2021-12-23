
package configure_WRIG0483;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;

import java.sql.ResultSet;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
//import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class RemoveRequiredCustomFields_WRIG0483 extends WebDriverHelper
{
	
	ResultSet rs;
	@Test(priority =1)
	public void removeRequiredFieldsOnClientUserDefinedFields()throws Throwable
	{
			flag=true;
			String testName=this.getClass().getSimpleName();
			cReport.startTest(testName);
			Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
			waitForElementPresent(60,common, "SEARCH_LINK");
		Thread.sleep(10000);
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			
		
				rs=MSAccessCon.testCon(sQuery);
				
				Division.navigateToDivisionDetailPage(rs);
				Division.clickOnApprovalGroupsOnClientDetail();
				Division.clickOnCustomDefinedFieldsMenuOnClientDetail();
				waitForElementPresent(100, division, "Object_ApprovalFlowSetup");
				handleLoadingImage();
			
			
				Division.selectObjectOnClientUserDefinedFields(rs.getString("sObjectName"));
				Division.uncheckAllCustomeRequiredFields();
			
				Common.clickOnSaveButton();
				Thread.sleep(20000);
				Common.verifySuccessMessage("Updated successfully.");
			/*	
				Common.clickOnSaveButton();
				handleLoadingImage();
				waitForElementPresent(100, common, "SUCCESS_MESSAGE");
				Thread.sleep(20000);
				Common.verifySuccessMessage("Updated successfully.");
			*/
		
			
			Common.logout();
		//Common.login(ResultSet rs);
			
		
			
	}

	

}
