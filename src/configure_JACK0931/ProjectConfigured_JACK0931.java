package configure_JACK0931;



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

public class ProjectConfigured_JACK0931 extends WebDriverHelper{

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
		
			handleLoadingImage(100);
		//	waitForElementPresent(100,common, "AdminDashboard_DDLDivision");
			String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
			rs=MSAccessCon.testCon(sQuery);
			//Contract.setEnforceAndRequieMUMOnAccountPage(rs,"GuideWell Group Inc.", 0, 0);
			
			Common.searchByResourceID(rs);
			
			//WebControls.clickOnLinkObjects("Remove DNR Flag");
			handleLoadingImage(100);
			//WebControls.clickOnLinkObjects("Activate");
			handleLoadingImage(100);
			Common.clickOnTabInResourceDetailPage("Projects");
			handleLoadingImage(100);
			Thread.sleep(10000);
		//	driver.findElement(By.xpath("//*[@id='ctl00_contplhDynamic_ZeroChaosGrid_ctl00_ctl04_LinkButton1']")).click();
			Project.clickOnCopytoNewLink();
			waitForElementToBeClickable(60, common, "OKButton_ConfirmPopup");
			WebControls.clickOnWebObjects("WebButton", common, "OKButton_ConfirmPopup");
			handleLoadingImage(100);
			//Thread.sleep(20000);
			waitForElementPresent(60,project, "ProjectHeader");
			
			Contract.setEnforceAndRequieMUMOnAccountPage(rs, Common.getLinkByLabel("Contract"), 0, 0);
			
			
			ResourceAdmin.clickOnPayCodesTabOnProjectDetail();
			
			Project.overrideOTFactorOnPaycodePage(1.50);
		
			//WebControls.setValuesOnWebTable("WebEdit",resourceAdmin, "PayCodeTable", "Regular Time","Supplier Rate","100", 1, 2);
			
			ResourceAdmin.selectPayCodeOnPayCodes("Double Time");
			ResourceAdmin.setCustomerRateOnPayCodes("110.22");
			
			ResourceAdmin.addPayCodeOnPayCodes("500000");
			
			
			
			//ResourceAdmin.setResourceRateOnPayCodes(ResourceAdmin.getValueFromSupplierRate());
			
			ResourceAdmin.clickOnPaycodesAddButton();
			
		//	driver.navigate().refresh();
			handleLoadingImage(100);
			
		//	ResourceAdmin.clickOnDetailsTabProjectDetail();
			
		
			
			Common.logout();
			
						
		//Common.login(ResultSet rs);
	}



}
