package com.mycare.actions.utils.scjp.chapter7;

import java.util.Enumeration;
import java.util.Vector;

public class VectorCheck {
	public static void main(String[] args) {
		Vector vector = new Vector();
		Vector vector1 = new Vector(20);
		Vector vector2 = new Vector(10,20);
		Vector vector3 = new Vector(vector);
		vector1.add(10);
		vector1.add(null);
		vector1.add(1,15);
		System.out.println(vector1.get(0));
		Enumeration e = vector1.elements();
		while(e.hasMoreElements())
			System.out.println("Enumerated Elements::"+e.nextElement());
		System.out.println(vector1.capacity());
		vector1.ensureCapacity(40);
		System.out.println(vector1.capacity());
	}
}
