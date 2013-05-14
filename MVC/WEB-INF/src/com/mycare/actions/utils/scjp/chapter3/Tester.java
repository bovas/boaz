package com.mycare.actions.utils.scjp.chapter3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ListModel;

import com.google.common.collect.ListMultimap;
/*
public class Tester {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String htmlString = scanner.nextLine();
		String noHTMLString = htmlString.replaceAll("\\<.*?>","");
		System.out.println(noHTMLString);
	}
	public enum Suits {		
		CLUBS(20), DIAMONDS(20), HEARTS(30), SPADES(30),		
		NOTRUMP(40) { public int getValue(int bid) {
		return ((bid-1)*30)+40; } };		
		Suits(int points) { this.points = points; }		
		private int points;		
		public int getValue(int bid) { return points * bid; }
	}
		static int intVal = 8;
		int a;
		static String s="";
		public static void main(String[] args) {					
		System.out.println(Suits.NOTRUMP.getValue(3));		
		System.out.println(Suits.SPADES + " " + Suits.SPADES.points);		
		System.out.println(Suits.values());
		int x=4;Boolean y=true;short sht[] = {1,2,3};
		try{
			checkVrargs(7);			
		}catch(Exception e){

		}finally{

		}
		doSome(x,y);
		doSome(x);
		doSome(sht,sht);
		System.out.println(s);
		}
		void iterate(int itVal){			
			itVal++;			
		}
		static void doSome(Object o){
			s+="1";
		}static void doSome(Object... o){
			s+="2";
		}static void doSome(Integer i){
			s+="3";
		}static void doSome(Long l){
			s+="4";
		}
		static void checkVrargs(int ab)throws Exception,IOException{
			throw new Exception("");
		}
		static void checkVrargs(int... ab){

		}
}*/
public class Tester {/* 
public static void main(String[] args) { 
try { 
doStuff(); 
} 
catch (Exception e) { 
System.out.print("catch block "); 
} 
finally { 
System.out.print("finally block "); 
} 
} 
static void doStuff() { 
throw new Error(); 
 }*/
	public static void main(String[] args) {			
		String property = System.getProperty("sun.boot.library.path");
		/*StringTokenizer parser = new StringTokenizer(property, ";");
		while (parser.hasMoreTokens()) {
			System.err.println(parser.nextToken());
		}	*/		
		String[] arr = null;
		Vector v;
		try {
			arr = Tester.initializePath("java.library.path");
			Throwable th = new Throwable();
			Exception e = (Exception)th;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s:arr)
		System.out.println(s);
	}
	private static String[] initializePath(String propname)throws Throwable {
		String ldpath = System.getProperty(propname, "");
		String ps = File.pathSeparator;
		int ldlen = ldpath.length();
		int i, j, n;
		// Count the separators in the path
		i = ldpath.indexOf(ps);
		n = 0;
		while (i >= 0) {
			n++;
			i = ldpath.indexOf(ps, i + 1);
		}

		// allocate the array of paths - n :'s = n + 1 path elements
		String[] paths = new String[n + 1];

		// Fill the array with paths from the ldpath
		n = i = 0;
		j = ldpath.indexOf(ps);
		while (j >= 0) {
			if (j - i > 0) {				
				paths[n++] = ldpath.substring(i, j);				
			} else if (j - i == 0) {
				paths[n++] = ".";
			}
			i = j + 1;
			j = ldpath.indexOf(ps, i);
		}
		paths[n] = ldpath.substring(i, ldlen);
		return paths;
	}
} 