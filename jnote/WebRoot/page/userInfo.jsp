<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script src="page/assets/bootstrap-3.3.7/js/tests/vendor/jquery.min.js" type="text/javascript"></script>
	<link href="page/assets/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<script src="page/assets/bootstrap-3.3.7/dist/js/bootstrap.min.js" type="text/javascript"></script>

  </head>
  
  <body>
  	<a class="btn btn-primary" href="note/home">返回</a>
    <%= session.getAttribute("user").toString() %>
    <br>
   <%--  <nav class="navbar-default">
    <ul  class="nav  nav-tabs" id="noteTab" role="tablist">
				<li class="active" role="presentation" >
					 <a href="#home" data-toggle="tab" role="tab" aria-controls="home">云笔记</a>
				</li>
				<li class="" role="presentation">
					 <a href="#share" data-toggle="tab" role="tab"aria-controls="share">云协作</a>
				</li>
				<li class="" role="presentation">
					 <a href="#help" data-toggle="tab" role="tab" aria-controls="help">帮助</a>
				</li>
	</ul>
	</nav>
	<div class="tab-content" id="tabContent">页签content
		<div id="home" class="tab-pane fade active" role="tabpanel">页签1 start
			1
		</div>
		<div id="share" class="tab-pane fade" role="tabpanel">
			2
		</div>
		<div id="help" class="tab-pane fade" role="tabpanel">
			3
		</div>
	</div> --%>
	 <!-- Nav tabs -->
	 
	<div class="container-fluid container-home">
	<div class="navbar-header">
		<a class="navbar-brand" href="javascript:return fasle;">Jnote</a>
	</div>
  	<ul class="nav nav-tabs" role="tablist">
    	<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
    	<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
    	<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li>
    	<li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
  		<ul class="nav navbar-nav navbar-right navbar-above-margin">
			<%--<s:if test="#session.user==null">
				<li class="pull-right"><a href="page/loginAndRegister.jsp"><span class="glyphicon glyphicon-log-in"></span>登录</a></li>
			</s:if> --%>
			<s:elseif test="#session.user!=null">
				<li class="pull-right"><a href="note/logout"><span class="glyphicon glyphicon-log-out"></span>退出</a></li>
				<li class="pull-right"><a href="page/userInfo.jsp"><span class="glyphicon glyphicon-user"></span>${session.user.username}</a></li>
			</s:elseif>
		</ul>
  	</ul>
  	</div>
  <!-- Tab panes -->
  	<div class="tab-content">
   		<div role="tabpanel" class="tab-pane active" id="home">1</div>
    	<div role="tabpanel" class="tab-pane" id="profile">2</div>
    	<div role="tabpanel" class="tab-pane" id="messages">.3</div>
    	<div role="tabpanel" class="tab-pane" id="settings">4</div>
	</div>
	
  </body>
  
</html>
