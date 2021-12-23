package com.zc.webdriver.applicationLiberaries;

import static com.zc.webdriver.commonLiberaries.ReadObjectProperties.objLocatorValue;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.contract;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.resourceAdmin;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.zc.webdriver.commonLiberaries.ReadObjectProperties;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class ResourceAdmin extends WebDriverHelper
{

	public static void navigateToProjectListPageFromResourceDetail() throws Throwable
	{
		cReport.testStep("navigateToProjectListPageFromResourceDetail method started");
		handleLoadingImage();
		WebControls.clickOnWebObjects("Link", resourceAdmin, "ProjectTab");
		waitForElementPresent(60,resourceAdmin, "ShowColumnsButton_ProjectsHome");
	}
	
	public static void clickOnCopytoNewLinkOnProjectHome() throws Throwable
	{
		cReport.testStep("clickOnCopytoNewLinkOnProjectHome method started");
		WebControls.clickOnWebObjects("WebButton", resourceAdmin, "ActionIcon_ProjectHome");
		WebControls.clickOnWebObjects("Link", resourceAdmin, "CopytoNewLink_ProjectHome");
		waitForElementPresent(60,resourceAdmin, "ActionIcon_ProjectHome");
		handleLoadingImage();
		WebControls.clickOnWebObjects("WebButton", common, "PopupOKButton");
		waitForElementPresent(60, resourceAdmin, "FlagasDoNotReturnButton_ProjectDetails");
		
	}
	
	public static void setProjectStartEndDates() throws Throwable
	{
		cReport.testStep("setProjectStartEndDates method started");
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebRadioGroup", resourceAdmin, "HasExpirationRadioGroup","true");
		String prjStartDate=AddSubtractDateMonths(8, -1);
		String prjEndDateDate=AddSubtractDateMonths(2, 2);
		WebControls.setValuesForWebObjects("WebEdit", resourceAdmin, "ProjectStartDate_ProjectDetails",prjStartDate);
		WebControls.setValuesForWebObjects("WebEdit", resourceAdmin, "ProjectEndDate_ProjectDetails",prjEndDateDate);
		
		
	}
	
	
	public static void setHoursPerWeekOnProjectDetail() throws Throwable
	{
		cReport.testStep("setHoursPerWeekOnProjectDetail method started");
		WebControls.setValuesForWebObjects("WebEdit", resourceAdmin, "HoursPerWeek_ProjectDetails","40");
		
	}
	
	public static void clickOnContractLinkOnProjectDetail() throws Throwable
	{	
		cReport.testStep("clickOnContractLinkOnProjectDetail method started");
		WebControls.clickOnWebObjects("WebButton", resourceAdmin, "ContractLink_ProjectDetails");
		waitForElementPresent(100, contract, "DivisionDD_ContractSummary");
	}
	
	public static void clickOnPayCodesTabOnProjectDetail() throws Throwable
	{	
		cReport.testStep("clickOnPayCodesTabOnProjectDetail method started");
		WebControls.clickOnWebObjects("Link", resourceAdmin, "PayCodesTab");
		waitForElementPresent(100, resourceAdmin, "PayCodeDDL_PayCodes");
	}
	
	public static void addPayCodeOnPayCodes(String spendLimt) throws Throwable
	{	
		cReport.testStep("addPayCode_PayCodes method started");
		
		WebControls.setValuesForWebObjects("WebEdit", resourceAdmin, "TimeSpendLimit_PayCodes",spendLimt);
		handleLoadingImage(60);
		
		WebControls.clickOnWebObjects("WebButton", resourceAdmin, "ActiveCheckBox_PayCodes");
		handleLoadingImage(60);
								
		
		
	}
	
	public static void clickOnDetailsTabProjectDetail() throws Throwable
	{	
		cReport.testStep("clickOnDetailsTabProjectDetail method started");
		WebControls.clickOnWebObjects("Link", resourceAdmin, "DetailsTab_ProjectDetails");
		waitForElementPresent(100, resourceAdmin, "HoursPerWeek_ProjectDetails");
	}
	
	public static void setResourceRateOnPayCodes(String resRate) throws Throwable
	{	
		cReport.testStep("setResourceRateOnPayCodes method started");
		WebControls.setValuesForWebObjects("WebEdit", resourceAdmin, "ResourceRate_PayCodes",resRate);
		handleLoadingImage();
	}
	
	public static void selectPayCodeOnPayCodes(String payCodeName) throws Throwable
	{	
		cReport.testStep("selectPayCodeOnPayCodes method started");
		WebControls.setValuesForWebObjects("WebList", resourceAdmin, "PayCodeDDL_PayCodes",payCodeName);
		handleLoadingImage(100);
	}
	
	public static void selectPayunitOnPayCodes(String payCodeName) throws Throwable
	{	
		cReport.testStep("selectPayCodeOnPayCodes method started");
		WebControls.setValuesForWebObjects("WebList", resourceAdmin, "PayCodeDDL_PayCodes",payCodeName);
		handleLoadingImage();
	}
	
	
	public static void setCustomerRateOnPayCodes(String customerRate) throws Throwable
	{	
		cReport.testStep("setCustomerRateOnPayCodes method started");
		WebControls.setValuesForWebObjects("WebEdit", resourceAdmin, "CustomerRate_PayCodes",customerRate);
		Thread.sleep(8000);
		handleLoadingImage(100);
	}
	
	public static void setSupplierRateOnPayCodes(String suppRate) throws Throwable
	{	
		cReport.testStep("setSupplierRateOnPayCodes method started");
		WebControls.setValuesForWebObjects("WebEdit", resourceAdmin, "SupplierRate_PayCodes",suppRate);
		Thread.sleep(8000);
		handleLoadingImage(100);
	}
	
	public static void setInactiveThirdActivePayCodeOnPayCodes() throws Throwable
	{	
		cReport.testStep("setInactiveThirdActivePayCodeOnPayCodes method started");
		WebControls.setValuesForWebObjects("WebCheckBox", resourceAdmin, "ThirdPayCodeActiveCheckBox_PayCodes","OFF");
	//	Thread.sleep(8000);
		handleLoadingImage(100);
	}
	
	
	

	
	public static void enterPostalCode(ResultSet rs) throws Throwable
	{
		driver.findElement(By.id("EmergencyContactInfoEdit_EmerPostalCode")).sendKeys("123");
		Thread.sleep(5000);
		driver.findElement(By.id("EmergencyContactInfoEdit_EmerPostalCode")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.id("EmergencyContactInfoEdit_EmerPostalCode")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
	}
	public static void verifySuccessMessageForEmergencyContact(String expectedMsg) throws Throwable
	{
		cReport.testStep("verifySuccessMessage method started");
		String actualString = WebControls.getText("WebElement", resourceAdmin, "EmergencyContactInfosuccessText");
		
		if(expectedMsg.equalsIgnoreCase(actualString))
		{
			cReport.verificationStep("Pass", "Successful Message is coming fine", "sc");
			Reporter.log("Success message is  "+actualString);
			//ReportBuilder.ReportTestStep("Success message is ", "Pass", actualString,"success message");
		}
		else
		{
			cReport.verificationStep("Fail", "Success message is not being appeared ", "sc");
			Reporter.log("Success message is not being appeared  "+actualString);
			//ReportBuilder.ReportTestStep("Success message is ", "Fail", actualString,"success message");
		}
		
	}
	
	public static void clickOnPaycodesAddButton() throws Throwable
	{	
		cReport.testStep("clickOnPaycodesAddButton method started");
		WebControls.clickOnWebObjects("WebButton", resourceAdmin, "AddPaycodeButton_Paycodes");
	//	Thread.sleep(8000);
		handleLoadingImage(60);
	}
	
	public static String getValueFromSupplierRate() throws Throwable
	{
		cReport.testStep("getValueFromSupplierRate method started");
		String keyDefaultApprover=SetProperpertiesFile.resourceAdmin.getPropertyValue("SupplierRate_PayCodes");
		String xpathValueDefaultApprover=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyDefaultApprover);
		String supplierRate = driver.findElement(By.id(xpathValueDefaultApprover)).getAttribute("value");
		return supplierRate;
	}
	
	
	
}
