package com.mycare.actions.utils.scjp.chapter1;

public class FerrariSubClass extends CarSuperClass{
	private String color = "";	
	protected FerrariSubClass(String color) {		
		super(color);		
		this.color = color;
	}
	public static void main(String[] args) {
		char[] exChar={'r','e','d'};		
		String ex = String.valueOf(exChar);
		ex = new String(exChar);
		for(CardsEnum.Rank c:CardsEnum.Rank.values()){			
			ex = c.toString();
		}
		
		FerrariSubClass sub = new FerrariSubClass(ex);
		sub.accelerate();
	}	
	public void accelerate(){
		System.out.println("The color of the color is: "+this.color);
	}
}

