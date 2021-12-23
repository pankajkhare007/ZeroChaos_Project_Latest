package projectApprovalGroup;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.*;
import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class AddStanleyKreydinInApprovalGroup extends WebDriverHelper {
	ResultSet rs;
	@Test(priority =1)
	public void addContactApprovalGroup()throws Throwable
	{
		
		SetProperpertiesFile.setAllProperpertiesFile();
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		waitForElementPresent(100,common, "SEARCH_LINK");
		Common.quickSearch("Customer Contacts", "Jeffrey.Dowd@rci.com");
		handleLoadingImage();
		WebControls.clickOnWebObjects("WebButton", division, "CreateUpdateContacts_ApprovalGroupLink");
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "ContactApprovalGroup_DivisionDDL", "Wyndham Worldwide");
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "Object_ApprovalFlowSetup", "Projects");
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "ContactApprovalGroup_ApproverNameDDL", "Stanley Kreydin (stan.kreydin@wyn.com)");
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "ContactApprovalGroup_ItemTypeDDL", "Approval Item");
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebEdit", division, "ContactApprovalGroup_FlowOrderTxt", "02");
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
	}

}
