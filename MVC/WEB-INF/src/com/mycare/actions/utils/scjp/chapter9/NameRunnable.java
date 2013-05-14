package com.mycare.actions.utils.scjp.chapter9;

public class NameRunnable implements Runnable{
	public void run(){
		for (int i = 0; i < 5; i++) {
			System.out.println("Current thread name::"+Thread.currentThread().getName()+" state::"+Thread.currentThread().getState()+" "+i);
			Thread.yield();
			/*try {
				if(i==10 && Thread.currentThread().equals("thrd2"))
				Thread.currentThread().join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			/*if( i%10 == 0)
				Thread.yield();*/			
		}		
	}
}
