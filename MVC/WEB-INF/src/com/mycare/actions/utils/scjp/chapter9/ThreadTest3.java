package com.mycare.actions.utils.scjp.chapter9;

public class ThreadTest3 {	
		public static void main(String [] args) throws InterruptedException {
			printAll(args);
		}
		public static void printAll(String[] lines) throws InterruptedException {
			System.out.println(Thread.currentThread().getName());
		for(int i=0;i<lines.length;i++){
			System.out.println(lines[i]);
			Thread.currentThread().sleep(1000);
		}
		}		
}
