package com.mycare.actions.utils.scjp.chapter2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dog extends Animal implements Pet{
	int a;
	public Dog(int z){
		System.out.println("sub clas cons");
	}
	public static void main(String[] args) {
		Animal animal = new Animal();
		Dog dog = new Dog(10);				
		Animal[] animals= new Animal[]{
				//new Animal(),
				new Dog(10),
				new Animal()
			};

		Animal a  = new Dog(1);
		Dog d = new Dog(1);
		d.getAnimalHeight(10, 20);
		try {
			a.getAnimalHeight();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(Animal animalllov:animals){			
			
			//if(animalllov instanceof Dog){
			
				((Dog)animalllov).eatLikePet();
				
				//List a = new ArrayList();
				
				try {
					//d = d.getAnimalHeight();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				char[] args1=new char[]{'a'};
				//d.main(args1);
				
			//}else{
				 try {
					animalllov.getAnimalHeight();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//}
		}		
		//animal.getAnimalHeight();
		//dog.getAnimalHeight();
		return;
	}
	@Override
	public void eatLikePet() {
		System.out.println("I am eating like pet and good...");
		
	}
	@Override
	public Dog getAnimalHeight()throws Exception{		
		System.out.println("My height is 3 feet..");
		return new Dog[]{null}[0];
	}
	public Dog getAnimalHeight(int a,int b){		
		System.out.println("My height is 3 feet..");
		return new Dog[]{null}[0];
	}
	public long getAnimalHeight(int a) {		
		System.out.println("My height is 3 feet..");
		int b=1;
		return b;
	}	
	protected void main(char[] args) {		
		super.main(args);
		super.main(new String[]{"hello"});
	}
	@Override
	public Integer checkOverloading() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Dog checkMethod(Animal test) {
		// TODO Auto-generated method stub
		return null;
	}
}
