package com.zc.webdriver.applicationLiberaries;

import java.sql.ResultSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.zc.webdriver.commonLiberaries.ConnectionWithSQLServer;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.ReadObjectProperties;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.*;;

public class Contract extends WebDriverHelper
{

	public static void navigateToContractDetailPage(ResultSet rs)throws Throwable
	{
		cReport.testStep("navigateToContractDetailPage method started");
		WebControls.clickOnWebObjects("Link", common, "SetupLink");
		WebControls.clickOnWebObjects("Link", common, "ContractsMenu");
		waitForElementPresent(100, contract, "DivisionDD_ContractSummary");
		WebControls.setValuesForWebObjects("WebList", contract, "DivisionDD_ContractSummary",rs.getString("sDivisionName"));
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", contract, "StatusDD_ContractSummary","Approved");
		handleLoadingImage();
		
		WebControls.setValuesForWebObjects("WebEdit", contract, "ComboBoxContractName_ContractSummary", rs.getString("sContractName"));
		WebControls.setValuesForWebObjects("SuggetionEditBox", contract, "ComboBoxContractName_ContractSummary", rs.getString("sContractName"));
		handleLoadingImage();
		WebControls.clickOnLinkObjects(rs.getString("sContractName"));
		
		waitForElementPresent(100, contract, "ContractName_ContractGeneral");
		
	}
	public static void clickOnTimeExpenseTabOnContract()throws Throwable
	{
		cReport.testStep("clickOnTimeExpenseTabOnContract method started");
		WebControls.clickOnWebObjects("Link", contract, "TimeExpenseEngagementTab");
		handleLoadingImage();
		waitForElementPresent(100, contract, "ManagerAddTime_ContractTimeExp");
		
	}
	
	public static void assignMUMOnCotractAccounting(ResultSet rs)throws Throwable
	{
		cReport.testStep("assignMUMOnCotractAccounting method started");
		WebControls.clickOnWebObjects("Link", contract, "AccountingTab");
		handleLoadingImage();
		waitForElementPresent(100, contract, "EnforcedCheckBox_ContractAccounting");
		WebControls.setValuesForWebObjects("WebCheckBox", contract, "EnforcedCheckBox_ContractAccounting", "ON");
		
		String keyAvailMUMTable=SetProperpertiesFile.contract.getPropertyValue("AvailableMUMTable_ContractAccounting");
		String xpathAvailMUMTable=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyAvailMUMTable);
		WebElement tableMUM=driver.findElement(By.xpath(xpathAvailMUMTable));
		List<WebElement> rows_table = tableMUM.findElements(By.tagName("tr"));
		for(int i=0; i < rows_table.size() ; i++)
		{
			 List<WebElement> columns = rows_table.get(i).findElements(By.tagName("td"));
			 int columnInRow=columns.size();
			 for(int j=0;j<columnInRow;j++)
			 {
				String MUMName = columns.get(j).getText();
				if((MUMName.trim()).equalsIgnoreCase(rs.getString("sMUM_Name")))
				{
					String elementXpath = getXPath(columns.get(columnInRow-2));
					String actionExactPath = elementXpath+"/a";
					
					System.out.println("xpath ::: "+elementXpath);
					Actions action = new Actions(driver);
					action.moveToElement(driver.findElement(By.xpath(actionExactPath))).build().perform();
					Thread.sleep(500);
					List<WebElement> linkList=driver.findElements(By.partialLinkText("Assign Markup Matrix"));
					int k =0;
					for(WebElement ele : linkList)
					{
						if(k==1)
						{
							ele.click();
							handleLoadingImage();
						}
						k++;
					}
					
					break;
				}
			 }
		 }
		
	}
	
	public static void configureManagerAddTimesheetOnContractTimeExp(String checkUncheckFlag)throws Throwable
	{
		cReport.testStep("configureManagerAddTimesheetOnContractTimeExp method started");
		WebControls.setValuesForWebObjects("WebCheckBox", contract, "ManagerAddTime_ContractTimeExp", checkUncheckFlag);
	}
	
	
	public static void clickOnAddNewContractButton()throws Throwable
	{
		cReport.testStep("clickOnAddNewContractButton method started");
		WebControls.clickOnWebObjects("WebButton", contract, "AddNewContract_ontractSummary");
		waitForElementPresent(100, contract, "ContractCode_ContractGeneral");
		
	}
	public static void fillContractGeneralPage(ResultSet rs)throws Throwable
	{
		cReport.testStep("fillContractGeneralPage method started");
		WebControls.setValuesForWebObjects("WebEdit", contract, "ContractName_ContractGeneral",rs.getString("sContractName"));
		WebControls.setValuesForWebObjects("WebList", contract, "ContractDivision_ContractGeneral",rs.getString("sDivisionName"));
		WebControls.setValuesForWebObjects("WebEdit", contract, "ContractCode_ContractGeneral",rs.getString("sCode"));
		WebControls.setValuesForWebObjects("WebList", contract, "InvoiceRelationship_ContractGeneral",rs.getString("InvoiceRelationship"));
		WebControls.setValuesForWebObjects("WebEdit", contract, "Description_ContractGeneral",rs.getString("Description"));
		WebControls.setValuesForWebObjects("WebList", contract, "Status_ContractGeneral","Draft");
		Thread.sleep(20000);
		Common.clickOnSaveButton();
		handleLoadingImage();
		Common.verifySuccessMessage("Data saved successfully.");
	
	}
	public static void fillContractEnrollmentPagePage(ResultSet rs)throws Throwable
	{
		cReport.testStep("fillContractEnrollmentPagePage method started");
		WebControls.setValuesForWebObjects("WebList", contract, "BIRequired_ContractEnrollmen",rs.getString("bIsBIRequired"));
		Common.clickOnSaveButton();
		handleLoadingImage();
		Common.verifySuccessMessage("Data saved successfully.");
	}
	
	public static void clickOnPayrollTabOnContract()throws Throwable
	{
		cReport.testStep("clickOnPayrollTabOnContract method started");
		WebControls.clickOnWebObjects("Link", contract, "PayrollTab");
		handleLoadingImage();
		waitForElementPresent(100, contract, "EnableDisableResourceRate_ContractPayroll");
		
	}
	
	public static void setEnableResourceRateOnContractPayroll(String YesNo)throws Throwable
	{
		cReport.testStep("setEnableResourceRateOnContractPayroll method started");
		WebControls.setValuesForWebObjects("WebList", contract, "EnableDisableResourceRate_ContractPayroll",YesNo);
					
	}
	
	public static void clickOnAccountingTabOnContractAccounting()throws Throwable
	{
		cReport.testStep("clickOnAccountingTabTabOnContractAccounting method started");
		WebControls.clickOnWebObjects("Link", contract, "AccountingTab");
		handleLoadingImage();
		waitForElementPresent(100, contract, "EnforcedCheckBox_ContractAccounting");
				
	}
	
	public static void setEnforceMUMRuleOnContractAccounting(String OnOff)throws Throwable
	{
		cReport.testStep("setEnforceMUMRuleOnContractAccounting method started");
		WebControls.setValuesForWebObjects("WebCheckBox", contract, "EnforcedCheckBox_ContractAccounting",OnOff);
					
	}
	public static void setRequireMUMRuleOnContractAccounting(String OnOff)throws Throwable
	{
		cReport.testStep("setRequireMUMRuleOnContractAccounting method started");
		WebControls.setValuesForWebObjects("WebCheckBox", contract, "RequiredCheckBox_ContractAccounting",OnOff);
					
	}
	
	public static void setAllowFutureDatedManagerApproval_ContractTimeExp(String OnOff)throws Throwable
	{
		cReport.testStep("setAllowFutureDatedManagerApproval_ContractTimeExp method started");
		WebControls.setValuesForWebObjects("WebCheckBox", contract, "AllowFutureDatedManagerApproval_ContractTimeExp",OnOff);
		handleLoadingImage();
					
	}
	
	public static void clickOnGeneralTabOnContract()throws Throwable
	{
		cReport.testStep("clickOnGeneralTabOnContract method started");
		WebControls.clickOnWebObjects("Link", contract, "GeneralTab");
		handleLoadingImage();
		waitForElementPresent(100, contract, "StatusDD_ContractSummary");
		
	}
	
	public static void setEnforceAndRequieMUMOnAccountPage(ResultSet rs,String contractName,int enforce,int require)throws Throwable
	{
		String sSql="update zcprojectclass set RequireMarkupMatrix ="+enforce+" , EnforceMarkupMatrix ="+require+" where clientid in (select clientid from zcclient where clientname='"+rs.getString("sDivisionName")+"') and classname= '"+contractName+"'";
		ConnectionWithSQLServer.connectionSqlServerExecuteUpdate(sSql);
	}
	
	public static void setTimeAndExpenseManagerApprovalRequiredFromDB(ResultSet rs,String contractName,int timeReq,int expenseReq)throws Throwable
	{
		String sSql="update zcprojectclass set ManagerExpenseApprovalRequired ="+timeReq+" , ManagerTimeApprovalRequired ="+expenseReq+" where clientid in (select clientid from zcclient where clientname='"+rs.getString("sDivisionName")+"') and classname= '"+contractName+"'";
		ConnectionWithSQLServer.connectionSqlServerExecuteUpdate(sSql);
	}
	
}
