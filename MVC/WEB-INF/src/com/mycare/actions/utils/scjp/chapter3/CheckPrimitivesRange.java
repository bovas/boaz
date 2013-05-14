package com.mycare.actions.utils.scjp.chapter3;

import java.util.Date;

public class CheckPrimitivesRange {
	Date instDate;
	public static void main(String[] args) {
		byte b = 1;
		byte c = 1;
		byte a =(byte)(b+c);
		byte d = (byte)(b*c);
		short e = 2;
		byte f= (byte)(e/b); 
		
		
		float flt = (float) 10.0110;
		int newint = (int)flt;
		System.out.println(flt);
		double doubl = 1009000001L;
		System.out.println(doubl);
		
		long newNum = 128;
		byte bite = (byte)newNum;
		System.out.println(bite);
		boolean  bool = false;
		if(false){
			bool  = false;
		}
		System.out.println(bool);
		
		Date date = null;
		if(date!=null)
			System.out.println(date);		
		CheckPrimitivesRange check = new CheckPrimitivesRange();
		check.setDate();
		check.instDate.getDate();
	}
	public void setDate(){
		instDate = new Date();
	}
}
