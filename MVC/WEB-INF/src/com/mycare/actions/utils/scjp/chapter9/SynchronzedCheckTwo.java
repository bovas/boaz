package com.mycare.actions.utils.scjp.chapter9;

public class SynchronzedCheckTwo implements Runnable{
	private StringBuffer stringbuff;
	public SynchronzedCheckTwo(StringBuffer buffvalue) {
		stringbuff = buffvalue;
	}
	public static void main(String[] args) {
		SynchronzedCheckTwo checkTwo = new SynchronzedCheckTwo(new StringBuffer("A"));
		Thread thread1 = new Thread(checkTwo);
		Thread thread2 = new Thread(checkTwo);
		Thread thread3 = new Thread(checkTwo);
		thread1.start();
		thread2.start();
		thread3.start();
	}
	public void run() {
		synchronized(stringbuff){
			for(int count=0;count<100;count++){
				System.out.println(count+" Accessing thread---->"+Thread.currentThread().getName()+"--->"+stringbuff);				
			}
			char charStr =stringbuff.charAt(0);
			String newStr = Character.toString(++charStr);			
			stringbuff = new StringBuffer(newStr);
		}
	}
}
