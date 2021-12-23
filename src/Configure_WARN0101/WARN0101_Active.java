package Configure_WARN0101;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.resourceAdmin;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Contract;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class WARN0101_Active extends WebDriverHelper {
	
	ResultSet rs;
	@Test
	public void makeActive() throws Throwable
	{
		SetProperpertiesFile.setAllProperpertiesFile();
		flag=true;
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		handleLoadingImage();
		
		//waitForElementPresent(100,common, "DivisionDD_AdminDashboard");
		String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
		rs=MSAccessCon.testCon(sQuery);
		Contract.setEnforceAndRequieMUMOnAccountPage(rs, "Epicor Software Canada", 0, 0);
		Common.searchByResourceID(rs);
		WebControls.clickOnWebObjects("WebButton", resourceAdmin, "ActivateLink_AdminResource");
		handleLoadingImage();
		Contract.setEnforceAndRequieMUMOnAccountPage(rs, "Epicor Software Canada", 0, 0);
		
		Common.logout();
	}

}
