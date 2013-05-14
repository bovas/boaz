package com.mycare.actions.utils.scjp.chapter1;

public class BeansInvoker {
	public static void main(String[] args) {
		PropertyChangeListenerSample1 sample1 = new PropertyChangeListenerSample1();
		sample1.setSize(10);
		sample1.setTestGrades(0,10);
		System.out.println(sample1.getTestGrades(0));
		sample1.setTestGrades(0,20);
		System.out.println(sample1.getTestGrades(0));
	}
}
