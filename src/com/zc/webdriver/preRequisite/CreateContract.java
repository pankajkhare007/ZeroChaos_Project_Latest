
package com.zc.webdriver.preRequisite;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.contract;

import java.sql.ResultSet;
import org.testng.annotations.Test;
import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Contract;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
//import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class CreateContract extends WebDriverHelper
{
	
	ResultSet rs;
	@Test
	public void createContractMethod()throws Throwable
	{
		flag=true;
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		waitForElementPresent(60,common, "SEARCH_LINK");
		String queryForDuplicateRecords= "Select count(*) From CreateContract Where sTestCaseName = '"+testName+"'";
		rs=MSAccessCon.testCon(queryForDuplicateRecords);
		int dupRecords=rs.getInt(1);
		String sQuery="select * From CreateContract where sTestCaseName='"+testName+"'";
	
		for(int i=0; i<dupRecords;i++ )
		{
			rs=MSAccessCon.testCon(sQuery);
			flag=false;
			
			WebControls.clickOnWebObjects("Link", common, "SetupLink");
			WebControls.clickOnWebObjects("Link", common, "ContractsMenu");
			waitForElementPresent(100, contract, "DivisionDD_ContractSummary");
			Contract.clickOnAddNewContractButton();
			Contract.fillContractGeneralPage(rs);
			Contract.fillContractEnrollmentPagePage(rs);
			
		}
		
		Common.logout();			
	
	}
		
			
			
		//Common.login(ResultSet rs);
}
