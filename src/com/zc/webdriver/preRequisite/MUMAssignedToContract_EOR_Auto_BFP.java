
package com.zc.webdriver.preRequisite;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import java.sql.ResultSet;
import org.testng.annotations.Test;
import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Contract;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
//import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class MUMAssignedToContract_EOR_Auto_BFP extends WebDriverHelper
{
	
	ResultSet rs;
	@Test
	public void assignMUMEOR_Auto_BFPToContract()throws Throwable
	{
		flag=true;
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		waitForElementPresent(60,common, "SEARCH_LINK");
			
		String sQuery="select * From CreateMarkupMatrix where sTestCaseName='"+testName+"'";
	
		rs=MSAccessCon.testCon(sQuery);
		Contract.navigateToContractDetailPage(rs);	
		Contract.assignMUMOnCotractAccounting(rs);
		handleLoadingImage();
		Common.verifySuccessMessage("Markup matrix is assigned successfully.");
		Common.logout();			

	}
		
			
			
		//Common.login(ResultSet rs);
}
