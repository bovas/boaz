package com.mycare.actions.utils.scjp.chapter4;

public class OperatorChecking {
	public static void main(String[] args) {
		
		String i="String";
		int i1=3;
		int i2=4;
		System.out.println(i + i1 + i2);
		testRightShiftOp(100, 2);
	}
	public static void testRightShiftOp(int i,int rotator){
		System.out.println("-----Right Shift----");
		System.out.println(Integer.toBinaryString(i));
		i = i>>>rotator;
		System.out.println(Integer.toBinaryString(i));
		System.out.println("-----Right Shift----");
	}
}
