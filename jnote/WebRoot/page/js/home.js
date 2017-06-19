var currentFolderId;//当前目录id
var currentFileId;//当前文件

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
	            		$("#rootFolderList-div").append('<a href="javascript:return false;" class="list-group-item glyphicon glyphicon-bookmark a-list" onclick="getChild(this)" name="' +value.folderid+ '">&nbsp' + value.foldername + '</a>');
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
	            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" name="' + value.folderid + '">&nbsp' + value.foldername + '</a>');
	            	});
	            	$(d.fileList).each(function (i, value) {
	            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item glyphicon glyphicon-file a-list" onclick="getMdFile(this)" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
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
	            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" name="' + value.folderid + '">&nbsp' + value.foldername + '</a>');
	            	});
	            	$(d.fileList).each(function (i, value) {
	            		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item glyphicon glyphicon-file a-list" onclick="getMdFile(this)" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
	            	});
	            }
		});
	});

	$("#saveFilebtn").click(function(){
		mdEditor.setCursor({line:1, ch:1});
		mdEditor.insertValue("#qwedascsa");
		alert(mdEditor.getMarkdown());    // 获取 Markdown
		alert(mdEditor.getHTML());
//		alert($("input[name=mdTitle]").val());
//		$("input[name=mdTitle]").val("asdasdsad");
	});
	
});

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
        		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item glyphicon glyphicon-folder-close a-list" onclick="getChild(this)" name="' +value.folderid+ '">&nbsp' + value.foldername + '</a>');
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
        		$("#childList-div").append('<a href="javascript:return false;" class="list-group-item glyphicon glyphicon-file a-list" onclick="getMdFile(this)" name="' +value.mdfileid+ '">&nbsp' + value.filename + '</a>');
        	});
//        	alert(d.result);
        }
	});
}
function getMdFile(obj){
	var fileId = obj.name;
	alert(fileId);
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
        	alert(file.content);
    		mdEditor.insertValue(file.content);//设置文本内容
//    		alert(d.result);//
        }
	});
}