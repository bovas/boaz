package com.mycare.actions.utils;


import java.io.IOException;
import javax.mail.Authenticator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SendMailServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse resp){
		try {
			performAction(req,resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest req,HttpServletResponse resp){
		try {
			performAction(req,resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	public void performAction(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		System.out.println("Calling");				
		String[] reciep={"bovas@glenwoodsystems.com"};
		String[] ccreciep={"bovas@glenwoodsystems.com"};
		String[] bccreciep={"bovas1990@gmail.com","krrishbovas.fire@gmail.com"};
		//String[] attachments=null;
		String messgae=Utilities.Nz(req.getParameter("message"),"");
		String subj=Utilities.Nz(req.getParameter("subject"),"");
		String toReqParam = Utilities.Nz(req.getParameter("to"),"");
		if(!toReqParam.equals("")){
			String[] toReqArr = toReqParam.split(",");
			reciep = toReqArr;
		}
		String[] attachments={"/media/Mine/KRRISH/wallpapers/enjoy-life-.jpg"};
		SendMyMail.sendMail("notifications@gmail.com",reciep,ccreciep,bccreciep,messgae,subj,attachments);		
	}	
}