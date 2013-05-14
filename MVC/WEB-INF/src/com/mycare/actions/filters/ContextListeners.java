package com.mycare.actions.filters;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListeners implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Context Destroyed...");		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Context initialized...");
		
	}

}
