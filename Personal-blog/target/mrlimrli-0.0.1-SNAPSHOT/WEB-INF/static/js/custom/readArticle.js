$(document).ready(function () {
	
	//获取评论id并置为全局变量
	var commentId;
	$("#replyBt").click(function(){
		commentId = this.value
	});
	
	//回复评论
	$("#saveReplyBt").click(function(){
		var replyComment = $("#replyComment").val();
		if(replyComment == "" || replyComment == undefined) {
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/replyComment",
			async:false,
			data:{
				commentId:commentId,
				replyComment:replyComment
			},success:function(data){
				if(data.code == "200"){
					window.location.reload(); 
				}else{
					alert(data.msg);
				}
			},error:function(){
				alert("失败");
			}
		});
	});
	
});