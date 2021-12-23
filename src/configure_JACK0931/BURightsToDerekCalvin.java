package configure_JACK0931;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;

import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.grid.web.HandlerNotFoundException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;
@Listeners(com.zc.webdriver.commonLiberaries.Listener.class)
public class BURightsToDerekCalvin extends WebDriverHelper {
	
	ResultSet rs;
	@Test(priority =1)
	public void addViewRightsToContact()throws Throwable
	{
		//Set Initial Work order , Amended Offer Letter and Pepco Holdings-Beeline as per division
		  		
			SetProperpertiesFile.setAllProperpertiesFile();
			flag=true;
			
			String testName=this.getClass().getSimpleName();
			cReport.startTest(testName);
			Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		
			handleLoadingImage();
			//waitForElementPresent(100,common, "AdminDashboard_DDLDivision");
		
					
			
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			rs=MSAccessCon.testCon(sQuery);
			WebElement ele = driver.findElement(By.xpath("//*[text()='Customer Contacts']"));
			Common.quickSearch("Customer Contacts", rs.getString("ContactEmailID"));
			waitForElementPresent(60, division, "CreateUpdateContacts_BtnLoginAsManager");
			Common.clickOnTab("Org Access");
			//handleLoadingImage();
			waitForElementPresent(30, division, "AssignPermissionToManager_chkBoxViewAll");
			WebControls.clickOnWebObjects("WebButton", division, "AssignPermissionToManager_chkBoxViewAll");
			handleLoadingImage(100);
			WebControls.clickOnWebObjects("WebButton", common, "SAVE_BUTTON");
			//handleLoadingImage();
			waitForElementPresent(60, common, "SUCCESS_MESSAGE");
			Common.verifySuccessMessage("Successfully assigned the organization level to the contact.");
			Common.logout();
			
			
			
	}

}
