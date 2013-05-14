package com.mycare.actions.utils.scjp.chapter9;

public class CheckMyThread {
	public static void main(String[] args) throws InterruptedException {
		NameRunnable runnabl1 = new NameRunnable();
		Thread thrd1 = new Thread(runnabl1);
		Thread thrd2 = new Thread(runnabl1);
		Thread thrd3 = new Thread(runnabl1);
		thrd1.setName("thrd1");
		thrd2.setName("thrd2");
		/*thrd1.setPriority(Thread.MAX_PRIORITY);*/
		thrd1.start();		
		thrd1.join();
		thrd2.start();
		thrd3.start();
		System.out.println("reached end.");
	}
}
