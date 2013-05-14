package com.mycare.actions.utils.scjp.chapter9;

public class ThreadsDoubt1 implements Runnable{

	public static void main(String[] args) throws InterruptedException {
		ThreadsDoubt1 doubt1 = new ThreadsDoubt1();
		Thread thrd1 = new Thread(doubt1);
		Thread thrd2 = new Thread(doubt1);
		Thread thrd3 = new Thread(doubt1);
		thrd1.setName("thrd1");
		thrd2.setName("thrd2");
		thrd3.setName("thrd3");
		thrd1.start();
		thrd2.start();
		thrd3.start();		
		thrd1.join();		
		System.out.println("Main method...");
		/*synchronized (doubt1) {
			doubt1.wait();
		}*/
	}
	@Override
	public void run() {
		Thread currentThrd = Thread.currentThread();
		System.out.println(currentThrd.getName());
		if(currentThrd.getName().equals("thrd1")){			
			for (int i = 0; i < 1000000000; i++) {
				;
			}
			/*Thread.sleep(3000);			*/
		}
		System.out.println(currentThrd.getName()+"Running...");
		
	}

}
