package com.mycare.actions.utils.scjp.chapter9;

public class SynchronizedStaticCheck implements Runnable{
	private static String staticVar = "100";
	public static void main(String[] args) {
		SynchronizedStaticCheck staticCheck = new SynchronizedStaticCheck();
		Thread thread1 = new Thread(staticCheck);
		Thread thread2 = new Thread(staticCheck);
		thread1.setName("thread");
		thread2.setName("thread1");
		thread1.start();		
		thread2.start();
	}

	@Override
	public void run() {
		if(Thread.currentThread().getName().equals("thread"))
			try {
				checkSynchMethod();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(Thread.currentThread().getName().equals("thread1"))
			checkSynchMethod1();		
	}

	private static void checkSynchMethod() throws InterruptedException, ClassNotFoundException {
		System.out.println(Thread.currentThread().getName()+ " going to be started....");
		synchronized(Class.forName("com.mycare.actions.utils.scjp.chapter9.SynchronizedStaticCheck")){
			Thread.sleep(5000);
		}				
		System.out.println(Thread.currentThread().getName()+ " going to be completed....");
	}

	private synchronized static void checkSynchMethod1() {		
		System.out.println(Thread.currentThread().getName()+ " going to be completed....");
	}
}
