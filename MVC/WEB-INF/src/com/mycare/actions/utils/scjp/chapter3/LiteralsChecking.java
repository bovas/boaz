package com.mycare.actions.utils.scjp.chapter3;

public class LiteralsChecking {
	int instnce;
	int[] a = new int[1];
	public static void main(String[] args) {
		long no = 0000000000000000000000000000000000001111111111111111111111L;	//Octal represent
		long num = 0XFFFFFFFFFFFFFFFFL;
		double flt= 12.211231123123121;
		char a='\n';
		/*a = (char)-10;
		System.out.println(a);*/
		a = '\"';
		String str="";
		Thread t;		
		System.out.println(a);
		System.out.println(a+=68);
		System.out.println(no);
		System.out.println(flt);
		LiteralsChecking check = new LiteralsChecking();
		check.printPrimitiveValues("byte",1);
		check.printPrimitiveValues("short",2);
		check.printPrimitiveValues("int",4);
		check.printPrimitiveValues("long",8);
		System.out.println("value is:: "+check.convertIntoBinary(127));		
		for(int i=0; i < check.a.length;i++)
			System.out.println(check.a[i]);
		
	}
	void printPrimitiveValues(String name,int x){
		int ml;
		x = x * 8;
		long startVal = (long) Math.pow(-2, x-1);
		long endVal = (long) (Math.pow(2, x-1) - 1);		
		System.out.println("Range of the primitive -"+name +"  "+startVal+" - "+endVal);				
	}
	String convertIntoBinary(long x){
		StringBuffer buff = new StringBuffer();
		long rem;
		while(x > 0){
			rem = x % 2;
			x = x / 2;			
			buff.append(rem);
		}
		return buff.reverse().toString();
	}
}
