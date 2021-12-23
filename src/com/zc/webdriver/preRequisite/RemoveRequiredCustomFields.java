
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

public class RemoveRequiredCustomFields extends WebDriverHelper
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
			String queryForDuplicateRecords= "Select count(*) From PreRequisiteForProject Where sTestCaseName = '"+testName+"'";
			rs=MSAccessCon.testCon(queryForDuplicateRecords);
			int dupRecords=rs.getInt(1);
		
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			
			for(int i=0; i<dupRecords;i++ )
			{
				rs=MSAccessCon.testCon(sQuery);
				flag=false;
				Division.navigateToDivisionDetailPage(rs);
				
				Division.clickOnCustomDefinedFieldsMenuOnClientDetail();
				waitForElementPresent(100, division, "Object_ApprovalFlowSetup");
				handleLoadingImage();
				if(rs.getString("sDivisionName").equals("Pepco Holdings-Beeline"))
				{
					
					Division.selectObjectOnClientUserDefinedFields(rs.getString("sObjectName"));
					Division.uncheckAllCustomeRequiredFields();
					
				}
				else if(rs.getString("sDivisionName").equals("Iberdrola USA"))
				{
				
					Division.selectObjectOnClientUserDefinedFields(rs.getString("sObjectName"));
					Division.uncheckAllCustomeRequiredFields();
				}
				
				else if(rs.getString("sDivisionName").equals("Jackson Health System"))
				{
				
					//Division.selectObjectOnClientUserDefinedFields(rs.getString("sObjectName"));
					Division.uncheckAllCustomeRequiredFields();
				}
				else if(rs.getString("sDivisionName").equals("NetApp"))
				{
					
					Division.uncheckAllCustomeRequiredFields();
					Common.clickOnSaveButton();
					handleLoadingImage();
					waitForElementPresent(100, common, "SUCCESS_MESSAGE");
					Thread.sleep(20000);
					Common.verifySuccessMessage("Updated successfully.");
					
					Division.selectObjectOnClientUserDefinedFields("Enrollment");
					handleLoadingImage();
					Division.uncheckAllCustomeRequiredFields();
					Common.clickOnSaveButton();
					Thread.sleep(20000);
					Common.verifySuccessMessage("Updated successfully.");
					
					Division.selectObjectOnClientUserDefinedFields("Project");
					handleLoadingImage();
					Division.uncheckAllCustomeRequiredFields();
					Common.clickOnSaveButton();
					Thread.sleep(20000);
					Common.verifySuccessMessage("Updated successfully.");
					
					
				}
				else if(rs.getString("sDivisionName").equals("CBSi"))
				{
				
					Division.selectObjectOnClientUserDefinedFields(rs.getString("sObjectName"));
					Division.uncheckAllCustomeRequiredFields();
				}
				Common.clickOnSaveButton();
				handleLoadingImage();
				waitForElementPresent(100, common, "SUCCESS_MESSAGE");
				Thread.sleep(20000);
				Common.verifySuccessMessage("Updated successfully.");
			}
		
			
			Common.logout();
		//Common.login(ResultSet rs);
	}

	

}
