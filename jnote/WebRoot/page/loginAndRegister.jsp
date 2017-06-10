<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>welcome to jnote</title>
<script type="text/javascript" src="page/js/jquery-1.9.0.min.js"></script>
<script type='text/javascript' src='/jnote/dwr/interface/check.js'></script>
<script type='text/javascript' src='/jnote/dwr/engine.js'></script>
<script type='text/javascript' src='/jnote/dwr/util.js'></script>
<script type="text/javascript" src='page/js/login.js'></script>
<link href="page/css/login2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
		//dwr ajax框架实现检验用户名是否注册
		function checkUser() {
			var username = $("#user").val();
			check.checkUser(username, onData);
		}
		function onData(data) {
			if (data == true) {
				$('#user').focus().css({
					border: "1px solid red",
					boxShadow: "0 0 2px red"
				});
				$("#userCue").html("<font color='red'><b>×用户名被占用了</b></font>");
			} else{
				$('#user').css({
					border: "1px solid #D7D7D7",
					boxShadow: "none"
				});
				$("#userCue").html("<font color='green'><b>√用户名可用</b></font>");
			}
		}
</script>

</head>
<body>
<h1>Jnote 开始记录吧^-^<sup></sup></h1>
<s:if test="#session.user!=null"><% session.invalidate(); %></s:if>
<div class="login" style="margin-top:50px;">
    <!--  -->
    <div class="header">
        <div class="switch" id="switch">
     	    <a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a>
			<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>
    
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">
		<!--登录-->
        <div class="web_login" id="web_login">
		<div class="login-box">

			<div class="login_form">
				<form action="note/login" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
					<input type="hidden" name="did" value="0"/>
               		<input type="hidden" name="to" value="log"/>
                	<div class="uinArea" id="uinArea">
                		<label class="input-tips" for="u">帐号：</label>
                		<div class="inputOuter" id="uArea">
                   			<input type="text" id="u" name="username" class="inputstyle"/>
              			</div>
                	</div>
               		<div class="pwdArea" id="pwdArea">
               			<label class="input-tips" for="p">密码：</label> 
               			<div class="inputOuter" id="pArea">
                   			<input type="password" id="p" name="password" class="inputstyle"/>
                		</div>
              		</div>
                	<div style="padding-left:50px;margin-top:20px;">
                		<input type="submit" value="登 录" style="width:150px;" class="button_blue"/>
                	</div>
              	</form>
           </div>
           
		</div>
		</div>
  	</div>
  	<!--登录end-->
  	
 	<!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">
    	<div class="web_login">
    		<form name="form2" id="regUser" accept-charset="utf-8"  action="note/register" method="post">
	      		<input type="hidden" name="to" value="reg"/>
				<input type="hidden" name="did" value="0"/>
        		<ul class="reg_form" id="reg-ul">
        		<div id="userCue" class="cue">用户名不少于4字符，密码大于6位</div>
                	<li>
                 	   	<label for="user"  class="input-tips2">用户名：</label>
                	    <div class="inputOuter2">
                        	<input type="text" id="user" name="username" onblur="checkUser()" maxlength="16" class="inputstyle2"/>
                    	</div>
               	 	</li>
                
                	<li>
                	<label for="passwd" class="input-tips2">密码：</label>
                    	<div class="inputOuter2">
                        	<input type="password" id="passwd"  name="password" maxlength="16" class="inputstyle2"/>
                    	</div>
               	 	</li>
               	 	
                	<li>
                		<label for="passwd2" class="input-tips2">确认密码：</label>
                    	<div class="inputOuter2">
                        <input type="password" id="passwd2" name="repassword" maxlength="16" class="inputstyle2" />
                    	</div>  
                	</li>
                	
                	<!-- <li>
                		<label for="email" class="input-tips2">邮箱：</label>
                    	<div class="inputOuter2">
                        <input type="text" id="email" name="email" maxlength="30" class="inputstyle2" />
                    	</div>  
                	</li> -->
                
                	<!-- <li>
                 		<label for="qq" class="input-tips2">QQ：</label>
                    	<div class="inputOuter2">
                        	<input type="text" id="qq" name="qq" maxlength="10" class="inputstyle2"/>
                    	</div>
                	</li> -->
                
                	<li>
                    	<div class="inputArea">
                        	<input type="button" id="reg"  style="margin-top:10px;margin-left:85px;width:150px;" class="button_blue" value="注册"/> 
                        	<!-- <a href="#" class="zcxy" target="_blank">注册协议</a> -->
                    	</div>  
                	</li>
                	<div class="cl"></div>
            	</ul>
            </form>     
    	</div>
    </div>
    <!--注册end-->
</div>



<!-- 
<span>register</span>
<form name=regist action="/jnote/register" method="post">
		username:<input type=text name=username id=username value="${username}"> <span id="msg"></span><br>
		password:<input type=password name=password value="${password}"><br>
		password again:<input type=password name=repassword value="${repassword}"><br> 	
		<s:fielderror></s:fielderror>
		<input type=submit value=submit>
</form>
<span>login</span>
<form name=regist action="/jnote/login" method="post">
		username:<input type=text name=username id=username value="${username}"> <span id="msg"></span><br>
		password:<input type=password name=password value="${password}"><br>	
		<input type=submit value=submit>
</form>

 -->
</body>
</html>