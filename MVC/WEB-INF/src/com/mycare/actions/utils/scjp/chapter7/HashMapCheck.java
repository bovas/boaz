package com.mycare.actions.utils.scjp.chapter7;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapCheck{
	public static void main(String[] args) {
		HashMap hMap = new HashMap();
		HashMap hMap1 = new HashMap(10);
		HashMap hMap2 = new HashMap<>(20, 1.0f);
		HashMap hmap3 = new HashMap<>(hMap);
		
		Object o1 = new Object();
		System.out.println("First null key "+hMap.put(null, "StringValue"));
		System.out.println("Old null value:; "+hMap.put(null, "1"));
		System.out.println("First time insert:: "+hMap.put("1", "1"));
		System.out.println("Old value of this bucket:: "+hMap.put("1", "2"));
		
		hMap.put("2", "3");
		hMap.put("3", "4");
		hMap.put("4", "5");
		hMap.put("5", "6");
		hMap.put("6", "7");
		hMap.put("7", "8");
		Map m = new HashMap<>();
		m.put("", "");		
		hMap.putAll(m);
		System.out.println("toString::"+hMap.toString());
		System.out.println(hMap.get("1"));
		
		System.out.println(hMap.remove("7"));
		System.out.println("Contains key::"+hMap.containsKey("1"));
		System.out.println("Contains value::"+hMap.containsValue("6"));
		System.out.println();
		Collection newMap = hMap.values();
		Iterator<Map.Entry<String,String>> iterator = newMap.iterator();
		while(iterator.hasNext())
			System.out.println("Iterator values "+iterator.next());		
		
		System.out.println();
		
		Set keySet= hMap.keySet();
		Iterator iteratorNew = keySet.iterator();
		while(iteratorNew.hasNext())
			System.out.println("Iterator keys::"+iteratorNew.next());
		
		Set eSet = hMap.entrySet(); 
		Iterator iteratorNewOne = eSet.iterator();
		while(iteratorNewOne.hasNext()){
			Entry etry= (Entry) iteratorNewOne.next();
			System.out.println("Key::"+etry.getKey()+" Value::"+etry.getValue());
		}
		System.out.println(hMap);		
		//checkShiftOpertor();
	}
	static void checkShiftOpertor(){
		int a = 1;
		while(a < 16)
			System.out.println(a <<=1);
		System.out.println(Math.min(a * 0.75f, 1073741824 + 1));
	}	
}

