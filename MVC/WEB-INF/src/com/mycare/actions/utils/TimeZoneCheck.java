package com.mycare.actions.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneCheck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy hh:mm a");
				
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));	//getting the time based on the local time zone
		
		String disclosure_date = sdf.format(new Date());
		System.out.println(disclosure_date);
		
	}

}
