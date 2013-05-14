package com.mycare.actions.collections;

import java.util.Hashtable;

public class ChangeHashtableValue implements Runnable{
	Hashtable hs= new Hashtable();
	@Override
	public void run() {		
		hs.put("1", "hello");
	}
	
}
