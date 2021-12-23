package com.zc.webdriver.commonLiberaries;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/*When using Windows Authentication Integrated Security

Download the sqljdbc_6.0.8112.100_enu.exe from Microsoft Site
Install the exe (read the instructions in the zip path)
copy sqljdbc_4.0/enu/auth/x64/sqljdbc_auth.dll to

Java/jre7/bin and to

Java/jre7/lib

*sqljdbc42.jar  configure with project
*/
 
public class ConnectionWithSQLServer {
	static ResultSet rs=null;
 
	public static ResultSet connectionSqlServer(String sqlQuery) throws SQLException, ClassNotFoundException {
		String sqlServerName="qavenussql01"; 
		String environmentName=SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT");
		Statement sta=null; 
		if(environmentName.equals("qamars"))
			 sqlServerName="qamarssql01";
		else if(environmentName.equals("qamercury"))
			 sqlServerName="qamercurysql01";
		else if(environmentName.equals("qaperformance"))
			 sqlServerName="QAPERFSQL01";
		else if(environmentName.equals("qasaturn"))
			 sqlServerName="QASATURN01";
		else if(environmentName.equals("qasaturn"))
			 sqlServerName="QASATURN01";
		
		if(environmentName.equals("qaio"))
		{
			
		}
		else
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			String connectionString="jdbc:sqlserver://"+sqlServerName+".zcdev.local;database=One;integratedSecurity=true";
			Connection conn = DriverManager.getConnection(connectionString);
			System.out.println("test");
			sta = conn.createStatement();
		}
		
	//	String Sql = "select * from zcclient where ClientID = 344";
		rs = sta.executeQuery(sqlQuery);
		if(rs!=null)
		{
			rs.next();
		}
		return rs;
		
	}
	public static void connectionSqlServerExecuteUpdate(String sqlQuery) throws SQLException, ClassNotFoundException {
		String sqlServerName="qavenussql01"; 
		String environmentName=SetProperpertiesFile.appConfig.getPropertyValue("ENVIRONMENT");
		Statement sta=null; 
		if(environmentName.equals("qamars"))
			 sqlServerName="qamarssql01";
		else if(environmentName.equals("qamercury"))
			 sqlServerName="qamercurysql01";
		else if(environmentName.equals("qaperformance"))
			 sqlServerName="QAPERFSQL01";
		else if(environmentName.equals("qajupiter"))
			 sqlServerName="qajupiterSQL01";
		else if(environmentName.equals("qapluto"))
			 sqlServerName="qaplutoSQL01";
		else if(environmentName.equals("qaneptune"))
			 sqlServerName="qaneptuneSQL01";
		else
			sqlServerName="qavenusSQL01";
		
		if(environmentName.equals("qaio"))
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			String connectionString="jdbc:sqlserver://qa-sqlserver-eastus.database.windows.net:1433;database=qa-sqldb-io-eastus;User=thedoctor;password=!wd$3H52NTg4EAUR;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
			Connection conn = DriverManager.getConnection(connectionString);
			System.out.println("test");
			 sta = conn.createStatement();
		}
		else if(environmentName.equals("stagingusaz"))
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			String connectionString="jdbc:sqlserver://qa-sqlserver-eastus.database.windows.net:1433;database=staging-sqlserver-eastus;User=thedoctor;password=!wd$3H52NTg4EAUR;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
			Connection conn = DriverManager.getConnection(connectionString);
			System.out.println("test");
			 sta = conn.createStatement();
		}
		else
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			String connectionString="jdbc:sqlserver://"+sqlServerName+".zcdev.local;database=vms;integratedSecurity=true";
			Connection conn = DriverManager.getConnection(connectionString);
			System.out.println("test");
			 sta = conn.createStatement();
		}
		
	//	String Sql = "select * from zcclient where ClientID = 344";
		 sta.executeUpdate(sqlQuery);
		
		
	}
	
	
}