package com.mycare.actions.utils.scjp.chapter2;

public class AccountManager implements calculatable{
	
	public void getPersonDetails() {		
		
	}
	
	public void getAccountDetails() {
		
		
	}
	
}
class Cashier implements humanable{

	public void getPersonDetails() {
		
	}
	
}
interface calculatable extends humanable{
	void getAccountDetails();
}
interface humanable{
	void getPersonDetails();
}