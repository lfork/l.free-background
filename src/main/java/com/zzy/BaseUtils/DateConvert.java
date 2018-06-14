package com.zzy.BaseUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConvert {
	
	public static String DateConvertToShort(String date) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
		
		Date date2 = null;
		
		try {
			date2 = dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		
		String dateCN = dateFormat.format(date2);
		
		return dateCN;
	}
	
	public static String DateConvertToLong(Date date){
		
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		
		String dateCN = dateFormat.format(date);
		
		return dateCN;
	}
	
}
