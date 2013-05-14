package com.mycare.actions.utils.scjp.chapter3;

import java.util.Date;

public class GarbageCollectionCheck {
	GarbageCollectionCheck check;
	public static void main(String[] args) {		
		/*GarbageCollectionCheck check =  new GarbageCollectionCheck();				
		GarbageCollectionCheck check1 = new GarbageCollectionCheck ();
		GarbageCollectionCheck check2 = new GarbageCollectionCheck ();
		GarbageCollectionCheck check3 = new GarbageCollectionCheck ();
		check1.check  = check2.check;
		check2.check = check3.check;
		check3.check = check1.check;*/		
		Runtime rt = Runtime.getRuntime();
		Date d;
		System.out.println("Initial::"+rt.freeMemory());
		for(int i=0;i<10;i++){
			d = new Date();
			d=null;
			System.out.println("Runtime::"+rt.freeMemory());
			System.gc();
		}				
		Object a;
		System.out.println("Final::"+rt.freeMemory());
	}	
	void checkStringOperations(){
		StringBuilder sb = new StringBuilder("Initial Capacity"); 		//16+16
		String hannah = "Did Hannah see bees? Hannah did.";		//32,e,
		System.out.println("Was it a car or a cat I saw?".substring(9, 12));
		String original = "software";
        StringBuilder result = new StringBuilder("hi");
        int index = original.indexOf('a');	//5
        result.setCharAt(0, original.charAt(0));	//si
        result.setCharAt(1, original.charAt(original.length()-1));	//se
        result.insert(1, original.charAt(4));	//swe
        result.append(original.substring(1,4));	//sweoft
        result.insert(3, (original.substring(index, index+2) + " ")); 	//swear oft
        System.out.println(result.toString());
        
        String str1= "hi, ";
        String str2="bvs.";
        System.out.println(str1+str2);
        System.out.println(str1.concat(str2));
        System.out.println("bovas".substring(3));
	}
	@Override
	protected void finalize() throws Throwable {		
		System.out.println("I am not working always.");
	}
}
