package com.mycare.actions.utils.scjp.chapter7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import edu.emory.mathcs.backport.java.util.Collections;

public class TreeMapCheck {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		TreeMap treeMap = new TreeMap();

		//put
		System.out.println(treeMap.put("string3", "bovas"));
		System.out.println(treeMap.put("string2", "arun"));
		System.out.println(treeMap.put("string1", "zantac"));
		System.out.println(treeMap.put("string4", "amanda"));
		//get
		System.out.println("Get value::"+treeMap.get("string1"));

		//comparator
		TreeMap treeMap2 = new TreeMap(new ComparatorClass());
		System.out.println("Comparator::"+treeMap2.comparator());


		//Entry set
		Set set = treeMap.entrySet();
		Iterator itr=  set.iterator();
		while(itr.hasNext()){
			Entry entry = (Entry)itr.next();
			System.out.println("Sorted Key Value pair:::"+entry.getKey()+"---->"+entry.getValue());
		}

		//descendingKeySet
		Set set1 =treeMap.descendingKeySet();
		itr=  set1.iterator();
		while(itr.hasNext()){
			System.out.println("Descending Sorted Key :::"+itr.next());
		}

		System.out.println("Ceiling key:::"+treeMap.ceilingKey("string"));
		System.out.println("Flooring key::"+treeMap.floorKey("string4"));
		System.out.println("Ceiling entry:::"+treeMap.ceilingEntry("string"));
		System.out.println("Flooring entry::"+treeMap.floorEntry("string4"));

		System.out.println("firstEntry::"+treeMap.firstEntry());
		System.out.println("lastEntry::"+treeMap.lastEntry());
		System.out.println("firstKey::"+treeMap.firstKey());
		System.out.println("lastKey::"+treeMap.lastKey());		
		System.out.println("firstEntry::"+treeMap.firstEntry());	
		System.out.println("lastEntry::"+treeMap.lastEntry());
		System.out.println("lowerKey::"+treeMap.lowerKey("string2"));
		System.out.println("higherKey::"+treeMap.higherKey("string2"));
		System.out.println("lowerEntry::"+treeMap.lowerEntry("string2"));
		System.out.println("higherEntry::"+treeMap.higherEntry("string2"));		

		TreeMap treeMap1 = new TreeMap(treeMap);
		Map normalmap = new TreeMap();
		TreeMap treeMap3 = new TreeMap(normalmap);

		NavigableMap nMap =  treeMap.descendingMap();
		Set navSet = nMap.entrySet();
		System.out.println(nMap);
		System.out.println(navSet);
		SortedMap subMap = treeMap.headMap("string3");
		System.out.println("head Map::"+subMap);
		System.out.println("tail Map::"+treeMap.pollFirstEntry());
		System.out.println("head Map::"+subMap);
		System.out.println("tail Map::"+treeMap.tailMap("string4"));

		System.out.println("head Map::"+treeMap.headMap("string3",true));
		System.out.println("tail Map::"+treeMap.tailMap("string4",false));

		SortedMap map = treeMap.tailMap("string4");
		Set setNew = map.keySet();
		Iterator itrNew= setNew.iterator();
		while(itrNew.hasNext())
			System.out.println(itrNew.next());
		
		System.out.println(treeMap.subMap("string2", "string4"));
		System.out.println(treeMap.subMap("string2",true, "string4",true));
		/*	List li = new ArrayList();
		li.add("bovas");
		li.add("aron");
		Collections.sort(li,new ComparatorClass());*/
		//TreeMap treeMap7 = new TreeMap(new nonComparatorClass());
	}

}
class ComparatorClass implements Comparator<String>{

	public int compare(String o1, String o2) {
		System.out.println(o1.compareToIgnoreCase(o2));
		return o1.compareToIgnoreCase(o2);
	}

}
class nonComparatorClass{

}
