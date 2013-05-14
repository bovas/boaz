package com.mycare.actions.utils.scjp.chapter7;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class HeadTailMapTest {
	public static void main(String[] args){
		TreeSet setTest = new TreeSet();
		setTest.add(1000);
		setTest.add(1200);
		setTest.add(1100);
		setTest.add(900);
		setTest.add(2400);		
		/*System.out.println(setTest.subSet(900, 1000));
		System.out.println(setTest.headSet(900, false));
		System.out.println(setTest.tailSet(2400, true));*/
		Set set = setTest.subSet(900,true,2000,true);
		System.out.println(set);
		System.out.println(setTest.pollFirst());
		System.out.println(set);
	}
}
