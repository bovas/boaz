package com.mycare.actions.utils.scjp.chapter2;

public class CheckBoxingMethods extends SuperClass{
	String name="new";
	public static void main(String[] args) {
		CheckBoxingMethods[] methods = new CheckBoxingMethods[10];
		SuperClass[] supers = new SuperClass[10];
		SuperClass sc = new CheckBoxingMethods();		
		checkStaticMethod(supers);
		checkStaticMethod(methods);
		checkStaticMethod(10);		
	}

	private static void checkStaticMethod(SuperClass[]... supers) {
		System.out.println("Super var method");
	}
	private static void checkStaticMethod(CheckBoxingMethods[]... subs) {
		System.out.println("sub var method");
	}
	private static void checkStaticMethod(CheckBoxingMethods[] subs) {
		System.out.println("sub method");
	}
	private static void checkStaticMethod(Object subs) {
		System.out.println("Object method");
	}
	private static void checkStaticMethod(Integer subs) {
		System.out.println("Object Integer method");
	}
	private static void checkStaticMethod(int subs) {
		System.out.println("Primitive Integer method");
	}
}
class SuperClass{
	String name="bovas";
}
