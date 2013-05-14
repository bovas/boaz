package com.mycare.actions.utils.scjp.chapter1;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ElectronicClass extends Phone1{
	long [][] [][]  []       []x= null;
	boolean []ba[]=null;
	private Phone1 ph=null;	//must be initialized
	Object a;
	public void doStuff(){
		ba[0][1] = true;
	}	
	ElectronicClass(){
		
	}
	public void doIt()throws RuntimeException{		
	}	
	void callMe() {
		//Phone1 ph1 = Phone1.class;
		//ph1.doSomethings();
		String a="MONITOR: Many psychotherapeutic and CNS-active agents (e.g., anxiolytics, sedatives, hypnotics, antidepressants, antipsychotics, opioids, alcohol, muscle relaxants) exhibit hypotensive effects, especially during initiation of therapy and dose escalation. Coadministration with antihypertensive agents, in particular vasodilators and alpha-blockers, may result in additive effects on blood pressure and orthostasis.";
		System.out.println(a.length());
	}
	public static void main(String[] args) {
		
		ElectronicClass ec = new ElectronicClass();
		ec.callMe();		
		Phone1 ph=new ElectronicClass();
		ph.callMe();		
		Object a=new Object();
		Object b= a;
		char c= 'c';		
		System.out.println((int) c);
	}
		
	String doSomethings(int a) {	//overloading rule		
		//super.doSomethings();
		return "";
	}		
	ArrayList doSomethings() {			//legal overriding		
		return null;
	}	
	Integer checkMyMethod() {		//legal overriding
		return null;
	}
	public void doany(int a){
		
	}	
}
class ElectroncClass{
	
}
