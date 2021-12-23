package com.zc.webdriver.preRequisite.approvalWorkFlow;


import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;

import java.sql.ResultSet;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class Toyota_ApprovalWorkFlow extends WebDriverHelper{

	ResultSet rs;
	@Test
	public void setAWF_Toyota()throws Throwable
	{
		//Set Initial Work order , Amended Offer Letter and Pepco Holdings-Beeline as per division
		  		
			SetProperpertiesFile.setAllProperpertiesFile();
			
			
			flag=true;
			
			String testName=this.getClass().getSimpleName();
			cReport.startTest(testName);
			Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
			waitForElementPresent(100,common, "SEARCH_LINK");
		
			//Common.setDate(elem,-60);
			
			//driver.switchTo().activeElement();
			driver.findElement(By.xpath("//*[@class='btn submitbtn']")).click();
			
			
			
			//Thread.sleep(10000);
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			rs=MSAccessCon.testCon(sQuery);
			Division.navigateToDivisionDetailPage(rs);
			waitForElementPresent(60, division, "ApprovalWorkFlowTab_ClientDetails");
			Division.clickOnApprovalGroupsOnClientDetail();
			Division.addApprovalGroupName("Approval Owner");
			handleLoadingImage();
			Common.verifySuccessMessage("Approval group has been added successfully.");
			Division.addContactToApprovalGroup("Approval Owner", rs);
			Division.addApprovalWorkFlow_PMO(rs,"=");
			Common.logout();
						
		//Common.login(ResultSet rs);
	}



}

