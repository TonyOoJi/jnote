<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'uploadError.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
		document.getElementById('ShowDiv').innerHTML = '将在'+num+'秒后自动跳转到上级页面' ;
		if(num == 0) { window.location = URL; }
	}
</script>
  </head>
  
  <body onload="Load('note/getUserInfo')">
    	请检查是否选择了文件，文件格式、大小等事都符合要求 <br>
	<div id="ShowDiv"></div><br>
  </body>
</html>
