package com.mycare.actions.utils.scjp.chapter6;

public class StringTest1 {
	public static void main(String[] args) {
		String s1 = "bovas";		
		String s2 = s1;
		String s3 = s1.concat(s2);
		System.out.println(s3);
		
		StringBuffer sbuf = new StringBuffer("krish");		
		sbuf.insert(0,"bovas ");
		System.out.println(sbuf.toString());
	}
}
