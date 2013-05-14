package com.mycare.actions.utils.scjp.chapter7;

import java.util.ArrayList;

public class ArrayListProblem {

	public static void main(String[] args) throws InterruptedException {
		ArrayList<String> al = new ArrayList<>();
		al.add("10");
		al.add("20");		
		al.add("40");
		al.add(3,"10");
		al.add("30");
		al.add("10");
		System.out.println(al);		
		removeTens(al);
	}
	static void removeTens(ArrayList<String> al){		
		for(int count1=0;count1<al.size();count1++){
			int count=1;
			String str = al.get(count1);
			while(!removeDups(str,al)){
				count++;
			}
			System.out.println(str+"--"+count);
		}					
		System.out.println(al);
	}
	static boolean removeDups(String str,ArrayList<String> al){
		int index= al.indexOf(str) ;
		int lastindex = al.lastIndexOf(str);
		if(index != lastindex){
			al.remove(lastindex);
			return false;
		}
		else{
			return true;			
		}
	}
}
