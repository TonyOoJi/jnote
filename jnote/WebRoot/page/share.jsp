<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分享-${title} </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link href="page/assets/editormd/css/editormd.min.css" rel="stylesheet" type="text/css"></link>
	<link href="page/css/editormd.preview.css" rel="stylesheet" type="text/css"></link>
  </head>
  
  <body style="text-align:center;width:60%;margin:0 auto;">
  	 <br>
  	 <div style="width:100%;height:5%;background:#f5f5f5;">
  	 	<div style="float:right;">
  	 	<img alt="Pic" style="weight:auto;height:100%;" src="${userInfo.headurl}">
  	 	<span style="position:relative;top:-15px;margin-right:15px;">${user.username}</span>
  	 	</div>
  	 </div>
	 <div style="margin:0 auto;">
	 	<h1><font style="color:#aaa">${title}</font></h1>
	 </div>
	 <br>
     <div style="margin:0 auto;" id="editormd-View">
     		${mdHtml}
     </div>
     <%-- 使用editermd来显示样式 --%>
        <script src="page/assets/editormd/js/jquery.min.js"></script>
        <script src="page/assets/editormd/lib/marked.min.js"></script>
        <script src="page/assets/editormd/lib/prettify.min.js"></script>
        
        <script src="page/assets/editormd/lib/raphael.min.js"></script>
        <script src="page/assets/editormd/lib/underscore.min.js"></script>
        <script src="page/assets/editormd/lib/sequence-diagram.min.js"></script>
        <script src="page/assets/editormd/lib/flowchart.min.js"></script>
        <script src="page/assets/editormd/lib/jquery.flowchart.min.js"></script>

        <script src="page/assets/editormd/editormd.js"></script>
        <script type="text/javascript">
            $(function() {
                var editormdView;
                $.get("page/assets/editormd/test.md", function(markdown) {
				    editormdView = editormd.markdownToHTML("editormd-View", {
                        markdown        : markdown ,
                        htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
                        htmlDecode      : "style,script,iframe",  // you can filter tags decode
                        //toc             : false,
                        tocm            : true,    // Using [TOCM]
                        //tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
                        //gfm             : false,
                        //tocDropdown     : true,
                        // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
                        emoji           : true,
                        taskList        : true,
                        tex             : true,  // 默认不解析
                        flowChart       : true,  // 默认不解析
                        sequenceDiagram : true,  // 默认不解析
                    });
                });
            });
        </script>
     
  </body>
</html>
