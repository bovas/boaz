package com.mycare.actions.utils.scjp.chapter9;

public class ThreadTest2 implements Runnable{
	public static synchronized void main(String[] args) throws InterruptedException {
		Thread thread = new Thread();
		thread.start();
		System.out.println("X");
		synchronized(thread){
			thread.wait(1000);
		}
		System.out.println("Y");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
