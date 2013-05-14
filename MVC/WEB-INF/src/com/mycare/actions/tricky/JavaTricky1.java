package com.mycare.actions.tricky;

public class JavaTricky1 {
	 public static void main(String[] args) {
	     
		 try{
				swap(1000, 2000);
//				main(null);
				//System.exit(0);
			}catch(StackOverflowError e){
				System.out.println("error::"+e);
			}finally{
				System.out.println("finally..");
			}
		 
		 	char x = 'X';
	        int i = 0;	        
	        
		 	Object x1=1;
	        String i1="";
	        
	        System.out.println(true  ? x1 : i1);
	        System.out.println(false ? i : x1);
	        
	        
	        x+=i;     // Must be LEGAL
//	        x=x+i;  // Must be ILLEGAL
	        
	    }	
	private static void swap(int x,int y) {        
		x ^= y ^= x ^= y;		
        System.out.println("x = " + x + "; y = " + y);
	}
}
