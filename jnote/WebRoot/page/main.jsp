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

<link href="page/assets/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="page/css/home.css" rel="stylesheet" type="text/css"></link>
<script src="page/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="page/js/home.js" type="text/javascript"></script>
</head>
<body>
<div class="container-fluid container-home">
<!-- 导航栏  -->
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Jnote</a>
			</div>
			<ul class="nav nav-tabs">
				<li class="active">
					 <a href="#">首页</a>
				</li>
				<li>
					 <a href="#">简介</a>
				</li>
				<li class="disabled">
					 <a href="#">信息</a>
				</li>
				<!-- <li class="dropdown pull-right"> -->
				<li class="dropdown">
					 <a href="#" data-toggle="dropdown" class="dropdown-toggle">下拉<strong class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li>
							 <a href="#">操作</a>
						</li>
						<li>
							 <a href="#">设置栏目</a>
						</li>
						<li>
							 <a href="#">更多设置</a>
						</li>
						<li class="divider">
						</li>
						<li>
							 <a href="#">分割线</a>
						</li>
					</ul>
				</li>
				<!-- 右侧 -->
				<ul class="nav navbar-nav navbar-right navbar-above-margin">
					<s:if test="#session.user==null">
						<li class="pull-right"><a href="page/loginAndRegister.jsp"><span class="glyphicon glyphicon-log-in"></span>登录</a></li>
					</s:if>
					<s:elseif test="#session.user!=null">
						<li class="pull-right"><a href="note/logout"><span class="glyphicon glyphicon-log-out"></span>退出</a></li>
						<li class="pull-right"><a href="page/loginAndRegister.jsp"><span class="glyphicon glyphicon-user"></span>用户</a></li>
					</s:elseif>
				</ul>
			</ul>
			<!-- 导航栏2  -->
			<%-- <nav class="navbar navbar-default" role="navigation">
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
			</nav> --%>
			<!-- 路径导航  -->
			<ul class="breadcrumb breadcrumb-crumbs">
				<li>
					<a href="#">Home</a>
				</li>
				<li>
					<a href="#">Library</a>
				</li>
				<li class="active">
					Data
				</li>
			</ul>
		</div>
	</div>
	
	<!-- body -->
	<!-- first Dir -->
	<div class="row clearfix">
		<div class="col-md-2 column col-above">
			<!-- <div class="panel-group" id="panel-569164">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <a class="panel-title" data-toggle="collapse" data-parent="#panel-569164" href="#panel-element-933230">Collapsible Group Item #1</a>
					</div>
					<div id="panel-element-933230" class="panel-collapse collapse in">
						<div class="panel-body">
							Anim pariatur cliche...
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						 <a class="panel-title" data-toggle="collapse" data-parent="#panel-569164" href="#panel-element-249110">Collapsible Group Item #2</a>
					</div>
					<div id="panel-element-249110" class="panel-collapse collapse">
						<div class="panel-body">
							Anim pariatur cliche...
						</div>
					</div>
				</div>
			</div> -->
			<div class="list-firstDir">
			<!-- 用户根文件夹 -->
				<!-- <a href="#" class="list-group-item active">1</a> -->
				<s:iterator value="rootFolderList">
					<a href="#" class="list-group-item">${foldername}</a>
				</s:iterator>
			</div>
		</div>
		<div class="col-md-2 column col-above">
			<div class="list-group">
				 <a href="#" class="list-group-item active">Home</a>
				<div class="list-group-item">
					List header
				</div>
				<div class="list-group-item">
					<h4 class="list-group-item-heading">
						List group item heading
					</h4>
					<p class="list-group-item-text">
						...
					</p>
				</div>
				<div class="list-group-item">
					 <span class="badge">14</span> Help
				</div> <a class="list-group-item active"> <span class="badge">14</span> Help</a>
			</div>
		</div>
		<div class="col-md-8 column col-above">
		</div>
	</div>
	
</div>
	
	
<%= session.getAttribute("user") %>

</body>
</html>