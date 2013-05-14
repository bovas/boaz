package com.mycare.actions.servlet_test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetServlet extends HttpServlet{

	private static final long serialVersionUID = 6367932074974804468L;
	@Override
	public void init(ServletConfig config) throws ServletException {		
		super.init(config);
		System.out.println("Init method of Get sevlet.");
	}
	public synchronized void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println(Thread.currentThread().getName());

		try {
			//response.setContentType("text/html");
			response.setContentType("application/jar");
			ServletContext ct = getServletContext();
			InputStream is = ct.getResourceAsStream("/mail.jar");
			response.setHeader( "Content-Disposition", "attachment; filename=mail.jar" );
			if(is==null)
				response.sendError(500);
			int r = 0;
			byte[] byits = new byte[1024];
			ServletOutputStream sop = response.getOutputStream();
			while ((r = is.read(byits)) != -1)
				sop.write(r);
			is.close();
			sop.flush();
			sop.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("Headers:");
		Enumeration enumer = request.getHeaderNames();
		while(enumer.hasMoreElements()){
			String headerName = (String) enumer.nextElement();
			System.out.println(headerName+"---"+request.getHeader(headerName));
		}
		System.out.println();
		System.out.println("Cookies:");
		Cookie[] ck = request.getCookies();
		for(Cookie c:ck)
			System.out.println(c.getName()+"--"+c.getValue());
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("<html><body>Html Post response</body></html>");
	}	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {		
		super.service(req, resp);		
		System.out.println("Last modified..."+getLastModified(req));		
	}
	@Override
	public void destroy() {		
		super.destroy();
		System.out.println("Going to destroy "+this.getServletName());
	}
}
