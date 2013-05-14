package com.mycare.actions.utils.scjp.chapter7;


import java.util.Arrays;
import java.util.List;

public class ArraysCheck {
	public static void main(String[] args) {
		Integer[] a = {10,20,5,15,25};
		
		List list = Arrays.asList(a);
		
		Arrays.sort(a,1,4);
		System.out.println(Arrays.asList(a));
		Arrays.sort(a);
		System.out.println(Arrays.asList(a));
		Arrays.sort(a,new newComparator());
		System.out.println(Arrays.asList(a));
		
		System.out.println(Arrays.binarySearch(a, 20));
		System.out.println(Arrays.binarySearch(a,1,3,12));		
		Object[] a1 = list.toArray();		
		System.out.println(list);
		
		Integer[] a3 = {10,20,5,15,25};
		//Arrays.sort(a3);
		Integer[] a2 = {10,20,5,15,25};
		System.out.println(Arrays.equals(a3, a2));
		System.out.println(Arrays.toString(a2));
	}
}
