package com.mycare.actions.utils.scjp.chapter2;

import java.util.Scanner;

public class PalyndromeChecking {
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		String org= sc.nextLine();
		int length = org.length();
		String newone="";
		for(int i=length-1;i>=0;i--)
			newone+=org.charAt(i);
		if(org.equals(newone))
			System.out.println("Maatikitaan..");
	}
}
