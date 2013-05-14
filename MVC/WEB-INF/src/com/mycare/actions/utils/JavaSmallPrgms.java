package com.mycare.actions.utils;

import com.mycare.actions.utils.scjp.chapter2.Animal;

public class JavaSmallPrgms {
	Animal animal;
	public JavaSmallPrgms() {
		animal = new Animal(); 
	}
	public void setAnimal(Animal animal){
		this.animal =animal;  
	}
	public static void main(String[] args) {
		reverseNumbers();
		stringReverse();
		System.out.println(stringReverseRecursion("avula"));
		printAddNumbers();
	}

	private static void reverseNumbers() {
		int a=123456789;
		int rem=0;
		while(a > 0){			
			rem = (rem*10) + (a%10);
			a = a / 10;
		}
		System.out.println(rem);
	}
	private static void stringReverse(){
		String a="bovas";
		char[] chars = a.toCharArray();		
		String s="";
		for(int c=chars.length-1; c>=0; c--)
			s+=chars[c]; 		
		System.out.println(s);
			
	}
	private static String stringReverseRecursion(String a){
		if(a.length() < 2)
			return a;
		return stringReverseRecursion(a.substring(1))+ a.charAt(0);					
	}
	private static void printAddNumbers(){
		for(int c=1;c<=9;c++){
			System.out.print(c+"---");
			for(int c1=1;c1<=100;c1++){
				if(c1%10 == c){
					System.out.print(c1+"--");
				}				
			}
			System.out.println();
		}
	}
}
