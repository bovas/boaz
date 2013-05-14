package com.mycare.actions.utils.scjp.chapter3;

public class CheckConditionalOps {
	public static void main(String[] args) {
		Integer i1 = 128;
		Integer i2 = 128;		
		System.out.println(i1 == i2);
		System.out.println(i1 != i2);
		i1 = 127;
		i2 = 127;
		System.out.println(i1 == i2);
		System.out.println(i1 != i2);
		System.out.println();
		
		Boolean b1=true;
		Boolean b2 = true;
		System.out.println(b1==b2);
		System.out.println();
		
		byte by =127;
		byte by1 =127;
		System.out.println(by==by1);
		
		String a=new String("hello");
		String b=new String("hello");
		System.out.println(a.equals(b));
	}
}
