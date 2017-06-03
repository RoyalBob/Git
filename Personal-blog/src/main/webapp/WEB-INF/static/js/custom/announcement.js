$(document).ready(function () {
	
	//保存
	$("#save").click(function(){
		var content = CKEDITOR.instances.textAreaCK.getData();
		if (content == "" || content == null) {
			alert("内容不能为空");
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/saveAnnouncement",
			async:false,
			data:{
				content:content
			},success:function(data){
				if(data.code == "200"){
					alert(data.msg);
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//修改
	$("#updateAnnouncementBt").click(function(){
		var id = $('#announcementId').val();
		var content = CKEDITOR.instances.textAreaCK.getData();
		if (content == "" || content == null) {
			alert("内容不能为空");
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/updateAnnouncement",
			async:false,
			data:{
				id:id,
				content:content
			},success:function(data){
				if(data.code == "200"){
					alert(data.msg);
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//删除
	$("#delAnnouncementBt").click(function(){
		var id = $('#announcementId').val();
		if (confirm("此操作不可恢复，确定要删除吗？")) {
			$.ajax({
				type:"post",
				url:"/admin/delAnnouncement",
				async:false,
				data:{
					id:id
				},success:function(data){
					if(data.code == "200"){
						alert(data.msg);
						location.href = data.url;
					}else{
						alert(data.msg);
					}
				},error:function(){
					alert("失败");
				}
			});
		}
	});
	
	//增加公告跳转
	$("#addAnnouncementBt").click(function(){
		location.href = "/admin/addAnnouncement";
	});
	
	//修改公告跳转
	$("#editAnnouncementBt").click(function(){
		var announcementId = $('#announcementId').val();
		location.href = "/admin/editAnnouncement/" + announcementId;
	});
	
});