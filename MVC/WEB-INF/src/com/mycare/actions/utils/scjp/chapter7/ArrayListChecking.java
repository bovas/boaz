package com.mycare.actions.utils.scjp.chapter7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ArrayListChecking {
	public ArrayListChecking() {		
		MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
		System.out.println(MAX_ARRAY_SIZE);
	}
	private int MAX_ARRAY_SIZE;
	public static void main(String[] args) {
		List<Integer> list10 = new ArrayList<>();
		List<Integer> list = new ArrayList<>(15);
		List<Integer> list9 = new ArrayList<>(list);
		list.add(10);
		list.add(20);
		list.add(10);
		list.add(1, 15);					
		int b = list.get(0);
		List<Integer> list2 = (List<Integer>) ((ArrayList<Integer>)list).clone();
		System.out.println("Contains:: "+list2.contains(15));
		System.out.println("Contains:: "+list2.containsAll(list));
		System.out.println("Is Empty::"+list2.isEmpty());
		System.out.println("Index Of::"+list2.indexOf(10));
		System.out.println("Last index of::"+list2.lastIndexOf(10));
		System.out.println("Size::"+list2.size());
		System.out.println("SubList::"+list2.subList(1, 2));
		
		Object[] list3 = list2.toArray();
		for(Object o:list3)
			System.out.println("To array value::"+(int)o);
		
		Object[] list4 = list2.toArray(list3);
		for(Object o1:list4)
			System.out.println("To array value::"+(int)o1);
		
		List<Integer> list5 = new ArrayList();
		list5.add(40);
		list5.add(60);
		
		List<String> list6 = new ArrayList();
		list6.add("new");
		list6.add("old");
		
		list5.addAll(list);
		list5.remove((Object)20);
		System.out.println("list---"+list);
		System.out.println("list5---"+list5);
		//list5.addAll(2,list6);
		
		System.out.println(list5);
		
		Iterator it = list5.iterator();
		while(it.hasNext())
			System.out.println("Iterator value::"+it.next());
		
		list5.remove(new Integer(10));
		System.out.println(list5);
		list5.set(2, 56);
		//list5.removeAll(list);
		
		System.out.println(list2);
		System.out.println(list5);
		System.out.println(list5.retainAll(list2));
		System.out.println(list5);
		
		((ArrayList<Integer>)list5).ensureCapacity(20);
		((ArrayList<Integer>)list5).trimToSize();
		
		ListIterator lit = list5.listIterator(0);
		while(lit.hasNext()){
			System.out.println("ListIt Prevouns::"+lit.next());
		}
		System.out.println(list5);		
	}
	public void checkSystemArrayCopy(){
		int[] arr =new int[6];
		arr[0] =1;
		arr[1] =2;
		arr[2] = 3;
		System.arraycopy(arr, 0, arr, 1, arr.length-1);
		for(int a:arr)
			System.out.println(a);
	}
}
