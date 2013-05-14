package com.mycare.actions.utils.scjp.chapter2;

public class StaticTest extends Alpha{
	private StaticTest(){
		s+="subsub ";
	}
	public static void main(String[] args) {
		new StaticTest();
		System.out.println(s);
	}	
}
class Alpha {
	static String s = " ";
	protected Alpha() { s += "alpha "; }
}



