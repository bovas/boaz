package com.mycare.actions.utils.scjp.chapter7;

import java.util.NavigableMap;
import java.util.TreeMap;

public class CollectionsTest1 {	
	public static void main(String[] args) {
		TreeMap<Integer,String> map = new TreeMap<>();
		map.put(1, "1");
		map.put(2, "2");
		map.put(3, "3");
		map.put(4, "4");
		map.put(5, "5");
		
		NavigableMap<Integer, String> nMap = map.tailMap(1,true);		
		System.out.println(nMap);
		nMap.pollFirstEntry();
		System.out.println(map);
		System.out.println(nMap);
	}
}
