package com.zc.webdriver.applicationLiberaries;

import static com.zc.webdriver.commonLiberaries.ReadObjectProperties.objLocatorValue;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.*;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.grid.web.HandlerNotFoundException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;


import com.zc.webdriver.commonLiberaries.ConnectionWithSQLServer;
import com.zc.webdriver.commonLiberaries.ReadObjectProperties;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.Utilities;
import com.zc.webdriver.commonLiberaries.WebControls;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

public class Project extends WebDriverHelper
{
	
	public static void clickOnCopytoNewLink()throws Throwable
	{
		WebElement ele = driver.findElement(By.xpath("//*[text()=' Copy to New']"));
		
		ele.click();
		
	}
	
	public static void addContactToProjectOnContactPage(String UserType,String ContactType,String contactName)throws Throwable
	{
		
			cReport.testStep("isContactAssignedToProject method started");
			WebControls.setValuesForWebObjects("WebList", project, "UserTypeDDL_Contacts", UserType);
			WebControls.setValuesForWebObjects("WebList", project, "ContactTypeDDl_Contacts", ContactType);
			handleLoadingImage(60);
			Thread.sleep(10000);
			String[] fullName = contactName.split("\\s");
			String firstName = fullName[1].trim();
			String lastName = fullName[0].trim().replace(',', ' ').trim();
			boolean isManagerAvail=false;
			//*[@id='ContactDropDown_chzn_o_0']
			WebControls.clickOnWebObjects("WebButton",  project, "Contact_Contacts");
			WebElement ele1 =driver.findElement(By.xpath("//*[@value='Click to select one or more options']"));
			TypeInField(ele1, lastName);
			//WebControls.setValuesForWebObjects("WebList", project, "Contact_Contacts", contactName);
			Thread.sleep(5000);
			
		/*	String selectContact = driver.findElement(By.xpath("//*[@class='active-result highlighted']")).getText();
			if(selectContact.contains(contactName))
			{
				driver.findElement(By.xpath("//*[@class='active-result highlighted']")).click();
			}*/
			List<WebElement> eleList = driver.findElements(By.xpath("//*[@class='active-result']"));
			//List<WebElement> eleList= se.getOptions();
			for(WebElement ele : eleList)
			{
				if(ele.getText().contains(contactName))
				{
					ele.click();
					isManagerAvail = true;
					break;
				}
			}
			if(!isManagerAvail)
			{
				WebControls.clickOnWebObjects("WebButton",  project, "Contacts_ViewAllContactLink");
				handleLoadingImage(60);
				Common.autoCompleteSeatchFilter(firstName);
				
				WebControls.clickOnWebObjects("WebButton",  common, "ApplyButton");
				handleLoadingImage(60);
				Common.selectViewDivisionManager(firstName, lastName);
				WebControls.clickOnWebObjects("WebButton",  project, "Contacts_SelectContactButton");
				handleLoadingImage(60);
				
			}
			WebControls.clickOnWebObjects("WebButton",  project, "AddButton_Contacts");
			handleLoadingImage(60);
			
		
	}
	
	public static boolean isContactAssignedToProject(String contactName) throws Throwable
	{
		cReport.testStep("isContactAssignedToProject method started");
		boolean flag= false;
		String name;
		String dataHeader;
		int columnNum=0;
		int ij = 0;
	
		WebElement projectOw = driver.findElement(By.id("ProjectOwnersGrid"));
		WebElement projectTable=projectOw.findElement(By.xpath("//table[@class='table table-bordered table-full-width']"));
		WebElement projectTableData=projectOw.findElement(By.xpath("//table[@class='table table-bordered table-full-width']/tbody"));
		List<WebElement> tableRow= projectTable.findElements(By.tagName("tr"));
		List<WebElement> tableRowData= projectTableData.findElements(By.tagName("tr"));
		int rowCount=tableRowData.size();
		if(rowCount > 0)
		{
			for(int i =0; i<rowCount;i++)
			{
				if(ij>0)
				{	
					Common.clickOnLinkByText("Contacts", 1);
					handleLoadingImage(60);
					 projectOw = driver.findElement(By.id("ProjectOwnersGrid"));
					 projectTable=projectOw.findElement(By.xpath("//table[@class='table table-bordered table-full-width']"));
					 projectTableData=projectOw.findElement(By.xpath("//table[@class='table table-bordered table-full-width']/tbody"));
					tableRow= projectTable.findElements(By.tagName("tr"));
					 tableRowData= projectTableData.findElements(By.tagName("tr"));
				}
				List<WebElement> dataElements= tableRowData.get(i).findElements(By.tagName("td"));
				List<WebElement> dataHeaderEle= tableRow.get(i).findElements(By.tagName("th"));
				for(int j=0;j<dataHeaderEle.size();j++)
				{
					dataHeader = dataHeaderEle.get(j).getText();
					//System.out.println(data);
					
					if(dataHeader.contains("Name"))
					{
						columnNum = j;
						for(int k=1;k<=rowCount;k++)
						{
							// dataElements= tableRowData.get(i).findElements(By.tagName("td"));
							name = dataElements.get(columnNum).getText();
							if(!name.contains(contactName))
							{
								//String xpathGen=getXPath(dataElements.get(dataElements.size()-1));
								WebElement eleFullPlus = dataElements.get(dataElements.size()-1);
								highLightElement(eleFullPlus.findElement(By.className("popovers")));
								String xpathGen=getXPath(eleFullPlus.findElement(By.className("popovers")));
								eleFullPlus.findElement(By.className("popovers")).click();
								driver.findElement(By.xpath("//*[text()=' Un-assign Contact']")).click();
								waitForElementToBeClickable(60, common, "OKButton_ConfirmPopup");
								WebControls.clickOnWebObjects("WebButton", common, "OKButton_ConfirmPopup");
								
								handleLoadingImage(60);
								ij++;
								break;
								
							}
							else
								flag=true;
							/*tableRowData= projectTableData.findElements(By.tagName("tr"));	
							rowCount=tableRowData.size();*/
						}
					}
					if(ij > 0)
						break;
				}
				
			}
		}
		
		return flag;
		
		
	}
	
	
	
	public static void clickOnTopProjectIdFromAdminProjectListPage() throws Exception
	{
		Thread.sleep(10000);
		
		WebElement tableHeaderEle=driver.findElement(By.xpath("//*[@id='AdminResourceProjectGrid']"));
		int projectIDCol=getColumnNumberFromColumnName(tableHeaderEle,"th", "ID");
		
		WebElement elementTable=driver.findElement(By.xpath("//*[@id='AdminResourceProjectGrid']//table[@class='table table-bordered table-full-width']/tbody"));
		List<WebElement> dataList=elementTable.findElements(By.tagName("td"));
		String projectID = dataList.get(projectIDCol+1).getText();
		driver.findElement(By.linkText(projectID)).click();
		handleLoadingImage(60);
		
	}
	
	public static void paycodeActiveFromTable(String payCodeName) throws Exception
	{
		WebElement tableHeaderEle=driver.findElement(By.xpath("//*[@id='ProjectPayCodeGrid']"));
		int actionColNo=getColumnNumberFromColumnName(tableHeaderEle,"th", "Actions");
		
		
		
		WebElement tableDataEle=driver.findElement(By.xpath("//*[@id='ProjectPayCodeGrid']//table[@class='table table-bordered table-full-width']/tbody"));
		List<WebElement> dataList = tableDataEle.findElements(By.tagName("td"));
		for(int i = 0;i<dataList.size();i++)
		{
						
			if(dataList.get(i).getText().contains(payCodeName))
			{
				String xpathFind = getXPath(dataList.get(i+(actionColNo)));
				String partialPath="/div[@class='actions-collapsed in']/a[@class='popovers ']";
				String fixpath=xpathFind+partialPath;
				dataList.get(i+(actionColNo)).findElement(By.xpath(fixpath)).click();
				driver.findElement(By.linkText("Activate")).click();
				handleLoadingImage(60);
			}
		}
		
			
	}
	
	public static int getColumnNumberFromColumnName(WebElement ele,String tagName,String columnName)
	{
		WebElement tableEle=ele;
		List<WebElement> headerElements = tableEle.findElements(By.tagName(tagName));
		int actionColumn=0;
		
		for(int i=0;i<headerElements.size();i++)
		{
			if(headerElements.get(i).getText().equalsIgnoreCase(columnName))
			{
				actionColumn =i;
				break;
			}
		}
		return actionColumn-1;
		
	}
	
	public static void clickOnBUOnProjectDetailPage(String labelName)
	{
		 String valueName=null;
		List<WebElement> eleList=driver.findElements(By.xpath("//*[@class='span3 no-margin']/a/parent::*"));
		for (int i = 0; i < eleList.size(); i++) 
		{
			if(eleList.get(i).getText().contains(labelName))
			{
				String sBUWithLabelName=eleList.get(i).getText();
				 if(sBUWithLabelName.contains(":"))
				 {
					 String[] labelArr=sBUWithLabelName.split(":",2);
					 valueName=labelArr[1].trim();
				 }
				 else
				 {
					 String[] labelArr=sBUWithLabelName.split("\\s",2);
					 valueName=labelArr[1].trim(); 
				 }
				 
				break;
				
				
			}
		}
		   List<WebElement> listEle=driver.findElements(By.xpath("//*[@class='span3 no-margin']/a"));
			 for (int k = 0; k < eleList.size(); k++) 
			{
					if(listEle.get(k).getText().contains(valueName))
					{
						listEle.get(k).click();
						break;
					}
			}
		
	}
	
	public static void payCodeUpdateFromDB(String payCodeName) throws Exception
	{
		String sQLQuery = "update zcProjPayCode set Active = 1 where projid="+ Common.projectID+" and PayCodeDesc like'%" + payCodeName +'%'+"'";
		ConnectionWithSQLServer.connectionSqlServerExecuteUpdate(sQLQuery);
		
	}
	
	public static void inactivePayCodeUpdateFromDB(String payCodeName) throws Exception
	{
		String sQLQuery = "update zcProjPayCode set Active = 0 where projid="+ Common.projectID+" and PayCodeDesc like'%" + payCodeName +'%'+"'";
		ConnectionWithSQLServer.connectionSqlServerExecuteUpdate(sQLQuery);
		
	}
	public static String getProjectIDFromProjectDetailPage()
	{
		String projectIDXpath =  driver.findElement(By.xpath("//*[@class='caption']")).getText();
		String str[]=projectIDXpath.split("\\s");
		String projectWithBracket = str[str.length-1];
		
		String projectID = projectWithBracket.replace(")", "");
		projectID = projectID.replace("(", "");
		return projectID.trim();
		
		
	}
	public static void setProjectStartDate(int days) throws Throwable
	{
		String sDate = Utilities.getDate(days);
		WebControls.setValuesForWebObjects("WebEdit", project, "ProjectEdit_ProjectStartDate", sDate);
		driver.findElement(By.id("ProjectDurationAndSettingsEdit_StartDate")).sendKeys(Keys.TAB);
		
		
	}
	public static void setProjectEndDate(int days) throws Throwable
	{
		String sDate = Utilities.getDate(days);
		WebControls.setValuesForWebObjects("WebEdit", project, "ProjectEdit_ProjectEndDate", sDate);
		driver.findElement(By.id("ProjectDurationAndSettingsEdit_EndDate")).sendKeys(Keys.TAB);
	}
	
	public static void closeAllProjectFromDB() throws Exception
	{
		String sSQLForProject = "Update zcProject set ProjStatusID=16 where ResourceCode ='" + globalRS.getNString("sResourceID") + "' and ProjStatusID<>16";
		ConnectionWithSQLServer.connectionSqlServerExecuteUpdate(sSQLForProject);
		
	}
	public static void declineAllTimesheetFromDB() throws Exception
	{
		
		String sSQLForTimesheet = "Update zcTimeSheet set TimeStatusID=64 where ResourceCode ='" + globalRS.getNString("sResourceID") + "'";
		ConnectionWithSQLServer.connectionSqlServerExecuteUpdate(sSQLForTimesheet);
		
	}
	public static void setProjectWorkStateOnProjectDetail() throws Throwable
	{
		cReport.testStep("setProjectWorkStateOnProjectDetail method started");
		WebControls.setValuesForWebObjects("WebList", project, "ProjectEdit_ProjectWorkState", globalRS.getString("sProjectWorkState"));
		
	}
	public static void setProjectLocationCodeOnProjectDetail() throws Throwable
	{
		cReport.testStep("setProjectLocationCodeOnProjectDetail method started");
		WebControls.setValuesForWebObjects("WebList", project, "ProjectEdit_ProjectLocationCode", globalRS.getString("sProjectlLocationCode"));
		
	}
	
	public static void unAssignAllTrackCodesOnGetProjectsTrackCodeAdmin() throws Throwable
	{
		cReport.testStep("unAssignAllTrackCodesOnGetProjectsTrackCodeAdmin method started");
		driver.findElement(By.id("CheckAllCheckbox")).click();
		Common.clickOnLinkByText("Unassign", 0);
		handleLoadingImage(60);
			
	}
	public static void addTrackCodeOnGetProjectsTrackCodeAdmin(String TCName) throws Throwable
	{
		cReport.testStep("addTrackCodeOnGetProjectsTrackCodeAdmin method started");
		WebControls.setValuesForWebObjects("WebEdit", project, "GetProjectsTrackCodeAdmin_TrackCodeEditBox", TCName);
		WebControls.setValuesForWebObjects("WebEdit", project, "GetProjectsTrackCodeAdmin_TrackCodeNameEditBox", TCName);
		WebControls.setValuesForWebObjects("WebCheckBox", project, "GetProjectsTrackCodeAdmin_ActiveChkBox", "ON");
	
		
		Common.clickOnLinkByText("Create & Assign", 0);
		
		handleLoadingImage(60);
			
	}
	
	public static void verifyProjectStatus(String actualStatus ,String expenctedStatus)
	{
		cReport.testStep("verifyProjectStatus method started");
		Calendar currentDate = Calendar.getInstance(); // gets current date instance. 
		 SimpleDateFormat formatter=  new SimpleDateFormat("yyyy.MMM.dd HH.mm.ss");
			String dateNow = formatter.format(currentDate.getTime());
		if(expenctedStatus.equalsIgnoreCase(actualStatus))
		{
			cReport.verificationStep("Pass", "Project Staus : "+actualStatus, dateNow);
			Reporter.log("Success message is  "+actualStatus);
			//ReportBuilder.ReportTestStep("Success message is ", "Pass", actualString,"success message");
		}
		else
		{
			cReport.verificationStep("Fail", "Project Staus : "+actualStatus, dateNow);
			Reporter.log("Success message is not being appeared  "+actualStatus);
			//ReportBuilder.ReportTestStep("Success message is ", "Fail", actualString,"success message");
		}
	}
	
	public static void updateResourceRatetFromDB(String proojectID,int resourceRate) throws Exception
	{
		cReport.testStep("updateResourceRatetFromDB method started");
		String sSQLForProject = "update zcProjPayCode set EndMemberPayRate = "+resourceRate+" where projid="+proojectID;
		
		ConnectionWithSQLServer.connectionSqlServerExecuteUpdate(sSQLForProject);
		
	}
	public static String getProjectStatusOnProjectDetailPage() throws Exception
	{
		cReport.testStep("getProjectStatusOnProjectDetailPage method started");
		
		String keyProjectStatus=SetProperpertiesFile.project.getPropertyValue("ProejctStatus");
		String xpathValueProjectStatus=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyProjectStatus);
		String projectStatus = driver.findElement(By.xpath(xpathValueProjectStatus)).getText();
		return projectStatus;
		
	}
	
	public static void assignTrackCodeToProject(String trackCodneName) throws Throwable
	{
		cReport.testStep("assignTrackCodeToProject method started");
		Common.clickOnLinkByText("Clear All", 0);
		WebControls.setValuesForWebObjects("WebList", project, "GetProjectsTrackCodeAdmin_FilterDDL", "Track Code");
		
		Thread.sleep(10000);
		WebElement TextBox = driver.findElement(By.xpath("//*[@id='TrackCodeSearchList_FilterRows_0__SelectedItems_chzn']/ul/li/input"));
		//driver.findElement(By.xpath("//*[@id='TrackCodeSearchList_FilterRows_0__SelectedItems_chzn']/ul/li/input")).sendKeys(Keys.);
	//	WebControls.setValuesForWebObjects("WebEdit", project, "GetProjectsTrackCodeAdmin_FilterEditBox", trackCodneName);
		Robot rb = new Robot();
		/*rb.keyPress(KeyEvent.VK_T);
		rb.keyPress(KeyEvent.VK_C);
		rb.keyPress(KeyEvent.VK_1);
		Thread.sleep(30000);
		rb.keyPress(KeyEvent.VK_DOWN);
		rb.keyPress(KeyEvent.VK_ENTER);*/
		
		typeKeys(trackCodneName, rb);
		Thread.sleep(3000);
		//rb.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='active-result']")).click();
        rb.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click	
        rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click	
        Thread.sleep(3000);
       // handleLoadingImage(60);
		//driver.findElement(By.linkText(trackCodneName)).click();
		/*JavascriptExecutor jst= (JavascriptExecutor) driver;
		jst.executeScript("arguments[1].value = arguments[0]; ", trackCodneName, TextBox);   
	*/
		//WebControls.clickOnWebObjects("Link", project, "GetProjectsTrackCodeAdmin_AutoPopulatedOption");
		Common.clickOnLinkByText("Apply", 0);
		handleLoadingImage(60);
		driver.findElement(By.id("CheckAllCheckbox")).click();
		handleLoadingImage(60);
		Common.clickOnLinkByText("Assign", 0);
		handleLoadingImage(60);
		
		
		
	}
	
	public static void unAssignContactFromProjectOnProjectDeail(String contactEmailID) throws Throwable
	{
		cReport.testStep("unAssignContactFromProjectOnProjectDeail method started");
		WebElement tableHeaderEle=driver.findElement(By.xpath("//*[@id='ProjectOwnersGrid']"));
		int actionColNo=getColumnNumberFromColumnName(tableHeaderEle,"th", "Actions");
		
		
		
		WebElement tableDataEle=driver.findElement(By.xpath("//*[@id='ProjectOwnersGrid']/table/tbody"));
		List<WebElement> dataList = tableDataEle.findElements(By.tagName("td"));
		for(int i = 0;i<dataList.size();i++)
		{
						
			if(dataList.get(i).getText().contains(contactEmailID))
			{
				String xpathFind = getXPath(dataList.get(i+(actionColNo-1)));
				String partialPath="/div/a/i";


				//*[@id="ProjectOwnersGrid"]/table/tbody/tr[4]/td[8]/div/a/i
				String fixpath=xpathFind+partialPath;
				Thread.sleep(2000);
				//dataList.get(i+(actionColNo-1)).findElement(By.xpath(fixpath)).click();
				driver.findElement(By.xpath(fixpath)).click();
				Thread.sleep(2000);
				driver.findElement(By.linkText("Un-assign Contact")).click();
				handleLoadingImage(60);
				waitForElementPresent(60, common, "OKButton_ConfirmPopup");
				WebControls.clickOnWebObjects("WebButton", common, "OKButton_ConfirmPopup");
				handleLoadingImage(60);
			}
			
		}
		
			
	}
	
	public static void selectOvertimeStrategyOnProectDetail(String OTName) throws Throwable
	{
		cReport.testStep("selectOvertimeStrategyOnProectDetail method started");
		
		WebControls.setValuesForWebObjects("WebList", project, "ProjectEdit_OTStrategyDDL", OTName);
		handleLoadingImage(60);
		
	
	}
	public static void selectJobCategoryOnProectDetail() throws Throwable
	{
		cReport.testStep("selectJobCategoryOnProectDetail method started");
		
		WebControls.setValuesForWebObjects("WebListIndex", project, "ProjectEdit_JobClassCategoryDDL", "1");
		handleLoadingImage(60);
		WebControls.setValuesForWebObjects("WebListIndex", project, "ProjectEdit_JobCategoryDDL", "1");
		handleLoadingImage(60);
		
	
	}
	
	public static void unAssignContactFromProject() throws Throwable
	{
		cReport.testStep("unAssignContactFromProject method started");
		driver.findElement(By.xpath("//*[@class='popovers']")).click();
		driver.findElement(By.xpath("//*[text()=' Un-assign Contact']")).click();
		handleLoadingImage(60);
		WebControls.clickOnWebObjects("WebButton",common, "OKButton_ConfirmPopup");
		handleLoadingImage(60);
	}
	
	public static void overrideOTFactorOnPaycodePage(double oTFactor) throws Throwable
	{
		WebControls.setValuesForWebObjects("WebEdit", project, "Rates_EditOvertimeFactor", oTFactor+"");
		WebControls.clickOnWebObjects("WebButton", common, "GreenButton");
		handleLoadingImage(60);
		
	}


}
