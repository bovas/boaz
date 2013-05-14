package com.mycare.actions.utils.scjp.chapter9;

public class ThreadWaitCheck implements Runnable{
	public static void main(String[] args) throws InterruptedException {
		ThreadWaitCheck waitCheck = new ThreadWaitCheck();
		Thread thread = new Thread(waitCheck);
		thread.start();
		//thread.join();
		synchronized(waitCheck){
			try {
				System.out.println("GOing to wait...");
				waitCheck.wait(100);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Main thread stops here...");
		}
	}

	@Override
	public void run() {
		synchronized(this){
			System.out.println("GOing to do the job...");
		for(int count=1;count<100;count++)
		;
		System.out.println("Going to notify...");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//notify();
		System.out.println("Notified...");
		}
	}
}
