package com.mycare.actions.utils.scjp.chapter7;

import java.util.Set;
import java.util.HashSet;

public class InsertObjectInMapCheck {
	private int age;
	public InsertObjectInMapCheck(int age) {
		this.age = age;
	}
	public static void main(String[] args){
		Set<InsertObjectInMapCheck> set = new HashSet<>();
		set.add(new InsertObjectInMapCheck(10));
		set.add(new InsertObjectInMapCheck(20));
		set.add(new InsertObjectInMapCheck(10));
		for(InsertObjectInMapCheck check:set)
			System.out.println(check.age);
	}	
	public boolean equals(Object obj) {
		InsertObjectInMapCheck curObj =null;
		if(obj!=null){
			curObj= (InsertObjectInMapCheck) obj;
			return this.age == curObj.age;
		}else{
			throw new NullPointerException("Null argument passed.");
		}
		
	}
	@Override
	public int hashCode(){
		return 0;
	}	
}
