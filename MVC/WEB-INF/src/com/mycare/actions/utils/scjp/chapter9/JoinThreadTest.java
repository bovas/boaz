package com.mycare.actions.utils.scjp.chapter9;

public class JoinThreadTest {
	public static void main(String[] args) throws InterruptedException {
		threadStarted thrdJob = new threadStarted();
		Thread thrd1 = new Thread(thrdJob);
		Thread thrd2 = new Thread(thrdJob);
		Thread thrd3 = new Thread(thrdJob);
		thrd1.setName("thread1");
		thrd2.setName("thread2");
		thrd3.setName("thread3");
		thrd1.start();
		thrd2.start();
		thrd3.start();
		System.out.println("Thread going to join...");
		thrd1.join();
		System.out.println("Main completed...");
	}
}
class threadStarted implements Runnable{
	public void run() {
		for(int count=0;count<100;count++){
			System.out.println(Thread.currentThread().getName()+" is running...");
			if(count==50)
				try {
					System.out.println("GOing to sleep---"+Thread.currentThread().getName());
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
