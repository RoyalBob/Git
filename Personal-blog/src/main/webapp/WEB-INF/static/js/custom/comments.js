$(document).ready(function () {
	//通过审核
	$(".passBt").click(function(){
		var objArray = document.getElementsByName("checkbox");
		var commentIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  commentIds += objArray[i].value + ",";
		  }
		}
		if (commentIds == "") {
		  alert("还没有选中任何评论");
		  return;
		}
		$.ajax({
			type:"post",
			url:"/admin/pass",
			async:false,
			data:{
				commentIds:commentIds
			},success:function(data){
				if(data.code == "200"){
					alert("审核通过" + data.setTotal + "篇评论");
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
		var commentIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  commentIds += objArray[i].value + ",";
		  }
		}
		if (commentIds == "") {
		  alert("还没有选中任何评论");
		  return;
		}
		$.ajax({
			type:"post",
			url:"/admin/unPass",
			async:false,
			data:{
				commentIds:commentIds
			},success:function(data){
				if(data.code == "200"){
					alert("审核不通过" + data.setTotal + "篇评论");
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
		var commentIds="";
		for (i=0 ; i<objArray.length ; i++) {
		  if (objArray[i].checked == true) {
			  commentIds += objArray[i].value + ",";
		  }
		}
		if (commentIds == "") {
		  alert("还没有选中任何评论");
		  return;
		}
		if (confirm("此操作不可恢复，确定要删除吗？")) {
			$.ajax({
				type:"post",
				url:"/admin/delete",
				async:false,
				data:{
					commentIds:commentIds
				},success:function(data){
					if(data.code == "200"){
						alert("成功删除" + data.setTotal + "篇评论");
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
	
	//审核跳转
	$(".commentsType li").click(function(){
		var key = this.value;
		if (key == 1) {
			location.href = "/admin/passComments/1";
		}
		if (key == 2) {
			location.href = "/admin/unPassComments/1";
		}
		if (key == 3) {
			location.href = "/admin/unCheckComments/1";
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
    
    //审核未通过评论上一页
	$("#unPassCommentPre").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum < 1) {
			alert("查询页数不能小于1");
			return;
		}
		pageNum = parseInt(pageNum) - 1;
		location.href = "/admin/unPassComments/" + pageNum;
	});
	
	//审核未通过评论下一页
	$("#unPassCommentNext").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum > pageCount) {
			alert("查询页数不能大于总页数");
			return;
		}
		pageNum = parseInt(pageNum) + 1;
		location.href = "/admin/unPassComments/" + pageNum;
	});
	
	//未审核评论上一页
	$("#unCheckCommentPre").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum < 1) {
			alert("查询页数不能小于1");
			return;
		}
		pageNum = parseInt(pageNum) - 1;
		location.href = "/admin/unCheckComments/" + pageNum;
	});
	
	//未审核评论下一页
	$("#unCheckCommentNext").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum > pageCount) {
			alert("查询页数不能大于总页数");
			return;
		}
		pageNum = parseInt(pageNum) + 1;
		location.href = "/admin/unCheckComments/" + pageNum;
	});
	
	//审核通过评论上一页
	$("#passCommentPre").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum < 1) {
			alert("查询页数不能小于1");
			return;
		}
		pageNum = parseInt(pageNum) - 1;
		location.href = "/admin/passComments/" + pageNum;
	});
	
	//审核通过评论下一页
	$("#passCommentNext").click(function(){
		var pageNum = $('#pageNum').val();
		var pageCount = $('#pageCount').val();
		if (pageNum > pageCount) {
			alert("查询页数不能大于总页数");
			return;
		}
		pageNum = parseInt(pageNum) + 1;
		location.href = "/admin/passComments/" + pageNum;
	});
    
});

//文章详情
function readArticle(articleId) {
	location.href="/admin/getArticle/" + articleId;
}