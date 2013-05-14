package com.mycare.actions.utils.scjp.chapter2;

public class OverloadedCons {	
	String name;
	static int abs;
	public OverloadedCons(){
		this(genRandomname());
	}
	public OverloadedCons(String name){
		this.name = name;
	}
	static String genRandomname(){		
		String name = new String[]{"bovas","bovas1","bovas2"}[(int)Math.random()*1];		
		return name; 
	}
	void doSomething(OverloadedCons cons1){
		System.out.println(cons1.abs);
		//doSomethingExtra();
	}
	void doSomethingExtra(){
		//doSomething();
	}
	public static void main(String[] args) {
		OverloadedCons cons  =  new OverloadedCons();
		System.out.println(cons.name);
		OverloadedCons cons1  =  new OverloadedCons("Krrish");
		System.out.println(cons1.name);
		cons1.doSomething(cons1);		
	}
}
