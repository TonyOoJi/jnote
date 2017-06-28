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

<title>Jnote可在线编辑markdown笔记</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="page/assets/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="page/css/home.css" rel="stylesheet" type="text/css"></link>
<link href="page/assets/editormd/css/editormd.min.css" rel="stylesheet" type="text/css"></link>
<script src="page/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="page/js/home.js" type="text/javascript"></script>
<script src="page/assets/bootstrap-3.3.7/dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="page/assets/editormd/editormd.min.js" type="text/javascript"></script>
<script src="page/assets/bootstrap-3.3.7/js/BootstrapMenu.min.js" type="text/javascript"></script>

<script>
//多向弹出框的启用
/* $(function () { 
	$("[data-toggle='popover']").popover();
}); */
</script>
<script type="text/javascript">
/* js配置markdown编辑器 */
				var mdEditor;
            	/* $(function() {
                	testEditor = editormd("editor-editormd", {
                   		width   : "100%",
                    	height  : 640,
                    	syncScrolling : "single",
                    	path    : "./page/assets/editormd/lib/"
                	}); */
                /*
                // or
                testEditor = editormd({
                    id      : "test-editormd",
                    width   : "90%",
                    height  : 640,
                    path    : "../lib/"
                });
                */
                
            	/* }); */
            	$(function() {
            	 $.get('page/assets/editormd/test.md', function(md){
                    mdEditor = editormd("editor-editormd", {
                        width: "100%",
                        height: "77%",
                        path : './page/assets/editormd/lib/',
                        //theme : "dark",
                        //previewTheme : "dark",
                        //editorTheme : "pastel-on-dark",
                        markdown : md,
                        codeFold : true,
                        //syncScrolling : false,
                        saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
                        searchReplace : true,
                        //watch : false,                // 关闭实时预览
                        htmlDecode : "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启    
                        //toolbar  : false,             //关闭工具栏
                        //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
                        emoji : true,
                        taskList : true,
                        tocm            : true,         // Using [TOCM]
                        tex : true,                   // 开启科学公式TeX语言支持，默认关闭
                        flowChart : true,             // 开启流程图支持，默认关闭
                        sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
                        //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
                        //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
                        //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
                        //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
                        //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
                        imageUpload : true,
                        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                        imageUploadURL : "./php/upload.php",
                        onload : function() {
                            console.log('onload', this);
                            //this.fullscreen();
                            //this.unwatch();
                            //this.watch().fullscreen();

                            //this.setMarkdown("#PHP");
                            //this.width("100%");
                            //this.height(480);
                            //this.resize("100%", 640);
                        }
                    });
                });

                $("#goto-line-btn").bind("click", function(){
                    mdEditor.gotoLine(90);
                });
                
                $("#show-btn").bind('click', function(){
                    mdEditor.show();
                });
                
                $("#hide-btn").bind('click', function(){
                    mdEditor.hide();
                });
                
                $("#get-md-btn").bind('click', function(){
                    alert(mdEditor.getMarkdown());
                });
                
                $("#get-html-btn").bind('click', function() {
                    alert(mdEditor.getHTML());
                });                
                
                $("#watch-btn").bind('click', function() {
                    mdEditor.watch();
                });                 
                
                $("#unwatch-btn").bind('click', function() {
                    mdEditor.unwatch();
                });              
                
                $("#preview-btn").bind('click', function() {
                    mdEditor.previewing();
                });
                
                $("#fullscreen-btn").bind('click', function() {
                    mdEditor.fullscreen();
                });
                
                $("#show-toolbar-btn").bind('click', function() {
                    mdEditor.showToolbar();
                });
                
                $("#close-toolbar-btn").bind('click', function() {
                    mdEditor.hideToolbar();
                });
                
                $("#toc-menu-btn").click(function(){
                    mdEditor.config({
                        tocDropdown   : true,
                        tocTitle      : "目录 Table of Contents",
                    });
                });
                
                $("#toc-default-btn").click(function() {
                    mdEditor.config("tocDropdown", false);
                });
            });
/**鼠标悬停获取name属性中的ID值和类型*/
function getFId(evt){
	var ex,objx,tn;
	ex=evt||window.event;
	objx = ex.srcElement || ex.target || ex;
	delType = objx.getAttribute("value");
	folderOrFileName = objx.innerText; //innerHTML
	//alert(folderOrFileName);
	if(delType == "folder"){
		delFolderId = objx.name;
	}else {
		delFileId = objx.name;
	}
}

</script>

</head>
<body class="body">
<div class="container-fluid container-home">
<%-- 导航栏  --%>
	 <div class="row clearfix">
		<div class="col-md-12 column"> 
			<div class="navbar-header">
				<a class="navbar-brand" href="javascript:return fasle;">Jnote</a>
			</div>
			<ul class="nav nav-tabs" role="tablist">
    			<li role="presentation" class="active">
    				<a href="#note" aria-controls="note" role="tab" data-toggle="tab">云笔记</a>
    			</li>
    			<li role="presentation">
    				<a href="#share" aria-controls="share" role="tab" data-toggle="tab" id="shareLib">云分享</a>
    			</li>
    			<li role="presentation">
    				<a href="#help" aria-controls="help" role="tab" data-toggle="tab">示例</a>
    			</li>
				<%-- <li class="dropdown pull-right"> --%>
				<%-- 
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
							 <a href="#">更多</a>
						</li>
					</ul>
				</li>
				--%>
				<%-- 右侧 --%>
				<ul class="nav navbar-nav navbar-right navbar-above-margin">
					<s:if test="#session.user==null">
						<li class="pull-right"><a href="page/loginAndRegister.jsp"><span class="glyphicon glyphicon-log-in"></span>登录</a></li>
					</s:if>
					<s:elseif test="#session.user!=null">
						<li class="pull-right"><a href="note/logout"><span class="glyphicon glyphicon-log-out"></span>退出</a></li>
						<li class="pull-right"><a href="note/getUserInfo"><span class="glyphicon glyphicon-user"></span>${session.user.username}</a></li>
					</s:elseif>
				</ul>
			</ul>
			
		</div><%-- 栅格结束 --%>
	</div><%-- row结束 --%>
	
	<%-- body --%>
	<%-- root Dir --%>
	<div class="tab-content" id="tabContent"><%--页签content --%>
	<div id="note" role="tabpanel" class="tab-pane active"><%--页签1 start --%>
	<div>
		<%-- 路径导航  --%>
		<ul id="route" class="breadcrumb breadcrumb-crumbs">
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
	<div class="row clearfix">
		<div class="col-md-2 column col-above">
			<div class="list-firstDir">
			<%-- 用户根文件夹 --%>
				<%-- <a href="#" class="list-group-item active">1</a> --%>
				<%-- <button type="button" class="btn btn-warning btn-block btn-backg" title='' data-container="body" 
					data-toggle="popover" data-placement="bottom" data-html="true"
					data-content=' 名称<input type=text name="newRootFolderName" id="newRootFolderName"><br><button type=button class="btn" id="addNewFolderButton">添加</button> '>
					添加分类目录
				</button> --%>
				<%-- 主目录的添加与删除下拉 --%>
				<div class="dropdown">
    				<button type="button" class="btn dropdown-toggle btn-block btn-backg" id="dropdownMenu1" data-toggle="dropdown">
        				分类<span class="caret"></span>
    				</button>
    				<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
       					<li role="presentation">
            				<button class="btn btn-primary btn-lg btn-block btn-backg" data-toggle="modal" data-target="#addNewFolderModal">
								添加分类目录
							</button>
        				</li>
        				<li role="presentation">
            				<button class="btn btn-primary btn-lg btn-block btn-backg" data-toggle="modal" data-target="#delFolderModal">
								删除目录
							</button>
        				</li>
    				</ul>
				</div>
				<%-- 弹出框 --%>
				<div class="modal fade" id="addNewFolderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									添加分类目录
								</h4>
							</div>
							<div class="modal-body">
									<input type=text name="newRootFolderName" id="newRootFolderName">
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
								<button type=button class="btn btn-primary" id="addNewFolderButton">添加</button>
							</div>
						</div><%-- /.modal-content --%>
					</div><%-- /.modal --%>
				</div><%-- 弹出框结束 --%>
				<%-- <a href="" id="addRootFolder" class="list-group-item active">添加分类</a> --%>
				<%-- <ul class="list-group"></ul> --%>
				<div data-spy="scroll" data-target="#myScrollspy" data-offset="0" id="rootFolderList-div" class="rootFolderList-div"  onmouseover="javascript:getFId(event);">
					<s:iterator value="rootFolderList">
						<a href="javascript:return false;" class="list-group-item select-root glyphicon glyphicon-bookmark a-list" onclick="RootGetChild(this)" value="folder" name="${folderid}">&nbsp${foldername}</a>
					</s:iterator>
				</div>
			</div>
		</div>
		<div class="col-md-2 column col-above">
			<%-- 子目录 --%>
			<div class="list-firstDir">
			<%-- 子目录 --%>
				<%-- 子目录的添加与新建文件下拉 --%>
				<div class="dropdown">
    				<button type="button" class="btn dropdown-toggle btn-block btn-backg" id="dropdownMenu1" data-toggle="dropdown">
        				文件目录<span class="caret"></span>
    				</button>
    				<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
       					<li role="presentation">
            				<button class="btn btn-primary btn-lg btn-block btn-backg" data-toggle="modal" data-target="#addChildFolderModal">
								添加子目录
							</button>
        				</li>
        				<li role="presentation">
            				<button class="btn btn-primary btn-lg btn-block btn-backg" data-toggle="modal" data-target="#addFileModal">
								新建文件
							</button>
        				</li>
    				</ul>
				</div>
				<%-- 弹出框 --%>
				<div class="modal fade" id="addChildFolderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									添加子目录
								</h4>
							</div>
							<div class="modal-body">
								<input type=text name="addChildFolderName" id="addChildFolderName">
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
								<button type=button class="btn btn-primary" id="addChildFolderButton">添加</button>
							</div>
						</div><%-- /.modal-content --%>
					</div><%-- /.modal --%>
				</div><%-- 弹出框结束 --%>
				<%-- 新建文件弹框 --%>
				<div class="modal fade" id="addFileModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									添加文件
								</h4>
							</div>
							<div class="modal-body">
									<input type=text name="addFileName" id="addFileName">
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
								<button type=button class="btn btn-primary" id="addFileButton">添加</button>
							</div>
						</div><%-- /.modal-content --%>
					</div><%-- /.modal --%>
				</div><%-- 新建文件弹出框结束 --%>
				
				<%-- <a href="" id="addRootFolder" class="list-group-item active">添加分类</a> --%>
				<%-- <ul class="list-group"></ul> --%>
				<%-- 子目录及文件 --%>
				<div data-spy="scroll" data-target="#myScrollspy" data-offset="0"  style="" id="childList-div" class="childList-div" onmouseover="javascript:getFId(event);">
					<%-- <s:iterator value="childFolderList">
						<a href="#" class="list-group-item glyphicon glyphicon-folder-close a-list" name="${folderid}">&nbsp${foldername}</a>
					</s:iterator>
					<s:iterator value="fileList">
						<a href="#" class="list-group-item a-list" name="${mdfileid}">&nbsp${filename}</a>
					</s:iterator> --%>
				</div>
			</div>
		</div>
		<div class="col-md-8 column col-above">
			<%-- 标题 --%>
			<div id="mdTitle">
				<div class="input-group">
					<span class="input-group-addon">
                       	标题
                    </span>
                    	<input type=text value="" class="form-control md-title-text" name="mdTitle" id="mdTitle">
                    <span class="input-group-btn">
                        <button class="btn btn-default glyphicon glyphicon-floppy-disk" type="button" id="saveFilebtn">保存</button>
                    </span>
                </div><%-- /input-group --%>
			</div>
			<%-- markdown编辑区域 --%>
			<div id="editor-editormd">
				<textarea style="display:none;" id="mdTextArea"></textarea>
			</div>
		</div>
	</div><%-- row结束 --%>
	</div><%--页签1 end --%>
	
	<div role="tabpanel" class="tab-pane" id="share"><%--页签2 start --%>
		<!-- 左侧列表 -->
		<div id="tab2-left" class="tab2-left" data-spy="scroll" data-target="#myScrollspy" data-offset="0" onmouseover="javascript:getFId(event);">
			<s:iterator value="sharedList">
				<a href="javascript:return false;" class="list-group-item share-list glyphicon glyphicon-file a-list" onclick="getContent(this)" value="file" name="${mdfileid}">&nbsp${filename}</a>
			</s:iterator>
		</div><!-- 左侧div显示列表 -->
		
		<!-- 右侧内容 -->
		<div id="tab2-right" class="tab2-right">
			<div style="text-align:center;width:80%;margin:0 auto;"><br>
	 		<div style="margin:0 auto;">
	 			<h1><font style="color:#aaa" id="lib2-title"></font></h1>
	 		</div><br>
     		<div style="margin:0 auto;" id="editormd-View"></div>
     		<%-- 使用editermd来显示样式 --%>
        		<%-- <script src="page/assets/editormd/js/jquery.min.js"></script>
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
				    		ditormdView = editormd.markdownToHTML("editormd-View", {
                        		markdown        : markdown ,
                        		htmlDecode      : true,       // 开启 HTML 标签解析，默认不开启
                        		htmlDecode      : "style,script,iframe",  // you can filter tags decode
                        		tocm            : true,    // Using [TOCM]
                        		emoji           : true,
                        		taskList        : true,
                        		tex             : true,  // 默认不解析
                        		flowChart       : true,  // 默认不解析
                        		sequenceDiagram : true,  // 默认不解析
                    		});
                		});
            		});
        		</script> --%>
     		</div><!-- 文件显示区域，只使用editermd编辑器的样式功能 -->
		</div><!-- 右侧div结束 -->
	</div><%--页签2 end --%>
	
	<div role="tabpanel" class="tab-pane" id="help">
		<%-- <iframe src="/test.html"></iframe> --%>
		<jsp:include page="test.html"></jsp:include>
	</div>
	</div><%--tab content end --%>
</div><%-- container-fluid结束 --%>

<%-- <span>测试session</span> --%>
<%-- <%= session.getAttribute("user") %> --%>
<%-- <input type="text" id="test"/> --%>
	
</body>
</html>