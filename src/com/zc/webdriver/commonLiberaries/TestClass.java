package com.zc.webdriver.commonLiberaries;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlClass;

public class TestClass
{
	static Workbook w;
	static Sheet sheet;
	static WritableWorkbook wwbCopy;
	static int j;
	
	public static void main(String args[]) throws BiffException, IOException
	{
		//String fileLocation="XmlSuite.xls";
		String fileLocation="TestData\\XmlSuite.xls";
		FileInputStream fs  = new FileInputStream(fileLocation);
		Listener listenerObject = new Listener();
		File inputWorkbook = new File(fileLocation);
		
		w = Workbook.getWorkbook(inputWorkbook);
		wwbCopy = Workbook.createWorkbook(inputWorkbook, w);
		// Get the first sheet
		 sheet = w.getSheet(0);
		for ( j = 1; j < sheet.getRows(); j++)
		{
			Cell runMode = sheet.getCell(1, j);
			if(runMode.getContents().equalsIgnoreCase("Yes"))
			{
				Listener.row=j;
				// Read suite name from excel sheet
				Cell suiteName = sheet.getCell(2, j);
				Cell testName = sheet.getCell(3, j);
				Cell packageName = sheet.getCell(4, j);
				Cell className = sheet.getCell(5, j);
				XmlSuite suite = new XmlSuite(); 
				suite.setName(suiteName.getContents());
				XmlTest test = new XmlTest(suite);
				test.setName(testName.getContents());
				
				ArrayList<XmlClass> classes = new ArrayList<XmlClass>();
				String classNameWithPackage=packageName.getContents()+"."+className.getContents();
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
	public static void setValueIntoCell(int iRowNumber,String strData) throws WriteException
    {
        WritableSheet wshTemp = wwbCopy.getSheet(0);
        Label labTemp = new Label(6, iRowNumber, strData);
                
        try {
            wshTemp.addCell(labTemp);
             } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
    }
    
    public static void closeFile()
    {
        try {
            // Closing the writable work book
            wwbCopy.write();
            wwbCopy.close();

            // Closing the original work book
            w.close();
        } catch (Exception e)

        {
            e.printStackTrace();
        }
    }

}
