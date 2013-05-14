package com.mycare.actions.utils.scjp.chapter7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Objective6_3 {
	private int instVal = 20;
	public static void main(String[] args) {
		List<Integer> list =new ArrayList<Integer>();
		list.add(12);
		list.add(11);
		list.add(19);				
		Collections.sort(list,new ComparatorEx());
		
		List list1 = new ArrayList();
		list1.add(25);
		list1.add(11);
		Collections.sort(list1);
		System.out.println(list1);
	}
}
class ComparatorEx implements Comparator<Integer>{
	
	public int compare(Integer o1, Integer o2) {
		
		return o1.compareTo(o2);
	}
	
}
class ComparableNew implements Comparable<Integer>{
	
	public int compareTo(Integer o) {
		Integer i = 10;
		return i.compareTo(o);
	}	
}
