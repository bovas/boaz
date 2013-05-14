package com.mycare.actions.utils.scjp.chapter9;

import java.util.ArrayList;
import java.util.List;

import com.mycare.actions.utils.scjp.chapter2.Animal;

public class GenericsTest1<E> {
	E instanceElement;
	public static void main(String[] args) {
		//List<Animal> list = new ArrayList<Dog>();
		List<Animal> list = new ArrayList<Animal>();
	}
	public void setElement(E element){
		this.instanceElement = element;
	}
	public E getElement(){
		return instanceElement;
	}
}
class GenericSubClsss<T>{
	public T telement;
	public GenericSubClsss(T telement){
		this.telement = telement;	
	}
	public T getElement(){
		return telement;
	}
	public <K,X> void setElement(K telement,X xelement){
		
	}
}
