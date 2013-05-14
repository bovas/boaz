package com.mycare.actions.utils.scjp.chapter7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

public class PriorityQueueCheck {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PriorityQueue que = new PriorityQueue<>();
		que.add(40);
		que.add(15);
		que.add(20);
		que.add(10);
		que.add(30);
		System.out.println((2-1) >>> 1);
		Iterator itr = que.iterator();
		while(itr.hasNext())
			System.out.println("Iterator value::"+itr.next());
		
		PriorityQueue que12 = new PriorityQueue(10);
		que12.add(1);
		que12.add(2);
		que12.add(3);
		que12.add(4);
		que12.add(5);
		System.out.println(que12);
		PriorityQueue que1 = new PriorityQueue(10, new newComparator());
		que1.add(1);
		que1.add(2);
		que1.add(3);
		que1.add(4);
		que1.add(5);
		que1.add(6);
		que1.add(7);
		que1.add(8);
		que1.add(9);
		for(int i=0;i<10;i++)
		System.out.println(que1.poll());
		PriorityQueue que2 = new PriorityQueue<>(20, new ComparatorClassNew());
		ArrayList list = new ArrayList();
		PriorityQueue que3 = new PriorityQueue<>(list);
		SortedSet set = new TreeSet();
		PriorityQueue que4 = new PriorityQueue<>(set);		
		PriorityQueue que5 = new PriorityQueue<>(que);
	}

}
class newComparator implements Comparator<Integer>{
	

	public int compare(Integer o1, Integer o2) {		
		return o2 - o1;
	}
	
}