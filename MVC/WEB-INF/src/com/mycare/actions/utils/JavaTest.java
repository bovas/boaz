package com.mycare.actions.utils;

import java.io.Serializable;
import java.util.RandomAccess;

public class JavaTest implements Serializable,RandomAccess,Cloneable{
	private int a;
	public static void main(String... args) {
		Integer i = null;
		//int in = i;
		//System.out.println(in);
		JavaTest jt = new JavaTest();
		JavaTest.as.test();
		int a=1;
		double d = 10;
		System.out.println(true&false);
		Throwable t;
		Error e1;
		Exception e;
		switch(a){
		default:
			System.out.println("default");
		case 2:
		System.out.println("case 2");
		case 3:
		System.out.println("case 3");
		break;
		}
		
		long b=10L;
		if(a==b)
			System.out.println("error");
		try {
			jt.clone();
		} catch (CloneNotSupportedException e11) {
			// TODO Auto-generated catch block
			e11.printStackTrace();
		}
	}
	static class as{
		static void test(){
		JavaTest test = new JavaTest();
		System.out.println(test.a);
		}
	}
}
