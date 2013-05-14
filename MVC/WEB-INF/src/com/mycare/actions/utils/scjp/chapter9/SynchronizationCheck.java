package com.mycare.actions.utils.scjp.chapter9;

import java.util.Vector;

public class SynchronizationCheck implements Runnable{
	private static int staticCount;
	private String resource1;
	private String resource2;
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getId());
		SynchronizationCheck checking = new SynchronizationCheck();
		Thread thread1 = new Thread(checking);
		Thread thread2 = new Thread(checking);
		thread1.setName("thread");
		thread2.setName("thread1");
		thread1.start();		
		thread2.start();	
	}	
	public void run() {
		try {			
			if(Thread.currentThread().getName().equals("thread"))
				checkSynchMethod();
			if(Thread.currentThread().getName().equals("thread1"))
				checkSynchMethod1();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	private synchronized void checkSynchMethod() throws InterruptedException {
		/*synchronized(this){			*/
			this.resource1="first resource";
			System.out.println(Thread.currentThread().getName());
			System.out.println("Count in 1st thred..."+getStaticCount());
			Thread.sleep(2000);
			System.out.println("I m back...");
		//}						
	}
	private synchronized void checkSynchMethod1(){				
		this.resource1="second resource";
		System.out.println("Count in 2nd thred..."+getStaticCount());
		System.out.println(this.resource1);
		System.out.println(Thread.currentThread().getName());
		System.out.println("I am completed...");
	}
	public synchronized static int getStaticCount(){		
		return ++staticCount;
	}
}
