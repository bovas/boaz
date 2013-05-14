package com.mycare.actions.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.mycare.actions.utils.scjp.chapter1.CarSuperClass;

public class ClassMethods {
	public static void main(String[] args) {
		CarSuperClass superclass = new CarSuperClass(null);
		Class testclass= superclass.getClass();
		Class classNew = Class.class;
		System.out.println("Class object: "+classNew);
		System.out.println("Class object: "+classNew.getCanonicalName());
		System.out.println("Class object: "+classNew.getName());
		System.out.println("Class object: "+classNew.getSimpleName());
		System.out.println("Class object: "+classNew.hashCode());
		System.out.println("Class object: "+classNew.desiredAssertionStatus());
		System.out.println();
		printMethods(classNew);
		System.out.println();
		printAnnotations(classNew);		
	}

	private static void printAnnotations(Class classNew) {		
		Annotation[] annotaions  = classNew.getAnnotations();
		for(int count=0;count<annotaions.length;count++)
			System.out.println(annotaions[count]);
	}
	private static void printMethods(Class classNew) {
		Method[] methods  = classNew.getMethods();
		for(int count=0;count<methods.length;count++){			
			printMethodDetails(methods[count]);
			System.out.println();
		}
	}

	private static void printMethodDetails(Method method) {
		System.out.println("-----Method Details----");
		System.out.println("Method name::"+method.getName());
		System.out.println("is accessible::"+method.isAccessible());
		System.out.println("Return type::"+method.getReturnType().getName());
		System.out.println("Is Annotation present::"+method.isAnnotationPresent(Annotation.class));
		System.out.println("-----Method Details----");
	}
}
