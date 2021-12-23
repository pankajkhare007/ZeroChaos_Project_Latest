package configure_WRIG0483;



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

public class AddContact_WRIG484 extends WebDriverHelper{

	ResultSet rs;
	@Test(priority =1)
	public void addContactToProject()throws Throwable
	{
		//Set Initial Work order , Amended Offer Letter and Pepco Holdings-Beeline as per division
		  		
			SetProperpertiesFile.setAllProperpertiesFile();
			flag=true;
			
			String testName=this.getClass().getSimpleName();
			cReport.startTest(testName);
			Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		
			waitForElementPresent(100,common, "SEARCH_LINK");
			Thread.sleep(10000);
		//	Project.addContactToProjectOnContactPage("Customer Manager", "Approval Manager", "Abbott, Justin");
			
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			rs=MSAccessCon.testCon(sQuery);
		
			
			Common.searchByResourceID(rs);
		
			Common.clickOnTabInResourceDetailPage("Projects");
			handleLoadingImage();
			Thread.sleep(10000);
			
			Project.clickOnCopytoNewLink();
			waitForElementToBeClickable(60, common, "OKButton_ConfirmPopup");
			WebControls.clickOnWebObjects("WebButton", common, "OKButton_ConfirmPopup");
			handleLoadingImage();
			
			waitForElementPresent(60,project, "ProjectHeader");
			
			Common.clickOnLinkByText("Contacts", 1);
			handleLoadingImage();
			Project.addContactToProjectOnContactPage("Customer Manager", "Approval Manager", "Abbott, Justin");
			
		
			
			Common.logout();
			
						
		//Common.login(ResultSet rs);
	}



}
