<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<span>register</span>
<form name=regist action="/jnote/register.action" method="post">
		username:<input type=text name=username id=username value="${username}"> <span id="msg"></span><br>
		password:<input type=password name=password value="${password}"><br>
		password again:<input type=password name=repassword value="${repassword}"><br> 	
		<s:fielderror></s:fielderror>
		<input type=submit value=submit>
</form>
<span>login</span>
<form name=regist action="" method="post">
		username:<input type=text name=username id=username value="${username}"> <span id="msg"></span><br>
		password:<input type=password name=password value="${password}"><br>	
		<input type=submit value=submit>
</form>
</body>
</html>