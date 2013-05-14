package com.mycare.actions.utils.scjp.chapter3;

public class CheckBoxingTechs {
	public static void main(String[] args) {
		callMethod(10);
		callMethod(new Byte("10"));
		callMethod(new Long("10"));		
		byte by = 10;
		checkBoxing(by);
	}

	private static void checkBoxing(Object by) {		
		//System.out.println((Long) by);
	}

	private static void callMethod(long a) {
			Long lng = a;
	}
	private static void callMethod(Integer a) {
		
	}
	private static void callMethod(Integer... a) {
		
	}
	private static void callMethod(Object a) {
		
	}
	private static void callMethod(Number a) {
		
	}
}
