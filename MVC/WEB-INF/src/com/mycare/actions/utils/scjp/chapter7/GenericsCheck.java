package com.mycare.actions.utils.scjp.chapter7;

import java.util.ArrayList;
import java.util.List;
import com.mycare.actions.utils.scjp.chapter2.Animal;
import com.mycare.actions.utils.scjp.chapter2.Dog;
import com.mycare.actions.utils.scjp.chapter2.Cat;

public class GenericsCheck {
	public static void main(String[] args) {
		
		Object[] obj = new Integer[]{20,30,40}; //can
		//obj[0] = new String("");	//Array Store exception
				
		List<?> list1 = new ArrayList<Animal>();				
		List<? extends Object> list = new ArrayList<Integer>();
		
		
		//List<? super Dog> list2 = new ArrayList<?>();
		//List<? super Dog> list2 = new ArrayList<Integer>();		
		//List<Animal> li = new ArrayList<Dog>(); //can't
		
		List<Dog> li = new ArrayList<Dog>();	//		
		li.add(new Dog(1));
		li.add(new Dog(2));
		li.add(new Dog(3));
		List<Cat> li1 = new ArrayList<Cat>();	//		
		li1.add(new Cat());
		li1.add(new Cat());
		li1.add(new Cat());		
		
		testGen(li);
		testGen(li1);
		
		testGen1(li);				
		testGen12(li1);
	}

	private static void testGen(List<? extends Animal> li) {		
		//li.add(new Dog());
		li.add(null);
	}
	private static void testGen1(List<? super Dog> li) {				
		li.add(new Dog(1));
		li.add(null);		
	}
	private static void testGen12(List<? super Cat> li) {		
		li.add(new Cat());
		li.add(null);
	}
}