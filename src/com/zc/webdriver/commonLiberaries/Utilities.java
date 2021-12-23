package com.zc.webdriver.commonLiberaries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utilities {
	
	public static String getDate(int days)
	{
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE,days);
		Date d = cal.getTime();
		System.out.println(d);
		
		String pattern = "dd-MMM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(d);
		return date;
			   
	}
	

}
