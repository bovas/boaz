<%@ page import="java.util.ArrayList,java.util.HashMap" %>
<script language='javascript1.2'>
<%
String adUrl = request.getScheme() + "://" + "www.glaceemr.com" + ":" + request.getServerPort() + request.getSession().getServletContext().getInitParameter("adAlertURL");

String isAdReq = "0"; 
  
String menuIds = "";
if(request.getAttribute("mainMenus") != null ){
	ArrayList mainMenusList = (ArrayList) request.getAttribute("mainMenus");	
	HashMap menu  = null;
	StringBuilder grpname = new StringBuilder();
	StringBuilder menuname = new StringBuilder();
	StringBuilder menuurl = new StringBuilder();
	StringBuilder isactive = new StringBuilder();
	StringBuilder loadloc = new StringBuilder();	
	int preGroup =-1;
	out.println(" var MenuLinks    = new Array();");
	for(int i=0,j=0; i < mainMenusList.size(); i++){
		menu = (HashMap) mainMenusList.get(i);
		menuIds += menu.get("menuid")+",";	
			if (Integer.parseInt(menu.get("groupid").toString())!= preGroup && j != 0 ) {
				menuname.append(");");
				menuurl.append(");");
				isactive.append(");");
				loadloc.append(");");
				out.println(grpname.toString()+menuname.toString()+menuurl.toString()+isactive.toString()+loadloc.toString());
				//GlaceOutLoggingStream.console(grpname.toString()+menuname.toString()+menuurl.toString()+isactive.toString()+loadloc.toString());
				grpname = new StringBuilder();
	            menuname = new StringBuilder();
	            menuurl = new StringBuilder();
	            isactive = new StringBuilder();
	            loadloc = new StringBuilder();
			}
			grpname.append(((Integer.parseInt(menu.get("groupid").toString())!= preGroup )?" MenuLinks["+ j++ +"] = \"" + menu.get("groupname").toString() + "\";":""));
			menuname.append(((Integer.parseInt(menu.get("groupid").toString())!= preGroup )?" MenuLinks["+ j++ +"] = new Array(":",") +"\""+ (menu.get("menuname")).toString()+"\"");
			menuurl.append(((Integer.parseInt(menu.get("groupid").toString())!= preGroup )?" MenuLinks["+ j++ +"] = new Array(":",") +"\""+ (menu.get("menuurl")).toString()+"\"");
			isactive.append(((Integer.parseInt(menu.get("groupid").toString())!= preGroup )?" MenuLinks["+ j++ +"] = new Array(":",") +"true");
			loadloc.append(((Integer.parseInt(menu.get("groupid").toString())!= preGroup )?" MenuLinks["+ j++ +"] = new Array(":",") +"\""+ (menu.get("loadloc")).toString()+"\"");
			preGroup = Integer.parseInt(menu.get("groupid").toString());
			if (i == mainMenusList.size()-1){
				menuname.append(");");
				menuurl.append(");");
				isactive.append(");");
				loadloc.append(");");
				out.println(grpname.toString()+menuname.toString()+menuurl.toString()+isactive.toString()+loadloc.toString());
				out.println(grpname.toString()+menuname.toString()+menuurl.toString()+isactive.toString()+loadloc.toString());
				grpname = new StringBuilder();
	            menuname = new StringBuilder();
	            menuurl = new StringBuilder();
	            isactive = new StringBuilder();
	            loadloc = new StringBuilder();
		}
	}
	grpname = null;
    menuname = null;
    menuurl = null;
	isactive = null;
	loadloc = null;
}else
	out.println(" ");
%>