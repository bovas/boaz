package com.mycare.actions.utils.scjp.chapter7;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.mycare.actions.utils.scjp.chapter2.Car;
import com.mycare.actions.utils.scjp.chapter2.Dog;

public class OverridingTOString{
	public static void main(String[] args) {
		OverridingTOString ovrriding = new OverridingTOString();
		String returnValue = ovrriding.toString().split("@")[1];
		System.out.println("Hash code::"+returnValue);
		
		System.out.println(ovrriding);
		ovrriding.equals("");
		
		HashMap hashMap = new HashMap();
		Car cr = new Car();
		hashMap.put(cr, new Dog(0));
		System.out.println(hashMap.get(cr));
		
	}	
	public String toString() {		
		return "Whatever the input, i will be the output";
	}		
	public boolean equals(String obj) {
		return true;
	}
}

