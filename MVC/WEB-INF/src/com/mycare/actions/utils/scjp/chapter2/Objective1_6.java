package com.mycare.actions.utils.scjp.chapter2;

public class Objective1_6 {
	protected Objective1_6(int a){
		System.out.println("Org cons");
	}
/*	protected Objective1_6(){
		System.out.println("Simple cons");
	} */
	static{
		System.out.println("static cons");
	}
}
class checkPrivateCons{
	private checkPrivateCons(){
		
	}
}
