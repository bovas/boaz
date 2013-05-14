package com.mycare.actions.annotations;

import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

public class AnnotationTest2 {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		AnnotationTest1 test  = new AnnotationTest1();
		test.suppressWarnings();
		Method[] method= test.getClass().getDeclaredMethods();
		for(int count=0;count<method.length;count++){
			System.out.println("Method name::"+method[count].getName());
			printAnnotations(method[count]);
		}
	}

	private static void printAnnotations(Method method) {
		Annotation[] anntn = (Annotation[]) method.getAnnotations();
		for(int i=0;i<anntn.length;i++)
			System.out.println("Annotation::"+anntn[i].toString());
	}
}
