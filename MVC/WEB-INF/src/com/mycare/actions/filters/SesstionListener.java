package com.mycare.actions.filters;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SesstionListener implements HttpSessionListener,HttpSessionAttributeListener,HttpSessionActivationListener,HttpSessionBindingListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("Session created for the application.");
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Session Destroyed for the application.");
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub		
		System.out.println("Session Attribute "+arg0.getName()+" created with the value "+arg0.getValue()+" for the application.");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Session Attribute "+arg0.getName()+" removed with the value "+arg0.getValue()+" for the application.");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Session Attribute "+arg0.getName()+" replaced with the value "+arg0.getValue()+" for the application.");
	}

}
