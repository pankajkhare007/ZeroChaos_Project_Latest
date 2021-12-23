package configure_WRIG0483;



import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.project;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.resourceAdmin;
import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class ProjectConfigured_WRIG0483 extends WebDriverHelper{

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
			handleLoadingImage();
	//		waitForElementPresent(100,common, "SEARCH_LINK");
	//		Thread.sleep(10000);
			
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			rs=MSAccessCon.testCon(sQuery);
			//Contract.setEnforceAndRequieMUMOnAccountPage(rs,"GuideWell MSP", 0, 0);
			
			//Contract.setEnforceAndRequieMUMOnAccountPage(rs,"GuideWell MSP", 0, 0);
			Common.searchByResourceID(rs);
			String resStatus = Common.getLinkByLabel("Resource Status :");
			if(resStatus.trim().equals("Terminated"))
			{
				WebControls.clickOnWebObjects("WebButton", resourceAdmin, "ActivateLink_AdminResource");
				handleLoadingImage();
			}
			
			Common.clickOnTabInResourceDetailPage("Projects");
			handleLoadingImage();
			Thread.sleep(10000);
			
			
		//	driver.findElement(By.xpath("//*[@id='ctl00_contplhDynamic_ZeroChaosGrid_ctl00_ctl04_LinkButton1']")).click();
			Project.clickOnCopytoNewLink();
			waitForElementToBeClickable(100, common, "OKButton_ConfirmPopup");
			WebControls.clickOnWebObjects("WebButton", common, "OKButton_ConfirmPopup");
			handleLoadingImage();
			Thread.sleep(20000);
			waitForElementPresent(100,project, "ProjectHeader");
			
			Common.projectID=Project.getProjectIDFromProjectDetailPage();
			Contract.setEnforceAndRequieMUMOnAccountPage(rs, Common.getLinkByLabel("Contract"), 0, 0);
				
			Project.setProjectStartDate(-365);
			Project.setProjectEndDate(60);
			Common.clickOnLinkByText("Save", 0);
			handleLoadingImage();
			waitForElementPresent(60, common, "SUCCESS_MESSAGE1");
			Common.verifySuccessMessage("Data updated successfully.");
			
			ResourceAdmin.clickOnPayCodesTabOnProjectDetail();
		
			//WebControls.setValuesOnWebTable("WebEdit",resourceAdmin, "PayCodeTable", "Regular Time","Supplier Rate","100", 1, 2);
			//driver.findElement(By.xpath("//*[@class='bootstrap-switch-handle-off bootstrap-switch-default']")).click();
			
			ResourceAdmin.selectPayCodeOnPayCodes("Double Time");
			ResourceAdmin.setCustomerRateOnPayCodes("110.22");
			
			ResourceAdmin.addPayCodeOnPayCodes("500000");
			
			ResourceAdmin.setSupplierRateOnPayCodes("50.12");
			driver.findElement(By.id("PayCode_Rate")).sendKeys(Keys.TAB);
			handleLoadingImage();
			
			ResourceAdmin.setResourceRateOnPayCodes("50.12");
			
			ResourceAdmin.clickOnPaycodesAddButton();
			
			Project.payCodeUpdateFromDB("Regular Time");
			Project.payCodeUpdateFromDB("Overtime");
			Project.payCodeUpdateFromDB("Double Time");
			
		//	driver.navigate().refresh();
			handleLoadingImage();
			
		//	ResourceAdmin.clickOnDetailsTabProjectDetail();
			
		
			
			Common.logout();
			
						
		//Common.login(ResultSet rs);
	}



}
