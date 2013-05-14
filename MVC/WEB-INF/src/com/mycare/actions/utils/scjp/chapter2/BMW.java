package com.mycare.actions.utils.scjp.chapter2;

import java.io.IOException;

public class BMW extends Car implements Drivable{
	public String color="";
	public int value1=20;
	/*public void drive(){
		System.out.println("Driving with 300km/hr");
	}*/
	public void getBMWCost(){
		System.out.println("MyCost is 2 ml crores");
	}	
	/*protected void getCarColor() {
		System.out.println("My value;;;");
	}*/
	public static void main(String[] args) throws IOException {
		BMW bmw = new BMW();
		Object o = bmw;
		Car car = bmw;
		Car.value=10;
		car.value1=10;
		car.getCarColor();
		bmw.checkInterfaceMethod(bmw);		
		car.getVehicleType();		
		System.out.println(car.value1);
	}
	public void checkInterfaceMethod(Drivable drivableCar){
		drivableCar.drive();
	}
}
