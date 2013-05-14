package com.mycare.actions.utils.scjp.chapter7;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapThreadCheck implements Runnable{
	private HashMap hMap;
	
	public HashMapThreadCheck() {
		hMap = new HashMap();
	}
	
	public static void main(String[] args) {
		HashMapThreadCheck check = new HashMapThreadCheck();
		Thread thread = new Thread(check);
		Thread thread1 = new Thread(check);
		thread.setName("1");
		thread1.setName("2");
		thread.start();		
		thread1.start();
	}
	
	public void run() {
		if(Thread.currentThread().getName().equals("1"))
			iterateTheHashMap();			
		if(Thread.currentThread().getName().equals("2"))
			try {
				assignValues();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	}

	private void assignValues() throws InterruptedException {
		hMap.put("1", "1");
		hMap.put("2", "2");
		hMap.put("3", "3");				
	}

	private void iterateTheHashMap() {
		Set set = hMap.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext())
			System.out.println(it.next());
	}		

}
