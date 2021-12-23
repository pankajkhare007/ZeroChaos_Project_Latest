package com.zc.webdriver.commonLiberaries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
 
/**
 * This program demonstrates how to write characters to a text file.
 * @author www.codejava.net
 *
 */
public class SetGetSessionURL {
	
	 public static String getSessionText() {
    	 String txtFile=null;
        try {
            FileReader reader = new FileReader("TestData/SessionID.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
                      
 
            while ((line = bufferedReader.readLine()) != null) {
               // System.out.println(line);
                txtFile=line;
                
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(txtFile);
        return txtFile;
    }
    
    public static String getURLText() {
   	 String txtFile=null;
       try {
           FileReader reader = new FileReader("TestData/URLText.txt");
           BufferedReader bufferedReader = new BufferedReader(reader);

           String line;
                     

           while ((line = bufferedReader.readLine()) != null) {
              // System.out.println(line);
               txtFile=line;
               
           }
           reader.close();

       } catch (IOException e) {
           e.printStackTrace();
       }
       System.out.println(txtFile);
       return txtFile;
   }
 
    public static void setSessionIDText(String str) {
        try {
        	clearTheSessionID();
            FileWriter writer = new FileWriter("TestData/SessionID.txt", true);
            writer.write(str);
          
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
    public static void setURLText(String str) {
        try {
        	clearTheURLText();
            FileWriter writer = new FileWriter("TestData/URLText.txt", true);
            writer.write(str);
          
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
    
    public static void clearTheSessionID() throws IOException {
        FileWriter fwOb = new FileWriter("TestData/SessionID.txt", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
    public static void clearTheURLText() throws IOException {
        FileWriter fwOb = new FileWriter("TestData/URLText.txt", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
 
}