package com.mycare.actions.utils.scjp.chapter7;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListCheck {
	public static void main(String[] args) {
		LinkedList newList = new LinkedList();		
		newList.add(10);
		newList.add(50);
		newList.add(1, 30);
		newList.addFirst(5);
		newList.addLast(100);		
		LinkedList newList1 = new LinkedList<>(newList);
		newList1.addAll(newList);
		newList1.addAll(2,newList);		
				
		newList1.offer(110);
		newList1.offerFirst(2);
		newList1.offerLast(120);
		newList1.offerLast(null);
		newList1.offerLast(null);
		
		System.out.println("Poll::"+newList1.poll());
		System.out.println("Poll first::"+newList1.pollFirst());
		System.out.println("Poll last::"+newList1.pollLast());		
		
		System.out.println("Peek::"+newList1.peek());
		System.out.println("Peek first::"+newList1.peekFirst());
		System.out.println("Peek last::"+newList1.peekLast());
		
		System.out.println("Peek last::"+newList1.removeFirstOccurrence(10));
		
		System.out.println("First element removable::"+newList1.remove());
		System.out.println("element removable at position 2::"+newList1.remove(2));
		System.out.println("element removable of object::"+newList1.remove(new Integer(110)));
		System.out.println("First element removable::"+newList1.removeFirst());
		System.out.println("Last element removable::"+newList1.removeLast());
		
		System.out.println("Element::"+newList1.element());
		System.out.println("Full list::"+newList1);
		System.out.println("First element ::"+newList.getFirst());
		System.out.println("Last element ::"+newList.getLast());		
		//Object[] obj = null;newList1.toArray(obj);
		//newList1.addAll(c)
		ListIterator itr = newList.listIterator();
		while(itr.hasNext()){
			System.out.println("Iterator value::"+itr.next());
		}		
		Iterator descitr = newList1.descendingIterator();
		while(descitr.hasNext()){
			System.out.println("desc Iterator value::"+descitr.next());
		}
	}
}
