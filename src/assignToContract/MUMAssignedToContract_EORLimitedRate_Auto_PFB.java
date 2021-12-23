package assignToContract;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Contract;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;


public class MUMAssignedToContract_EORLimitedRate_Auto_PFB extends WebDriverHelper
{
	
	ResultSet rs;
	@Test
	public void assignMUMAuto_EORLimitedRate_Auto_PFB()throws Throwable
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
		Common.logout();		
		//driver.manage().timeouts().setScriptTimeout(arg0, arg1)

	}
}