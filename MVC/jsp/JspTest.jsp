<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" buffer="16kb" isThreadSafe="false" errorPage="ErrorTesting.jsp"%>
<%@ page import="java.io.*;" %>
    <%!   	
    	int count=0;
    	public int getCount()throws Exception{    		    		
    		return count++;    		
    	}    	
    	public void jspInit(){
    		System.out.println("Jsp Initialization method:::");
    	}
    	
    	public void jspDestroy(){
    		System.out.println("Destroying jsp servlet:::");
    	}
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jsp Test Page</title>
</head>
<body>
	Page access count:::<%=getCount()%>		
	<%@include file="ErrorTesting.jsp"%>
	<jsp:forward page="Menu.jsp"></jsp:forward>
</body>
</html>