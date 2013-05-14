package com.mycare.actions.utils.scjp.chapter9;

public class SynchronizedMadProgram implements Runnable{
	private static int staticVar = 1;
	private int nonstaticVar = 10;	
	public static void main(String[] args) {
		SynchronizedMadProgram prgm = new SynchronizedMadProgram();
		Thread thread = new Thread(prgm);
		Thread thread1 = new Thread(prgm);
		thread.setName("thread");
		thread1.setName("thread1");
		thread.start();		
		thread1.start();
	}
	public static synchronized void staticMethod(SynchronizedMadProgram prgm) throws InterruptedException{
		prgm.nonstaticVar++;
		Thread.sleep(5000);
		System.out.println("Woke up...");
	}
	public synchronized void setnonStaticVar(){		
		staticVar++;		
		System.out.println("I completed my job");
	}
	@Override
	public void run() {
		try {
			if(Thread.currentThread().getName().equals("thread"))
				this.staticMethod(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Thread.currentThread().getName().equals("thread1"))
			setnonStaticVar();
	}
}