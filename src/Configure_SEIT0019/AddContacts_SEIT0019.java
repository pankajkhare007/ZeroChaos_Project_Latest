package Configure_SEIT0019;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.project;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Contract;
import com.zc.webdriver.applicationLiberaries.Project;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class AddContacts_SEIT0019 extends WebDriverHelper{
	
	ResultSet rs;
	@Test
	public void addContact() throws Throwable
	{
		SetProperpertiesFile.setAllProperpertiesFile();
		flag=true;
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		handleLoadingImage(100);
		//waitForElementPresent(100,common, "DivisionDD_AdminDashboard");
		String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
		rs=MSAccessCon.testCon(sQuery);
		Common.searchByResourceID(rs);
		Common.clickOnTabInResourceDetailPage("Projects");
		handleLoadingImage();
		
		
		Project.clickOnCopytoNewLink();
		waitForElementToBeClickable(100, common, "OKButton_ConfirmPopup");
		WebControls.clickOnWebObjects("WebButton", common, "OKButton_ConfirmPopup");
		handleLoadingImage();
		Thread.sleep(20000);
		waitForElementPresent(100,project, "ProjectHeader");
		
		Common.projectID=Project.getProjectIDFromProjectDetailPage();
			
		Contract.setEnforceAndRequieMUMOnAccountPage(rs, Common.getLinkByLabel("Contract"), 0, 0);
		
		Common.clickOnLinkByText("Contacts", 1);
		handleLoadingImage();
		
		Project.unAssignContactFromProject();
		Project.addContactToProjectOnContactPage("Customer Manager", "Primary Manager", "Acton Sara B.");
		Project.addContactToProjectOnContactPage("Customer Manager", "Approval Manager", "Clark, Willie");

		
		Common.logout();
		
		
	}

}
