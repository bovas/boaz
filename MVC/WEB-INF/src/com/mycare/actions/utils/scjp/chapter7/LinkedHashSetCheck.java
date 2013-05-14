package com.mycare.actions.utils.scjp.chapter7;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class LinkedHashSetCheck {

	public static void main(String[] args) {
		LinkedHashSet linkedSet = new LinkedHashSet();
		LinkedHashSet linkedSet1 = new LinkedHashSet<>(16);
		LinkedHashSet linkedSet2 = new LinkedHashSet<>(16,0.75f);
		
		LinkedHashSet linkedSet3 = new LinkedHashSet<>(linkedSet);
		
		linkedSet.add(20);
		linkedSet.add(22);
		linkedSet.add(10);
		
		Iterator itr = linkedSet.iterator();
		while(itr.hasNext())
			System.out.println(itr.next());
	}
}
