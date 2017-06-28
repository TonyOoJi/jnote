var currentFolderId;//当前目录id
var delFolderId;//需要删除的目录ID
var currentFileId;//当前文件
var delFileId;//需要删除或获取URL的文件ID
var delType;//删除操作的类型file&folder
var folderOrFileName;//文件夹或者文件名
var routeStacks = new Array();//路径栈
Array.prototype.contains = function ( obj ) {
	for(var i = 0;i<this.length;i++){
		if (this[i].id == obj.id && this[i].type == obj.type) return true;
	}
	return false;
}
//移除标识符后面的所有元素（与名称相同的元素之后的所有元素）
Array.prototype.containsAndPopHinder = function ( obj ) {
	var identifier = 0;
	for(var i = 0;i<routeStacks.length;i++){
		if (this[i].id == obj.id && this[i].type == obj.type) {
			identifier = i;
		}
	}
	for(var i =routeStacks.length-1;i>=0;i--){
		if(i > identifier) {
//			alert("pop:"+this[i].name);
			routeStacks.pop(this[i]);
		}
	}
	return false;
}
//路径节点对象
function routeNode(id,type,name){
	this.id = id;
	this.type = type;
	this.name = name;
}
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
	            		$("#rootFolderList-div").append('<a href="javascript:return false;" class="list-group-item select-root glyphicon glyphicon-bookmark a-list" onclick="RootGetChild(this)" value="folder"  name="' +value.folderid+ '">&nbsp' + value.foldername + '</a>');
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

	/**
	 * save file
	 */
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
	 * 分享删除子目录（子文件夹&文件）
	 */
//		var menu = new BootstrapMenu('.list-group-item.select-folder', {
		var menu = new BootstrapMenu('.list-group-item.select-child', {
		  actions: [{
		      name: '<div class="glyphicon glyphicon-share">分享</div>',
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
				            	//http://10.127.169.122/jnote/
				            	//localhost:8081/jnote/
				            	alert("localhost:8081/jnote/" + d.shareUrl + "      请将url复制到浏览器地址栏访问");
				            }//success	
			    	  });//ajax
		    	  }
//		    	alert(delFolderId);
//		        toastr.info("'Action' clicked!");
		      }
		    }, {
		      name: '<div class="glyphicon glyphicon-trash">删除</div>',
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
		    }]
		});
		/**
		 * 删除根目录
		 */
		var rootmenu = new BootstrapMenu('.list-group-item.select-root', {
			  actions: [ {
			      name: '<div class="glyphicon glyphicon-trash">删除</div>',
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
		
		/**
		 * 分享列表的右键
		 */
		var sharemenu = new BootstrapMenu('.list-group-item.share-list', {
			  actions: [{
			      name: '<div class="glyphicon glyphicon-share">获取链接</div>',
			      onClick: function() {
					alert("localhost:8081/jnote/note/share?fileid=" + delFileId + "      请将url复制到浏览器地址栏访问");
			      }
			    }, {
			      name: '<div class="glyphicon glyphicon-trash">不再分享</div>',
			      onClick: function() {
			    		  $.ajax({
							 	url:'/jnote/ajax/deleteFileShare.action',
					            type:'post',
					            data:{
					            	fileId:delFileId,
					            },
					            dataType:'json',
					            success:function (data) {
					            	var d = eval("("+data+")");
					            	$("#tab2-left").empty();
					            	// 回传的list中对象为 String
					            	$(d.fileList).each(function (i, value) {
					            		$("#tab2-left").append('<a href="javascript:return false;" class="list-group-item share-list glyphicon glyphicon-file a-list" onclick="getContent(this)" value="file" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
					            	});
					            	alert(d.result);
					            }//success	
				    	  });//ajax
//			        toastr.info("'Another action' clicked!");
			      }
			    }]
			});
		
		/*
		 * the top row of tablist -- sharelib,show the second tabpanel with you shared files
		 */
		$("#shareLib").click(function(){
		 	$.ajax({
			 	url:'/jnote/ajax/getShareFiles.action',
	            type:'post',
	            data:{
	            },
	            dataType:'json',
	            success:function (data) {
	            	var d = eval("("+data+")");
	            	$("#tab2-left").empty();
	            	// 回传的list中对象为 String
	            	$(d.fileList).each(function (i, value) {
	            		$("#tab2-left").append('<a href="javascript:return false;" class="list-group-item share-list glyphicon glyphicon-file a-list" onclick="getContent(this)" value="file" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
	            	});
	            }//success	
		 	});//ajax
		});
		 
});//ready

/**
 * click the route button it will back to Previous Catalog
 * @param obj
 */
function routeBack(obj){
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
        	//加入路径导航栈
        	var newNode = new routeNode(folderid,"folder",folderOrFileName);
//        	alert(routeStacks.contains(newNode));
        	routeStacks.containsAndPopHinder(newNode);
//			alert(routeStacks[routeStacks.length-1].name);        	
        	//路径导航的刷新
			$('#route').empty();
			for(var i = 0;i<routeStacks.length;i++){
				$('#route').append('<li><a href="javascript:return false;" onclick="routeBack(this)" name="' + routeStacks[i].id + '">' + routeStacks[i].name + '</a></li>');
			}
			// 回传的list中对象为 String
    		$("#childList-div").empty();
        	$(d.list).each(function (i, value) {
        		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-folder glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" value="folder" name="' +value.folderid+ '">&nbsp' + value.foldername + '</a>');
        	});
        	$(d.fileList).each(function (i, value) {
        		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-file glyphicon glyphicon-file a-list" onclick="getMdFile(this)" value="file" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
        	});
//        	alert(d.result);
        }
	});
}

/**
 * get child list with folder and file lists in tab1's second column list
 * add onclick folder node to routeStacks
 * this method is rootList's method
 * @param obj
 */
function RootGetChild(obj){
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
        	//加入路径导航栈
        	var newNode = new routeNode(folderid,"folder",folderOrFileName);
//        	alert(routeStacks.contains(newNode));
        	routeStacks.splice(0, routeStacks.length);
			routeStacks.push(newNode);
//			alert(routeStacks[routeStacks.length-1].name);        	
        	//路径导航的刷新
			$('#route').empty();
			for(var i = 0;i<routeStacks.length;i++){
				$('#route').append('<li><a href="javascript:return false;" onclick="routeBack(this)" name="' + routeStacks[i].id + '">' + routeStacks[i].name + '</a></li>');
			}
			// 回传的list中对象为 String
    		$("#childList-div").empty();
        	$(d.list).each(function (i, value) {
        		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-folder glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" value="folder" name="' +value.folderid+ '">&nbsp' + value.foldername + '</a>');
        	});
        	$(d.fileList).each(function (i, value) {
        		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-file glyphicon glyphicon-file a-list" onclick="getMdFile(this)" value="file" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
        	});
//        	alert(d.result);
        }
	});
}

/**
 * get child list with folder and file lists in tab1's second column list
 * add onclick folder node to routeStacks
 * @param obj
 */
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
        	//加入路径导航栈
        	var newNode = new routeNode(folderid,"folder",folderOrFileName);
        	//最后一个为文件时将其从栈中弹出再添加新的结点
        	if(routeStacks[routeStacks.length-1].type == "file"){
        		routeStacks.pop(routeStacks[routeStacks.length-1]);
        		routeStacks.push(newNode);
        	} else {
        		routeStacks.push(newNode);
        	}
        	//路径导航的刷新
			$('#route').empty();
			for(var i = 0;i<routeStacks.length;i++){
				$('#route').append('<li><a href="javascript:return false;" onclick="routeBack(this)" name="' + routeStacks[i].id + '">' + routeStacks[i].name + '</a></li>');
			}
        	// 回传的list中对象为 String
        	$(d.list).each(function (i, value) {
        		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-folder glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" value="folder" name="' +value.folderid+ '">&nbsp' + value.foldername + '</a>');
        	});
        	$(d.fileList).each(function (i, value) {
        		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item select-child select-file glyphicon glyphicon-file a-list" onclick="getMdFile(this)" value="file" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
        	});
//        	alert(d.result);
        }
	});
}
/**
 * get mdFIle and show file content in editermd'textarea
 * the param obj.name is mdFIle's id
 * add file node to routeStacks
 * @param obj
 */
function getMdFile(obj){
	//mdEditer.clear();	//清空操作
	mdEditor.cm.setValue("");//源码中参数为cm,设置cm的值可以达到清空效果
	var fileId = obj.name;
//	alert(fileId);
	currentFileId = fileId;//当前的文件id
	$.ajax({
		url:'/jnote/ajax/getMdFile.action',
        type:'post',
        data:{
        	mdFileId:fileId
        },
        dataType:'json',
        success:function (data) {
        	var newNode = new routeNode(fileId,"file",folderOrFileName);
        	if(routeStacks[routeStacks.length-1].id != newNode.id) {
        		routeStacks.push(newNode);
        	}
        	//路径导航的刷新
        	$('#route').empty();
        	for(var i = 0;i<routeStacks.length-1;i++){
				$('#route').append('<li><a href="javascript:return false;" onclick="routeBack(this)" name="' + routeStacks[i].id + '">' + routeStacks[i].name + '</a></li>');
			}
        	$('#route').append('<li>' + routeStacks[routeStacks.length-1].name + '</li>');
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
// show the MdFileHTML content in tab3's right tabpanel
function getContent(obj){
	var fileId = obj.name;
	$.ajax({
		url:'/jnote/ajax/getMdFileHtml.action',
        type:'post',
        data:{
        	mdFileId:fileId
        },
        dataType:'json',
        success:function (data) {
        	var d = eval("("+data+")");
        	var file = d.file;
        	// 回传的list中对象为 String
        	$("#lib2-title").empty();
        	$("#editormd-View").empty();
        	$("#lib2-title").append(file.title);
    		$("#editormd-View").append(file.content);
        }
	});
}
