package configure_WRIG0483;



import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.project;

import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;
import org.openqa.selenium.By;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.applicationLiberaries.Project;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class EmailTemplateConfigured_WRIG484 extends WebDriverHelper{

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
			Thread.sleep(10000);
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			rs=MSAccessCon.testCon(sQuery);
			//Contract.setEnforceAndRequieMUMOnAccountPage(rs,"GuideWell Group Inc.", 0, 0);
			
			Common.searchByResourceID(rs);
			//ResourceAdmin.navigateToProjectListPageFromResourceDetail();
			//Common.clickOnLinkByText(sText, index);
			Common.clickOnTabInResourceDetailPage("Projects");
			/*handleLoadingImage();
			Thread.sleep(10000);*/
			waitForElementPresent(60, project, "ApproveButton_ResourceProjects");
			Project.clickOnTopProjectIdFromAdminProjectListPage();
			
			waitForElementPresent(60,project, "ProjectHeader");
			
			Project.clickOnBUOnProjectDetailPage("Funding Cost Cente");
			
			waitForElementPresent(100,division, "DDLBUUnitStatus_OrgHierarchy");
			Common.clickOnLinkByText("Email Templates", 0);
			handleLoadingImage();
			//driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
			Thread.sleep(20000);
			//waitForElementPresent(100,division, "OrgLevelEventCategoryName_OrgLevelEventEmailTemplate");
			driver.findElement(By.xpath("//*[text()='Project']")).click();
			handleLoadingImage();
			
			Division.setEmailTemplateEventOnOrgLevelEventEmailTemplatePage("Project Amendment – General Data Change Approval Required by Managers");
			
			//WebControls.clickOnLinkObjects("Email Templates");
			
			
			Common.logout();
			
						
		//Common.login(ResultSet rs);
	}



}
