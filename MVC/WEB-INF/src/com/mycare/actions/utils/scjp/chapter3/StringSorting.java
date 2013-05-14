package com.mycare.actions.utils.scjp.chapter3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;

import edu.emory.mathcs.backport.java.util.Collections;

public class StringSorting implements Comparator<Object>{
	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<String>();
		al.add("zingza");
		al.add("mangja");
		al.add("bangja");
		al.add("Yamini");
		al.add("IvanSuan");
		al.add("apple");
		Collections.sort(al,new StringSorting());		
		Hashtable hs = new Hashtable();
		System.out.println(al);
	}	
	public int compare(Object o,Object o1){
		String firstStr = (String)o;
		String secndStr = (String)o1;
		return firstStr.compareToIgnoreCase(secndStr);
	}
}
