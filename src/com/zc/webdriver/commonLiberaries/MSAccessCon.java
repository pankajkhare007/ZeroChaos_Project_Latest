package com.zc.webdriver.commonLiberaries;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class MSAccessCon extends WebDriverHelper
{
	//public static  ResultSet rs=null;
	
	public static String dBLocation;
	public static Connection conn=null;
	//public static String dBLocation="E:/Testing/WebDriver_ZCProject/ExcelSheet/Mydb.mdb";
    public static ResultSet rs=null;
	public static int count=0;

	public static ResultSet testCon(String sQuery) throws Exception
	{
		SetProperpertiesFile.setAllProperpertiesFile();
		dBLocation=SetProperpertiesFile.appConfig.getPropertyValue("DB_LOCATION");
	
	//	 String dBLocation="C:/Users/pkhare/Google Drive/ZCPreRequisite_Workspace/ZeroChaos_PreRequisiteProject/TestData/ZeroChaos.accdb";
			try
			{
				
				
				conn = DriverManager.getConnection("jdbc:ucanaccess://"+dBLocation);
				
				Statement stmt=conn.createStatement();
				
				if(flag)
					rs=stmt.executeQuery(sQuery);
				rs.next();
				if (conn != null)
				{
				       
				   	   conn.close();
			  
					          
					}
					
			}//try
			catch (Exception e)
			{
				System.err.println("Got an exception! ");
				System.err.println(e.getMessage());
				e.printStackTrace();

			}//catch
			return rs;
	 }//method
	

	 
	/*public static void main(String[] args) throws Exception {
		String sQuery="Select count(*) From PreRequisiteForProject Where sTestCaseName = 'DivisionRequisite_WorkOrder_Project'";
		ResultSet r =testCon(sQuery);
		int dupRecords=rs.getInt(1);
		rs=null;
		
		sQuery="select * From PreRequisiteForProject where sTestCaseName = 'DivisionRequisite_WorkOrder_Project'";
		
		for(int i=0; i<dupRecords;i++ )
		{
			r =testCon(sQuery);
			flag=false;
			//System.out.println(rs.getString("sDivisionName"));
			System.out.println(rs.getString("sDivisionName"));
		}
		
		//Select count(*) From ProjectAmendment Where sTestCaseId='" & sTestCaseID

	}*/

}//class
