package com.mycare.actions.utils.scjp.chapter2;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Car extends Vehicle implements Drivable{
	public static int value=10;
	public int value1=10;
	protected void getCarColor(){
		System.out.println("Middle class...");
	}	
	@Override
	public void drive(int a) {
		// TODO Auto-generated method stub
		
	}
	public void drive(String a) {		
		
	}
	public static void main(String[] args) {		
		Drivable drivable = new Car();
		Movable movable = new Car();
		drivable = (Drivable) movable;
		List<Movable> m = new ArrayList<Movable>();
		
		Car[] car = new Car[10];
		shifter(car);
		shifter();
		
		
		shifter(7);
		check(10);
	}
	static void shifter(Car[] a){
		System.out.println("1");
	}
	static void shifter(Car[]... a){
		System.out.println("2");
	}
	static void check(int a){
		System.out.println("1a");
	}
	static void check(int... a){
		System.out.println("2a");
	}
	static void shifter(Object o){
		System.out.println("Object wins");
	}
	/*public void getVehicleType(){		
		//System.out.println("Middle class...");
	}*/	
	public void run() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void move() {
		System.out.println("Moving the vehicle..");
		
	}
}
