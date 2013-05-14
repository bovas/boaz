package com.mycare.actions.utils.scjp.chapter2;

public class Objective1_6_Super extends Objective1_6{
	static{
		System.out.println("Own statci class");
	}	
	Objective1_6_Super(){	
		super(10);
		System.out.println("Simple sub cons");
	}	
	Objective1_6_Super(int a){
		super(10);
		System.out.println("Parameterized cons");
	}
	Objective1_6_Super(String a){
		super(10);
		System.out.println("Overloadded cons");
	}
	public static void main(String[] args) {
		class nested{
			public nested() {
				System.out.println("Nested class cons...");
			}
		}
		Objective1_6 obj1_6 =  new Objective1_6(10);
		Objective1_6_Super obj1_6_super =  new Objective1_6_Super(10);
		nested nest= new nested();
		innerclass inner = obj1_6_super.new innerclass();
		nonnested nonnest = new nonnested();				
	}
	class innerclass{
		public innerclass() {
			System.out.println("inner class cons...");
		}
	}
	Object Objective1_6(){
		System.out.println("Calling...");
		return null;
	}
}
class nonnested{
	public nonnested() {
		System.out.println("Non Nested cons...");
	}
	static{
		System.out.println("Non Nested...");
	}
}
