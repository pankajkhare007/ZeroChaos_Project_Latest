package configure_WALD0079;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.project;

import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Contract;
import com.zc.webdriver.applicationLiberaries.Project;
import com.zc.webdriver.applicationLiberaries.ResourceAdmin;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class AddDoubleTimeRate_WALD0079 extends WebDriverHelper {
	
	ResultSet rs;
	@Test
	public void addDoubleTimeRateAndConfigureContract() throws Throwable
	{
		SetProperpertiesFile.setAllProperpertiesFile();
		flag=true;
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		
		
		
		
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		handleLoadingImage();
	//	waitForElementPresent(100,common, "DivisionDD_AdminDashboard");
		//Thread.sleep(10000);
		
		
		
		String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
		rs=MSAccessCon.testCon(sQuery);
		Common.searchByResourceID(rs);
		Common.clickOnTabInResourceDetailPage("Projects");
		handleLoadingImage();
		Thread.sleep(10000);
		
		Project.clickOnCopytoNewLink();
		waitForElementToBeClickable(100, common, "OKButton_ConfirmPopup");
		WebControls.clickOnWebObjects("WebButton", common, "OKButton_ConfirmPopup");
		handleLoadingImage();
		Thread.sleep(20000);
		waitForElementPresent(100,project, "ProjectHeader");
		
		Common.projectID=Project.getProjectIDFromProjectDetailPage();
		
		Project.payCodeUpdateFromDB("Regular Time");
		
		Contract.setEnforceAndRequieMUMOnAccountPage(rs, Common.getLinkByLabel("Contract"), 0, 0);
		
		Contract.setTimeAndExpenseManagerApprovalRequiredFromDB(rs, Common.getLinkByLabel("Contract"), 0, 0);
		
		ResourceAdmin.clickOnPayCodesTabOnProjectDetail();
		
		//WebControls.setValuesOnWebTable("WebEdit",resourceAdmin, "PayCodeTable", "Regular Time","Supplier Rate","100", 1, 2);
		//driver.findElement(By.xpath("//*[@class='bootstrap-switch-handle-off bootstrap-switch-default']")).click();
		
		ResourceAdmin.selectPayCodeOnPayCodes("Double Time");
		//ResourceAdmin.setCustomerRateOnPayCodes("110.22");
		
		ResourceAdmin.addPayCodeOnPayCodes("500000");
		
		//ResourceAdmin.setSupplierRateOnPayCodes("50.12");
		//driver.findElement(By.id("PayCode_Rate")).sendKeys(Keys.TAB);
		handleLoadingImage();
		
		ResourceAdmin.setResourceRateOnPayCodes("115.60");
		
		ResourceAdmin.clickOnPaycodesAddButton();
		
		Project.payCodeUpdateFromDB("Regular Time");
		Project.payCodeUpdateFromDB("Overtime");
		Project.payCodeUpdateFromDB("Double Time");
		
	//	driver.navigate().refresh();
		handleLoadingImage();
		
	//	ResourceAdmin.clickOnDetailsTabProjectDetail();
		
	
		
		Common.logout();
	
	}
	
	

}
