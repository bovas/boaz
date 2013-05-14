package com.mycare.actions.utils.scjp.design;

class StaticDesignCheck1 {	
	static StaticDesignCheck1 staticValue1 = new StaticDesignCheck1();
	static int staticValue=30;
	static final int finalValue=20;	
	public StaticDesignCheck1(){
		
	}	
}
public class StaticDesignCheck{
	public static void main(String[] args) {
		System.out.println(StaticDesignCheck1.staticValue1.staticValue);
	}
}
