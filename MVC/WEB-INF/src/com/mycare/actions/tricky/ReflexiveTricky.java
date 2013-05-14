package com.mycare.actions.tricky;

public class ReflexiveTricky {
	public static void main(String[] args) {
		double d = Double.NaN;
		float f = Float.NaN;
		System.out.println(f==f);
		System.out.println(d==d);
	}
}
