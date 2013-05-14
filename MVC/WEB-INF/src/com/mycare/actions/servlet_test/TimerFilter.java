package com.mycare.actions.servlet_test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public final class TimerFilter implements Filter 
{
	FilterConfig config;
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain)throws IOException, ServletException 
    {

        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long stopTime = System.currentTimeMillis();
        System.out.println("Time to execute request: " + (stopTime - startTime) + " milliseconds");

    }

	@Override
	public void destroy() {
		this.config = null;		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.config = arg0; 		
	}
}