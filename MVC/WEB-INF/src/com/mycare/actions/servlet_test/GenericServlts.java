package com.mycare.actions.servlet_test;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class GenericServlts extends GenericServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8793314281127482330L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Initialization::"+getServletName());
		super.init(config);
	}
	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		System.out.println("Service method...");
	}
	public void destroy(){
		System.out.println("Destroy::"+getServletName());		
	}
}
