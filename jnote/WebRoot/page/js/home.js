$(document).ready(function(){
	//滚动监听
//	$('rootFolderList-div').scrollspy({ target: '.navbar-example' });//启用滚动触发事件
	
	/**
	 * 添加根目录节点
	 */
	$("#addNewFolderButton").click(function(){//ajax请求信息在homeaction中写为html的String
//		alert($("#newRootFolderName").val());
		var folderName = $("#newRootFolderName").serialize();
		$.ajax({
	            url:'/jnote/ajax/addRootFolder.action',  
	            type:'post',
	            data:{
	            	foldername:$("input[name=newRootFolderName]").val()
//	            	foldername:folderName
	            },
	            dataType:'json',
	            success:function (data) {
	                /*$.each(data.userList, function(i, value){ 
	                 $("#allUser").append("<div>输出了：id:"+value.id+", name: "+value.name+"</div>"); 
	                 });*/
	                /*$(data.userList).each(function (i, value) {  
	                    $("#allUser").append("<div>输出了：id:" + value.id + ", name: " + value.name + "</div>");*/
	            	var d = eval("("+data+")");
	            	$("#rootFolderList-div").empty();
	            	//回传的list中对象为 String
	            	$(d.list).each(function (i, value) {
	            		$("#rootFolderList-div").append('<a href="#" class="list-group-item glyphicon glyphicon-folder-close a-list" name=" ' +value.folderid+ ' ">&nbsp' + value.foldername + '</a>');
	            	});
	            	alert(d.result);
	            	//$('#addNewFolderModal').modal('hide');
	            }
		});
	});
	

	
});

function getChild(obj){
	var folderid = obj.name;
	alert(folderid);
}