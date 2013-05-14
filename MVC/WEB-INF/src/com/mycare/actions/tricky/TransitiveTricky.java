package com.mycare.actions.tricky;

public class TransitiveTricky {
	public static void main(String[] args) {
		long x = Long.MAX_VALUE;
		double y= Long.MAX_VALUE;
		long z = Long.MAX_VALUE-1;
		System.out.println(x+"---"+y+"---"+z);
		System.out.println(x==y);
		System.out.println(y==z);
		System.out.println(x==z);
	}
}
