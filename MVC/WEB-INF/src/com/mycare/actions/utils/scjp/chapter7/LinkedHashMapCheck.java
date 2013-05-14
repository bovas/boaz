package com.mycare.actions.utils.scjp.chapter7;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class LinkedHashMapCheck {
	public static void main(String[] args) {
		LinkedHashMap linkedMap = new LinkedHashMap<>();
		LinkedHashMap linkedMap1 = new LinkedHashMap<>(10);
		LinkedHashMap linkedMap2 = new LinkedHashMap<>(10,0.75f);
				
		System.out.println(linkedMap.put("name", "value"));
		System.out.println(linkedMap.put("name1", "value1"));
		System.out.println(linkedMap.put("name2", "bovas"));
		System.out.println(linkedMap.put("name3", "krish"));
		System.out.println(linkedMap.put(null, null));
		System.out.println(linkedMap.remove(""));
		LinkedHashMap linkedMap3 = new LinkedHashMap<>(linkedMap);
		
		Set set1 = linkedMap.keySet();
		Iterator itr1 = set1.iterator();
		while(itr1.hasNext()){			
			System.out.println("Keys::"+itr1.next());
		}
		
		Collection coll = linkedMap.values();
		Iterator itr2 = coll.iterator();
		while(itr2.hasNext()){			
			System.out.println("Values::"+itr2.next());
		}
		
		Set set = linkedMap.entrySet();
		Iterator itr = set.iterator();
		while(itr.hasNext()){
			Entry entry = (Entry) itr.next();
			System.out.println("key value pairs:::"+entry.getKey()+"---"+entry.getValue());
		}
		
		LinkedHashMap linkedMap4 = new LinkedHashMap<>(20, 0.75f, true);
		System.out.println(linkedMap4.put("name", "value"));
		System.out.println(linkedMap4.put("name1", "value1"));
		System.out.println(linkedMap4.put("name2", "bovas"));
		System.out.println(linkedMap4.put("name3", "krish"));
		System.out.println("Get value::"+linkedMap4.get("name1"));
		Set set4 = linkedMap4.entrySet();
		Iterator itr4 = set4.iterator();
		while(itr4.hasNext()){
			Entry entry = (Entry) itr4.next();
			System.out.println("key value pairs:::"+entry.getKey()+"---"+entry.getValue());
		}
		
	}
}
