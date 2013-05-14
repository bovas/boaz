<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script>
	var getRequest=function(){
		document.forms[0].method="Get";
		document.forms[0].submit();
	}
	var postRequest=function(){
		document.forms[0].method="POST";
		document.forms[0].submit();
	}
	var optionsRequest=function(){
		document.forms[0].method="OPTIONS";
		document.forms[0].submit();
	}
	var boxclear=function(){		
		var inputTags = document.getElementsByTagName("input");		
		for(var count=0;count<inputTags.length;count++){			
			 if(inputTags[count].type=='text' || inputTags[count].type=='password')
				 inputTags[count].value="";
		}
	}
</script>
</head>
<body>
	<form action="../GetServlet">
		<input type="text" name="username" value="bovas" autocomplete="off">
		<input type="password" name="password" value="password" autocomplete="off">
		<input type="text" name="username" value="bovas" autocomplete="off">
		<input type="password" name="password" value="password" autocomplete="off">
		<input type="text" name="username1" value="bovas" autocomplete="off">
		<input type="password" name="password1" value="password" autocomplete="off">
		<input type="text" name="username2" value="bovas" autocomplete="off">
		<input type="password" name="password2" value="password" autocomplete="off">
		<input type="text" name="username3" value="bovas" autocomplete="off">
		<input type="password" name="password3" value="password" autocomplete="off">
		<input type="text" name="username4" value="bovas" autocomplete="off">
		<input type="password" name="password4" value="password" autocomplete="off">
		<input type="text" name="username5" value="bovas" autocomplete="off">
		<input type="password" name="password5" value="password" autocomplete="off">
		<input type="text" name="username6" value="bovas" autocomplete="off">
		<input type="password" name="password6" value="password" autocomplete="off">
		<input type="text" name="username7" value="bovas" autocomplete="off">
		<input type="password" name="password7" value="password" autocomplete="off">
		<input type="submit" value="Get Request" onclick="getRequest();">
		<input type="submit" value="Post Request" onclick="postRequest();">
		<input type="submit" value="Options Request" onclick="optionsRequest();">
		<input type="button" value="Clear fields" onclick="boxclear();">
	</form>
</body>
</html>