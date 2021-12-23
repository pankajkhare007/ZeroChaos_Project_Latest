package configureForProjectApprovalScripts;

import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.zc.webdriver.applicationLiberaries.Common;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class AddContactApprovalGroup  extends WebDriverHelper {
	
	
	ResultSet rs;
	@Test(priority =1)
	public void addContactLynnMurray()throws Throwable
	{
		SetProperpertiesFile.setAllProperpertiesFile();
		
		
		String testName=this.getClass().getSimpleName();
		cReport.startTest(testName);
		
		Common.login("ADMIN",SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT"));
	
		handleLoadingImage(100);
		Common.quickSearch("Customer Contacts", "Jeffrey.Dowd@rci.com");
	
	}
	

}
