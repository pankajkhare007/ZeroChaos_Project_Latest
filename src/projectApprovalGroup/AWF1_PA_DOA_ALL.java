package projectApprovalGroup;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class AWF1_PA_DOA_ALL extends WebDriverHelper{
	
	ResultSet rs;
	@Test(priority =1)
	public void setApprovalWorkFlow()throws Throwable
	{
		
		SetProperpertiesFile.setAllProperpertiesFile();
		flag=true;
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		waitForElementPresent(100,common, "SEARCH_LINK");
		Thread.sleep(10000);
		String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
		rs=MSAccessCon.testCon(sQuery);
		Division.navigateToDivisionDetailPage(rs);
		
		Division.clickOnApprovalGroupsOnClientDetail();
		Division.clickOnApprovalSetupOnClientDetail();
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "Object_ApprovalFlowSetup", rs.getString("sObjectName"));
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebEdit", division, "StepName_ApprovalFlowSetup", rs.getString("sAWFName"));
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebEdit", division, "OrderName_ApprovalFlowSetup", "1");
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "Field_ApprovalFlowSetup", rs.getString("sField"));
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebList", division, "CompareDD_ApprovalFlowSetup",">=");
		
		WebControls.setValuesForWebObjects("WebEdit", division, "CompareEditBox_ApprovalFlowSetup",rs.getString("iCompareValue"));
		handleLoadingImage();
		Thread.sleep(5000);
		WebControls.setValuesForWebObjects("WebList", division, "NamedApprovalGroup_ApprovalFlowSetup",rs.getString("sNamedApprovalGroup"));
		handleLoadingImage();	
		WebControls.setValuesForWebObjects("WebList", division, "DOAApprovalGroup_ApprovalFlowSetup", rs.getString("sDOAApproval"));
		handleLoadingImage();
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();
	
		Common.verifySuccessMessage("Approval flow has been added successfully.");
		Common.logout();
	}

}
