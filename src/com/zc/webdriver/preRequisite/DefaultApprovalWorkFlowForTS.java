
package com.zc.webdriver.preRequisite;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class DefaultApprovalWorkFlowForTS extends WebDriverHelper{

	ResultSet rs;
	@Test
	public void setDefaulApprovalWorkFlowForTS()throws Throwable
	{
		//Set Initial Work order , Amended Offer Letter and Pepco Holdings-Beeline as per division
		  		
			SetProperpertiesFile.setAllProperpertiesFile();
			flag=true;
			
			String testName=this.getClass().getSimpleName();
			cReport.startTest(testName);
			Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
			waitForElementPresent(60,common, "SEARCH_LINK");
			
			String queryForDuplicateRecords= "Select count(*) From PreRequisiteForProject Where sTestCaseName = '"+testName+"'";
			rs=MSAccessCon.testCon(queryForDuplicateRecords);
			int dupRecords=rs.getInt(1);
			
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			
			for(int i=0; i<dupRecords;i++ )
			{
				rs=MSAccessCon.testCon(sQuery);
				flag=false;
				rs=MSAccessCon.testCon(sQuery);
				
			//	waitForElementPresent(60, division, "ApprovalWorkFlowTab_ClientDetails");
				if(rs.getString("sDivisionName").equals("Jackson Health System"))
				{
					Division.navigateToDivisionDetailPage(rs);
					Division.addDefaulApprovalWorkFlowForTS_JHS(rs,">=","Default Rule (Amount>=0)");
				}
				else if(rs.getString("sDivisionName").equals("NetApp"))
				{
					Division.navigateToDivisionDetailPage(rs);
					Division.addDefaulApprovalWorkFlowForTS_JHS(rs,">=","Default Rule (Amount>=0)");
					
				}
				
			}
			Common.logout();
						
		//Common.login(ResultSet rs);
	}



}

