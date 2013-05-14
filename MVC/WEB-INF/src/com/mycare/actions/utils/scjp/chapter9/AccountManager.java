package com.mycare.actions.utils.scjp.chapter9;

public class AccountManager implements Runnable{
	private int totalAmt = 50;
	public static void main(String[] args) {
		AccountManager accManger = new AccountManager();
		Thread thread1 = new Thread(accManger);
		thread1.setName("Thala");
		Thread thread2 = new Thread(accManger);
		thread2.setName("Tharu thala");
		thread1.start();
		thread2.start();
		Object o;
	}
	
	public void run() {		
		for(int count=0;count<5;count++){
			if(totalAmt < 0)
				System.out.println(totalAmt+ "R u Mad...Going to get Money????");
			try {
				makeWithDrawl(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
	}

	private synchronized void makeWithDrawl(int amountToTake) throws InterruptedException {
		System.out.println(amountToTake +"<--->"+ totalAmt+"----->"+Thread.currentThread().getName());
		if(amountToTake <= totalAmt){						
			Thread.sleep(500);
			totalAmt = totalAmt - amountToTake; 
			System.out.println("Money Money Money....!"+totalAmt);
		} else{
			System.out.println("No money. No money...No money...No money da..."+Thread.currentThread().getName());
		}
	}
}
