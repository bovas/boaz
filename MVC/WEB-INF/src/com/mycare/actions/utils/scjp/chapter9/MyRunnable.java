package com.mycare.actions.utils.scjp.chapter9;

public class MyRunnable implements Runnable{
	public static void main(String[] args) {
		Thread mythread=new Thread(new MyRunnable());
		mythread.start();
	}
	@Override
	public void run() {		
		System.out.println("Job will be done here.");
		System.out.println("Current thread Name::"+Thread.currentThread().getName());
	}
}
