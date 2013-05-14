package com.mycare.actions.utils.scjp.chapter2;

import java.io.IOException;

public class Animal {
	Animal(int x){
		System.out.println("calling");
	}
	public Animal(){
		System.out.println("No arg cons");
	}
	public Animal getAnimalHeight()throws Exception{
		System.out.println("Dont ask my height..");
		return null;
	}
	public Integer checkOverloading(){
		return 0;
	}
	public static void main(String[] args) {							
		System.out.println("Upper main class.");
	}
	protected void main(char[] args) {
		System.out.println("Upper class.");
	}
	public Animal checkMethod(Animal test){
		return null;
		
	}
	public Dog checkMethod() {
		// TODO Auto-generated method stub
		return null;
	}
}
