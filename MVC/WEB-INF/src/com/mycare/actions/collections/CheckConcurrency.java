package com.mycare.actions.collections;

import java.util.Hashtable;
import java.util.Iterator;

public class CheckConcurrency{	
	public static void main(String[] args) {
		ChangeHashtableValue value = new ChangeHashtableValue();
		for(int i=0;i<10;i++)
			new Thread(value).start();
		Hashtable hs = value.hs;		
		Iterator it = (Iterator) hs.entrySet();
		while(it.hasNext()){
			it.next();
		}		
	}
	static{
		String s="hello";
		System.out.println(s.hashCode());
		s="newone";
		s.substring(0, 1);
		System.out.println(s.hashCode());
		Object o = new Object();
		System.out.println(o.hashCode());
		o = new Object();
		System.out.println(o.hashCode());
		
		try {
			//throw new Exception("hello");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
