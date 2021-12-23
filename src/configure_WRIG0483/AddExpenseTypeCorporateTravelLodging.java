package configure_WRIG0483;

import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.common;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.division;
import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.applicationLiberaries.Division;
import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class AddExpenseTypeCorporateTravelLodging extends WebDriverHelper{
	
	ResultSet rs;
	@Test(priority =1)
	public void addExepenseTypeOnDivision() throws Throwable
	{
		SetProperpertiesFile.setAllProperpertiesFile();
		flag=true;
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
		handleLoadingImage();
	
		//Thread.sleep(10000);
		String sQuery="select * From PreRequisiteForProject where sTestCaseName='"+testName+"'";
		rs=MSAccessCon.testCon(sQuery);
		Division.navigateToDivisionDetailPage(rs);
		//Division.clickOnApprovalGroupsOnClientDetail();
		Division.clickOnBUOnClientDetail();
		driver.findElement(By.linkText("GuideWell Group Inc.")).click();
		handleLoadingImage();
		Division.clickOnTabOnBusinessUnitPage("Expense Rules");
		waitForElementPresent(100,division, "ExpenseTypeDD_ExpRules");
		Division.addExpenseOnExpenseRulesPage("Corporate Travel - Lodging");
		Common.verifySuccessMessage("Successfully inserted.");
		Common.logout();
	}

}
