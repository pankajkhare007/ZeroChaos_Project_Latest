package com.zc.webdriver.preRequisite.approvalWorkFlow;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;

import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class AddApprovalAmount_JasonHiner extends WebDriverHelper
{
	
	ResultSet rs;
	@Test(priority =1)
	public void addApprovalAmountToContact()throws Throwable
	{
		//Set Initial Work order , Amended Offer Letter and Pepco Holdings-Beeline as per division
		  		
		SetProperpertiesFile.setAllProperpertiesFile();
		flag=true;
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		waitForElementPresent(100,common, "SEARCH_LINK");
		//Thread.sleep(10000);
		String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
		rs=MSAccessCon.testCon(sQuery);
		Division.navigateToDivisionDetailPage(rs);
	
		Division.addNewApprovalWorkFlow(rs);
		Common.logout();
		/*System.setProperty("webdriver.gecko.driver", "lib/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://qasaturn.zcdev.net/ZCW/AdminProject/Edit?TabId=1");
		driver.findElement(By.id("Username")).sendKeys("1445");*/
	}
	
	

}
