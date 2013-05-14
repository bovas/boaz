package com.mycare.actions.utils.scjp.chapter9;

import java.util.Scanner;

public class MailDeliverySystem {	
	public static void main(String[] args) {
		MailReceivingSystem receving = new MailReceivingSystem();
		Thread thread = new Thread(receving);
		thread.start();		
		while(true){
			synchronized(receving){
				try {										
					if(receving.totalMails ==0 ){
						System.out.println("Dont have mails to process...going to wait...");
						receving.wait();
						System.out.println("Woke up...");
					}
					System.out.println("Processing new mails:::"+receving.totalMails);
					receving.totalMails=0;												
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}
			}
		}
	}
}
class MailReceivingSystem extends Thread{
	public int totalMails=10;
	@Override
	public void run(){		
		Scanner scanner = new Scanner(System.in);
		while(true){			
			synchronized(this) {
				System.out.println("Got the lock again...");
				String nxtLine  = scanner.nextLine();
				int newMails = 0;
				if(!nxtLine.equals(""))
					newMails = Integer.parseInt(nxtLine);
				if(newMails > 0){
					totalMails+=newMails;
					System.out.println("Hey...I got new mails to Process...");
					notify();					
				}
			}
			System.out.println("Released the lock...");
		}
	}
}