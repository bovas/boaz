package com.mycare.actions.utils.jvm;

import com.mycare.actions.utils.scjp.chapter2.Movable;

public class JvmTest {	
	public int a;	////instance initialization	- method area
	public JvmTest() {
		//constructors - method area
	}
	public static void main(String[] args) {
		checkMyMethod();	//method invocation - stack
		new JvmTest();	//heap class instance
		JvmTest test = new JvmTest();		
		int[] a = null;
		a = new int[10];	//arrays - heap
		
		Movable mova = new Movable() {		//interface initialization - method area
			
			@Override
			public void move() {
				// TODO Auto-generated method stub
				
			}
		};
	}

	private static void checkMyMethod() {
		//method code - method area
		int a;		//local variable - stack		
		return;	//return - stack
	}
}
