package com.mycare.actions.servlet_test;

import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycare.actions.utils.MYException;
import com.mycare.actions.utils.Utilities;


public class TestAjaxAction extends HttpServlet{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8996915532706728963L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		doMyAction(req, resp);
	}	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		doMyAction(req, resp);
	}
	public void doMyAction(HttpServletRequest req, HttpServletResponse resp) throws IOException{					
		int requestMode = Integer.parseInt(Utilities.Nz(req.getParameter("requestMode"), "-1"));
		HelperModel helper = null;		
		try{						
			helper = new HelperModel();
			System.out.println("requestMode::"+requestMode);
			if(requestMode == HelperModel.INVALIDATE_SESSION){							
				HttpSession session = req.getSession(false);
				session.invalidate();
				StringTokenizer token  = new StringTokenizer("bovas");
				while(token.hasMoreTokens());
					token.nextToken();
				Utilities.writeIntoAjaxResult("Session Invalidated", resp);
			}else if(requestMode == HelperModel.VALIDATEFORM){
				Hashtable<String,String> table = new Hashtable<String, String>(5);
				table.put("name",Utilities.Nz(req.getParameter("name"), ""));
				table.put("mail",Utilities.Nz(req.getParameter("mail"), ""));
				table.put("phone",Utilities.Nz(req.getParameter("phone"), ""));
				table.put("profession",Utilities.Nz(req.getParameter("profession"), ""));
				Utilities.writeIntoAjaxResult(helper.validateWithHelper(table),resp);
			}
		}catch(MYException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}						
	}
}
