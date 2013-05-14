package com.mycare.actions.utils.scjp.chapter7;

public class StringCheck {
	public static void main(String[] args) {
		String s1="bovasnew";		
		String s2=s1;		
		s1+="one";		
		
		String s3=new String("bovas");	
		String s4=new String("bovas");
		System.out.println(s3.equals(s4));
	}
}
