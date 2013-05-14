package com.mycare.actions.utils.scjp.chapter2;

import java.util.Hashtable;



public class TestOverLoading{
	private final int testval;
	public TestOverLoading() {
		testval = 10;
	}
	void checkInteger(short a){
		System.out.println("Calling in short");
	}
	void checkInteger(byte a){
		System.out.println("Calling in byte");
	}
	void checkInteger(long a,long b){
		System.out.println("Calling in long");
	}
	void checkInteger(float a){
		System.out.println("Calling in float");
	}
	void checkInteger(double a){
		System.out.println("Calling in double");
	}
	void checkInteger(int a,int b){
		System.out.println("Calling in int");
	}
	void checkInteger(char a){
		System.out.println("Calling in char---->"+a);
	}
	void checkInteger(String a){
		System.out.println("Calling in string");
	}
	void checkObject(Car car){
		System.out.println("I am car object");
	}
	void checkObject(BMW bmw){
		System.out.println("I am bmw object");
	}
	public static void main(String[] args) {
		TestOverLoading test =  new TestOverLoading();		
		Car car = new Car();
		Car bmw=new BMW();		
		Object o = (long)1;	
		System.out.println("Class type"+o.getClass().getName());
		System.out.println("Name type"+o.getClass().getName());
		test.checkInteger(10,10);
		test.checkObject(car);
		test.checkObject(bmw);
	}
}
