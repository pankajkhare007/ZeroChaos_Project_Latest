package com.zc.webdriver.commonLiberaries;

import java.io.File;




public class SetProperpertiesFile
{
	public  static ReadObjectProperties appConfig=new ReadObjectProperties();
	public  static ReadObjectProperties login=new ReadObjectProperties();
	public  static ReadObjectProperties division=new ReadObjectProperties();
	public  static ReadObjectProperties common=new ReadObjectProperties();
	public  static ReadObjectProperties contract=new ReadObjectProperties();
	public  static ReadObjectProperties project=new ReadObjectProperties();
	
	public  static ReadObjectProperties resourceAdmin=new ReadObjectProperties();
	
	//	Constants.setFile(new File("conf/Constants.properties"));
		//Constants.readFile();
		
	public  static void setAllProperpertiesFile()throws Exception
	{
		
		appConfig.setFile(new File("ConfigData/ApplicationConfig.properties"));
		appConfig.readFile();
		
		login.setFile(new File("ObjectRepository/Login.properties"));
		login.readFile();
		
		division.setFile(new File("ObjectRepository/Division.properties"));
		division.readFile();
		
		common.setFile(new File("ObjectRepository/Common.properties"));
		common.readFile();
		
		contract.setFile(new File("ObjectRepository/Contract.properties"));
		contract.readFile();
		
		resourceAdmin.setFile(new File("ObjectRepository/ResourceAdmin.properties"));
		resourceAdmin.readFile();
		
		project.setFile(new File("ObjectRepository/Projects.properties"));
		project.readFile();
		
	}
}
