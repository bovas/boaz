package com.mycare.actions.utils.scjp.chapter1;

public class CarSuperClass {
	protected String color;
	public CarSuperClass(String color){
		setColor("blue");
	}
	protected void setColor(String color){
		this.color = color;
	}
	protected void accelerate(){
		System.out.println("The color of the color is: "+color);
	}
}
