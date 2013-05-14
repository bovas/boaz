package com.mycare.actions.reflection;

import com.mycare.actions.utils.scjp.chapter2.Animal;
import com.mycare.actions.utils.scjp.chapter2.Dog;

public class ReflectionProgram {	
	public static void main(String[] args) {
		try { 
			System.out.println("The first time calls forName:");
			Class c   = Class.forName("com.mycare.actions.reflection.RelectionTest"); 
			System.out.println("Getting clas object");
			RelectionTest a = (RelectionTest)c.newInstance();

			System.out.println("The second time calls forName:");
			Class c1 = Class.forName("com.mycare.actions.reflection.RelectionTest");
			
			Animal ani = new Dog(0);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}
}