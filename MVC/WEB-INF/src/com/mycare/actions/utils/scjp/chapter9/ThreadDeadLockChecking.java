package com.mycare.actions.utils.scjp.chapter9;

public class ThreadDeadLockChecking implements Runnable{
	public Resource resource1;
	public Resource resource2;
	public ThreadDeadLockChecking() {
		resource1 = new Resource();
		resource2 = new Resource();
	}
	public static void main(String[] args) {
		ThreadDeadLockChecking check = new ThreadDeadLockChecking();
		Thread thrd = new Thread(check);		
		Thread thrd1 = new Thread(check);
		thrd.setName("thread1");
		thrd1.setName("thread2");
		thrd.start();
		thrd1.start();
	}
	@Override
	public void run() {
		Thread thrd = Thread.currentThread();
		if(thrd.getName().equals("thread1"))
			accessInOneWay();
		if(thrd.getName().equals("thread2"))
			accessInAnotherWay();	
	}
	public void accessInOneWay(){
		synchronized(resource2){
			System.out.println("first time  "+System.currentTimeMillis());
			synchronized(resource1){
				System.out.println("Successfully reached in one way...."+Thread.currentThread().getName());
			}
		}
	}
	public void accessInAnotherWay(){
		synchronized(resource1){
			System.out.println("2nd time "+System.currentTimeMillis());
			synchronized(resource2){
				System.out.println("Successfully reached in another way...."+Thread.currentThread().getName());
			}
		}
	}
	static class Resource{
		int value = 10;
	}
}