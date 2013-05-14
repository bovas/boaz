package com.mycare.actions.servlet_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import com.mycare.actions.utils.Utilities;

public class LoginServlet extends HttpServlet{
	/**
	 *
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context = getServletContext();		
		super.init(config);
	}
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		try {			 			
			
			performAction(req,res);
			ServletContext context = req.getServletContext();
			ServletConfig config = 	getServletConfig();
			HttpSession session = req.getSession();

			System.out.println("Config Param"+config.getInitParameter("ServletVendor"));		//init
			System.out.println("COntext param"+config.getServletContext().getInitParameter("App Vendor"));

			context.setAttribute("AppVendor", "Bovas");		//application
			session.setAttribute("SessionActive", "yes");		//session for the page
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res){		
		try {
			performAction(req,res);					     
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void performAction(HttpServletRequest req,HttpServletResponse res)throws Exception{
		System.out.println("performaction...");
		HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(res);
		 req.getRequestDispatcher("/jsp/TestAjax.jsp").include(req, responseWrapper);
		 String content = responseWrapper.toString();
		 System.out.println("Output : " + content);
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://192.168.2.4/epcs", "glace", "glacenxt");
		Statement stmt= conn.createStatement();
		ArrayList<HashMap<String, String>> resultVal = getValues(stmt);
		req.setAttribute("mainMenus", resultVal);
		req.getServletContext().getRequestDispatcher("/jsp/TestAjax.jsp").forward(req, res);
	}
	public ArrayList<HashMap<String, String>> getValues(Statement stmt)throws Exception{
		StringBuilder query = new StringBuilder();
		query.append("select menu_config_menuname as menuname , menu_config_html_title as title , menus_name as groupname , menu_config_menu_url as menuurl  , menu_config_load_location as loadLoc , ");
		query.append(" menus_id as groupId  from menu_config " );
		query.append(" inner join menus on  menu_config_group_id = menus_id  and menus_isactive is true ");
		query.append(" where menu_config_unknown = 1 and menu_config_isactive is true " );
		query.append(" and menu_config_id in ( ");
		query.append(" select distinct menuid from user_mmap ");
		query.append(" where user_mmap.userid in (1)");
		query.append(" ) order by menus_order asc , menu_config_order asc ");
		ResultSet rx = stmt.executeQuery(query.toString());
		return Utilities.changeRStoAL(rx);
	}
}
