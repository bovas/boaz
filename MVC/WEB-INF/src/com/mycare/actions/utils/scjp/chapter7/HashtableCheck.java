package com.mycare.actions.utils.scjp.chapter7;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class HashtableCheck{
	public static void main(String[] args) {
		Hashtable htable = new Hashtable();
		Hashtable htable1 = new Hashtable(10);
		Hashtable htable2 = new Hashtable(20, 0.75f);
		
		System.out.println(htable.put("name", "bovas"));
		System.out.println(htable.put("name1", "arun"));
		System.out.println(htable.put("name1", "arone"));
		System.out.println(htable.put("name2", "raja"));
		System.out.println(htable.put("nam", "raj"));
		System.out.println(htable.get("name1"));
		System.out.println(htable);
		//htable.put(null, "");			//Not null
		
		System.out.println("Removed value::"+htable.remove("name"));
		
		System.out.println("Is present::"+htable.contains("raj"));
		
		Hashtable htable3 = new Hashtable(htable);
		
		
		Set set1  = htable3.keySet();
		Iterator it2 =set1.iterator();		
		while(it2.hasNext()){			
			System.out.println("Key set Iterator value::"+it2.next());
		}
		
		Collection coll = htable3.values();
		Iterator it =coll.iterator();
		while(it.hasNext())
			System.out.println("Value set Iterator value::"+it.next());
		
		Set set = htable3.entrySet();
		Iterator it1 =set.iterator();		
		while(it1.hasNext()){
			Entry ent = (Entry) it1.next();
			System.out.println("Key set Iterator value::"+ent.getKey()+" -- value "+ent.getValue());
		}		
		
		Enumeration enumeration = htable.keys();
		while(enumeration.hasMoreElements())
			System.out.println("Enum keys::"+enumeration.nextElement());
		
		Enumeration enumeration1 = htable.elements();
		while(enumeration1.hasMoreElements())
			System.out.println("Enum values::"+enumeration1.nextElement());
		//checkShiftOperator();
	}
	static void checkShiftOperator(){
		System.out.println(4 << 1);
	}
}

