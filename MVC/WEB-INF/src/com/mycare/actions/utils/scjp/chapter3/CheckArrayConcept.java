package com.mycare.actions.utils.scjp.chapter3;

import java.util.Date;

import sun.tools.jar.Main;

public class CheckArrayConcept extends SuperClass{
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Thread[] thread = new Thread[10];
		int [] a = new int[10];
		Date[] date = new Date[10];
		/*for(Thread th:thread){
			System.out.println(th.getName());
		}*/
		/*for(Date bool:date){
			System.out.println(bool);
		}*/
		checkAnonymousArray(new long[][]{{1,2,3},new long[]{2,3,4}});
		int[][] twod = new int[3][];
		for(int i=0;i<twod.length;i++){
			System.out.println(twod[i]);
			for(int j=0;j<twod[i].length;j++)
				System.out.println(twod[i][j]);
		}
	}	
	static void checkAnonymousArray(long[][] array){
		
		SuperClass[] superC = {new CheckArrayConcept()};
		CheckArrayConcept[] chk = {new CheckArrayConcept()};				
		superC = chk;			//Legal
		
		char[] ch = {'a','b'};
		/*int[] in =ch;*/		//Illegal
		
		/*for(long[] ar:array)			
			for(long a:ar)
				System.out.println(a);*/
		
		SuperClass[][] sup = new SuperClass[1][2];
		 
	}
}
class SuperClass{
	
}
