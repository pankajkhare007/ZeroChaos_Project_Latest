package com.zc.webdriver.commonLiberaries;

//import static com.rishabhSoft.selenium.zeroChaos.drivers.ReadObjectProperties.objLocatorValue;
import static com.zc.webdriver.commonLiberaries.ReadObjectProperties.*;
import static com.zc.webdriver.commonLiberaries.SetProperpertiesFile.resourceAdmin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
public class WebControls extends WebDriverHelper
{
	
	
	public static void setValuesForWebObjects(String controlType,ReadObjectProperties propObject,String proertiesValue,String sText )throws Throwable
	{
		LocatorType locator =propObject.getLocatorType(proertiesValue);
		
		switch(controlType)
		{
			case "WebEdit" :
				driver.findElement(locator.execute(objLocatorValue)).clear();
				driver.findElement(locator.execute(objLocatorValue)).sendKeys(sText);
				cReport.testStep("Entered in "+proertiesValue+": " +sText);
				Reporter.log("Entered in "+proertiesValue+": " +sText);
				//ReportBuilder.ReportTestStep("Entered in", "Pass", proertiesValue);
				break;
			case "WebList" :
				Select select = new Select(driver.findElement(locator.execute(objLocatorValue)));
				String selectedOption=select.getFirstSelectedOption().getText();
				if(!selectedOption.equals(sText))
				{
					select.selectByVisibleText(sText);
				}
				cReport.testStep("Selected on dropdown "+proertiesValue+": " +sText);
				Reporter.log("Selected on dropdown "+proertiesValue+": " +sText);
				
				//ReportBuilder.ReportTestStep("Selected on dropdown", "Pass", proertiesValue);
				break;
			case "WebListIndex" :
				Select select1 = new Select(driver.findElement(locator.execute(objLocatorValue)));
				int index = Integer.parseInt(sText);
				select1.selectByIndex(index);
				cReport.testStep("Selected on dropdown by index "+ index );
				Reporter.log("Selected on dropdown by index "+ index );
				
				//ReportBuilder.ReportTestStep("Selected on dropdown", "Pass", proertiesValue);
				break;
			case "SuggetionEditBox" :
				//driver.findElement(arg0)
				 driver.findElement(locator.execute(objLocatorValue)).sendKeys(Keys.ENTER);
				 cReport.testStep("Set on SuggetionEditBox "+proertiesValue+": " +sText);
				 Reporter.log("Set on SuggetionEditBox "+proertiesValue+": " +sText);
				// Thread.sleep(15000);
				 handleLoadingImage();
				 break;
			case "WebCheckBox" :
				if(sText.equalsIgnoreCase("ON"))
				{
					if( !driver.findElement(locator.execute(objLocatorValue)).isSelected())
					{
						driver.findElement(locator.execute(objLocatorValue)).sendKeys(Keys.SPACE);
					}
				}
				else
				{

					if( driver.findElement(locator.execute(objLocatorValue)).isSelected())
					{
						driver.findElement(locator.execute(objLocatorValue)).sendKeys(Keys.SPACE);
					}
				}
				
				 cReport.testStep("WebCheck box "+proertiesValue+ " checked");
				break;
			case "WebRadioGroup" :
			
				String rdoValue=driver.findElement(locator.execute(objLocatorValue)).getAttribute("checked");
				if(rdoValue==null)
				{
					rdoValue="false";
				}
				
				if(!sText.equalsIgnoreCase(rdoValue))
				{
					driver.findElement(locator.execute(objLocatorValue)).click();
					Thread.sleep(2000);
				}
			
				break;
			default :
				//ReportBuilder.ReportTestStep(stepName, status, testData);
				Reporter.log("Control Type is not present");	
				cReport.testStep("Control Type is not present");
				
				
		}
	}
	public static void clickOnWebObjects(String controlType,ReadObjectProperties propObject,String proertiesValue)throws Throwable
	{
		LocatorType locator =propObject.getLocatorType(proertiesValue);
		
		switch(controlType)
		{
			case "WebButton" :
				driver.findElement(locator.execute(objLocatorValue)).click();
				Reporter.log("Click on Button "+proertiesValue);
				cReport.testStep("Click on Button "+proertiesValue);
			//	ReportBuilder.ReportTestStep("Clicked on Button", "Pass", proertiesValue);
				break;
			case "Link" :
				driver.findElement(locator.execute(objLocatorValue)).click();
				Reporter.log("Click on Link "+proertiesValue);
				cReport.testStep("Click on Link "+proertiesValue);
			//	ReportBuilder.ReportTestStep("Clicked on Link", "Pass", proertiesValue);
				break;
						
				
			default :
				//ReportBuilder.ReportTestStep(stepName, status, testData);
				Reporter.log("Control Type is not present");
				cReport.testStep("Control Type is not present");
				
		}
	}
	
	public static void mouseOverOnWebObjects(ReadObjectProperties propObject,String proertiesValue)throws Throwable
	{
		LocatorType locator =propObject.getLocatorType(proertiesValue);
		//driver.findElement(locator.execute(objLocatorValue));
		//JavascriptExecutor je = (JavascriptExecutor) driver;
		//Actions action = new Actions(driver);
		//action.moveToElement(driver.findElement(locator.execute(objLocatorValue))).build().perform();
		String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";


		((JavascriptExecutor)driver).executeScript(javaScript, driver.findElement(locator.execute(objLocatorValue)));
		
		
		//WebElement element = driver.findElement(By.xpath(xpathValueDIVISION_MENU_LINK));
		
		
		/*Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator.execute(objLocatorValue))).build().perform();
		cReport.testStep("Mouse hover on  "+proertiesValue);*/
	}
	public static String getText(String controlType,ReadObjectProperties propObject,String proertiesValue) throws Throwable
	{
		String actualText=null;
		LocatorType locator =propObject.getLocatorType(proertiesValue);
		
		switch(controlType)
		{
			case "WebElement" :
				actualText = driver.findElement(locator.execute(objLocatorValue)).getText().trim();
				break;
			default :
				//ReportBuilder.ReportTestStep(stepName, status, testData);
				Reporter.log("WebElement is not present");
		    	cReport.testStep("WebElement is not present");
		}
		
		cReport.testStep("Getting  Value "+actualText);
		return actualText;
		
	}
	
	public static void clickOnLinkObjects(String linkObj)throws Throwable
	{
		
		driver.findElement(By.linkText(linkObj)).click();
		cReport.testStep("Click on link text  "+linkObj);
	}
	
	public static int getColmunNumberFromTable(ReadObjectProperties propObject,String proertiesValue,String colText) throws Throwable
	{	
		cReport.testStep("getColmunNumberFromTable method started");
		LocatorType locator =propObject.getLocatorType(proertiesValue);
		int  colNum=0;
		WebElement tableEle=driver.findElement(locator.execute(objLocatorValue));
		List<WebElement> tableHeaders= tableEle.findElements(By.tagName("th"));
		for (WebElement th : tableHeaders) {
			colNum++;
			if(th.getText().contains(colText))
			{
				
				break;
			}
			
		}
		
		return colNum;
		
	}
	
	
	
	
	/*public static WebElement getCellFromTable(WebElement table,int rowNum,int colNum) throws Throwable
	{	
		WebElement tCell = null;
	//	int  rowNum=0;
		int coln=getColmunNumberFromTable(table,colText);
		if(coln > 0)
		{
			cReport.testStep("setInactiveThirdActivePayCodeOnPayCodes method started");
			
			WebElement tableEle=table; //driver.findElement(By.id("ctl00_contplhDynamic_gvPaycode_ctl00"));
			List<WebElement> tableRows= tableEle.findElements(By.tagName("tr"));
			
			for (WebElement tr : tableRows) {
				
				List<WebElement> tableCells= tr.findElements(By.tagName("td"));
				
				for (WebElement tc : tableCells) {
					rowNum++;
					if(tableCells.get(coln-1).getText().equals(rowText))
					{
						tCell=tc;
						break;
					}
					
				}
			}
		}
		return tCell;
		
	}*/
	
	
	
	public static int getRowFromTable(ReadObjectProperties propObject,String proertiesValue,String colText,String rowText) throws Throwable
	{	
		
		cReport.testStep("getRowFromTable method started");
		LocatorType locator =propObject.getLocatorType(proertiesValue);
		int  rowNum=0;
		int rowNumTemp=0;
		int coln=getColmunNumberFromTable(propObject,proertiesValue,colText);
		if(coln > 0)
		{
			cReport.testStep("getRowFromTable method started");
			
			WebElement tableEle=driver.findElement(locator.execute(objLocatorValue));
			
			List<WebElement> tbodyElement =  tableEle.findElements(By.tagName("tbody"));
			for (WebElement webElement : tbodyElement) {
				
			
			List<WebElement> tableRows= webElement.findElements(By.tagName("tr"));
			
			rowNumTemp=0;
			for (WebElement tr : tableRows) {
				
				List<WebElement> tableCells= tr.findElements(By.tagName("td"));
				
				//for (WebElement tc : tableCells) {
				rowNumTemp++;
					System.out.println(tableCells.get(coln-1).getText());
					if(rowText.equals(tableCells.get(coln-1).getText()))
					{
						rowNum=rowNumTemp;
						break;
					}
					
				//}
			}
				if (rowNum>0)
				{break;}
			}
		}
		else 
		{
			//Fail step required column with name not found'
		}
		
		return rowNum;
		
	}
	
	/*public static void setValuesOnWebTable(String controlType,ReadObjectProperties propObject,String proertiesValue,String sText,int rowNum,int colNum)throws Throwable
	{
		LocatorType locator =propObject.getLocatorType(proertiesValue);
		WebElement tableEle=driver.findElement(locator.execute(objLocatorValue)); 
		
		List<WebElement> tbodyElement =  tableEle.findElements(By.tagName("tbody"));
		for (WebElement webElement : tbodyElement) 
		{
			List<WebElement> tableRows= webElement.findElements(By.tagName("tr"));		
			WebElement trElement = tableRows.get(rowNum-1);

			List<WebElement> tableCells= trElement.findElements(By.tagName("td"));	
			if(tableCells.size()>=(colNum-1))
			{	
				WebElement tableCell= tableCells.get(colNum-1);
			
				tableCell.sendKeys("10.52");
			}
			
			
		}
	}*/
	
	public static void setValuesOnWebTable(String controlType,ReadObjectProperties propObject,String proertiesValue,String rowText,String colText, String sText,int rowNum,int colNum)throws Throwable
	{
		LocatorType locator =propObject.getLocatorType(proertiesValue);
		WebElement tableEle=driver.findElement(locator.execute(objLocatorValue)); 
		int coln=getColmunNumberFromTable(propObject,proertiesValue,colText);
			
		String keyAvailMUMTable=SetProperpertiesFile.resourceAdmin.getPropertyValue("PayCodeTable");
		String xpathAvailMUMTable=new ReadObjectProperties().getPropertyValueFromTypeIdentifier(keyAvailMUMTable);
		String xpathTableIDWithBody=xpathAvailMUMTable+"/tbody";
		WebElement tableMUM=driver.findElement(By.xpath(xpathAvailMUMTable));
		
		List<WebElement> rows_table = tableMUM.findElements(By.tagName("tr"));
		for(int i=0; i < rows_table.size() ; i++)
		{
			 List<WebElement> columns = rows_table.get(i).findElements(By.tagName("td"));
			 int columnInRow=columns.size();
			 for(int j=0;j<columnInRow;j++)
			 {
				 String MUMName = columns.get(j).getText();
					if((MUMName.trim()).equalsIgnoreCase(rowText))
					{
												
						columns.get(coln-1).findElement(By.tagName("input")).sendKeys(Keys.chord(Keys.CONTROL, "a"), sText);
						handleLoadingImage();
						
					}
			 }
		}
	}
	
	

}
