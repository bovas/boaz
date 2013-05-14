package com.mycare.actions.utils.scjp.chapter7;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class WeakHashMao {
	public static void main(String[] args) {
		//checkWeakHashMap();
		checkConcurrencyHashMap();
	}
	public static void checkWeakHashMap(){
		String name=new String("string");
		String name1=new String("string");
		
		WeakHashMap map = new WeakHashMap();
		HashMap map1 = new HashMap();
		
		map.put(name, new WeakHashMao());
		map1.put(name1, new WeakHashMao());
		
		name=null;
		
		System.gc();
		System.out.println("Weak hashmap::"+map);
		System.out.println("Normal hash map::"+map1);
	}
	public static void checkConcurrencyHashMap(){
		ConcurrentHashMap map  = new ConcurrentHashMap();
		HashMap map1  = new HashMap();

		//map.put(null, "10");
		//map1.put(null, "10");		
		
		//Collections.synchronizedMap(map1);
		

		map.put(1, "10");
		map1.put(1, "10");
		map.put(2, "10");
		map1.put(2, "10");
		map.put(3, "10");
		map1.put(3, "10");
		
		Set set =map1.entrySet();
		Iterator it =set.iterator();
		while(it.hasNext()){			
			it.next();
			it.remove();			
			//map1.remove(4);
		}
		
		
		Set set1 =map.entrySet();
		Iterator it1 =set1.iterator();
		
		while(it1.hasNext()){				
			it1.next();
			map1.remove(4);
		}
		
	}
}
