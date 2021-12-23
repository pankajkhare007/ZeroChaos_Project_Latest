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

public class CreateMUM extends WebDriverHelper
{
	
	ResultSet rs;
	@Test
	public void createMarkupMatrixForDivision()throws Throwable
	{
			flag=true;
			String testName=this.getClass().getSimpleName();
			cReport.startTest(testName);
			Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
			waitForElementPresent(60,common, "SEARCH_LINK");
			String queryForDuplicateRecords= "Select count(*) From CreateMarkupMatrix Where sTestCaseName = '"+testName+"'";
			rs=MSAccessCon.testCon(queryForDuplicateRecords);
			int dupRecords=rs.getInt(1);
		
			String sQuery="select * From CreateMarkupMatrix where sTestCaseName='"+testName+"'";
			
			for(int i=0; i<dupRecords;i++ )
			{
				rs=MSAccessCon.testCon(sQuery);
				flag=false;
				Division.navigateToDivisionDetailPage(rs);
				Division.clickOnMarkupMatrixMenuOnClientDetail();
			
				handleLoadingImage();
				if(rs.getString("sDivisionName").equals("Jackson Health System"))
				{
					
					Division.addNewMarkupMatrix(rs);
					Division.addPayCodesInMarkupMatrix(rs);
					
				}
				else if(rs.getString("sDivisionName").equals("NetApp"))
				{
					
					Division.addNewMarkupMatrix(rs);
					Division.addPayCodesInMarkupMatrix(rs);
					
				}
				
			}
		
			
			Common.logout();
		//Common.login(ResultSet rs);
	}

	

}
