package com.mycare.actions.utils.scjp.chapter2;

public class ConstructoChecking {
	public static void main(String[] args) {
		ConstructoChecking check = new ConstructoChecking();
		innerClass cls = check.new innerClass();
		NonpublicClass nonclass = new NonpublicClass();
		class nestedClass{
			public nestedClass() {
				
			}
		}
	}
	class innerClass{
		public innerClass() {
			System.out.println("Inner class");
		}
	}
}
class NonpublicClass{
	protected NonpublicClass() {
		System.out.println("Non public cons");
	}	
}
