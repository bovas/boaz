package com.mycare.actions.utils.scjp.chapter3;

import java.io.File;
import java.util.Date;

public class StaticBlockCheck extends SuperOneClass{	
	java.util.Collection<?> ac;
	java.util.Collections c;
	java.util.Hashtable hs;
	java.util.HashMap hm;
	Integer in;
	String str;
	int intg=10;
	static{
		System.out.println("first block");
	}
	static{
		System.out.println("Second block");
	}{
		System.out.println("Instant print");
	}
	public static void main(String[] args) {
		StaticBlockCheck check = new StaticBlockCheck();
		System.out.println(0xFFFFFF);		
		System.out.println(check.hashcode("bovas"));
		System.out.println("bovas".hashCode());
		Date date = new Date(new File("/home/glace/object.txt").lastModified());
		System.out.println(date);
	}
	StaticBlockCheck(){
		System.out.println("Sub class cons");
	}
	long hashcode(String str){
		int h=0;
		char[] val=str.toCharArray();
		int off=0;
		int len = val.length;
		for (int i = 0; i < len; i++) {
	        h = 31*h + val[off++];
	    }
		return h;
	}
}
class SuperOneClass{
	SuperOneClass(){
		System.out.println("Super one class cons");
	}
	{
		System.out.println("Inst super class");
	}
	static{
		System.out.println("Super class static");
	}	
}

