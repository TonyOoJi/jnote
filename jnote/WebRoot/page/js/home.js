var currentFolderId;//当前目录id
var delFolderId;//需要删除的目录ID
var currentFileId;//当前文件
var delFileId;//需要删除或获取URL的文件ID
var delType;//删除操作的类型file&folder

//启用页签1
//$('#noteTab a[href="#note"]').tab('show');

$(document).ready(function(){
	// 滚动监听
// $('rootFolderList-div').scrollspy({ target: '.navbar-example' });//启用滚动触发事件
	
	/**
	 * 添加根目录节点
	 */
	$("#addNewFolderButton").click(function(){// ajax请求信息在homeaction中写为html的String
// alert($("#newRootFolderName").val());
		var folderName = $("#newRootFolderName").serialize();
		$.ajax({
	            url:'/jnote/ajax/addRootFolder.action',  
	            type:'post',
	            data:{
	            	foldername:$("input[name=newRootFolderName]").val()
// foldername:folderName
	            },
	            dataType:'json',
	            success:function (data) {
	                /*
					 * $.each(data.userList, function(i, value){
					 * $("#allUser").append("<div>输出了：id:"+value.id+", name:
					 * "+value.name+"</div>"); });
					 */
	                /*
					 * $(data.userList).each(function (i, value) {
					 * $("#allUser").append("<div>输出了：id:" + value.id + ",
					 * name: " + value.name + "</div>");
					 */
	            	var d = eval("("+data+")");
	            	$("#rootFolderList-div").empty();
	            	// 回传的list中对象为 String
	            	$(d.list).each(function (i, value) {
	            		$("#rootFolderList-div").append('<a href="javascript:return false;" class="list-group-item select-root glyphicon glyphicon-bookmark a-list" onclick="getChild(this)" value="folder"  name="' +value.folderid+ '">&nbsp' + value.foldername + '</a>');
	            	});
	            	alert(d.result);
	            	// $('#addNewFolderModal').modal('hide');
	            }
		});
	});
	
	/**
	 * 添加子目录节点
	 */
	$("#addChildFolderButton").click(function(){
		$.ajax({
	            url:'/jnote/ajax/addChildFolder.action',
	            type:'post',
	            data:{
	            	foldername:$("input[name=addChildFolderName]").val(),
	            	currentFolderId:currentFolderId,
	            	parentid:currentFolderId
	            },
	            dataType:'json',
	            success:function (data) {
	            	var d = eval("("+data+")");
	            	$("#childList-div").empty();
	            	// 回传的list中对象为 String
	            	$(d.list).each(function (i, value) {
	            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-folder glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" value="folder"  name="' + value.folderid + '">&nbsp' + value.foldername + '</a>');
	            	});
	            	$(d.fileList).each(function (i, value) {
	            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-file glyphicon glyphicon-file a-list" onclick="getMdFile(this)" value="file" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
	            	});
	            	alert(d.result);
	            	// $('#addNewFolderModal').modal('hide');
	            }
		});
	});
	
	/**
	 * 添加文件
	 */
	$("#addFileButton").click(function(){
		$.ajax({
	            url:'/jnote/ajax/addFile.action',
	            type:'post',
	            data:{
	            	fileName:$("input[name=addFileName]").val(),
	            	currentFolderId:currentFolderId,
	            	parentid:currentFolderId
	            },
	            dataType:'json',
	            success:function (data) {
	            	var d = eval("("+data+")");
	            	$("#childList-div").empty();
	            	alert(d.result);
	            	// 回传的list中对象为 String
	            	$(d.list).each(function (i, value) {
	            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-folder glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" value="folder"  name="' + value.folderid + '">&nbsp' + value.foldername + '</a>');
	            	});
	            	$(d.fileList).each(function (i, value) {
	            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-file glyphicon glyphicon-file a-list" onclick="getMdFile(this)" value="file" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
	            	});
	            }
		});
	});

	$("#saveFilebtn").click(function(){
//		mdEditor.setCursor({line:1, ch:1});
//		mdEditor.insertValue("#qwedascsa");
//		alert(mdEditor.getMarkdown());    // 获取 Markdown
//		alert(mdEditor.getHTML());
//		alert($("input[name=mdTitle]").val());
//		$("input[name=mdTitle]").val("asdasdsad");
		$.ajax({
			 	url:'/jnote/ajax/updataFile.action',
	            type:'post',
	            data:{
	            	mdFileId:currentFileId,
	            	fileTitle:$("input[name=mdTitle]").val(),
	            	content:mdEditor.getMarkdown(),
	            	mdhtml:mdEditor.getHTML()
	            },
	            dataType:'json',
	            success:function (data) {
	            	var d = eval("("+data+")");
	            	alert(d.result);
	            }//success	
		});//ajax
	});//click
	
	/**
	 * 删除子目录（子文件夹&文件）
	 */
//	var menu = new BootstrapMenu('.list-group-item.select-folder', {
		var menu = new BootstrapMenu('.list-group-item.select-child', {
		  actions: [{
		      name: '分享',
		      onClick: function() {
		    	  if(delType == "folder"){
		    		  alert("不支持文件夹分享");
		    	  }else{
		    		  //
		    		  //
		    		  //此处添加ajax post至分享action
		    		  //
		    		  $.ajax({
						 	url:'/jnote/ajax/buildShareUrl.action',
				            type:'post',
				            data:{
				            	fileId:delFileId
				            },
				            dataType:'json',
				            success:function (data) {
				            	var d = eval("("+data+")");
				            	// 回传的list中对象为 String
				            	alert(d.shareUrl + "      请将url复制到浏览器地址栏访问");
				            }//success	
			    	  });//ajax
		    	  }
//		    	alert(delFolderId);
//		        toastr.info("'Action' clicked!");
		      }
		    }, {
		      name: '删除',
		      onClick: function() {
		    	  if(delType == "folder"){//删除类型是文件夹
		    		  
		    		  //修改：：：：：删除子目录文件夹时应刷新全部，注意图标
		    		  //删除根目录时另写
		    		  //deleteChildFolder写
		    		  //目录的更新
		    		  $.ajax({
						 	url:'/jnote/ajax/deleteChildFolder.action',
				            type:'post',
				            data:{
				            	folderId:delFolderId,
				            	parentid:currentFolderId
				            },
				            dataType:'json',
				            success:function (data) {
				            	var d = eval("("+data+")");
				            	// 回传的list中对象为 String
				            	$("#childList-div").empty();
				            	$(d.list).each(function (i, value) {
				            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-folder glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" value="folder"  name="' +value.folderid+ '">&nbsp' + value.foldername + '</a>');
				            	});
				            	$(d.fileList).each(function (i, value) {
				            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-file glyphicon glyphicon-file a-list" onclick="getMdFile(this)" value="file" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
				            	});
				            	alert(d.result);
				            }//success	
			    	  });//ajax
		    	  }else{		//删除类型是文件
		    		  $.ajax({
						 	url:'/jnote/ajax/deleteFile.action',
				            type:'post',
				            data:{
				            	fileId:delFileId,
				            	parentid:currentFolderId
				            },
				            dataType:'json',
				            success:function (data) {
				            	var d = eval("("+data+")");
				            	$("#childList-div").empty();
				            	// 回传的list中对象为 String
				            	$(d.list).each(function (i, value) {
				            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-folder glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" value="folder"  name="' +value.folderid+ '">&nbsp' + value.foldername + '</a>');
				            	});
				            	$(d.fileList).each(function (i, value) {
				            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-file glyphicon glyphicon-file a-list" onclick="getMdFile(this)" value="file" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
				            	});
				            	alert(d.result);
				            }//success	
			    	  });//ajax
		    	  }
//		        toastr.info("'Another action' clicked!");
		      }
		    }, {
		      name: '打开',
		      onClick: function() {
		        toastr.info("'A third action' clicked!");
		      }
		  }]
		});
		/**
		 * 删除根目录
		 */
		var rootmenu = new BootstrapMenu('.list-group-item.select-root', {
			  actions: [{
			      name: '添加',
			      onClick: function() {
			    	alert(delType);
			    	alert(delFolderId);
//			        toastr.info("'Action' clicked!");
			      }
			    }, {
			      name: '删除',
			      onClick: function() {
			    	  if(delType == "folder"){//删除类型是文件夹
			    		  $.ajax({
							 	url:'/jnote/ajax/deleteFolder.action',
					            type:'post',
					            data:{
					            	folderId:delFolderId
					            },
					            dataType:'json',
					            success:function (data) {
					            	var d = eval("("+data+")");
					            	$("#rootFolderList-div").empty();
					            	// 回传的list中对象为 String
					            	$(d.list).each(function (i, value) {
					            		$("#rootFolderList-div").append('<a href="javascript:return false;" class="list-group-item select-root glyphicon glyphicon-bookmark a-list" onclick="getChild(this)" value="folder"  name="' +value.folderid+ '">&nbsp' + value.foldername + '</a>');
					            	});
					            	$("#childList-div").empty();
					            	alert(d.result);
					            }//success	
				    	  });//ajax
			    	  }
//			        toastr.info("'Another action' clicked!");
			      }
			    }]
			});
		
});//ready

function getChild(obj){
	var folderid = obj.name;
	currentFolderId = folderid;
//	alert(folderid);
	$.ajax({
        url:'/jnote/ajax/getChildListToJsonByFolderid.action',  
        type:'post',
        data:{
        	parentid:folderid
        	// foldername:folderName
        },
        dataType:'json',
        success:function (data) {
        	var d = eval("("+data+")");
//        	alert(d);
        	$("#childList-div").empty();
        	// 回传的list中对象为 String
        	$(d.list).each(function (i, value) {
        		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-folder glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" value="folder" name="' +value.folderid+ '">&nbsp' + value.foldername + '</a>');
        		/*
        		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" name=" ');
        		$("#childList-div").append( value.folderid );
        		$("#childList-div").append(' ">&nbsp ');
        		$("#childList-div").append( value.foldername );
        		$("#childList-div").append( '</a>' );
        		*/
//        		$("#childList-div").append();
        	});
        	$(d.fileList).each(function (i, value) {
        		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-file glyphicon glyphicon-file a-list" onclick="getMdFile(this)" value="file" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
        	
        	});
//        	alert(d.result);
        }
	});
}
/**
 * get mdFIle 
 * the param obj.name is mdFIle's id
 * @param obj
 */
function getMdFile(obj){
	//mdEditer.clear();	//清空操作
	mdEditor.cm.setValue("");//源码中参数为cm,设置cm的值可以达到清空效果
	var fileId = obj.name;
//	alert(fileId);
	currentFileId = fileId;
	$.ajax({
		url:'/jnote/ajax/getMdFile.action',  
        type:'post',
        data:{
        	mdFileId:fileId
        	// foldername:folderName
        },
        dataType:'json',
        success:function (data) {
        	var d = eval("("+data+")");
        	var file = d.file;
        	// 回传的list中对象为 String
        	$("input[name=mdTitle]").val(file.title);//将标题添加
        	
        	mdEditor.setCursor({line:1, ch:1});//设置光标到1，1位置
//        	alert(file.content);
    		mdEditor.insertValue(file.content);//设置文本内容
//    		alert(d.result);//
        }
	});
}
/**
 * 
 * @param evt
 * @returns
 */
function getTagName(evt){
	var ex,objx,tn;
	ex=evt||window.event;
	objx = ex.srcElement || ex.target || ex; 
	delFodlerId = objx.name;
}
/**
 * 显示右键菜单
 * @returns
 */
function showmenu(){  
//    document.getElementById("help").onmousedown = function(e){  
	onmousedown = function(e){
        if(e.which == 3){  
            if (!e) {  
                e = window.event;  
            }else {  
                e.srcElement = e.target;  
            }  
            if(e.srcElement.innerHTML != ""){  
            	delFolderId = delObj.name;//记录需要删除的ID 
                document.getElementById("rightmenu").style.left = e.clientX + "px";  
                document.getElementById("rightmenu").style.top = e.clientY + "px";  
                document.getElementById("rightmenu").style.display = "block";  
            }  
        }  
    }  
} 
/**
 * 隐藏右键菜单
 * @returns
 */
function hiddenrightmenu(){  
    document.getElementById("rightmenu").style.display = "none";  
}
