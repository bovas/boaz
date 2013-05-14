package com.mycare.actions.utils.scjp.chapter3;

public class MainMethodOverloading  {

	static public void main(String args[]){
		System.out.println("Inside main method");		
	}
	public static void main(int args[]){
		System.out.println("overloding the main method");
	}
	public static void main(){
		System.out.println("overloding the main method11111111");
	}
	public void newCheck(){
		System.out.println("Parent methog");
	}
	public static void newCheckStatic(){
		System.out.println("Parent methog");
	}
}
