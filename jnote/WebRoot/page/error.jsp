<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<base href="<%=basePath%>">
<title>神奇页面</title>

<script type="text/javascript">
	var secs = 3; //倒计时的秒数 
	var URL ;
	function Load(url){
		URL = url;
		for(var i=secs;i>=0;i--) 
		{ 
		   window.setTimeout('doUpdate(' + i + ')', (secs-i) * 1000); 
		} 
	}
	function doUpdate(num){ 
		document.getElementById('ShowDiv').innerHTML = '将在'+num+'秒后自动跳转到登录' ;
		if(num == 0) { window.location = URL; }
	}
</script>
</head>
<body onload="Load('page/loginAndRegister.jsp')">
遇到未知错误<br>
<div id="ShowDiv"></div><br>
返回<a href="page/loginAndRegister.jsp">登录</a>页面
</body>
</html>