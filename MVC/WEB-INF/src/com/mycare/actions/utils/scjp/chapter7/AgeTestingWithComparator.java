package com.mycare.actions.utils.scjp.chapter7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AgeTestingWithComparator extends NameComparable{
	public int age;
	public String name=""; 
	public static void main(String[] args) {
		AgeTestingWithComparator ageTester = new AgeTestingWithComparator();
		List<AgeTestingWithComparator> ageTestingList = new ArrayList<>();		
		ageTester.age = 10;
		ageTester.name="bovas"; 
		ageTestingList.add(ageTester);
		ageTester = new AgeTestingWithComparator();
		ageTester.age = 70;
		ageTester.name="arun"; 
		ageTestingList.add(ageTester);
		ageTester = new AgeTestingWithComparator();
		ageTester.age = 20;
		ageTester.name="mani"; 
		ageTestingList.add(ageTester);
						
		/*for(AgeTestingWithComparator temp:ageTestingList)
			System.out.println(temp.age);
		
		Collections.sort(ageTestingList, new AgeComparator());
		*/
		for(AgeTestingWithComparator temp:ageTestingList)
			System.out.println(temp.age);
						
		Collections.sort(ageTestingList);
		
		for(AgeTestingWithComparator temp:ageTestingList)
			System.out.println(temp.age);

	}
}
class AgeComparator implements Comparator<AgeTestingWithComparator>{
	
	public int compare(AgeTestingWithComparator o1, AgeTestingWithComparator o2) {
		Integer age1 = o1.age;
		Integer age2 = o2.age;
		return age1.compareTo(age2);
	}
	
}
class NameComparable implements Comparable<AgeTestingWithComparator>{	
	public int compareTo(AgeTestingWithComparator o) {		
		return ((Integer)o.age).compareTo(20);
	}	
}
