$(document).ready(function () {
	//通过审核
	$(".passBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var messageIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  messageIds += objArray[i].value + ",";
		  }
		}
		if (messageIds == "") {
		  alert("还没有选中任何留言");
		  return;
		}
		$.ajax({
			type:"post",
			url:"/admin/passMsg",
			async:false,
			data:{
				messageIds:messageIds
			},success:function(data){
				if(data.code == "200"){
					alert("审核通过" + data.setTotal + "篇留言");
					location.href = data.url;
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
	//不通过审核
	$(".unPassBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var messageIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  messageIds += objArray[i].value + ",";
		  }
		}
		if (messageIds == "") {
		  alert("还没有选中任何留言");
		  return;
		}
		$.ajax({
			type:"post",
			url:"/admin/unPassMsg",
			async:false,
			data:{
				messageIds:messageIds
			},success:function(data){
				if(data.code == "200"){
					alert("审核不通过" + data.setTotal + "篇留言");
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
	$(".delBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var messageIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  messageIds += objArray[i].value + ",";
		  }
		}
		if (messageIds == "") {
		  alert("还没有选中任何留言");
		  return;
		}
		if (confirm("此操作不可恢复，确定要删除吗？")) {
			$.ajax({
				type:"post",
				url:"/admin/deleteMsg",
				async:false,
				data:{
					messageIds:messageIds
				},success:function(data){
					if(data.code == "200"){
						alert("成功删除" + data.setTotal + "篇留言");
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
	
	//未审核留言上一页
	$("#unCheckMessagePre").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum < 1) {
			alert("查询页数不能小于1");
			return;
		}
		pageNum = parseInt(pageNum) - 1;
		location.href = "/admin/unCheckMessages/" + pageNum;
	});
	
	//未审核留言下一页
	$("#unCheckMessageNext").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum > pageCount) {
			alert("查询页数不能大于总页数");
			return;
		}
		pageNum = parseInt(pageNum) + 1;
		location.href = "/admin/unCheckMessages/" + pageNum;
	});
	
	//审核未通过留言上一页
	$("#unPassMessagePre").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum < 1) {
			alert("查询页数不能小于1");
			return;
		}
		pageNum = parseInt(pageNum) - 1;
		location.href = "/admin/unPassMessages/" + pageNum;
	});
	
	//审核未通过留言下一页
	$("#unPassMessageNext").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum > pageCount) {
			alert("查询页数不能大于总页数");
			return;
		}
		pageNum = parseInt(pageNum) + 1;
		location.href = "/admin/unPassMessages/" + pageNum;
	});
	
	//审核通过留言上一页
	$("#passMessagePre").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum < 1) {
			alert("查询页数不能小于1");
			return;
		}
		pageNum = parseInt(pageNum) - 1;
		location.href = "/admin/passMessages/" + pageNum;
	});
	
	//审核通过留言下一页
	$("#passMessageNext").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum > pageCount) {
			alert("查询页数不能大于总页数");
			return;
		}
		pageNum = parseInt(pageNum) + 1;
		location.href = "/admin/passMessages/" + pageNum;
	});
	
	//跳转
	$(".messagesType li").click(function(){
		var key = this.value;
		if (key == 1) {
			location.href = "/admin/passMessages/1";
		}
		if (key == 2) {
			location.href = "/admin/unPassMessages/1";
		}
		if (key == 3) {
			location.href = "/admin/unCheckMessages/1";
		}
	});
	
	//复选框
    jQuery('.ckbox input').click(function(){
      var t = jQuery(this);
      if(t.is(':checked')){
        t.closest('tr').addClass('selected');
      } else {
        t.closest('tr').removeClass('selected');
      }
    });
    
});

//文章详情
function readMessage(messageId) {
	location.href="/admin/getMessage/" + messageId;
}