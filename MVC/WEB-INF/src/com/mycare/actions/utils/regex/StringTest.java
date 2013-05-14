package com.mycare.actions.utils.regex;

import java.util.Arrays;
import java.util.Comparator;

import edu.emory.mathcs.backport.java.util.Collections;

public class StringTest implements Comparator,Comparable{
	public static void main(String[] args) {
		String str="check all";
		System.out.println(str.replaceAll(Character.toString(','), ""));
		System.out.println(str.compareTo("asdsaasdasd check all "));
		char[] a = "bovas".toCharArray();
		String string = Character.toString(a[0]);
		System.out.println(string);
		StringTest test = new StringTest();		
	}
	
	public int compare(Object o1, Object o2) {		
		return this.compareTo(o2);
	}

	
	public int compareTo(Object o) {		
		return 0;
	}
}
