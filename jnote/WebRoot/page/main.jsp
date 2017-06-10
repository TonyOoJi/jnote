<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="page/assets/bootstrap-3.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script src="page/js/jquery-3.2.1.js" type="text/javascript"></script>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Jnote</a>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<s:if test="#session.user==null">
				<li><a href="page/loginAndRegister.jsp"><span class="glyphicon glyphicon-user"></span>登录</a></li>
			</s:if>
			<s:elseif test="#session.user!=null">
				<li><a href="note/logout"><span class="glyphicon glyphicon-log-out"></span>退出</a></li>
			</s:elseif>
		</ul>
	</div>
	</nav>
<%= session.getAttribute("user") %>
</body>
</html>