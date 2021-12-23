package ProjectConfiguration_ForVSTS_TS_Prequisite;



import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.project;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.resourceAdmin;
import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
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
@Listeners(com.zc.webdriver.commonLiberaries.Listener.class)
public class ProjectConfiguration_PARM0036 extends WebDriverHelper{

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
		
			waitForElementPresent(100,common, "SEARCH_LINK");
			
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			globalRS=MSAccessCon.testCon(sQuery);
			
			
			
			
			Project.closeAllProjectFromDB();
			Project.declineAllTimesheetFromDB();
			
			Common.searchByResourceID(globalRS);
			String resStatus = Common.getLinkByLabel("Resource Status :");
			if(resStatus.trim().equals("Terminated"))
			{
				WebControls.clickOnWebObjects("WebButton", resourceAdmin, "ActivateLink_AdminResource");
				handleLoadingImage();
			}
			
			Common.clickOnTabInResourceDetailPage("Projects");
			handleLoadingImage();
			Thread.sleep(10000);
			
			
		
			Project.clickOnCopytoNewLink();
			waitForElementToBeClickable(100, common, "OKButton_ConfirmPopup");
			WebControls.clickOnWebObjects("WebButton", common, "OKButton_ConfirmPopup");
			handleLoadingImage();
			
			waitForElementPresent(100,project, "ProjectHeader");
			
			Common.projectID=Project.getProjectIDFromProjectDetailPage();
			Contract.setEnforceAndRequieMUMOnAccountPage(globalRS, Common.getLinkByLabel("Contract"), 0, 0);
			
			Project.setProjectStartDate(-720);
			Project.setProjectEndDate(60);
			Project.setProjectWorkStateOnProjectDetail();
			Project.selectOvertimeStrategyOnProectDetail("Global Statutory Warn");
			Project.selectJobCategoryOnProectDetail();
			Common.clickOnLinkByText("Save", 0);
			handleLoadingImage();
			Common.verifySuccessMessage("Data updated successfully.");
			
			ResourceAdmin.clickOnPayCodesTabOnProjectDetail();
			//Project.updateResourceRatetFromDB(Common.projectID, 10);
			ResourceAdmin.selectPayCodeOnPayCodes("Double Time");
			ResourceAdmin.setCustomerRateOnPayCodes("110.22");
			
			ResourceAdmin.addPayCodeOnPayCodes("500000");
			
			
			driver.findElement(By.id("PayCode_Rate")).sendKeys(Keys.TAB);
			handleLoadingImage();
			
		
			
			ResourceAdmin.clickOnPaycodesAddButton();
			Project.inactivePayCodeUpdateFromDB("Holiday Time (Billable)");
			Project.inactivePayCodeUpdateFromDB("Vacation Time (Billable)");
			
			Project.payCodeUpdateFromDB("Regular Time");
			Project.payCodeUpdateFromDB("Overtime");
			Project.payCodeUpdateFromDB("Double Time");
			
		
			handleLoadingImage();
			
			Common.clickOnLinkByText("Track Codes", 0);
			waitForElementPresent(60, project, "GetProjectsTrackCodeAdmin_TrackCodeEditBox");
			handleLoadingImage();
			//Project.unAssignAllTrackCodesOnGetProjectsTrackCodeAdmin();
			Project.assignTrackCodeToProject("TC1");
			Project.assignTrackCodeToProject("TC2");
			
			Common.clickOnLinkByText("Summary", 0);
			
			waitForElementPresent(60, project, "ProjectEdit_ProjectWorkState");
			Division.setCurrencyCodeForVendorFromDB("USD", 500751);
			Common.clickOnLinkByText("Submit", 0);
			waitForElementPresent(60, project, "ProjectEdit_OKPopupButton");
			WebControls.clickOnWebObjects("WebButton",project, "ProjectEdit_OKPopupButton");
			handleLoadingImage();
			//Thread.sleep(20000);
			Common.verifySuccessMessage("Data updated successfully.");
			String projectActualStatus = Project.getProjectStatusOnProjectDetailPage();
			Project.verifyProjectStatus(projectActualStatus, "Active");
			Common.logout();
			
						
		//Common.login(ResultSet rs);
	}



}
