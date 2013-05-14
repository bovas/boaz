package com.mycare.actions.test;

import java.util.*;
public class SimpleSetDemo {
	public static void main(String[] args) {
		Set s = new HashSet();
		s.add("odin"); s.add("dva"); s.add("tri");
		Set t = new TreeSet();
		t.add("dva"); t.add("tri"); t.add("shest");
		s.retainAll(t);
		for (Iterator it = s.iterator(); it.hasNext();)
			System.out.println(it.next());
	}
}