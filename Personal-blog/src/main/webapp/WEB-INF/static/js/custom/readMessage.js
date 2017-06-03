$(document).ready(function () {
	
	//获取评论id并置为全局变量
	var messageId;
	$("#replyBt").click(function(){
		messageId = this.value
	});
	
	//回复评论
	$("#saveReplyBt").click(function(){
		var replyMessage = $("#replyMessage").val();
		if(replyMessage == "" || replyMessage == undefined) {
			return;
		}
		$.ajax({
			type:"post",
			url:"/admin/replyMessage",
			async:false,
			data:{
				messageId:messageId,
				replyMessage:replyMessage
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