package com.mycare.actions.utils.scjp.chapter7;

import java.lang.reflect.Method;

public class CollectionsReflection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Collection interface Methods...");
		writtenClassMethods(java.util.Collection.class);
		
		System.out.println();
		System.out.println("Collections Sub Interface Methods...");
		writtenClassMethods(java.util.List.class);
		writtenClassMethods(java.util.Set.class);
		writtenClassMethods(java.util.Map.class);
		writtenClassMethods(java.util.Queue.class);
		writtenClassMethods(java.util.SortedSet.class);
		writtenClassMethods(java.util.NavigableSet.class);
		writtenClassMethods(java.util.SortedSet.class);
		writtenClassMethods(java.util.NavigableMap.class);
		
		System.out.println();
		System.out.println("Collections Sub Class Methods...");
		writtenClassMethods(java.util.HashSet.class);
		writtenClassMethods(java.util.LinkedHashSet.class);
		writtenClassMethods(java.util.TreeSet.class);
		
		writtenClassMethods(java.util.ArrayList.class);
		writtenClassMethods(java.util.Vector.class);
		writtenClassMethods(java.util.LinkedList.class);
		
		writtenClassMethods(java.util.PriorityQueue.class);
					
		writtenClassMethods(java.util.Hashtable.class);
		writtenClassMethods(java.util.HashMap.class);		
		writtenClassMethods(java.util.LinkedHashMap.class);
		writtenClassMethods(java.util.TreeMap.class);
		
		System.out.println();
		System.out.println("Collections Utility Methods...");
		writtenClassMethods(java.util.Collections.class);
		writtenClassMethods(java.util.Arrays.class);
		
		System.out.println();
		System.out.println("Iterator methods..");
		writtenClassMethods(java.util.Iterator.class);
		writtenClassMethods(java.util.ListIterator.class);		
	

	}
	static void writtenClassMethods(Class<?> className){
		System.out.println();
		Method[] method = className.getMethods();
		System.out.println(className.getName()+" has the following methods.");
		for(Method me : method){
			if(me.getDeclaringClass() == Object.class )
				continue;
			System.out.println(me.getName());
		}
	}
	@Override
	public String toString() {
		return "I am the output...";
	}
}
