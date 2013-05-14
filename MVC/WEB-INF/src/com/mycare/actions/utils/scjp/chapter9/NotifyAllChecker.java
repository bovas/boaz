package com.mycare.actions.utils.scjp.chapter9;

public class NotifyAllChecker extends Thread{
	private NotifyAllJobThread notifyAllThrd;
	public NotifyAllChecker(NotifyAllJobThread notifyAllThrd) {
		this.notifyAllThrd = notifyAllThrd;
	}
	public static void main(String[] args) {
		NotifyAllJobThread notifyAllThrd = new NotifyAllJobThread();
		/*notifyAllThrd.start();*/
		new NotifyAllChecker(notifyAllThrd).start();
		new NotifyAllChecker(notifyAllThrd).start();
		new NotifyAllChecker(notifyAllThrd).start();
		notifyAllThrd.start();				
	}	
	public void run() {
		synchronized(notifyAllThrd){
			System.out.println("Going to wait..."+Thread.currentThread().getName());
			try {
				if(Thread.currentThread().getName().equals("Thread-1"))
				notifyAllThrd.wait();
				System.out.println("Thread woke up..."+Thread.currentThread().getName());
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
}
class NotifyAllJobThread extends Thread{	
	public void run() {		
		synchronized(this){
			for(int count=0;count<100;count++)
				;
			System.out.println("Going to notify all the thread...");
			notifyAll();
		}
	}
}
