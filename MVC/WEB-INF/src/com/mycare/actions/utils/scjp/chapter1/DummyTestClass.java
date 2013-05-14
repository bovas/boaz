package com.mycare.actions.utils.scjp.chapter1;

public class DummyTestClass {
	public static void main(String[] args) {
		
		DummySuperClas objA = new DummySubClass();
		
		objA.get(); //Calls Function of B .... Why ?? -	Overridden 
		System.out.println(objA.a); //Variable of A .....why is not accessing B's variable "a" Object is super class object 
		objA.put(); //Calls Function of A	
		//objA.test(); // Compilation Error ....Why ?? The method test() is undefined for the type A		//Super class doesn't have the method
		
		
		DummySubClass objB = new DummySubClass();
		
		DummySuperClas objA2  = objB;
		
		objA2.get(); //Calls Function of B .... Why ??
		objA2.put(); //Calls Function of A
		System.out.println(objA2.a); //Variable of A .....why is not accessing B's variable "a" 
		//objA2.test(); // Compilation Error ....Why ?? The method test() is undefined for the type A
				
		DummySubClass errorObj = (DummySubClass) objA; //// No Compilation error ...but Why..?? Run Time Exception Occours.
		objA.get();
	}
}
