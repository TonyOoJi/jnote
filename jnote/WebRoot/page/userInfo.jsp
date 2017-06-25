<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户信息</title>
    
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
	<script type="text/javascript"> 
	//JS控制文件的上传，包括文件格式和大小
		var isIE = /msie/i.test(navigator.userAgent) && !window.opera; 
		function fileChange(target,id) { 
			var fileSize = 0; 
			var filetypes =[".jpg",".png",".gif"]; 
			var filepath = target.value; 
			var filemaxsize = 1024*2;//2M 
			if(filepath){ 
				var isnext = false; 
				var fileend = filepath.substring(filepath.indexOf(".")); 
				if(filetypes && filetypes.length>0){ 
					for(var i =0; i<filetypes.length;i++){ 
						if(filetypes[i]==fileend){ 
							isnext = true; 
							break; 
						} 
					} 
				} 
				if(!isnext){ 
					alert("不接受此文件类型！"); 
					target.value =""; 
					return false; 
				} 
			}else{ 
				return false; 
			} 
			if (isIE && !target.files) { 
				var filePath = target.value; 
				var fileSystem = new ActiveXObject("Scripting.FileSystemObject"); 
				if(!fileSystem.FileExists(filePath)){ 
					alert("附件不存在，请重新输入！"); 
					return false; 
				} 
				var file = fileSystem.GetFile (filePath); 
				fileSize = file.Size; 
			} else { 
				fileSize = target.files[0].size; 
			} 
			var size = fileSize / 1024; 
			if(size>filemaxsize){ 
				alert("附件大小不能大于"+filemaxsize/1024+"M！"); 
				target.value =""; 
				return false; 
			} 
		} 
</script> 
  </head>
  
  <body>
  	<a class="btn btn-primary" href="note/home">返回</a>
    <br>
    <br>
    <img alt="pic" src="${userInfoExist.headurl}" style="width:100px;height:100px;">
	<form action="note/uploadFile" method="post" enctype="multipart/form-data">
    <!--文件域-->
    <input type="file" name="upload" onchange="fileChange(this);"/><input type="submit" value="上传">
  	</form>
  	<span>不要忘记点击上传哦。</span>
	<br>
	<br>
	<br>
	<form action="note/userinfo" method="post">
		用户手机：<input type="text" name="tel" id="tel" value="${userInfoExist.tel}"><br>
		用户邮箱：<input type="text" name="email" id="email" value="${userInfoExist.email}"><br>
		<input type="submit" value="提交">
	</form>
  </body>
  
</html>
