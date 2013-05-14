package com.mycare.actions.utils.scjp.chapter9;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.RandomAccess;

public class CheckThreadSafe implements Runnable{
	List list = new ArrayList();
	CheckThreadSafe(){		
		Collections.synchronizedList(list);
		add("bovas");
	}
	public static void main(String[] args) {				
		CheckThreadSafe threadSafeObj = new CheckThreadSafe();
		Thread thread1 = new Thread(threadSafeObj);
		Thread thread2 = new Thread(threadSafeObj);
		thread1.start();
		thread2.start();		
	}
	@Override
	public void run() {			
		try {
			System.out.println(removeElement(0));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void add(String name){
		list.add(name);
	}
	private String removeElement(int index) throws InterruptedException{		
		if(list.size() > 0){
			Thread.sleep(100);
			return (String) list.remove(0);
		}
		else 
			return null;
	}
}
