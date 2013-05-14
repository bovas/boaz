package com.mycare.actions.utils.scjp.chapter3;

public class OverrideMainMethod extends MainMethodOverloading{	
	public static void main(String args[]){		
		System.out.println("Overriding also possible.......");
	}	
	public void newCheck(){
		super.newCheck();
		System.out.println("Parent methog");
	}
	public static void newCheckStatic(){		
		System.out.println("Parent methog");
	}
}
class test{
	public static void main(String[] args) {
		OverrideMainMethod overriden = new OverrideMainMethod();
		overriden.main(new String[]{"string..."});
	}
}