package com.mycare.actions.utils.scjp.chapter7;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args){
		Map<Object,Object> map = new HashMap<>();
		map.put("k1",new Dog("puppy"));
		map.put("k2",new Dog("mydog"));
		map.put( Pets.CAT,new Cat());
		Dog dogKey = new Dog("rosy");
		map.put(dogKey, "string value");
		System.out.println(map.get(dogKey));
	}
}
class Cat extends Animal{}
enum Pets{CAT,DOG,MONKEY}

class Dog extends Animal{
	private String name;
	public Dog(String name) {		
		this.name = name;
	}
	/*public boolean equals(Object obj) {		
		return this.name == ((Dog)obj).name;
	}*/
}