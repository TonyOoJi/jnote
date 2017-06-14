$(document).ready(function(){
	
	$("#addNewFolderButton").click(function(){
//		alert($("#newRootFolderName").val());
		var folderName = $("#newRootFolderName").serialize();
//		alert(folderName);
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
	            	alert(d.result);
	            }
		});
	});
	
	
});