package com.mycare.actions.utils.scjp.chapter2;

public class StaticOnRxChecking {
	public static boolean checkMyMethod(boolean myValues){
		boolean myValue = myValues;
		return myValue;
	}
	public static void main(String[] args) {
		System.out.println(StaticOnRxChecking.checkMyMethod(true));
		StaticOnRxChecking check = new StaticOnRxChecking();
		System.out.println(check.checkMyMethod(true));
	}
}
