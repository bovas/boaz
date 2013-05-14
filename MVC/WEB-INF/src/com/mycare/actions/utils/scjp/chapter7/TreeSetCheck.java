package com.mycare.actions.utils.scjp.chapter7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import com.mycare.actions.utils.scjp.chapter2.Dog;


public class TreeSetCheck {
	public static void main(String[] args) {
		
		TreeSet treeSet = new TreeSet();
		treeSet.add(20);
		
		ArrayList al = new ArrayList();
		TreeSet treeSet1 = new TreeSet<>(al);
		TreeSet treeSet2 = new TreeSet<>(new ComparatorClassNew());		
		TreeSet treeSet3 = new TreeSet<>(treeSet);
		treeSet3.add(new ComparatorClassNew());
		//treeSet.add(null);
	}
}
class ComparatorClassNew implements Comparator<String>{
	
	public int compare(String o1, String o2) {		
		System.out.println(o1.compareToIgnoreCase(o2));
		return o1.compareToIgnoreCase(o2);
	}
	
}
class NonComparatorClassNew implements Comparator<String>{
	
	public int compare(String o1, String o2) {		
		System.out.println(o1.compareToIgnoreCase(o2));
		return o1.compareToIgnoreCase(o2);
	}
	
}