<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	var textAjax=function(){
		var requestObject = createRquestObject();		
		requestObject.onreadystatechange=function(){callbackfun(requestObject);};
		requestObject.open("POST","TestAjaxAction",true);		
		requestObject.send(null);
	}
	var callbackfun=function(requestObject){		
		if(requestObject.readyState==4 && requestObject.status ==200) 
			console.log("Result String::"+requestObject.responseText);
	}
	var createRquestObject=function(){
		var requestObject;
		if(window.XMLHttpRequest)
			requestObject = new XMLHttpRequest();
		else
			requestObject = new ActiveXObject("Microsoft.XMLHTTP");
		return requestObject; 
	}
	var invalidateSession=function(){
		var requestObject = createRquestObject();		
		requestObject.onreadystatechange=function(){callbackfun(requestObject);};
		requestObject.open("POST","TestAjaxAction",true);
		requestObject.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		requestObject.send("requestMode=1");
	}
	var validateField = function(object){
		if(object.id=='name'){
			console.log(object.value);			
		}
	}
	var removeUnwantedChars=function(object){
		
	}
</script>
<body>	
	<table>
		<tr><td><label for="name">Name&nbsp;</label></td><td>:<input type="text" id="name" value="" onblur="validateField(this)"/></td><td><div id="namediv" style="color:red"></div></td></tr>
		<tr><td><label for="name">E-Mail&nbsp;</label></td><td>:<input type="text" id="email" value="" onblur="validateField(this)"/></td><td><div id="maildiv" style="color:red"></div></td></tr>
		<tr><td><label for="phoneno">Phone Number&nbsp;</label></td><td>:<input type="text" id="phoneno" value="" onblur="validateField(this)"/></td><td><div id="phonediv"></div></td></tr>
		<tr><td><label for="profession">Profession&nbsp;</label></td><td>:<input type="text" id="profession" value="" onblur="validateField(this)"/></td><td><div id="professiondiv"></div></td></tr>		
		<tr><td align="center"><input type="button" value="Test Ajax" onclick="javascript:textAjax();"/></td><td align="center"><input type="button" value="LogOut" onclick="javascript:invalidateSession();"/></tr>		
	</table>
</body>
</html>