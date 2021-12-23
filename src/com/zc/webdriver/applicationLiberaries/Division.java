package com.zc.webdriver.applicationLiberaries;

import static com.zc.webdriver.commonLiberaries.ReadObjectProperties.objLocatorValue;
//import static com.zc.webdriver.commonLiberaries.ReadObjectProperties.objLocatorValue;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.*;
import static com.zc.webdriver.commonLiberaries.ReadObjectProperties.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.zc.webdriver.commonLiberaries.ConnectionWithSQLServer;
import com.zc.webdriver.commonLiberaries.Global;
import com.zc.webdriver.commonLiberaries.LocatorType;
import com.zc.webdriver.commonLiberaries.ReadObjectProperties;

import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class Division extends WebDriverHelper
{
	public static void navigateToDivisionDetailPage(ResultSet rs)throws Throwable
	{
		/*WebControls.clickOnWebObjects("Link", common, "SEARCH_LINK");
		handleLoadingImage();
		JavascriptExecutor je = (JavascriptExecutor) driver;*/

	/*	String keyDefaultApprover=SetProperpertiesFile.division.getPropertyValue("DIVISION_MENU_LINK");
		String xpathValueDIVISION_MENU_LINK=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyDefaultApprover);
		
		WebElement element = driver.findElement(By.xpath(xpathValueDIVISION_MENU_LINK));
		je.executeScript("arguments[0].scrollIntoView(true);",element);*/
		
		//WebControls.clickOnWebObjects("Link", division, "Setup_Link");
		List<WebElement> elements=driver.findElements(By.xpath("//i[@class='fa fa-chevron-down']"));
		elements.get(1).click();
		
		
		WebControls.clickOnWebObjects("Link", division, "Customers_Link");
		WebControls.clickOnWebObjects("Link", division, "DIVISION_MENU_LINK");
		handleLoadingImage();
		searchDivisionOnClientSearch(rs);
		handleLoadingImage();
//		WebControls.clickOnWebObjects("Link", division, "DIVISION_LINK_ClientSearch");
//		waitForElementPresent(150, division, "InvoiceTemplate_ClientDetail");
		//waitForPageTitle(100, "Customer Division Details Page");
		//Thread.sleep(10000);
		
		driver.findElement(By.linkText(rs.getString("sDivisionName"))).click();
		waitForElementPresent(150, division, "DivisionDetails_ConfigurationLink");
		
	}
	/**
	 * @throws Throwable 
	 * @throws SQLException ***********************************************************************************************************/
	public static void searchDivisionOnClientSearch(ResultSet rs) throws SQLException, Throwable
	{
		/*Search Division on Client search page on admin portal*/
		//WebElement element =driver.findElement(By.xpath("//*[@class='chosen-search-input ui-autocomplete-input ui-autocomplete-loading default']"));
		String selectSearch=driver.findElement(By.xpath("//*[@id='btnRunSearchDD']/span")).getText();
		if(!selectSearch.trim().contains("System Default Search"))
		{
			driver.findElement(By.id("btnRunSearchDD")).click();
			handleLoadingImage();
			driver.findElement(By.linkText("System Default Search")).click();
			handleLoadingImage();
		}
			
			
		
		String xpathString="//*[contains(@class,'zc-input-group')]//*[contains(text(),'Division')]/parent::*//*[contains(@class,'chosen-search-input')]";
		WebElement element =driver.findElement(By.xpath(xpathString));
		
		highLightElement(element);
		element.click();
		element.sendKeys(rs.getString("sDivisionName"));
		handleLoadingImage();
		driver.findElement(By.xpath("//*[@class='chosen-results']//*[text()='"+rs.getString("sDivisionName")+"']")).click();
		handleLoadingImage();
		//driver.findElement(By.xpath("//*[text()='"+rs.getString("sDivisionName")+"']")).click();
		driver.findElement(By.xpath("//a[text()='Run Search']")).click();
//		WebControls.setValuesForWebObjects("SuggetionEditBox", division, "DIVISION_SEARCH_EDITBOX", rs.getString("sDivisionName"));
		handleLoadingImage();
	
	}
	public static void clickOnMiscSettionMenuOnClientDetail()throws Throwable
	{
		WebControls.mouseOverOnWebObjects(division, "DivisionTab_ClientDetails");
		handleLoadingImage();
		WebControls.clickOnWebObjects("Link", division, "MiscSettingMenu_ClientDetails");
		waitForElementPresent(100, division, "InitialOfferLetterDD_Misc");
	}
	public static void setInitialOfferLetterOnMisc(String option)throws Throwable
	{
		WebControls.setValuesForWebObjects("WebList", division, "InitialOfferLetterDD_Misc", option);
		handleLoadingImage();
	}
	public static void setOverrideOfferLetterWorkOrderOnRateChangeOnMisc(String option)throws Throwable
	{
		WebControls.setValuesForWebObjects("WebCheckBox", division, "OLWO_OverrideRateChangeCheckBox_Misc", option);
		handleLoadingImage();
	}
	
	
	
	public static void setDisplayAmendmentReasonOnProjectAmendment_Misc(String option)throws Throwable
	{
		WebControls.setValuesForWebObjects("WebCheckBox", division, "DisplayAmendmentReasonOnProjectAmendment_Misc", option);
		handleLoadingImage();
	}
	
	public static void setAmendedOfferLetterOnMisc(String option)throws Throwable
	{
		WebControls.setValuesForWebObjects("WebList", division, "AmendedOfferLetterDD_Misc", option);
		handleLoadingImage();
	}
	
	public static void clickOnApprovalGroupsOnClientDetail()throws Throwable
	{
		WebControls.clickOnWebObjects("WebButton", division, "DivisionDetails_ConfigurationLink");
		
		WebControls.clickOnWebObjects("WebButton", division, "ApprovalWorkFlowTab_ClientDetails");
		handleLoadingImage();
		
	}
	public static void addApprovalGroupName(String appGrpName)throws Throwable
	{
		WebControls.setValuesForWebObjects("WebEdit", division, "ApprovalGroupName_ApprovalGroup", appGrpName);
		WebControls.clickOnWebObjects("Link", common, "ADD_BUTTON");
	}
	
	public static void addApprovalGroupName(ResultSet rs)throws Throwable
	{
		WebControls.setValuesForWebObjects("WebEdit", division, "ApprovalGroupName_ApprovalGroup",  rs.getString("sApprovalGroupName"));
		WebControls.clickOnWebObjects("Link", common, "ADD_BUTTON");
	}
	
	public static void clickOnApprovalSetupOnClientDetail()throws Throwable
	{
		WebControls.mouseOverOnWebObjects(division, "ApprovalWorkFlowTab_ClientDetails");
		handleLoadingImage();
		WebControls.clickOnWebObjects("Link", division, "ApprovalSetUp_ClientDetails");
		
		waitForElementPresent(60,division,"ApprovalGroupDD_ApprovalFlowSetup");
	}
	
	
	public static void addApprovalWorkFlow(ResultSet rs,String compareOptertor)throws Throwable
	{
		cReport.testStep("addApprovalWorkFlow method started");		
		clickOnApprovalSetupOnClientDetail();
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "Object_ApprovalFlowSetup", rs.getString("sObjectName"));
		handleLoadingImage();
		
		handleLoadingImage();
		if(!(rs.getString("bAllowOwnersToApproveWorkFlow")==null))
		{
			WebControls.setValuesForWebObjects("WebCheckBox", division, "AllowOwnersToApproveWorkFlowCheckBox", rs.getString("bAllowOwnersToApproveWorkFlow"));
		}
		
		if(!(rs.getString("bAcceptSingleApprovalForApprovers")==null))
		{
			WebControls.setValuesForWebObjects("WebCheckBox", division, "AcceptSingleApprovalRuleField_ApprovalFlowSetup", rs.getString("bAcceptSingleApprovalForApprovers"));
		}
		
		
		WebControls.setValuesForWebObjects("WebEdit", division, "StepName_ApprovalFlowSetup", rs.getString("sAWFName"));
		WebControls.setValuesForWebObjects("WebEdit", division, "OrderName_ApprovalFlowSetup", "1");
		WebControls.setValuesForWebObjects("WebList", division, "Field_ApprovalFlowSetup", rs.getString("sField"));
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "CompareDD_ApprovalFlowSetup",compareOptertor);
		
		if(rs.getString("sField").equals("AmendmentType"))
		{
			WebControls.setValuesForWebObjects("WebList", division, "DDLCompareValue_ApprovalFlowSetup",rs.getString("iCompareValue"));
		}
		else
			
			WebControls.setValuesForWebObjects("WebEdit", division, "CompareEditBox_ApprovalFlowSetup",rs.getString("iCompareValue"));
		handleLoadingImage();
		Thread.sleep(5000);
		WebControls.setValuesForWebObjects("WebEdit", division, "ApprovalGroupDD_ApprovalFlowSetup",rs.getString("sApprovalGroupName"));
		Thread.sleep(10000);
			
	//	driver.findElement(By.xpath("//*[@class='k-dropdown-wrap k-state-default k-state-focused k-state-active k-state-border-down']")).click();
		driver.findElement(By.xpath("//*[@id='ctl00_contplhDynamic_Div2']/span[2]/span")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("//*[@id='ctl00_contplhDynamic_Div2']/span[2]/span")).sendKeys(Keys.ENTER);
		handleLoadingImage();
		Thread.sleep(10000);
		
		waitForElementClick(60, division, "DefaultApproverDD_ApprovalFlowSetup");
		
		String keyDefaultApprover=SetProperpertiesFile.division.getPropertyValue("DefaultApproverDD_ApprovalFlowSetup");
		String xpathValueDefaultApprover=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyDefaultApprover);
		Select sel = new Select(driver.findElement(By.id(xpathValueDefaultApprover)));
		List<WebElement> approverList=sel.getOptions();
		for(WebElement wl : approverList)
		{
					
				if(wl.getText().contains(rs.getString("sLastName_ApprovalManager")))
				{
					sel.selectByVisibleText(wl.getText());
				}
			
		}
		handleLoadingImage();
		Thread.sleep(10000);
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
	
		Common.verifySuccessMessage("Approval flow has been added successfully.");
		
	}
	
	
	public static void addApprovalWorkFlow_PMO(ResultSet rs,String compareOptertor)throws Throwable
	{
				
		clickOnApprovalSetupOnClientDetail();
		
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "Object_ApprovalFlowSetup", rs.getString("sObjectName"));
		handleLoadingImage();
		Thread.sleep(20000);
		WebControls.setValuesForWebObjects("WebCheckBox", division, "AllowOwnersToApproveWorkFlowCheckBox", "ON");
		WebControls.setValuesForWebObjects("WebEdit", division, "StepName_ApprovalFlowSetup", "AWF");
		WebControls.setValuesForWebObjects("WebEdit", division, "OrderName_ApprovalFlowSetup", "1");
		WebControls.setValuesForWebObjects("WebList", division, "Field_ApprovalFlowSetup", rs.getString("sField"));
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "CompareDD_ApprovalFlowSetup",compareOptertor);
		
		WebControls.setValuesForWebObjects("WebEdit", division, "CompareEditBox_ApprovalFlowSetup",rs.getString("iCompareValue"));
				
		Thread.sleep(5000);
		WebControls.setValuesForWebObjects("WebEdit", division, "ApprovalGroupDD_ApprovalFlowSetup", "Primary Owner");
		handleLoadingImage();
			
	
		driver.findElement(By.xpath("//*[@id='ctl00_contplhDynamic_Div2']/span[2]/span")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("//*[@id='ctl00_contplhDynamic_Div2']/span[2]/span")).sendKeys(Keys.ENTER);
		handleLoadingImage();
		Thread.sleep(10000);
		WebControls.clickOnWebObjects("Link", resourceAdmin, "ResourceCodeMenuLink");
		Thread.sleep(2000);
		
		String keyDefaultApprover=SetProperpertiesFile.division.getPropertyValue("DefaultApproverDD_ApprovalFlowSetup");
		String xpathValueDefaultApprover=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyDefaultApprover);
		Select sel = new Select(driver.findElement(By.id(xpathValueDefaultApprover)));
		List<WebElement> approverList=sel.getOptions();
		for(WebElement wl : approverList)
		{
					
				if(wl.getText().contains(rs.getString("sLastName_ApprovalManager")))
				{
					  sel.selectByVisibleText(wl.getText());
					Thread.sleep(10000);
					
				}
			
		}
		
		if(isElementPresent(common, "ADD_BUTTON"))
		{
			WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
			handleLoadingImage();
		}
		
		Common.verifySuccessMessage("Approval flow has been added successfully.");
		
	}

	public static void addApprovalWorkFlowForCandidateSubmission(ResultSet rs,String compareOptertor)throws Throwable
	{
				
		clickOnApprovalSetupOnClientDetail();
		
		
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "Object_ApprovalFlowSetup", rs.getString("sObjectName"));
		handleLoadingImage();
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebCheckBox", division, "AllowOwnersToApproveWorkFlowCheckBox", "ON");
		WebControls.setValuesForWebObjects("WebEdit", division, "StepName_ApprovalFlowSetup", "AWF");
		WebControls.setValuesForWebObjects("WebEdit", division, "OrderName_ApprovalFlowSetup", "1");
		WebControls.setValuesForWebObjects("WebList", division, "Field_ApprovalFlowSetup", rs.getString("sField"));
		handleLoadingImage();
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "CompareDD_ApprovalFlowSetup",compareOptertor);
		
		//WebControls.setValuesForWebObjects("WebEdit", division, "CompareEditBox_ApprovalFlowSetup",rs.getString("iCompareValue"));
				
		Thread.sleep(5000);
		WebControls.setValuesForWebObjects("WebEdit", division, "ApprovalGroupDD_ApprovalFlowSetup", "Approval Owner");
		handleLoadingImage();
			
	
		driver.findElement(By.xpath("//*[@id='ctl00_contplhDynamic_Div2']/span[2]/span")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("//*[@id='ctl00_contplhDynamic_Div2']/span[2]/span")).sendKeys(Keys.ENTER);
		handleLoadingImage();
		
		Thread.sleep(5000);
		String keyDefaultApprover=SetProperpertiesFile.division.getPropertyValue("DefaultApproverDD_ApprovalFlowSetup");
		String xpathValueDefaultApprover=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyDefaultApprover);
		Select sel = new Select(driver.findElement(By.id(xpathValueDefaultApprover)));
		List<WebElement> approverList=sel.getOptions();
		for(WebElement wl : approverList)
		{
					
				if(wl.getText().contains(rs.getString("sLastName_ApprovalManager")))
				{
					sel.selectByVisibleText(wl.getText());
				}
			
		}
		Thread.sleep(10000);
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
		
		Common.verifySuccessMessage("Approval flow has been added successfully.");
		
	}
	
	
	
	
	public static boolean isApprovalGroupExist(String appGrpName)throws Throwable
	{
		if(isElementPresent(common, "ErrorMessage"))
			return true;
		else
			return false;
	}
	
	public static void addContactToApprovalGroup(String appGrpName,ResultSet rs) throws Throwable
	{
		// Robot robot = new Robot();
	//	ReadObjectProperties propObject=new ReadObjectProperties();
		LocatorType locator = division.getLocatorType("FilterTextBox_ApprovalGroupName");
		if(rs.getString("sApprovalGroupName")!=null)
		{	
			appGrpName = rs.getString("sApprovalGroupName");
		}
		WebControls.setValuesForWebObjects("WebEdit", division, "FilterTextBox_ApprovalGroupName", appGrpName);
		driver.findElement(locator.execute(objLocatorValue)).sendKeys(Keys.ENTER);
		handleLoadingImage();
		WebControls.mouseOverOnWebObjects(division, "Actions");
		WebControls.clickOnWebObjects("Link", division, "AddEditRoleLink");
		waitForElementPresent(100, division, "ContactsTextBox");
		handleLoadingImage();
		if(rs.getString("sLastName_ApprovalManager").contains(","))
		{
			String lastNameArray[]=rs.getString("sLastName_ApprovalManager").split(",");
			for(String pd : lastNameArray)
			{
				TypeInField(driver.findElement(By.id("ctl00_contplhDynamic_ContactsTextBox")),pd);
				handleLoadingImage();
				Thread.sleep(5000);
				//locator = division.getLocatorType("ContactsTextBox");
				
				
				String keyGet=SetProperpertiesFile.division.getPropertyValue("ContactsTextBox");
				String xpathValue=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyGet);
				driver.findElement(By.id(xpathValue)).sendKeys(Keys.DOWN);
				driver.findElement(By.id(xpathValue)).sendKeys(Keys.ENTER);
				//driver.findElement(By.linkText("Blazejak Bryan (bryan.blazejak@delmarva.com)")).click();
				
				WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
				handleLoadingImage();
			}	
		}
		else
		{
			
			TypeInField(driver.findElement(By.id("ctl00_contplhDynamic_ContactsTextBox")),rs.getString("sLastName_ApprovalManager"));
			handleLoadingImage();
			Thread.sleep(5000);
			//locator = division.getLocatorType("ContactsTextBox");
			
			List<WebElement> eleList=driver.findElements(By.xpath("//*[@class='LiAutoCompleteClass ui-menu-item']"));
			for(WebElement ele : eleList)
			{
				if(ele.getText().contains(rs.getString("ContactEmailID")))
				{
					ele.click();
					break;
				}
			}
		/*	String keyGet=SetProperpertiesFile.division.getPropertyValue("ContactsTextBox");
			String xpathValue=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyGet);
			driver.findElement(By.id(xpathValue)).sendKeys(Keys.DOWN);
			driver.findElement(By.id(xpathValue)).sendKeys(Keys.ENTER);
			//driver.findElement(By.linkText("Blazejak Bryan (bryan.blazejak@delmarva.com)")).click();
*/			
			WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
			handleLoadingImage();
		}
		
		
	}
	
	public static void clickOnCustomDefinedFieldsMenuOnClientDetail()throws Throwable
	{
		WebControls.mouseOverOnWebObjects(division, "DivisionTab_ClientDetails");
		handleLoadingImage();
		WebControls.clickOnWebObjects("Link", division, "CustomDefinedFieldsMenu_ClientDetails");
		handleLoadingImage();
	}
	
	public static void selectObjectOnClientUserDefinedFields(String objectName)throws Throwable
	{	
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "Object_ApprovalFlowSetup",objectName);
		handleLoadingImage();
		
	}
	
	public static void uncheckAllCustomeRequiredFields()throws Throwable
	{
		
		handleLoadingImage();
		String keyGet=SetProperpertiesFile.division.getPropertyValue("CheckAllClientUserDefRequired_ClientUserDefinedFields");
		String xpathValue=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyGet);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement about = driver.findElement(By.id(xpathValue));
		jse.executeScript("arguments[0].scrollIntoView();",about);
		Thread.sleep(1000);
		
		
		driver.findElement(By.id(xpathValue)).click();
		Thread.sleep(500);
		driver.findElement(By.id(xpathValue)).click();
		
	
	}
	
	public static void clickOnMarkupMatrixMenuOnClientDetail()throws Throwable
	{
		WebControls.mouseOverOnWebObjects(division, "DivisionTab_ClientDetails");
		handleLoadingImage();
		WebControls.clickOnWebObjects("Link", division, "MarkupMatrixMenu_ClientDetails");
		waitForElementPresent(100, division, "MatrixNameAdd_MarkupMatrix");
	}
	
	/*public static void addMarkupMarixOnMarkupMatrix()throws Throwable
	{
		WebControls.mouseOverOnWebObjects(division, "DivisionTab_ClientDetails");
		WebControls.clickOnWebObjects("Link", division, "MarkupMatrixMenu_ClientDetails");
		handleLoadingImage();
	}*/
	
		
	public static void clickOnSaveButtonOnMiscSetting()throws Throwable
	{
		cReport.testStep("clickOnSaveButtonOnMiscSetting method started");
		WebControls.clickOnWebObjects("WebButton", division, "SaveButton_MiscSetting");
		handleLoadingImage();
	}
	public static void configureProjectOptionSectionForAmendment()throws Throwable
	{
		cReport.testStep("configureProjectOptionSectionForAmendment method started");
		WebControls.setValuesForWebObjects("WebCheckBox", division, "AllowManagerToEditProjectCheckBox_MiscSetting", "ON");
		WebControls.setValuesForWebObjects("WebCheckBox", division, "EnableManagerAmendProjectField_MiscSetting", "ON");
		WebControls.setValuesForWebObjects("WebCheckBox", division, "DisplayProjectExtensiononOptionOnAmendment_MiscSetting", "ON");
		WebControls.setValuesForWebObjects("WebCheckBox", division, "DisplayRateChangeOptionOnAmendment_Misc", "ON");
	}
	
	public static void addNewMarkupMatrix(ResultSet rs)throws Throwable
	{
		cReport.testStep("addNewMarkupMatrix method started");
		WebControls.setValuesForWebObjects("WebEdit", division, "MatrixNameAdd_MarkupMatrix", rs.getString("sMUM_Name"));
		WebControls.setValuesForWebObjects("WebList", division, "MarkupMatrixRule", rs.getString("sMUM_Rule"));
		//handleLoadingImage();
		Thread.sleep(20000);
		WebControls.setValuesForWebObjects("WebList", division, "PayRollType", rs.getString("sPayrollType"));
		//handleLoadingImage();
		Thread.sleep(20000);
		WebControls.clickOnWebObjects("WebButton", division, "SaveButton_MarkupMatrix");
		//handleLoadingImage();
		Thread.sleep(20000);
		Common.verifySuccessMessage("Markup matrix has been added successfully.");
	}
	

	public static void addPayCodesInMarkupMatrix(ResultSet rs)throws Throwable
	{
		cReport.testStep("addPayCodesInMarkupMatrix method started");
		String paycodesArray[]=rs.getString("sPayCodes").split(",");
		for(String pd : paycodesArray)
		{
			
			WebControls.setValuesForWebObjects("WebList", division, "PayCodeTypeAdd_MUM",pd);
//			waitForElementToBeClickable(100, division, "MarukupPercentage_MUM");
		//	handleLoadingImage();
			Thread.sleep(15000);
			
			WebControls.setValuesForWebObjects("WebEdit", division, "MarukupPercentage_MUM",rs.getString("iPercentage"));
			WebControls.setValuesForWebObjects("WebList", division, "MarkupMatrixName",rs.getString("sMUM_Name"));
			
			Thread.sleep(15000);
			WebControls.setValuesForWebObjects("WebEdit", division, "MinRate_MUM",rs.getString("iMinRate"));
			WebControls.setValuesForWebObjects("WebEdit", division, "MaxRate_MUM",rs.getString("iMaxRate"));
			
			if (isElementVisible(division, "AffordableCarePortionTextBox"))
			{
				WebControls.setValuesForWebObjects("WebEdit", division, "AffordableCarePortionTextBox","0");
				Thread.sleep(15000);
			}
			
			WebControls.clickOnWebObjects("WebButton", division, "AddButton_MUM");
			Thread.sleep(15000);
			
		}
		WebControls.clickOnWebObjects("WebButton", division, "SubmitButton_MUM");
		handleLoadingImage();
		
		Common.verifySuccessMessage("Markup matrix tier has been added successfully.");
	}
	
	public static void addDefaulApprovalWorkFlowForTS_JHS(ResultSet rs,String compareOptertor,String stepName)throws Throwable
	{
				
		clickOnApprovalSetupOnClientDetail();
		
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "Object_ApprovalFlowSetup", rs.getString("sObjectName"));
		handleLoadingImage();
		
		WebControls.setValuesForWebObjects("WebEdit", division, "StepName_ApprovalFlowSetup", stepName);
		WebControls.setValuesForWebObjects("WebEdit", division, "OrderName_ApprovalFlowSetup", "1");
		WebControls.setValuesForWebObjects("WebList", division, "Field_ApprovalFlowSetup", rs.getString("sField"));
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "CompareDD_ApprovalFlowSetup",compareOptertor);
		
		WebControls.setValuesForWebObjects("WebEdit", division, "CompareEditBox_ApprovalFlowSetup",rs.getString("iCompareValue"));
		Thread.sleep(5000);
		WebControls.setValuesForWebObjects("WebList", division, "NamedApprovalGroup_ApprovalFlowSetup","Project Owner Approval Group");
		handleLoadingImage();		
		//Thread.sleep(10000);
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
		
		Common.verifySuccessMessage("Approval flow has been updated successfully.");
		
	}
	
	
	public static void navigateToRateCardPage(ResultSet rs)throws Throwable
	{
		handleLoadingImage();
		cReport.testStep("navigateToRateCardPage method started");	
		WebControls.mouseOverOnWebObjects(division, "RequisitionsTab_ClientDetails");
		handleLoadingImage();
		WebControls.clickOnWebObjects("Link", division, "RateCardSetupMenu");
		WebControls.clickOnWebObjects("Link", division, "ManageRateCardsGroupMenu");
		waitForElementPresent(100,  division, "RateCardGroupType_RateCard");
			
	}
	public static void addRateCardGroup(ResultSet rs)throws Throwable
	{
		cReport.testStep("addRateCardGroup method started");	
		WebControls.setValuesForWebObjects("WebEdit", division, "RateCardGroupName_RateCard",rs.getString("sRateCardGroupName"));
		WebControls.setValuesForWebObjects("WebList", division, "RateCardGroupType_RateCard",rs.getString("sRateCardType"));
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
		Common.verifySuccessMessage("Record inserted successfully.");	
	}
	
	public static void clickOnViewRateCardLinkFromWebTabl(ResultSet rs)throws Throwable
	{
		cReport.testStep("clickOnViewRateCardLinkFromWebTabl method started");
		String keyRateCardTable=SetProperpertiesFile.division.getPropertyValue("RateCardTable_RateCard");
		String xpathRateCardTable=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyRateCardTable);
		WebElement tableMUM=driver.findElement(By.xpath(xpathRateCardTable));
		List<WebElement> rows_table = tableMUM.findElements(By.tagName("tr"));
		for(int i=0; i < rows_table.size() ; i++)
		{
			 List<WebElement> columns = rows_table.get(i).findElements(By.tagName("td"));
			 int columnInRow=columns.size();
			
				
				String xpathRC=getXPath(columns.get(0));
				String exactXpathRC=xpathRC+"/input";
				String RCGroup=null;
				
					RCGroup = driver.findElement(By.xpath(exactXpathRC)).getAttribute("value");
					if((RCGroup.trim()).equalsIgnoreCase(rs.getString("sRateCardGroupName")))
					{
						String elementXpath = getXPath(columns.get(columnInRow-2));
						String actionExactPath = elementXpath+"/a";
						
						System.out.println("xpath ::: "+elementXpath);
						Actions action = new Actions(driver);
						action.moveToElement(driver.findElement(By.xpath(actionExactPath))).build().perform();
						Thread.sleep(500);
						driver.findElement(By.linkText("View Rate Card")).click();
						waitForElementPresent(100, division, "JobClassificationField_RateCard");
			
					}
			
	
		 }
	}
	
	public static void addNewRateCard(ResultSet rs,String enableOverride,String AllowBillRateToExceedMaxBillRate  )throws Throwable
	{
		
		cReport.testStep("addNewRateCard method started");	
		clickOnViewRateCardLinkFromWebTabl(rs);
		WebControls.setValuesForWebObjects("WebList", division, "JobClassificationField_RateCard",rs.getString("sJobClassification"));
		//handleLoadingImage();
		waitForElementToBeClickable(100, division,"JobCategoryField_RateCard");
		WebControls.setValuesForWebObjects("WebList", division, "JobCategoryField_RateCard",rs.getString("sJobCategory"));
		handleLoadingImage();
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "SupplierMarkupRuleField_RateCard",rs.getString("sSupplierMarkupRule"));
		waitForElementPresent(100, division, "MaxBillRateField_RateCard");
		WebControls.setValuesForWebObjects("WebEdit", division, "MaxMarkupField_RateCard",rs.getString("iMaxMarkup"));
		WebControls.setValuesForWebObjects("WebCheckBox", division, "EnableOverrideField_RateCard", enableOverride);
		WebControls.setValuesForWebObjects("WebEdit", division, "TargetBillRateField_RateCard",rs.getString("iTargetBillRate"));
		WebControls.setValuesForWebObjects("WebEdit", division, "MaxBillRateField_RateCard",rs.getString("iMaxBillRate"));
		WebControls.setValuesForWebObjects("WebCheckBox", division, "AllowToExceedMaxField_RateCard", AllowBillRateToExceedMaxBillRate);
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
		Common.verifySuccessMessage("New Rate Card successfully added.");	
		
	}
	
	public static void removeApprovalWork() throws Throwable
	{
		boolean isRecord=true;
		while(isRecord)
		{
			try
			{
				WebControls.mouseOverOnWebObjects(common, "ActionIcon_FirstRecord");
				WebControls.clickOnWebObjects("WebButton", common, "EditLink");
				handleLoadingImage();
				Thread.sleep(10000);
				WebControls.setValuesForWebObjects("WebCheckBox", division, "ActiveCheckBox_ApprovalFlowSetup", "OFF");
				Common.clickOnSaveButton();
				handleLoadingImage();
				Common.verifySuccessMessage("Approval flow has been updated successfully.");	
			}
			catch(Exception e)
			{
				isRecord=false;
			}

			
		}
	}
	
	public static void addNewRateCardForFixBillMaxMarkup(ResultSet rs,String enableOverride,String fixedBillRate  )throws Throwable
	{
		
		cReport.testStep("addNewRateCardForFixBillMaxMarkup method started");	
		clickOnViewRateCardLinkFromWebTabl(rs);
		
		WebControls.setValuesForWebObjects("WebList", division, "JobClassificationField_RateCard",rs.getString("sJobClassification"));
		//handleLoadingImage();
		waitForElementToBeClickable(100, division,"JobCategoryField_RateCard");
		WebControls.setValuesForWebObjects("WebList", division, "JobCategoryField_RateCard",rs.getString("sJobCategory"));
		handleLoadingImage();
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "SupplierMarkupRuleField_RateCard",rs.getString("sSupplierMarkupRule"));
		waitForElementPresent(100, division, "MaxMarkupField_RateCard");
		WebControls.setValuesForWebObjects("WebEdit", division, "MaxMarkupField_RateCard",rs.getString("iMaxMarkup"));
		WebControls.setValuesForWebObjects("WebCheckBox", division, "EnableOverrideField_RateCard", enableOverride);
		WebControls.setValuesForWebObjects("WebEdit", division, "FixedBillRateField_RateCardDetail", fixedBillRate);
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
		Common.verifySuccessMessage("New Rate Card successfully added.");	
		
	}
	
	public static void selectSupplierNameOnRateCardDetail(String supplierName  )throws Throwable
	{
		cReport.testStep("selectSupplierNameOnRateCardDetail method started");	
		WebControls.setValuesForWebObjects("WebList", division, "SupplierNameField_RateCardDetail",supplierName);
		handleLoadingImage();
	}
	
	public static void addNewRateCardForFixBillFixMarkup(ResultSet rs,String fixMarkup,String fixedBillRate  )throws Throwable
	{
		
		cReport.testStep("addNewRateCardForFixBillFixMarkup method started");	
		clickOnViewRateCardLinkFromWebTabl(rs);
		
		WebControls.setValuesForWebObjects("WebList", division, "JobClassificationField_RateCard",rs.getString("sJobClassification"));
		//handleLoadingImage();
		waitForElementToBeClickable(100, division,"JobCategoryField_RateCard");
		WebControls.setValuesForWebObjects("WebList", division, "JobCategoryField_RateCard",rs.getString("sJobCategory"));
		handleLoadingImage();
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "SupplierMarkupRuleField_RateCard",rs.getString("sSupplierMarkupRule"));
		waitForElementPresent(100, division, "MaxMarkupField_RateCard");
		
		WebControls.setValuesForWebObjects("WebEdit", division, "FixedMarkupField_RateCardDetail", fixMarkup);
		WebControls.setValuesForWebObjects("WebEdit", division, "FixedBillRateField_RateCardDetail", fixedBillRate);
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
		Common.verifySuccessMessage("New Rate Card successfully added.");	
		
	}
	
	public static void addNewRateCardForFixMarkup(ResultSet rs,String AllowBillRateToExceedMaxBillRate  )throws Throwable
	{
		
		cReport.testStep("addNewRateCard method started");	
		clickOnViewRateCardLinkFromWebTabl(rs);
		WebControls.setValuesForWebObjects("WebList", division, "JobClassificationField_RateCard",rs.getString("sJobClassification"));
		//handleLoadingImage();
		waitForElementToBeClickable(100, division,"JobCategoryField_RateCard");
		WebControls.setValuesForWebObjects("WebList", division, "JobCategoryField_RateCard",rs.getString("sJobCategory"));
		handleLoadingImage();
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "SupplierMarkupRuleField_RateCard",rs.getString("sSupplierMarkupRule"));
		waitForElementPresent(100, division, "MaxBillRateField_RateCard");
		WebControls.setValuesForWebObjects("WebEdit", division, "FixedMarkupField_RateCardDetail", rs.getString("iMaxMarkup"));
	//	WebControls.setValuesForWebObjects("WebEdit", division, "MaxMarkupField_RateCard",rs.getString("iMaxMarkup"));
		//WebControls.setValuesForWebObjects("WebCheckBox", division, "EnableOverrideField_RateCard", enableOverride);
		WebControls.setValuesForWebObjects("WebEdit", division, "TargetBillRateField_RateCard",rs.getString("iTargetBillRate"));
		WebControls.setValuesForWebObjects("WebEdit", division, "MaxBillRateField_RateCard",rs.getString("iMaxBillRate"));
		WebControls.setValuesForWebObjects("WebCheckBox", division, "AllowToExceedMaxField_RateCard", AllowBillRateToExceedMaxBillRate);
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
		Common.verifySuccessMessage("New Rate Card successfully added.");	
		
	}
	
	public static void addNewApprovalWorkFlow(ResultSet rs)throws Throwable
	{
		cReport.testStep("addNewApprovalWorkFlow method started");		
		clickOnApprovalSetupOnClientDetail();
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "Object_ApprovalFlowSetup", rs.getString("sObjectName"));
		handleLoadingImage();
		if(!rs.getString("sObjectName").equalsIgnoreCase("Projects"))
		{	
			WebControls.setValuesForWebObjects("WebList", division, "RequisitionTypeGroup_ApprovalFlowSetup", rs.getString("sRequisition Type Group"));
			handleLoadingImage();
		
			WebControls.setValuesForWebObjects("WebList", division, "RequisitionType_ApprovalFlowSetup", rs.getString("Requisition Type"));
			handleLoadingImage();
			if(rs.getString("bAcceptSingleApprovalForApprovers")!=null)
			{
				WebControls.setValuesForWebObjects("WebCheckBox", division, "AcceptSingleApprovalRuleField_ApprovalFlowSetup", rs.getString("bAcceptSingleApprovalForApprovers"));
			}
		}
		
		if(rs.getString("sApprovalGroupName")!=null)
		{
			WebControls.setValuesForWebObjects("WebCheckBox", division, "AllowOwnersToApproveWorkFlowCheckBox",rs.getString("sApprovalGroupName"));
		}
		
		WebControls.setValuesForWebObjects("WebEdit", division, "StepName_ApprovalFlowSetup", rs.getString("sAWFName"));
		
		WebControls.setValuesForWebObjects("WebEdit", division, "OrderName_ApprovalFlowSetup", rs.getString("sFlowOrder"));
		
		WebControls.setValuesForWebObjects("WebList", division, "Field_ApprovalFlowSetup", rs.getString("sField"));
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "CompareDD_ApprovalFlowSetup",rs.getString("sCompareOperator"));
		
		WebControls.setValuesForWebObjects("WebEdit", division, "CompareEditBox_ApprovalFlowSetup",rs.getString("iCompareValue"));
				
		Thread.sleep(5000);
		if(rs.getString("sApprovalGroupName")!=null)
		{
			WebControls.setValuesForWebObjects("WebEdit", division, "ApprovalGroupDD_ApprovalFlowSetup", rs.getString("sApprovalGroupName"));
			handleLoadingImage();
				
		
			driver.findElement(By.xpath("//*[@id='ctl00_contplhDynamic_Div2']/span[2]/span")).sendKeys(Keys.ARROW_DOWN);
		
			driver.findElement(By.xpath("//*[@id='ctl00_contplhDynamic_Div2']/span[2]/span")).sendKeys(Keys.ENTER);
			handleLoadingImage();
			
			String keyDefaultApprover=SetProperpertiesFile.division.getPropertyValue("DefaultApproverDD_ApprovalFlowSetup");
			String xpathValueDefaultApprover=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyDefaultApprover);
			Select sel = new Select(driver.findElement(By.id(xpathValueDefaultApprover)));
			List<WebElement> approverList=sel.getOptions();
			for(WebElement wl : approverList)
			{
						
					if(wl.getText().contains(rs.getString("sLastName_ApprovalManager")))
					{
						sel.selectByVisibleText(wl.getText());
					}
				
			}
			Thread.sleep(10000);
			if(rs.getString("bRequireDefaultApproverOnly")!=null)
			{
				WebControls.setValuesForWebObjects("WebCheckBox", division, "DefaultApproverRuleField_ApprovalFlowSetup", rs.getString("bRequireDefaultApproverOnly"));
			}
		}
		if(rs.getString("sDOAApproval")!=null)
		{
			WebControls.setValuesForWebObjects("WebList", division, "NamedApprovalGroup_ApprovalFlowSetup", rs.getString("sNamedApprovalGroup"));
			handleLoadingImage();
			WebControls.setValuesForWebObjects("WebList", division, "DOAApprovalGroup_ApprovalFlowSetup", rs.getString("sDOAApproval"));
			handleLoadingImage();
		}
		
		
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
	
		Common.verifySuccessMessage("Approval flow has been added successfully.");
		
	}
	
	public static void setAutoTerminatateProjectAndResource(String option) throws Throwable
	{
		cReport.testStep("setAutoTerminatateProjectAndResource method started");		
		WebControls.setValuesForWebObjects("WebCheckBox", division, "AutoTerminateResources_Misc", option);
		WebControls.setValuesForWebObjects("WebCheckBox", division, "AutoTerminateProjects_Misc", option);
	}
	
	public static void setEmailTemplateEventOnOrgLevelEventEmailTemplatePage(String eventName)
	{
		cReport.testStep("setEmailTemplateEventOnOrgLevelEventEmailTemplatePage method started");	
		List<WebElement> lisEvents = driver.findElements(By.xpath("//*[@class='orgLevelEventName']/span"));
		int eventNo=0;
		for(int i=0;i<lisEvents.size();i++)
		{
			String xpathValue = getXPath(lisEvents.get(i));
			System.out.println(xpathValue);
		}
		
		List<WebElement> listActiveInput = driver.findElements(By.xpath("//*[@class='aspNetDisabled DummyActive']/input"));
		String checkBox=listActiveInput.get(eventNo).getAttribute("checked");
		if(!checkBox.equalsIgnoreCase("checked"))
		{
			listActiveInput.get(eventNo).click();
		}
		
	}
	
	public static void addContactToApprovalGroupForDarel(String appGrpName,ResultSet rs) throws Throwable
	{
		cReport.testStep("addContactToApprovalGroupForDarel method started");
		// Robot robot = new Robot();
	//	ReadObjectProperties propObject=new ReadObjectProperties();
		LocatorType locator = division.getLocatorType("FilterTextBox_ApprovalGroupName");
		if(rs.getString("sApprovalGroupName")!=null)
		{	
			appGrpName = rs.getString("sApprovalGroupName");
		}
		WebControls.setValuesForWebObjects("WebEdit", division, "FilterTextBox_ApprovalGroupName", appGrpName);
		driver.findElement(locator.execute(objLocatorValue)).sendKeys(Keys.ENTER);
		handleLoadingImage();
		WebControls.mouseOverOnWebObjects(division, "Actions");
		WebControls.clickOnWebObjects("Link", division, "AddEditRoleLink");
		waitForElementPresent(100, division, "ContactsTextBox");
		handleLoadingImage();
		
			//WebControls.setValuesForWebObjects("WebEdit", division, "ContactsTextBox","Calvin Derek");
			TypeInField(driver.findElement(By.id("ctl00_contplhDynamic_ContactsTextBox")),"Calvin Derek");
			handleLoadingImage();
			Thread.sleep(5000);
			//locator = division.getLocatorType("ContactsTextBox");
			String keyGet=SetProperpertiesFile.division.getPropertyValue("ContactsTextBox");
			String xpathValue=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyGet);
			driver.findElement(By.id(xpathValue)).sendKeys(Keys.DOWN);
			driver.findElement(By.id(xpathValue)).sendKeys(Keys.ENTER);
			//driver.findElement(By.linkText("Blazejak Bryan (bryan.blazejak@delmarva.com)")).click();
			
			WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
			handleLoadingImage();
		
		
	}
	public static void setCurrencyCodeForVendorFromDB(String currencyCode,int vendorID) throws Throwable
	{
		cReport.testStep("addContactToApprovalGroupForDarel method started");
		//String sQuery = "update zcVendor set PayCurrencyCode = 'USD' where VendorID=500751"
				
		String sSQLForProject = "update zcVendor set PayCurrencyCode = '" +currencyCode+"' where VendorID="+ vendorID;
		ConnectionWithSQLServer.connectionSqlServerExecuteUpdate(sSQLForProject);
	
		
	}
	
	public static void clickOnBUOnClientDetail()throws Throwable
	{
		cReport.testStep("clickOnBUOnClientDetail method started");
		WebControls.clickOnWebObjects("WebButton", division, "DivisionDetails_ConfigurationLink");
		handleLoadingImage();
		WebControls.clickOnWebObjects("WebButton", division, "DivisionDetails_ExpenseRulesLink");
		handleLoadingImage();
		
	}
	
	public static void clickOnTabOnBusinessUnitPage(String tabName)
	{
		cReport.testStep("clickOnTabOnBusinessUnitPage method started");
		
		List<WebElement> listWebElement = driver.findElements(By.xpath("//*[@class='tab']"));
		for(WebElement ele : listWebElement)
		{
			if(tabName.equals(ele.getText().trim()))
			{
				ele.click();
				break;
			}
		}
			
	}
	
	public static void addExpenseOnExpenseRulesPage(String expenseType) throws Throwable
	{
		WebControls.setValuesForWebObjects("WebList", division, "ExpenseTypeDD_ExpRules", expenseType);
		WebControls.setValuesForWebObjects("WebEdit", division, "MaxNoReciptAmountEditBox_ExpRules", "1");
		WebControls.setValuesForWebObjects("WebEdit", division, "MaxReceiptAmountAllowedEditBox_ExpRules", "9999.99");
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
	}
	
	public static void addApprovalWorkFlowForKINI0002()throws Throwable
	{
		cReport.testStep("addApprovalWorkFlow method started");		
		clickOnApprovalSetupOnClientDetail();
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "Object_ApprovalFlowSetup", Global.resultSet.getString("sObjectName"));
		handleLoadingImage();
		
		handleLoadingImage();
		if(!(Global.resultSet.getString("bAllowOwnersToApproveWorkFlow")==null))
		{
			WebControls.setValuesForWebObjects("WebCheckBox", division, "AllowOwnersToApproveWorkFlowCheckBox", Global.resultSet.getString("bAllowOwnersToApproveWorkFlow"));
		}
		
		if(!(Global.resultSet.getString("bAcceptSingleApprovalForApprovers")==null))
		{
			WebControls.setValuesForWebObjects("WebCheckBox", division, "AcceptSingleApprovalRuleField_ApprovalFlowSetup", Global.resultSet.getString("bAcceptSingleApprovalForApprovers"));
		}
		
		
		WebControls.setValuesForWebObjects("WebEdit", division, "StepName_ApprovalFlowSetup", Global.resultSet.getString("sAWFName"));
		WebControls.setValuesForWebObjects("WebEdit", division, "OrderName_ApprovalFlowSetup", "1");
		WebControls.setValuesForWebObjects("WebList", division, "Field_ApprovalFlowSetup", Global.resultSet.getString("sField"));
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "CompareDD_ApprovalFlowSetup", Global.resultSet.getString("sCompareOperator"));
		
		if(Global.resultSet.getString("sField").equals("AmendmentType"))
		{
			WebControls.setValuesForWebObjects("WebList", division, "DDLCompareValue_ApprovalFlowSetup",Global.resultSet.getString("iCompareValue"));
		}
		
			
			WebControls.setValuesForWebObjects("WebEdit", division, "CompareEditBox_ApprovalFlowSetup",Global.resultSet.getString("iCompareValue"));
				
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "NamedApprovalGroup_ApprovalFlowSetup",Global.resultSet.getString("sNamedApprovalGroup"));
		handleLoadingImage();
			
		WebControls.setValuesForWebObjects("WebList", division, "DOAApprovalGroup_ApprovalFlowSetup",Global.resultSet.getString("sDOAApproval"));
		handleLoadingImage();
		
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
	
		Common.verifySuccessMessage("Approval flow has been added successfully.");
		
	}
	
}
