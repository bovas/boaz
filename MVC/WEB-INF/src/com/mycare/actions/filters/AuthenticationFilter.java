package com.mycare.actions.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter{
	FilterConfig config;
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		
	}	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("Got response...");
		HttpServletRequest req = (HttpServletRequest)request; 
		HttpServletResponse res = (HttpServletResponse)response;
		boolean isAuthenticated = false;
		try{
			String authHeader = req.getHeader("Authorization");
			if(authHeader.equals("Bovas"))
				isAuthenticated = true;
		}catch(Exception e){
			e.printStackTrace();
			res.sendError(500);
		}finally{
			if(!isAuthenticated)
				res.sendError(401);
		}
		chain.doFilter(req, res);
	}	
	@Override
	public void destroy() {
		config = null;	
	}
}
