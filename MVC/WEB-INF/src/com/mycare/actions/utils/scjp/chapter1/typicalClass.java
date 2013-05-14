package com.mycare.actions.utils.scjp.chapter1;

import java.lang.reflect.Modifier;

	public class typicalClass {
		public static void main(String args[]){
			subclass s = new subclass();	
			//System.out.println(Modifier.toString(s.getClass().getModifiers()));			
			System.out.println();
			//s.test();
		}
	}
	class subclass{
		public void test()
		{
			System.out.println("test program------");
		}
		
	}


