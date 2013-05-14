package com.mycare.actions.utils.scjp.chapter2;

import java.io.IOException;
import java.sql.SQLException;

public class LoadAndRide extends ParentClass{	
	public LoadAndRide(){
		//System.out.println("Child Class Constructor");
	}
	public void callParent()throws IOException{
		System.out.println("I am child...");
	}
	public void callParent(int i) {
		System.out.println("I am child...");
	}
	public static void main(String[] args)throws Exception{
		LoadAndRide ride = new LoadAndRide();
		//ride.callParent();
		//ride.callParent(1);
		ParentClass parent = new ParentClass();
		ParentClass child = new LoadAndRide();
		parent.callParent();
		child.callParent();
	}
}
class ParentClass{
	public ParentClass() {
		//System.out.println("Parent class constructor..");
	}
	public void callParent()throws IOException{
		System.out.println("I am parent...");
	}
}
