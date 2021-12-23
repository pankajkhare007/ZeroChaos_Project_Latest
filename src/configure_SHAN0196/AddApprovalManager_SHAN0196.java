package configure_SHAN0196;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.project;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.resourceAdmin;

import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Contract;
import com.zc.webdriver.applicationLiberaries.Project;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class AddApprovalManager_SHAN0196 extends WebDriverHelper {
	
	ResultSet rs;
	@Test
	public void addContact() throws Throwable
	{
		SetProperpertiesFile.setAllProperpertiesFile();
		flag=true;
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		handleLoadingImage(60);
		//waitForElementPresent(100,common, "DivisionDD_AdminDashboard");
		String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
		rs=MSAccessCon.testCon(sQuery);
		Common.searchByResourceID(rs);
		Common.clickOnTabInResourceDetailPage("Projects");
		waitForElementPresent(60, By.xpath("//*[text()=' Copy to New']"));
		handleLoadingImage(100);
		
		
		Project.clickOnCopytoNewLink();
		waitForElementToBeClickable(100, common, "OKButton_ConfirmPopup");
		WebControls.clickOnWebObjects("WebButton", common, "OKButton_ConfirmPopup");
		handleLoadingImage(60);
		Thread.sleep(20000);
		waitForElementPresent(100,project, "ProjectHeader");
		
		Common.projectID=Project.getProjectIDFromProjectDetailPage();
			
		Contract.setEnforceAndRequieMUMOnAccountPage(rs, Common.getLinkByLabel("Contract"), 0, 0);
		
		Common.clickOnLinkByText("Contacts", 1);
		handleLoadingImage(200);
		Project.addContactToProjectOnContactPage("Customer Manager", "Approval Manager", "Bursey, Angela");
		
		Common.logout();
		
		
	}

}
