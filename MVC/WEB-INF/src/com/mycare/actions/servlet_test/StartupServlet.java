package com.mycare.actions.servlet_test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class StartupServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2449978011619791178L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Initalization of "+getServletName());
		super.init();
	}
	
	
	@Override
	public void destroy() {		
		System.out.println("death of "+getServletName());
		super.destroy();
	}
}
