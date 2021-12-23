package configure_DUAN0007;



import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.project;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.resourceAdmin;
import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Contract;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.applicationLiberaries.Project;
import com.zc.webdriver.applicationLiberaries.ResourceAdmin;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class ProjectConfigured_DUAN0007 extends WebDriverHelper{

	ResultSet rs;
	@Test(priority =1)
	public void projectAndContractSetting()throws Throwable
	{
		//Set Initial Work order , Amended Offer Letter and Pepco Holdings-Beeline as per division
		  		
			SetProperpertiesFile.setAllProperpertiesFile();
			flag=true;
			
			String testName=this.getClass().getSimpleName();
			cReport.startTest(testName);
			Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		
			waitForElementPresent(60,common, "SEARCH_LINK");
			
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			rs=MSAccessCon.testCon(sQuery);
			Common.searchByResourceID(rs);
			//ResourceAdmin.navigateToProjectListPageFromResourceDetail();
			//Common.clickOnLinkByText(sText, index);
			Common.clickOnTabInResourceDetailPage("Projects");
			handleLoadingImage();
			Thread.sleep(10000);
		//	driver.findElement(By.xpath("//*[@id='ctl00_contplhDynamic_ZeroChaosGrid_ctl00_ctl04_LinkButton1']")).click();
			Project.clickOnCopytoNewLink();
			waitForElementToBeClickable(60, common, "OKButton_ConfirmPopup");
			WebControls.clickOnWebObjects("WebButton", common, "OKButton_ConfirmPopup");
			handleLoadingImage();
			//Thread.sleep(20000);
			waitForElementPresent(60,project, "ProjectHeader");
			
			Common.clickOnLinkByText(Common.getLinkByLabel("Contract"),0);
			handleLoadingImage();
			
			Contract.clickOnPayrollTabOnContract();
		
			Contract.setEnableResourceRateOnContractPayroll("Yes");
			Common.clickOnSaveButton();
			Common.verifySuccessMessage("Data saved successfully.");	
			
	//		driver.navigate().refresh();
			handleLoadingImage();
			
			Contract.clickOnTimeExpenseTabOnContract();
			Contract.setAllowFutureDatedManagerApproval_ContractTimeExp("OFF");
			Common.clickOnSaveButton();
			
	//		driver.navigate().refresh();
			handleLoadingImage();
			
			Contract.clickOnAccountingTabOnContractAccounting();
			Contract.setEnforceMUMRuleOnContractAccounting("OFF");
			Contract.setRequireMUMRuleOnContractAccounting("OFF");
			Common.clickOnSaveButton();
			Common.verifySuccessMessage("Data saved successfully.");
			Contract.clickOnGeneralTabOnContract();
			
		//	ResourceAdmin.clickOnCopytoNewLinkOnProjectHome();
			
			
			//ResourceAdmin.setProjectStartEndDates();
			//ResourceAdmin.setHoursPerWeekOnProjectDetail();
			//Common.clickOnSaveButton();		
			//handleLoadingImage();
			//Common.verifySuccessMessage("Data updated successfully.");	
			
			ResourceAdmin.clickOnPayCodesTabOnProjectDetail();
		
			WebControls.setValuesOnWebTable("WebEdit",resourceAdmin, "PayCodeTable", "Regular Time","Supplier Rate","100", 1, 2);
			
			ResourceAdmin.selectPayCodeOnPayCodes("Double Time");
			ResourceAdmin.setCustomerRateOnPayCodes("110");
			
			ResourceAdmin.addPayCodeOnPayCodes("500000");
			
			ResourceAdmin.setResourceRateOnPayCodes("104.19");
			
			Common.clickOnAddButton();
			
			driver.navigate().refresh();
			handleLoadingImage();
			
			ResourceAdmin.clickOnDetailsTabProjectDetail();
			
		
			
			Common.logout();
			
						
		//Common.login(ResultSet rs);
	}



}
