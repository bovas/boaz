package com.mycare.actions.utils.scjp.chapter7;

import java.util.HashSet;
import java.util.Iterator;


public class HashSetCheck {
	public static void main(String[] args) {
		HashSet hashset = new HashSet();
		HashSet hashset1 = new HashSet<>(20);		
		HashSet hashset3 = new HashSet<>(10,1.0f);
		HashSet hashset2 = new HashSet<>(hashset1);		
		
		System.out.println(hashset1.add(10));
		System.out.println(hashset1.add(20));
		System.out.println(hashset1.add(10));
		System.out.println(hashset1.add(30));
		System.out.println(hashset1.add(40));
		System.out.println(hashset1.add(null));
		
		hashset.add(20);
		hashset.add(80);
		System.out.println("containsALl "+hashset1.containsAll(hashset));
		System.out.println("Element removal::"+hashset1.remove(10));
		System.out.println("Element removal::"+hashset1.removeAll(hashset));
		System.out.println("Contains::"+hashset1.contains(10));
		Iterator itr  = hashset1.iterator();
		while(itr.hasNext())
			System.out.println("Iterator value::"+itr.next());
		System.out.println(hashset1);
	}
}
