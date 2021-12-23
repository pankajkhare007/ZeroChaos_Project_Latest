package com.zc.webdriver.commonLiberaries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.IInvokedMethod;

import org.testng.IInvokedMethodListener;

import org.testng.ISuite;

import org.testng.ISuiteListener;

import org.testng.ITestContext;

import org.testng.ITestListener;

import org.testng.ITestNGMethod;

import org.testng.ITestResult;

import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import RegressionClasses.TestClass_Updated;



public class Listener extends WebDriverHelper implements ITestListener, ISuiteListener, IInvokedMethodListener {

	// This belongs to ISuiteListener and will execute before the Suite start
	
	//WebDriverHelper webDriverHelper=new WebDriverHelper();
	
	public static int row;
	
	@Override

	public void onStart(ISuite arg0)
	{
		
		Reporter.log("About to begin executing Suite " + arg0.getName(), true);
		
		
		//ReportBuilder.ReportInitialization(arg0.getName());

	}

	// This belongs to ISuiteListener and will execute, once the Suite is finished

	@Override

	public void onFinish(ISuite arg0)  {

		Reporter.log("About to end executing Suite " + arg0.getName(), true);
		try {
			
			
		} catch (Exception e) {
		
			e.printStackTrace();
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		finally
		{
			cReport.endTest();
		}
		//driver.quit();

	}

	// This belongs to ITestListener and will execute before starting of Test set/batch 

	public void onStart(ITestContext arg0) {

		Reporter.log("About to begin executing Test " + arg0.getName(), true);
		//cReport.startTest("About to begin executing Test " + arg0.getName());
		//cReport.startTest(arg0.getTestName());
	}

	// This belongs to ITestListener and will execute, once the Test set/batch is finished

	public void onFinish(ITestContext arg0) {

		Reporter.log("Completed executing test " + arg0.getName(), true);
		cReport.testStep("Completed executing test " + arg0.getName());
		//driver.close();
		cReport.endTest();
		

	}

	// This belongs to ITestListener and will execute only when the test is pass

	public void onTestSuccess(ITestResult arg0) {

		// This is calling the printTestResults method
		try {
			
			//Reporter.log("<a href='" +"CustomReports/screen.jpg" + "'>screenshot</a>");
			Calendar currentDate = Calendar.getInstance(); // gets current date instance. 
			 SimpleDateFormat formatter=  new SimpleDateFormat("yyyy.MMM.dd HH.mm.ss");
				String dateNow = formatter.format(currentDate.getTime());
			cReport.verificationStep("Pass", "Test Script Passed", dateNow);
			//TestClass_Updated.setValueIntoCell(row, "Pass");
		
			
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
		finally
		{
			printTestResults(arg0);
			
			driver.close();
		}

	
	}

	// This belongs to ITestListener and will execute only on the event of fail test

	public void onTestFailure(ITestResult arg0) {

		// This is calling the printTestResults method
		try {
			//ReportBuilder.ReportTestStep(arg0.getTestName(), "Fail", "",arg0.getTestName());
			Calendar currentDate = Calendar.getInstance(); // gets current date instance. 
			 SimpleDateFormat formatter=  new SimpleDateFormat("yyyy.MMM.dd HH.mm.ss");
				String dateNow = formatter.format(currentDate.getTime());
			cReport.verificationStep("Fail", "Test Script Failed",dateNow );
			//TestClass_Updated.setValueIntoCell(row, "Fail");
		} catch (Throwable e) {
		
			e.printStackTrace();
		}
		finally
		{
			printTestResults(arg0);
			//TestClass.closeFile();
			driver.close();
		}
	}

	// This belongs to ITestListener and will execute before the main test start (@Test)

	public void onTestStart(ITestResult arg0) {

		System.out.println("The execution of the main test starts now");
		
	}

	// This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped

	public void onTestSkipped(ITestResult arg0) {

		printTestResults(arg0);

	}

	// This is just a piece of shit, ignore this

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	// This is the method which will be executed in case of test pass or fail

	// This will provide the information on the test

	private void printTestResults(ITestResult result) {

		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);

		if (result.getParameters().length != 0) {

			String params = null;

			for (Object parameter : result.getParameters()) {

				params += parameter.toString() + ",";

			}

			Reporter.log("Test Method had the following parameters : " + params, true);

		}

		String status = null;

		switch (result.getStatus()) {

		case ITestResult.SUCCESS:

			status = "Pass";

			break;

		case ITestResult.FAILURE:

			status = "Failed";

			break;

		case ITestResult.SKIP:

			status = "Skipped";

		}

		Reporter.log("Test Status: " + status, true);
		//webDriverHelper.captureScreen();

	}

	// This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {

		String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());

		Reporter.log(textMsg, true);

	}

	// This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {

		String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());

		Reporter.log(textMsg, true);

	}

	// This will return method names to the calling function

	private String returnMethodName(ITestNGMethod method) {

		return method.getRealClass().getSimpleName() + "." + method.getMethodName();

	}

}
