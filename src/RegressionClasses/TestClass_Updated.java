package RegressionClasses;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.zc.webdriver.commonLiberaries.Listener;
import com.zc.webdriver.commonLiberaries.SetProperpertiesFile;
import com.zc.webdriver.commonLiberaries.WebDriverHelper;

import org.testng.xml.XmlClass;

public class TestClass_Updated extends WebDriverHelper
{
	
	//static WritableWorkbook wwbCopy;
	
	private static XSSFSheet sheet;
	 
	private static XSSFWorkbook w;

	private static XSSFCell Cell;

	private static XSSFRow Row;
	static int j;
	static String fileLocation="TestData\\XmlSuite.xlsx";
	static FileOutputStream fileOut;
	static FileInputStream fs;
	static String sheetName;
	
	public static void main(String args[]) throws Exception
	{
		//String fileLocation="XmlSuite.xls";
		SetProperpertiesFile.setAllProperpertiesFile();
		sheetName = SetProperpertiesFile.appConfig.getPropertyValue("SheetName");
		 fs  = new FileInputStream(fileLocation);
		
		
		
		w = new XSSFWorkbook(fs);
		//wwbCopy = Workbook.createWorkbook(inputWorkbook, w);
		// Get the first sheet
		 sheet = w.getSheet(sheetName);
		for ( j = 1; j < sheet.getPhysicalNumberOfRows(); j++)
		{
			Cell = sheet.getRow(j).getCell(1);
			 
  			String runMode = Cell.getStringCellValue();
			
			if(runMode.equalsIgnoreCase("Yes"))
			{
				Listener.row=j;
				// Read suite name from excel sheet
				
				Cell = sheet.getRow(j).getCell(2);
				String suiteName = Cell.getStringCellValue();
	  			
				
				Cell = sheet.getRow(j).getCell(3);
				String testName = Cell.getStringCellValue();
				
				
				Cell = sheet.getRow(j).getCell(4);
				String packageName = Cell.getStringCellValue();
								
				
				Cell = sheet.getRow(j).getCell(5);
				String className = Cell.getStringCellValue();
				
				XmlSuite suite = new XmlSuite(); 
				suite.setName(suiteName);
				XmlTest test = new XmlTest(suite);
				test.setName(testName);
				
				ArrayList<XmlClass> classes = new ArrayList<XmlClass>();
				String classNameWithPackage=packageName+"."+className;
				classes.add(new XmlClass(classNameWithPackage));
				test.setXmlClasses(classes) ;
				
				
				List<Class> listeners = new ArrayList<Class>();
				//String listenerClassWithPackage="com.zc.webdriver.commonLiberaries.Listener";
				//listeners.add(new Class(listenerClassWithPackage));
				//listeners.add(new class(listenerClassWithPackage));
				listeners.add(Listener.class);
				
				ArrayList<XmlSuite> suites = new ArrayList<XmlSuite>();
				suites.add(suite);
				TestNG tng = new TestNG();
				
			   tng.setListenerClasses(listeners);
			   tng.setXmlSuites(suites);
				
				tng.run(); 
			}//End of if block
		}//end of for loop
		
		
	}// end of main method
	public static void setValueIntoCell(int iRowNumber,String result) throws Exception
    {
       Row = sheet.getRow(iRowNumber);
      Cell=  Row.getCell(6,Row.RETURN_BLANK_AS_NULL);
                

		if (Cell == null) {

			Cell = Row.createCell(6);

			Cell.setCellValue(result);

			} else {

				Cell.setCellValue(result);

			}
		FileOutputStream fileOut = new FileOutputStream(fileLocation);

		w.write(fileOut);
		
    }
    
    public static void closeFile()
    {
        try {
            // Closing the writable work book
        	fileOut.flush();
			fileOut.close();

            // Closing the original work book
            w.close();
        } catch (Exception e)

        {
            e.printStackTrace();
        }
    }

}
