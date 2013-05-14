package com.mycare.actions.utils.scjp.chapter3;

public class WrapperClassChecking {
	public static void main(String[] args) {
		Float f = new Float(10.00);	//double cons
		float flt = 10.00F;	//Float assigning
		Integer.valueOf("00000",2);
		Integer.parseInt("");
		Integer newint = Integer.parseInt("10");
		newint.intValue();
		Boolean bool =new Boolean("true");
		Boolean.parseBoolean("true");
		bool.toString();
		Boolean.toString(bool);
	}
}
