package com.mycare.actions.utils.scjp.chapter7;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.mycare.actions.utils.scjp.chapter2.Animal;
import com.mycare.actions.utils.scjp.chapter2.Dog;

public class OwnGenericClass{
	static public void main(String[] args) {
		List li2 = new ArrayList(10);		
		OwnList ownList= new OwnList(li2);
		ownList.addElement(10);
		
		List li = new Vector();		
		OwnList<String> ownList2= new OwnList<>(li);
		ownList2.addElement("String");
//		ownList2.addElement(20);
		
		System.out.println(ownList.getElement(0));
		System.out.println(ownList2.getElement(0));
		
		System.out.println();
		
		ownClass oc = new ownClass(10, "string");
		oc.b = 20;
		System.out.println("Changed param::"+oc.getB());
		
		System.out.println(GenericMethod.UtilityMethod("hello"));
		
		TypeClass<String> type = new TypeClass<String>(10);
		X x = new X(10);	
		
		newClass<Dog> nc = new newClass<>(new Dog(1));
		
	}
}
class OwnList<E>{
	public List<E> element;
	public OwnList(List<E> element) {
		this.element = element;
	}
	public E getElement(int index){
		return this.element.get(index);
	}
	public void addElement(E addElement){
		this.element.add(addElement);
	}
}
class ownClass<A,B>{
	public A a;
	public B b;
	public ownClass(A a,B b){
		this.a = a;
		this.b = b;
	}
	public A getA(){
		return a;
	}
	public B getB(){
		return b;
	}
}
class TypeClass<T>{
	public T type;
	public <T> TypeClass(T type) {
		System.out.println("Type of class  "+this.type);
		System.out.println("Type of class  "+type);
		//this.type = type;
	}
}
class GenericMethod{	
	public static  <T> T UtilityMethod(T element){
		List<T> mylist = new ArrayList<>();
		mylist.add(element);
		return element;		
	}
}
class X{
	public <X> X (X x){
		
	}
	public  X (X x){
		
	}
}
class newClass<T extends Animal>{
	T anim;
	public newClass(T anim){
		this.anim = anim;
	}
	public <K extends Integer,V extends Integer> void setElemet(K k,V v){
		
	}
}