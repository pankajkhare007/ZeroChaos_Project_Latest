package com.zc.webdriver.applicationLiberaries;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.zc.webdriver.commonLiberaries.MSAccessCon;
import com.zc.webdriver.commonLiberaries.ReadObjectProperties;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.*;;

public class Common extends WebDriverHelper {
	public static String status;
	static ResultSet rs;
	public static String projectID = null;

	public static void login(String userRole, String environment) throws Throwable {
		String sQuery = "select * From Login where sUserRole='" + userRole + "' and sExecutionEnvironment = '"
				+ environment + "'";

		rs = MSAccessCon.testCon(sQuery);
		invokeBrowser();
		WebControls.setValuesForWebObjects("WebEdit", login, "UserNameTxtBox", rs.getString("sEmail_Address"));
		WebControls.setValuesForWebObjects("WebEdit", login, "PasswordTxtBox", rs.getString("sPassword"));
		WebControls.clickOnWebObjects("WebButton", login, "LoginButton");

		// handleLoadingImage();
		rs = null;
		Thread.sleep(5000);
		//*[@class='span12 page-heading']
		waitForElementPresent(100,common, "PageHeading");

	}

	public static void clickOnSaveButton() throws Throwable {
		cReport.testStep("clickOnSaveButton method started");
		WebControls.clickOnWebObjects("WebButton", common, "SAVE_BUTTON");
		handleLoadingImage();
	}

	public static void verifySuccessMessage(String expectedMsg) throws Throwable {
		handleLoadingImage();
		cReport.testStep("verifySuccessMessage method started");
		String actualString = null;

		if (isElementPresentWithoutProperties(By.id("successText"))) {
			actualString = driver.findElement(By.id("successText")).getText();
		}

		if (isElementPresent(common, "SUCCESS_MESSAGE")) {
			actualString = WebControls.getText("WebElement", common, "SUCCESS_MESSAGE");
		}

		Calendar currentDate = Calendar.getInstance(); // gets current date instance.
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MMM.dd HH.mm.ss");
		String dateNow = formatter.format(currentDate.getTime());
		if (expectedMsg.equalsIgnoreCase(actualString)) {
			cReport.verificationStep("Pass", "Successful Message is coming fine", dateNow);
			Reporter.log("Success message is  " + actualString);
			// ReportBuilder.ReportTestStep("Success message is ", "Pass",
			// actualString,"success message");
		} else {
			cReport.verificationStep("Fail", "Success message is not being appeared ", dateNow);
			Reporter.log("Success message is not being appeared  " + actualString);
			// ReportBuilder.ReportTestStep("Success message is ", "Fail",
			// actualString,"success message");
		}

	}

	public static void logout() throws Throwable {
		cReport.testStep("logout method started");
		/*List<WebElement> elements=driver.findElements(By.xpath("//i[@class='fa fa-chevron-down']"));
		elements.get(3).click();*/
		driver.findElement(By.xpath("//*[@id='dossieravatar']/a/span[3]")).click();
		
		handleLoadingImage();
		Common.clickOnLinkByText("Log Out", 0);
		waitForElementPresent(100, login, "LoginButton");
	}

	public static void clickOnSetupLink() throws Throwable {
		cReport.testStep("clickOnSetupLink method started");
		WebControls.clickOnWebObjects("Link", common, "SetupLink");
		handleLoadingImage();

	}

	public static void clickOnContractsMenu() throws Throwable {
		cReport.testStep("clickOnContractsMenu method started");
		WebControls.clickOnWebObjects("Link", common, "SetupLink");
		handleLoadingImage();

	}

	public static void searchByResourceID(ResultSet rs) throws Throwable {
		cReport.testStep("searchByResourceID method started");
		// WebControls.clickOnWebObjects("Link", common, "SEARCH_LINK");
		/*String keySearchLink = SetProperpertiesFile.common.getPropertyValue("SEARCH_LINK");
		String xpathValueSEARCH_LINK = new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keySearchLink);
		List<WebElement> searchElements = driver.findElements(By.xpath(keySearchLink));
		searchElements.get(2).click();*/
		
		driver.findElement(By.xpath("//*[@class='icon-btn-search zc-tooltip']")).click();

		handleLoadingImage();

		WebControls.clickOnWebObjects("Link", common, "RunQuickSearchMenu");

		JavascriptExecutor je = (JavascriptExecutor) driver;

		String keyDefaultApprover = SetProperpertiesFile.resourceAdmin.getPropertyValue("ResourceCodeMenuLink");
		String xpathValueDIVISION_MENU_LINK = new ReadObjectProperties()
				.getPropertyValueFromTypeIdentifier(keyDefaultApprover);

		WebElement element = driver.findElement(By.xpath(xpathValueDIVISION_MENU_LINK));
		je.executeScript("arguments[0].scrollIntoView(true);", element);

		WebControls.clickOnWebObjects("Link", resourceAdmin, "ResourceCodeMenuLink");
		Thread.sleep(2000);
		WebControls.setValuesForWebObjects("WebEdit", common, "QuickSearchText", rs.getString("sResourceID"));
		WebControls.clickOnWebObjects("Link", common, "QuickSearchButton");
		waitForElementPresent(60, resourceAdmin, "EditLink_AdminResource");

	}

	public static void clickOnAddButton() throws Throwable {
		cReport.testStep("clickOnAddButton method started");
		WebControls.clickOnWebObjects("WebButton", common, "ADD_BUTTON");
		handleLoadingImage();

	}

	public static void searchByCustomerContact(ResultSet rs) throws Throwable {
		cReport.testStep("searchByCustomerContact method started");
		WebControls.setValuesForWebObjects("WebEdit", common, "QuickSearchText", rs.getString("sCustomerEmailID"));
		String keyDefaultApprover = SetProperpertiesFile.division.getPropertyValue("CUSTOMERCONTACT_MENU_LINK");
		String xpathValueDIVISION_MENU_LINK = new ReadObjectProperties()
				.getPropertyValueFromTypeIdentifier(keyDefaultApprover);

		WebElement element = driver.findElement(By.xpath(xpathValueDIVISION_MENU_LINK));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", element);

		WebControls.clickOnWebObjects("Link", division, "CUSTOMERCONTACT_MENU_LINK");

		// waitForElementPresent(100, division, "CustomerNameDDL_ClientSearch");
		WebControls.clickOnWebObjects("Link", common, "QuickSearchButton");
		waitForElementPresent(60, resourceAdmin, "LoginAsResource_ResourceDetail");

	}

	public static void setDate(WebElement element, int daysAdded) throws Exception {

		WebElement selectDate = element;

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, daysAdded);
		int month = cal.get(Calendar.MONTH);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MMM/yyyy");

		String formatted = format1.format(cal.getTime());

		// button to open calendar

		selectDate.click();

		// button to move next in calendar

		WebElement nextLink = driver.findElement(By.xpath("html/body/div[11]/div[2]/table/thead/tr/th[3]"));

		// button to click in center of calendar header

		WebElement midLink = driver.findElement(By.xpath("//table[@class=' table-condensed']//tr[1]/th[2]"));

		// button to move previous month in calendar

		WebElement previousLink = driver.findElement(By.xpath("html/body/div[11]/div[2]/table/thead/tr/th[1]"));

		// Split the date time to get only the date part

		String date_dd_MM_yyyy[] = formatted.split("/");

		// get the year difference between current year and year to set in calander

		midLink.click();

		String defaultYear = driver.findElement(By.xpath("html/body/div[11]/div[2]/table/thead/tr/th[2]")).getText();

		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Integer.parseInt(defaultYear);

		if (yearDiff != 0) {

			// if you have to move next year

			if (yearDiff > 0) {

				for (int i = 0; i < yearDiff; i++) {

					System.out.println("Year Diff->" + i);

					nextLink.click();

				}

			}

			// if you have to move previous year

			else if (yearDiff < 0) {

				for (int i = 0; i < (yearDiff * (-1)); i++) {

					System.out.println("Year Diff->" + i);

					previousLink.click();

				}

			}

		}

		Thread.sleep(1000);

		// Get all months from calendar to select correct one

		// List<WebElement> list_AllMonthToBook =
		// driver.findElements(By.xpath("//div[@class='datepicker-months']//table//tbody//td[not(contains(@class,'month'))]"));

		List<WebElement> list_AllMonthToBook = driver.findElements(By.xpath("//span[@class='month']"));

		list_AllMonthToBook.get(month).click();

		Thread.sleep(1000);

		// get all dates from calendar to select correct one

		List<WebElement> list_AllDateToBook = driver.findElements(By.xpath("//*[@class='day']"));

		list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0]) - 1).click();
		Thread.sleep(1000);

		/*
		 * ///FOR TIME
		 * 
		 * WebElement selectTime = driver.findElement(By.xpath(
		 * "//span[@aria-controls='datetimepicker_timeview']"));
		 * 
		 * //click time picker button
		 * 
		 * selectTime.click();
		 * 
		 * //get list of times
		 * 
		 * List<WebElement> allTime = driver.findElements(By.
		 * xpath("//div[@data-role='popup'][contains(@style,'display: block')]//ul//li[@role='option']"
		 * ));
		 * 
		 * dateTime = dateTime.split(" ")[1]+" "+dateTime.split(" ")[2];
		 * 
		 * //select correct time
		 * 
		 * for (WebElement webElement : allTime) {
		 * 
		 * if(webElement.getText().equalsIgnoreCase(dateTime))
		 * 
		 * {
		 * 
		 * webElement.click();
		 * 
		 * }
		 */

	}

	public static void clickOnLinkByText(String sText, int index) {
		String customXpath = "//a[text()='" + sText + "']";
		if (index > 0) {
			List<WebElement> listLink = driver.findElements(By.xpath(customXpath));

			listLink.get(index).click();
		} else
			driver.findElement(By.linkText(sText)).click();

	}

	public static String getTextByLabel(String labelName) {
			String customXpath = "//*[text()='" + labelName + "']/parent::*";
			String labelWithText = driver.findElement(By.xpath(customXpath)).getText();
			String arr[] = labelWithText.split(":");
			return arr[1].trim();

	}

	public static void verifyStatus(String expectedStatus) {
		cReport.testStep("verifyStatus method started");

		if (expectedStatus.equalsIgnoreCase(Common.status)) {
			cReport.verificationStep("Pass", "Status is " + Common.status, "sc");
			Reporter.log("Status is  " + Common.status);
			// ReportBuilder.ReportTestStep("Success message is ", "Pass",
			// actualString,"success message");
		} else {
			cReport.verificationStep("Fail", "Status is " + Common.status, "sc");
			Reporter.log("Status is  " + Common.status);
			// ReportBuilder.ReportTestStep("Success message is ", "Fail",
			// actualString,"success message");
		}

	}

	public static void clickOnTabInResourceDetailPage(String linkName) {
		WebElement tabsEle = driver.findElement(By.xpath("//*[@class='drop_menu']"));
		//tabsEle.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		tabsEle.findElement(By.linkText(linkName)).click();
		// for()
	}

	public static String getLinkByLabel(String labelName) throws Throwable {
		String customXpath = "//*[text()='" + labelName + "']/parent::*";
		String labelWithValue;
		String valueName;

		customXpath = "//*[text()='" + labelName + "']/parent::*";
		labelWithValue = driver.findElement(By.xpath(customXpath)).getText();
		if (labelName.contains(":")) {
			String[] labelArr = labelWithValue.split(":", 2);
			valueName = labelArr[1].trim();
		} else {
			String[] labelArr = labelWithValue.split("\\s", 2);
			valueName = labelArr[1].trim();
		}

		return valueName;
	}

	public static void searchByProjectIDID(String projectID) throws Throwable {
		cReport.testStep("searchByResourceID method started");
		// WebControls.clickOnWebObjects("Link", common, "SEARCH_LINK");
		String keySearchLink = SetProperpertiesFile.common.getPropertyValue("SEARCH_LINK");
		String xpathValueSEARCH_LINK = new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keySearchLink);
		List<WebElement> searchElements = driver.findElements(By.xpath(keySearchLink));
		searchElements.get(2).click();

		handleLoadingImage();

		WebControls.clickOnWebObjects("Link", common, "RunQuickSearchMenu");

		JavascriptExecutor je = (JavascriptExecutor) driver;

		String keyDefaultApprover = SetProperpertiesFile.resourceAdmin.getPropertyValue("ProjectIDMenuLink");
		String xpathValueDIVISION_MENU_LINK = new ReadObjectProperties()
				.getPropertyValueFromTypeIdentifier(keyDefaultApprover);

		WebElement element = driver.findElement(By.xpath(xpathValueDIVISION_MENU_LINK));
		je.executeScript("arguments[0].scrollIntoView(true);", element);

		WebControls.clickOnWebObjects("Link", resourceAdmin, "ProjectIDMenuLink");
		Thread.sleep(2000);
		WebControls.setValuesForWebObjects("WebEdit", common, "QuickSearchText", projectID);
		WebControls.clickOnWebObjects("Link", common, "QuickSearchButton");
		waitForElementPresent(100, project, "ProjectHeader");

	}

	public static String getData(String labelName) {
		WebElement tableEle = driver.findElement(By.xpath("//*[@id='EngagementSpendSummaryDetailTable']/tbody"));

		List<WebElement> tRow = tableEle.findElements(By.tagName("tr"));
		// List<WebElement> tHeader = tableEle.findElements(By.tagName("th"));
		// List<WebElement> tData = tableEle.findElements(By.tagName("td"));
		// int i = 0;
		String data = null;
		/// int j=0;
		String str = driver.findElement(By.xpath("//*[text()='Approved Budget:']//parent::*")).getAttribute("value");
		String label;
		for (int i = 0; i < tRow.size(); i++) {
			List<WebElement> tHeader = tRow.get(i).findElements(By.tagName("th"));
			List<WebElement> tData = tRow.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < tHeader.size(); j++) {
				label = tHeader.get(j).getText();
				data = tData.get(j).getText();
				if (label.equals(labelName)) {
					data = tData.get(j).getText();
				}
			}

			// i++;
		}

		return data;
	}

	public static void LeftNavigationMenu(String MainMenu, String SubValue, String ChildValue) {
		
		WebElement e = driver.findElement(By.xpath("//ul[@class='nav-items']"));

		List<WebElement> sube = e.findElements(By.tagName("li"));

		// ArrayList<String> Menu = new ArrayList<>();
		for (WebElement w : sube) {

			String value = w.getText().toString();
			if (MainMenu.equals(value)) {

				Actions build = new Actions(driver);
				build.moveToElement(w).build().perform();
				if (SubValue != null )
				{
					if(ChildValue==null)
						driver.findElement(By.linkText(SubValue)).click();
					else
					{
						build = new Actions(driver);
						build.moveToElement(driver.findElement(By.linkText(SubValue))).build().perform();
						driver.findElement(By.linkText(ChildValue)).click();
					}
				}
				else
				{
					
					driver.findElement(By.linkText(MainMenu)).click();
				}

			}

		}
					
					/*List<WebElement> subMenuList = w.findElements(By.xpath("//ul[@class='class-children']"));
					for (WebElement sList : subMenuList) {

						String subMenuText = sList.getText().toString();
						if (SubValue.contains(subMenuText)) {
							if (ChildValue != null) {
								build = new Actions(driver);
								build.moveToElement(sList).build().perform();
								if (ChildValue != null) {
									List<WebElement> finalList = sList
											.findElements(By.xpath("//ul[@class='class-submenu']"));
									for (WebElement finalEle : finalList) {
										String finalMenuText = finalEle.getText().toString();
										if (ChildValue.equals(finalMenuText))
											finalEle.click();
										break;

									}
								}

							} else {
								w.findElement(By.linkText(SubValue)).click();
							}
								//sList.click();
								

						}
					} // end of loop
*/
			
	}
	public static void autoCompleteSeatchFilter(String str) throws Throwable
	{
		WebControls.setValuesForWebObjects("WebList", project, "Contacts_SearchContactFilter", "First Name");
		handleLoadingImage();
		WebControls.setValuesForWebObjects("WebEdit", project, "Contacts_ContactAutoCompleteInput", str);
		handleLoadingImage();
		//List<WebElement> wList=driver.findElements(By.xpath("//*[@class=active-result]"));
		driver.findElement(By.id("ManagerContactSearch_FilterRows_0__SelectedItems_chzn_o_0")).click();
		handleLoadingImage();
		/*for(WebElement ele : wList)
		{
			if(ele.getText().trim().equals(str))
			{
				ele.click();
			}
		}*/
	}
	
	public static void selectViewDivisionManager(String firstName,String lastName)
	{
		WebElement tableElement=driver.findElement(By.id("ManagersContactGrid"));
		List<WebElement> tableRow = tableElement.findElements(By.tagName("tr"));
		for(WebElement we : tableRow)
		{
			List<WebElement>rowEle=we.findElements(By.tagName("td"));
			for(int i =0; i<rowEle.size();i++)
			{
				if(rowEle.get(i).getText().trim().equals(lastName))
				{
					if(rowEle.get(i+1).getText().trim().equals(firstName))
					{
						rowEle.get(i-1).click();
						break;
					}
				}
			}
		}
	}
	
	public static int getRowIndexByCellTextDataFromTable(String tableID,String data)
	{
		int rowNum=0;
		WebElement tableEle = driver.findElement(By.id(tableID));
		List<WebElement> rowList = tableEle.findElements(By.tagName("tr"));
		for(int i= 1;i<rowList.size();i++)
		{
			List<WebElement> dataList = rowList.get(i).findElements(By.tagName("td"));
			for(int j=0;j<dataList.size();j++)
			{
				if(dataList.get(j).getText().trim().equals(data))
				{
					rowNum=i;
					break;
				}
				
			}
		}
		return rowNum;
	}
	public static int getColumnIndexByCellTextDataFromTable(String tableID,String headerColumn) throws InterruptedException
	{
		boolean flag = false;
		int colNum=0;
		WebElement tableEle = driver.findElement(By.xpath(tableID));
		List<WebElement> rowList = tableEle.findElements(By.tagName("tr"));
		highLightElement(rowList.get(0));
		for(int i= 0;i<rowList.size();i++)
		{
			List<WebElement> headerList = rowList.get(i).findElements(By.tagName("th"));
			highLightElement(rowList.get(i));
			for(int j=0;j<headerList.size();j++)
			{
			
				if(headerList.get(j).getText().trim().equals(headerColumn))
				{
					
					colNum=j;
					flag = true;
					break;
					
				}
				if(flag)
					break;
			}
			if(flag)
				break;
		}
		return colNum;
	}
	
	public static WebElement getDataFromTable(String tableID,String tableHeaderColumn,String tableData) throws InterruptedException
	{
		WebElement data=null;
		
	
		int colNum = getColumnIndexByCellTextDataFromTable(tableID,tableHeaderColumn);
		WebElement tableEle = driver.findElement(By.xpath(tableID));
		List<WebElement> rowList = tableEle.findElements(By.tagName("tr"));
		for(int i= 1;i<rowList.size();i++)
		{
			List<WebElement> dataList = rowList.get(i).findElements(By.tagName("td"));
			for(int j=0;j<dataList.size();j++)
			{
				if(dataList.get(j).getText().trim().contains(tableData))
				{
					System.out.println("Row Number : "+i);
					data = dataList.get(colNum);
					break;
				}
				if(data!=null)
					break;
				
			}
			if(data!=null)
				break;
		}
		return data;
		
	}
	
	public static WebElement getDataFromTable(String tableID,int colNum,String tableData)
	{
		WebElement data=null;
		
	
		//int colNum = getColumnIndexByCellTextDataFromTable(tableID,tableHeaderColumn);
		WebElement tableEle = driver.findElement(By.id(tableID));
		List<WebElement> rowList = tableEle.findElements(By.tagName("tr"));
		for(int i= 1;i<rowList.size();i++)
		{
			List<WebElement> dataList = rowList.get(i).findElements(By.tagName("td"));
			for(int j=0;j<dataList.size();j++)
			{
				if(dataList.get(j).getText().toLowerCase().contains(tableData.toLowerCase()))
				{
					System.out.println("Row Number : "+i);
					data = dataList.get(colNum);
					break;
				}
				if(data!=null)
					break;
				
			}
			if(data!=null)
				break;
		}
		return data;
		
	}
	public static void quickSearch(String objectName,String searchItem ) throws Throwable
	{
		cReport.testStep("quickSearch method started");
		
		driver.findElement(By.xpath("//*[@class='icon-btn-search zc-tooltip']")).click();

		handleLoadingImage();

		WebControls.clickOnWebObjects("Link", common, "RunQuickSearchMenu");
		String xpathString= "//a[contains(text(),'"+objectName+"')]";
		
		WebElement objectNameEle =driver.findElement(By.xpath(xpathString));

		JavascriptExecutor je = (JavascriptExecutor) driver;

		
		je.executeScript("arguments[0].scrollIntoView(true);", objectNameEle);
		objectNameEle.click();
		Thread.sleep(2000);
		WebControls.setValuesForWebObjects("WebEdit", common, "QuickSearchText",searchItem);
		WebControls.clickOnWebObjects("Link", common, "QuickSearchButton");
		//waitForElementPresent(60, resourceAdmin, "EditLink_AdminResource");
	}
	public static void clickOnTab(String tabName)
	{
		cReport.testStep("clickOnTab method started");
		List<WebElement> eleList = driver.findElements(By.xpath("//*[@class='tab-item']"));
		for(WebElement ele : eleList)
		{
			if(ele.getText().trim().equals(tabName))
			{
				ele.click();
				break;
			}
		}
	}
	public static WebElement getRowElementByCellTextDataFromTable(String tableID,String data)
	{
		WebElement rowEle=null;
		WebElement tableEle = driver.findElement(By.id(tableID));
		List<WebElement> rowList = tableEle.findElements(By.tagName("tr"));
		for(int i= 1;i<rowList.size();i++)
		{
			List<WebElement> dataList = rowList.get(i).findElements(By.tagName("td"));
			for(int j=0;j<dataList.size();j++)
			{
				if(dataList.get(j).getText().trim().contains(data))
				{
					rowEle=rowList.get(i);
					break;
				}
				
			}
		}
		return rowEle;
	}
}
